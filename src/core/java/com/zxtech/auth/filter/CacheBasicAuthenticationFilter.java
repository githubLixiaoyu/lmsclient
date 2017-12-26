package com.zxtech.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.codec.Base64;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.Assert;

import com.zxtech.auth.CustomUserDetails;
import com.zxtech.support.cache.Cache;
import com.zxtech.support.cache.ehcache.EhCacheImpl;

import net.sf.ehcache.CacheManager;

public class CacheBasicAuthenticationFilter extends BasicAuthenticationFilter {
	// ~ Instance fields
	// ================================================================================================

	private AuthenticationDetailsSource authenticationDetailsSource = new WebAuthenticationDetailsSource();
	private AuthenticationEntryPoint authenticationEntryPoint;
	private AuthenticationManager authenticationManager;
	private RememberMeServices rememberMeServices = new NullRememberMeServices();
	private boolean ignoreFailure = false;
	private String credentialsCharset = "UTF-8";

	// ~ Methods
	// ========================================================================================================

	@Override
	public void afterPropertiesSet() {
		Assert.notNull(this.authenticationManager, "An AuthenticationManager is required");

		if (!isIgnoreFailure()) {
			Assert.notNull(this.authenticationEntryPoint, "An AuthenticationEntryPoint is required");
		}
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		final boolean debug = logger.isDebugEnabled();
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;

		String header = request.getHeader("Authorization");

		if ((header != null) && header.startsWith("Basic ")) {
//			byte[] base64Token = header.substring(6).getBytes("UTF-8");
//			String token = new String(Base64.decode(base64Token), getCredentialsCharset(request));
			String token = header.substring(6);
			
			String username = "";
			String password = "";

			// 缓存有，利用token访问的情况
			username = token;
			// 获取存在的cache
			Cache cache = new EhCacheImpl(username);
			// 获得cache中的内容
			if (cache.isKeyInCache(username)) {
				CustomUserDetails userDetails = (CustomUserDetails) cache.get(username);
				password = userDetails.getPassword();
			} else {
				byte[] base64Token = header.substring(6).getBytes("UTF-8");
				token = new String(Base64.decode(base64Token), getCredentialsCharset(request));
				
				int delim = token.indexOf(":");
				int timeDelim = token.indexOf("=");
				
				if (delim != -1) {
					username = token.substring(0, delim);
					
					if(timeDelim != -1){
						//token在缓存中没有，在缓存中已过期，直接decode token获取密码
						password = token.substring(delim+1,timeDelim);
					} else {
						// 通过header传递用户名和密码的情况
						password = token.substring(delim + 1);
					}

				}
			}

			if (debug) {
				logger.debug("Basic Authentication Authorization header found for user '" + username + "'");
			}

			if (authenticationIsRequired(username)) {
				UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,
						password);
				authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

				Authentication authResult;

				try {
					authResult = authenticationManager.authenticate(authRequest);
				} catch (AuthenticationException failed) {
					// Authentication failed
					if (debug) {
						logger.debug("Authentication request for user: " + username + " failed: " + failed.toString());
					}

					SecurityContextHolder.getContext().setAuthentication(null);

					rememberMeServices.loginFail(request, response);

					onUnsuccessfulAuthentication(request, response, failed);

					if (ignoreFailure) {
						chain.doFilter(request, response);
					} else {
						authenticationEntryPoint.commence(request, response, failed);
					}

					return;
				}

				// Authentication success
				if (debug) {
					logger.debug("Authentication success: " + authResult.toString());
				}

				SecurityContextHolder.getContext().setAuthentication(authResult);

				rememberMeServices.loginSuccess(request, response, authResult);

				onSuccessfulAuthentication(request, response, authResult);
			}
		}

		chain.doFilter(request, response);
	}

	private boolean authenticationIsRequired(String username) {
		// Only reauthenticate if username doesn't match SecurityContextHolder
		// and user isn't authenticated
		// (see SEC-53)
		Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();

		if (existingAuth == null || !existingAuth.isAuthenticated()) {
			return true;
		}

		// Limit username comparison to providers which use usernames (ie
		// UsernamePasswordAuthenticationToken)
		// (see SEC-348)

		if (existingAuth instanceof UsernamePasswordAuthenticationToken && !existingAuth.getName().equals(username)) {
			return true;
		}

		// Handle unusual condition where an AnonymousAuthenticationToken is
		// already present
		// This shouldn't happen very often, as BasicProcessingFitler is meant
		// to be earlier in the filter
		// chain than AnonymousAuthenticationFilter. Nevertheless, presence of
		// both an AnonymousAuthenticationToken
		// together with a BASIC authentication request header should indicate
		// reauthentication using the
		// BASIC protocol is desirable. This behaviour is also consistent with
		// that provided by form and digest,
		// both of which force re-authentication if the respective header is
		// detected (and in doing so replace
		// any existing AnonymousAuthenticationToken). See SEC-610.
		if (existingAuth instanceof AnonymousAuthenticationToken) {
			return true;
		}

		return false;
	}

	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException {
	}

	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException {
	}

	protected AuthenticationEntryPoint getAuthenticationEntryPoint() {
		return authenticationEntryPoint;
	}

	public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

	protected AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	protected boolean isIgnoreFailure() {
		return ignoreFailure;
	}

	public void setIgnoreFailure(boolean ignoreFailure) {
		this.ignoreFailure = ignoreFailure;
	}

	public void setAuthenticationDetailsSource(AuthenticationDetailsSource authenticationDetailsSource) {
		Assert.notNull(authenticationDetailsSource, "AuthenticationDetailsSource required");
		this.authenticationDetailsSource = authenticationDetailsSource;
	}

	public void setRememberMeServices(RememberMeServices rememberMeServices) {
		Assert.notNull(rememberMeServices, "rememberMeServices cannot be null");
		this.rememberMeServices = rememberMeServices;
	}

	public void setCredentialsCharset(String credentialsCharset) {
		Assert.hasText(credentialsCharset, "credentialsCharset cannot be null or empty");
		this.credentialsCharset = credentialsCharset;
	}

	protected String getCredentialsCharset(HttpServletRequest httpRequest) {
		return credentialsCharset;
	}
}
