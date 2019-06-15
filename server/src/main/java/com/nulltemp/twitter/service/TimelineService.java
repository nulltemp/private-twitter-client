package com.nulltemp.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Service
public class TimelineService {
	@Autowired
	private Twitter twitter;

	public ResponseList<Status> getTimeline() throws TwitterException {
		return twitter.getHomeTimeline();
	}
}
