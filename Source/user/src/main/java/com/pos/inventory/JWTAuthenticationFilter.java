package com.pos.inventory;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JWTAuthenticationFilter extends GenericFilterBean {

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	static final String TOKEN_PREFIX = "Bearer";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		try{

			Authentication authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) request);

			SecurityContextHolder.getContext().setAuthentication(authentication);
			filterChain.doFilter(request, response);
		}catch (ExpiredJwtException e){
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

}
