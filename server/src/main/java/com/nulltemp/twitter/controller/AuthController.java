package com.nulltemp.twitter.controller;

import com.nulltemp.twitter.request.LoginRequest;
import com.nulltemp.twitter.response.LoginResponse;
import com.nulltemp.twitter.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private SystemUserService systemUserService;

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request) {
		return new LoginResponse(systemUserService.createToken(request.getEmail(), request.getPassword()));
	}

	@PostMapping("/logout")
	public void logout() {
	}
}
