package com.pos.identity.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.pos.identity.domain.User;
import com.pos.identity.dto.ClientDto;
import com.pos.identity.service.JwtUserDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenUtil implements Serializable {

	@Autowired
	private Environment env;
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 3600;
	public static final long REFRESH_TOKEN_VALIDITY = 604800;

	@SuppressWarnings("unused")
	private String secret;

	private String getSecret() {
		return this.secret = env.getProperty("jwt.secret");
	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public boolean getIsRefresh(String token) {
		final Claims claims = getAllClaimsFromToken(token);
		return (boolean) claims.get("refresh", Object.class);
//        return true;
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(getSecret()).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private Boolean ignoreTokenExpiration(String token) {
		// here you specify tokens, for that the expiration is ignored
		return false;
	}

//    public String generateToken(UserDetails userDetails) {
//
//        ClientDto clientDto = jwtUserDetailsService.getClientCode(userDetails.getUsername());
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("client_code", clientDto.getClientCode());
//        return doGenerateToken(claims, userDetails.getUsername(), "access");
//    }

	public String generateToken(String userName, User user) {

		ClientDto clientDto = jwtUserDetailsService.getClientCode(userName);

		Map<String, Object> claims = new HashMap<>();
		claims.put("client_code", clientDto.getClientCode());
		claims.put("user_id", user.getId());
		claims.put("is_tfa_enabled", user.getIsTfaEnabled());
		claims.put("tfaDefaultType", user.getTfaDefaultType());
		return doGenerateToken(claims, userName, "access");
	}

	public String generateRefreshToken(UserDetails userDetails, User user) {

		ClientDto clientDto = jwtUserDetailsService.getClientCode(userDetails.getUsername());

		Map<String, Object> claims = new HashMap<>();
		claims.put("client_code", clientDto.getClientCode());
		claims.put("refresh", true);
		claims.put("user_id", user.getId());
		claims.put("is_tfa_enabled", user.getIsTfaEnabled());
		claims.put("tfaDefaultType", user.getTfaDefaultType());
		return doGenerateToken(claims, userDetails.getUsername(), "refresh");
	}

	private String doGenerateToken(Map<String, Object> claims, String subject, String tokenType) {

		log.info("JwtTokenUtil.doGenerateToken() invoked." + env.getProperty("jwt.secret"));

//        return Jwts.builder().setAudience()

		Date tokenExp = null;

		if (tokenType.equals("access")) {
			tokenExp = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000);
		} else if (tokenType.equals("refresh")) {
			tokenExp = new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY * 1000);
		}

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(tokenExp).signWith(SignatureAlgorithm.HS512, getSecret()).compact();
	}

	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
