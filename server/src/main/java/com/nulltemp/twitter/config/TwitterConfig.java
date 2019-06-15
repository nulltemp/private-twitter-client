package com.nulltemp.twitter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@Configuration
public class TwitterConfig {
	@Bean
	public Twitter twitter() {
		return TwitterFactory.getSingleton();
	}
}
