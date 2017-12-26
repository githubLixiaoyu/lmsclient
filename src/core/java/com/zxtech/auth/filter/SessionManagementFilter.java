package com.zxtech.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.web.context.SecurityContextRepository;

public class SessionManagementFilter extends org.springframework.security.web.session.SessionManagementFilter {

	public SessionManagementFilter(SecurityContextRepository securityContextRepository) {
		super(securityContextRepository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		super.doFilter(req, res, chain);
	}
	
	

}
