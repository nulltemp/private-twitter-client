package com.nulltemp.twitter.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nulltemp.twitter.config.filter.TokenFilter;
import com.nulltemp.twitter.config.handler.AuthenticationFailureTokenHander;
import com.nulltemp.twitter.config.handler.AuthenticationSuccessTokenHandler;
import com.nulltemp.twitter.setting.TokenSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private TokenSetting tokenSetting;
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth,
								@Qualifier("systemUserService") UserDetailsService userDetailsService,
								PasswordEncoder passwordEncoder) throws Exception {
		auth.eraseCredentials(true)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		Algorithm algorithm = Algorithm.HMAC256(tokenSetting.getSecretKey());
		http
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint())
				.and()
				.formLogin()
				.loginProcessingUrl("/api/auth/login").permitAll().usernameParameter("email").passwordParameter("password")
				.successHandler(new AuthenticationSuccessTokenHandler(algorithm, tokenSetting, objectMapper))
				.failureHandler(new AuthenticationFailureTokenHander())
				.and()
				.logout()
				.logoutUrl("/api/auth/logout")
				.and()
				.cors()
				.configurationSource(corsConfigurationSource())
				.and()
				.csrf()
				.disable()
				.addFilterBefore(new TokenFilter(algorithm), UsernamePasswordAuthenticationFilter.class)
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	private CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
		corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
		corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
		UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
		corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return corsConfigurationSource;
	}

	private AuthenticationEntryPoint authenticationEntryPoint() {
		return (request, response, authException) -> {
			if (response.isCommitted()) {
				System.out.println("Response has already been committed.");
				return;
			}
			response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
		};
	}
}
