package com.nulltemp.twitter.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class TokenFilter extends GenericFilterBean {
	private final Algorithm algorithm;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String token = resolveToken(request);
		if (token == null) {
			chain.doFilter(request, response);
			return;
		}

		try {
			DecodedJWT jwt = verifyToken(token);
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(jwt.getAudience(), null, null));
		} catch (JWTVerificationException e) {
			e.printStackTrace();
			SecurityContextHolder.clearContext();
			((HttpServletResponse) response).sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
		}
		chain.doFilter(request, response);
	}

	private String resolveToken(ServletRequest request) {
		String token = ((HttpServletRequest) request).getHeader("Authorization");
		if (token == null || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7);
	}

	private DecodedJWT verifyToken(String token) {
		JWTVerifier verifier = JWT.require(algorithm).build();
		return verifier.verify(token);
	}
}
