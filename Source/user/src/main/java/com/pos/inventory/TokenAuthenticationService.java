/**
 ***************************************************************
 * Copyright(C) 2017 AUXENTA INC.
 * All rights reserved.
 * <p>
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF AUXENTA INC.
 * <p>
 * This copy of the Source Code is intended for AUXENTA INC's
 * internal use only and is intended for view by persons duly
 * authorized by the management of AUXENTA INC.
 * No part of this file may be reproduced or distributed in any form or by any
 * means without the written approval of the Management of AUXENTA INC.
 * <p>
 ***************************************************************
 */
package com.pos.inventory;

import java.util.Collection;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.pos.inventory.common.dto.ResponseDto;

import io.jsonwebtoken.Jwts;

/**
 * This class going to remove .. Once implement the User service
 */
public class TokenAuthenticationService {
	static final long EXPIRATIONTIME = 900_0000; // 150 Minutes
	static final String SECRET = "$SqQE=YFFGFHA&RF3__=rzT2*H98M#e!j&ef=v%x?@@_$%_ts%";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();

			return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
		}
		return null;
	}

	/**
	 * @return
	 */
	private static Collection<? extends GrantedAuthority> emptyList() {
		return Collections.emptyList();
	}

	public static ResponseDto checkUserAuthentication(String username) {
		ResponseDto responseDto = new ResponseDto();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		Authentication authentication = TokenAuthenticationService.getAuthentication(request);
		if (!username.equals(authentication.getPrincipal())) {

			responseDto.setStatus(false);
			responseDto.setErrorCode(403);
			responseDto.setErrorDescription("Unauthorized access. Please use the proper credential.");
			return responseDto;
		} else {
			responseDto.setStatus(true);
			responseDto.setErrorCode(200);
			responseDto.setErrorDescription(null);
			return responseDto;
		}
	}
}
