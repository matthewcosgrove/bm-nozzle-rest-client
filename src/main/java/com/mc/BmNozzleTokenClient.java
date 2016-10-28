package com.mc;

import feign.Headers;
import feign.RequestLine;

public interface BmNozzleTokenClient {

	@RequestLine("GET /token")
	@Headers({
		   "Accept: application/json;charset=utf-8"
		 }) 
	public String getToken();
}
