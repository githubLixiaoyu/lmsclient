package com.zxtech.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.context.SecurityContextRepository;

import com.zxtech.auth.CustomUserDetails;
import com.zxtech.support.cache.Cache;
import com.zxtech.support.cache.ehcache.EhCacheImpl;

public class CacheSecurityContextPersistenceFilter extends
		SecurityContextPersistenceFilter {


    static final String FILTER_APPLIED = "__spring_security_scpf_applied";

    private SecurityContextRepository repo = new HttpSessionSecurityContextRepository();

    private boolean forceEagerSessionCreation = false;


    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (request.getAttribute(FILTER_APPLIED) != null) {
            // ensure that filter is only applied once per request
            chain.doFilter(request, response);
            return;
        }

        final boolean debug = logger.isDebugEnabled();

        request.setAttribute(FILTER_APPLIED, Boolean.TRUE);

        if (forceEagerSessionCreation) {
            HttpSession session = request.getSession();

            if (debug && session.isNew()) {
                logger.debug("Eagerly created session: " + session.getId());
            }
        }

        HttpRequestResponseHolder holder = new HttpRequestResponseHolder(request, response);
        SecurityContext contextBeforeChainExecution = repo.loadContext(holder);

        try {
            SecurityContextHolder.setContext(contextBeforeChainExecution);

            chain.doFilter(holder.getRequest(), holder.getResponse());

        } finally {
            SecurityContext contextAfterChainExecution = SecurityContextHolder.getContext();
            // Crucial removal of SecurityContextHolder contents - do this before anything else.
            if(contextAfterChainExecution != null){
            	Authentication authentication = contextAfterChainExecution.getAuthentication();
            	if(authentication != null){
            		Object principal = authentication.getPrincipal();
            		if(principal !=null && principal instanceof CustomUserDetails){
	            		//获得当前用户信息
	            		CustomUserDetails userDetails = (CustomUserDetails)principal;
	//            		//----------------------现在不手动修改过期时间，利用cache本身的timeToIdleSeconds进行维护
	//            		//-------------------------
	            		String token = userDetails.getToken();
	//            		//创建或获得缓存
	            		Cache cache = new EhCacheImpl(token);
	//            		//向缓存中添加数据，或更新让其自动更新过期时间
	            		cache.put(token, userDetails);
            		}
            		
            	}
            }
            SecurityContextHolder.clearContext();
            repo.saveContext(contextAfterChainExecution, holder.getRequest(), holder.getResponse());
            request.removeAttribute(FILTER_APPLIED);

            if (debug) {
                logger.debug("SecurityContextHolder now cleared, as request processing completed");
            }
        }
    }

    public void setSecurityContextRepository(SecurityContextRepository repo) {
        this.repo = repo;
    }

    public void setForceEagerSessionCreation(boolean forceEagerSessionCreation) {
        this.forceEagerSessionCreation = forceEagerSessionCreation;
    }

}
