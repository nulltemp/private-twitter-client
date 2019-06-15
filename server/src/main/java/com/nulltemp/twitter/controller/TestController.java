package com.nulltemp.twitter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

@RestController
@RequestMapping("/api")
public class TestController {
	@GetMapping("/user")
	public User user() throws TwitterException {
		Twitter twitter = TwitterFactory.getSingleton();
		twitter.getHomeTimeline();
		return twitter.verifyCredentials();
	}
}
