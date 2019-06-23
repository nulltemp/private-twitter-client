package com.nulltemp.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.UserList;

@Service
public class UserListService {
	@Autowired
	private Twitter twitter;

	public ResponseList<UserList> getAllUserList() throws IllegalStateException, TwitterException {
		return twitter.getUserLists(twitter.getId());
	}
}
