package com.mc;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import feign.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BmNozzleRestClientApplicationTests {
	
	@Autowired
	BmNozzleTokenClient tokenClient;

	@Test
	public void contextLoads() {
		assertNotNull(tokenClient);
	}
	
	@Test
	public void canGetToken() throws Exception {
		String token = tokenClient.getToken();
		assertThat(token, not(isEmptyOrNullString()));
	}

}
