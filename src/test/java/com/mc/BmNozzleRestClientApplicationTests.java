package com.mc;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BmNozzleRestClientApplicationTests {
	
	@Autowired
	BmNozzleTokenClient tokenClient;

	@Autowired
	BmNozzleRestAPIClient apiClient;

	@Test
	public void contextLoads() {
		assertNotNull(tokenClient);
		assertNotNull(apiClient);
	}
	
	@Test
	public void canGetToken() throws Exception {
		String token = tokenClient.getToken();
		assertThat(token, not(isEmptyOrNullString()));
	}
	
	@Test
	public void canCallRepsEndpoint() throws Exception {
		String reps = apiClient.getReps();
		System.out.println(reps);
		assertThat(reps, not(isEmptyOrNullString()));
	}

}
