package com.pos.identity.controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pos.identity.config.JwtTokenUtil;
import com.pos.identity.domain.User;
import com.pos.identity.dto.ResponseDto;
import com.pos.identity.model.JwtRequest;
import com.pos.identity.model.JwtResponse;
import com.pos.identity.service.JwtUserDetailsService;
import com.pos.identity.service.UserLoginDetailsService;
import com.pos.identity.service.client.PosServiceFeignClient;
import com.pos.utils.HttpReqRespUtils;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("v1/auth")
public class JwtAuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	JwtUserDetailsService userDetailsService;

	@Autowired
	UserLoginDetailsService userLoginDetailsService;

	@Autowired
	PosServiceFeignClient posServiceFeignClient;

	@Autowired
	Gson gson;

	public static final String invalidCredentials = "INVALID_CREDENTIALS";

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		log.info(authenticationRequest.getGrantType());
		
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
	
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	
		User user = userDetailsService.getUserByUserName(authenticationRequest.getUsername());

		log.info("userDetails -- " + userDetails);

		ResponseDto responseDto = null;
		ResponseEntity<Object> response = posServiceFeignClient
				.checkUserIsValidWithAgentCode(authenticationRequest.getUsername());
		if (response != null) {
			responseDto = gson.fromJson(gson.toJson(response.getBody()), ResponseDto.class);
		}
		if (responseDto.getResponseDto() == null) {
			user = null;
		}

		if (user != null) {
			final String token = jwtTokenUtil.generateToken(userDetails.getUsername(), user);
			final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails, user);

			userLoginDetailsService.updateLastRecord(userDetails.getUsername(), Boolean.TRUE);

			return ResponseEntity.ok(new JwtResponse(token, refreshToken));
		}

		return new ResponseEntity<String>(invalidCredentials, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationTokenFromRefreshToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

		Date exp = jwtTokenUtil.getClaimFromToken(authenticationRequest.getRefreshToken(), Claims::getExpiration);
		String username = jwtTokenUtil.getClaimFromToken(authenticationRequest.getRefreshToken(), Claims::getSubject);
		Date currentDate = new Date();

		User user = userDetailsService.getUserByUserName(username);

		if (exp.compareTo(currentDate) >= 0) {
			log.info("exp date {}", exp);
			log.info("today date {}", new Date());

			long diffInMillies = Math.abs(exp.getTime() - currentDate.getTime());
			long diffHr = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			log.info("hr difference {}", diffHr);
		} else {

		}

		if (jwtTokenUtil.getIsRefresh(authenticationRequest.getRefreshToken()) && exp.compareTo(currentDate) >= 0
				&& user != null) {
			log.info("{} user logged", username);

			final String token = jwtTokenUtil.generateToken(username, user);

			return ResponseEntity.ok(new JwtResponse(token, null));
		}
		return new ResponseEntity<String>(invalidCredentials, HttpStatus.UNAUTHORIZED);

	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception(invalidCredentials, e);
		} finally {
			userLoginDetailsService.save(username, HttpReqRespUtils.getClientIpAddressIfServletRequestExist());
		}
	}

}
