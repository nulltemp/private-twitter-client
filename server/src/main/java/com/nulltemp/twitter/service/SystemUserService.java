package com.nulltemp.twitter.service;

import com.nulltemp.twitter.db.dto.SystemUser;
import com.nulltemp.twitter.db.mapper.SystemUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("systemUserService")
public class SystemUserService implements UserDetailsService {
	@Autowired
	private SystemUserMapper systemUserMapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		SystemUser systemUser = systemUserMapper.findByEmail(email);
		if (systemUser == null) {
			throw new UsernameNotFoundException("user not found.");
		}
		return systemUser.getSpringUser();
	}
}
