package com.mc;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bm")
public class BmNozzleConfigurationProperties {

	/**
	 * Username from UAA as used to get token from BM nozzle
	 */
	private String username;
	
	/**
	 * Password from UAA as used to get token from BM nozzle
	 */
	private String password;
	
	private String host;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
