package com.nulltemp.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nulltemp.twitter.service.TimelineService;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

@RestController
@CrossOrigin
@RequestMapping("/api/timeline")
public class TimelineController {
	@Autowired
	private TimelineService timelineService;

	@GetMapping
	public ResponseList<Status> getTimeline() throws TwitterException {
		return timelineService.getTimeline();
	}
}
