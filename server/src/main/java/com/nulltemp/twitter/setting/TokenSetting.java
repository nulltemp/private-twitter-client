package com.nulltemp.twitter.setting;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "token")
@Data
public class TokenSetting {
	private String secretKey;
	private long expirationTimeMillis;
}
