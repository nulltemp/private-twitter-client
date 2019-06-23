package com.nulltemp.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nulltemp.twitter.service.UserListService;

import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.UserList;

@RestController
@CrossOrigin
@RequestMapping("/api/userlist")
public class UserListController {
	@Autowired
	private UserListService userListService;

	@GetMapping
	public ResponseList<UserList> getAllUserList() throws IllegalStateException, TwitterException {
		return userListService.getAllUserList();
	}
}
