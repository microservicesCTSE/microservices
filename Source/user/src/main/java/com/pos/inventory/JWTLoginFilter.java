package com.pos.inventory;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.poscore.common.dto.UserDto;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
//		UserDto creds = new ObjectMapper().readValue(req.getInputStream(), UserDto.class);

//		Authentication a = getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
//				creds.getUsername(), creds.getPassword(), Collections.emptyList()));

		/*if (("Public".equals(((List<AuthorityDto>) (a.getAuthorities())).get(0).getAuthority()))) {

			a = getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(null, creds.getPassword(), Collections.emptyList()));

		}*/
		
//		SecurityContextHolder.getContext().setAuthentication(a);

		return null;

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
//		TokenAuthenticationService.addAuthentication(response, authResult.getName());
	}

}
