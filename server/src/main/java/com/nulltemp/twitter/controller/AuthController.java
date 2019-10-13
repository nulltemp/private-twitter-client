package com.nulltemp.twitter.controller;

import com.nulltemp.twitter.request.LoginRequest;
import com.nulltemp.twitter.response.LoginResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request) {
		System.out.println(request.getEmail());
		return new LoginResponse("testtoken");
	}

	@PostMapping("/logout")
	public void logout() {
	}
}
