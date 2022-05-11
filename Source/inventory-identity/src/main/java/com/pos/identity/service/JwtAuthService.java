package com.pos.identity.service;

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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.pos.identity.config.JwtTokenUtil;
import com.pos.identity.domain.User;
import com.pos.identity.dto.ResponseDto;
import com.pos.identity.model.JwtRequest;
import com.pos.identity.model.JwtResponse;
import com.pos.identity.service.client.PosServiceFeignClient;
import com.pos.utils.HttpReqRespUtils;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtAuthService {

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

	public Object createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		log.info(authenticationRequest.getGrantType());

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		User user = userDetailsService.getUserByUserName(authenticationRequest.getUsername());
		if (user != null && authenticationRequest.getGrantType().equals("Platform Admin")) {
			log.info("Requested through Agent Portal");
			if (user.getUserTypeId().getUserTypeDesc().equals("Platform Admin")
					|| user.getUserTypeId().getUserTypeDesc().equals("Individual Admin")
//					|| user.getUserTypeId().getUserTypeDesc().equals("Sub Agent")
//					|| user.getUserTypeId().getUserTypeDesc().equals("Affiliate")
//					|| user.getUserTypeId().getUserTypeDesc().equals("Sub Agent User")
			) {
				authenticationRequest.setGrantType(user.getUserTypeId().getUserTypeDesc());
			}

		}
		if (authenticationRequest.getAgentRegNumber() != null) {
			ResponseDto responseDto = null;
			if (user.getUserTypeId().getUserTypeDesc().equals("Customer")) {

//					AgentSenderDetails agentSenderDetails = userDetailsService.checkUserIsMatchedWithAgentCode(
//							authenticationRequest.getUsername(), authenticationRequest.getAgentRegNumber());
//					ResponseEntity<Object> response =	userDetailsService.getUserDetailsFromBankingCore(authenticationRequest.getUsername(), authenticationRequest.getAgentRegNumber());
				ResponseEntity<Object> response = posServiceFeignClient.checkAgentIsValidWithAgentCode(
						authenticationRequest.getUsername(), authenticationRequest.getAgentRegNumber());
				if (response != null) {
					responseDto = gson.fromJson(gson.toJson(response.getBody()), ResponseDto.class);
				}
				if (responseDto.getResponseDto() == null) {
					user = null;
				}
			}
			if (user != null && user.getUserTypeId().getUserTypeDesc().equals("Platform Admin")) {
//					AgentDetails agentDetails = userDetailsService.checkUserIsMatched(authenticationRequest.getUsername(),
//							authenticationRequest.getAgentRegNumber());
//					ResponseEntity<Object> response =	userDetailsService.getUserDetailsFromBankingCore(authenticationRequest.getUsername(), authenticationRequest.getAgentRegNumber());
				ResponseEntity<Object> response = posServiceFeignClient.checkAgentIsValidWithAgentCode(
						authenticationRequest.getUsername(), authenticationRequest.getAgentRegNumber());
				if (response != null) {
					responseDto = gson.fromJson(gson.toJson(response.getBody()), ResponseDto.class);
				}
				if (responseDto.getResponseDto() == null) {
					user = null;
				}
			}
		}

		log.info("userDetails -- " + userDetails);

		if (user != null && authenticationRequest.getGrantType() != null
				&& authenticationRequest.getGrantType().equals(user.getUserTypeId().getUserTypeDesc())) {

			final String token = jwtTokenUtil.generateToken(userDetails.getUsername(), user);
			final String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails, user);

			log.info("token: {}", refreshToken);

			userLoginDetailsService.updateLastRecord(userDetails.getUsername(), Boolean.TRUE);

			return ResponseEntity.ok(new JwtResponse(token, refreshToken));
		}

		return new ResponseEntity<String>(invalidCredentials, HttpStatus.NOT_FOUND);

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

	public Object createAuthenticationTokenFromRefreshToken(@RequestBody JwtRequest authenticationRequest)
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
}

