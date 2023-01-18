package com.teacher.main.security.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	// 6.validate username in token and database, expdate
	public boolean validateToken(String token, String username) {
		String tokenUserName = getUsername(token);
		return (username.equals(tokenUserName) && !isTokenExpired(token));
	}

	// 5. Validate token exp:
	public boolean isTokenExpired(String token) {
		Date expDate = getExpirationDate(token);
		return expDate.before(new Date(System.currentTimeMillis()));
	}

	// 4. Read subject/username
	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	// 3.Read expiration of token:
	private Date getExpirationDate(String token) {
		return getClaims(token).getExpiration();
	}

	// 2, Read claims:
	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}

	// 1.Generate Token:
	public String generateToken(String subject) {

		return Jwts.builder().setSubject(subject).setIssuer("mPHATEK").setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}
}