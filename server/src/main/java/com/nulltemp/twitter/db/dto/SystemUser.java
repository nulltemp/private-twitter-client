package com.nulltemp.twitter.db.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SystemUser {
	private long id;
	private String email;
	private String password;
	private String token;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Timestamp deletedAt;
}
