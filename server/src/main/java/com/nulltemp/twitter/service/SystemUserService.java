package com.nulltemp.twitter.service;

import com.nulltemp.twitter.db.dto.SystemUser;
import com.nulltemp.twitter.db.mapper.SystemUserMapper;
import com.nulltemp.twitter.exception.NotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService {
	private static final int TOKEN_SIZE = 64;

	@Autowired
	private SystemUserMapper systemUserMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String createToken(String email, String password) {
		SystemUser systemUser = systemUserMapper.findByEmail(email);
		if (systemUser == null || !passwordEncoder.matches(password, systemUser.getPassword())) {
			throw new NotFoundException("user not found.");
		}
		String token = doCreateToken();
		systemUserMapper.saveToken(systemUser.getId(), token);
		return token;
	}

	private String doCreateToken() {
		return RandomStringUtils.randomAlphanumeric(TOKEN_SIZE);
	}
}
