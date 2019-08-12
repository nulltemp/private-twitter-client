package com.nulltemp.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
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

	public ResponseList<Status> getUserListStatuses(String slug) throws TwitterException {
		return twitter.getUserListStatuses(twitter.getId(), slug, new Paging(1, 10));
	}
}
