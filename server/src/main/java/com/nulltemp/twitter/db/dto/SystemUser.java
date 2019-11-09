package com.nulltemp.twitter.db.dto;

import lombok.Data;
import org.springframework.security.core.userdetails.User;

import java.sql.Timestamp;
import java.util.ArrayList;

@Data
public class SystemUser {
	private long id;
	private String email;
	private String password;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Timestamp deletedAt;

	public SpringUser getSpringUser() {
		return new SpringUser(email, password);
	}

	public static class SpringUser extends User {
		SpringUser(String username, String password) {
			super(username, password, new ArrayList<>());
		}
	}
}
