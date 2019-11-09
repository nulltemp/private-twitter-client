package com.nulltemp.twitter.config.handler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nulltemp.twitter.db.dto.SystemUser;
import com.nulltemp.twitter.response.LoginResponse;
import com.nulltemp.twitter.setting.TokenSetting;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
public class AuthenticationSuccessTokenHandler implements AuthenticationSuccessHandler {
	private final Algorithm algorithm;
	private final TokenSetting tokenSetting;
	private final ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		if (response.isCommitted()) {
			// TODO log
			System.out.println("Response has already been committed.");
			return;
		}
		try (PrintWriter writer = response.getWriter()) {
			writer.print(objectMapper.writeValueAsString(new LoginResponse(generateToken(authentication))));
		}
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.OK.value());
		clearAuthenticationAttributes(request);
	}

	private String generateToken(Authentication auth) {
		SystemUser.SpringUser springUser = (SystemUser.SpringUser) auth.getPrincipal();
		Date issuedAt = new Date();
		Date notBefore = new Date(issuedAt.getTime());
		Date expiresAt = new Date(issuedAt.getTime() + tokenSetting.getExpirationTimeMillis());
		return JWT.create()
				.withIssuedAt(issuedAt)
				.withNotBefore(notBefore)
				.withExpiresAt(expiresAt)
				.withAudience(springUser.getUsername())
				.withJWTId(UUID.randomUUID().toString())
				.sign(this.algorithm);
	}

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
