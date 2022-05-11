package com.pos.identity.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.pos.identity.PosIdentityApplicationTests;
import com.pos.identity.model.JwtRequest;
import com.pos.identity.service.JwtAuthService;
import com.pos.identity.service.JwtUserDetailsService;

@Transactional
@AutoConfigureMockMvc()
public class JwtAuthenticationControllerTest extends PosIdentityApplicationTests {

	@Autowired
	JwtAuthenticationController jwtAuthenticationController1;

	@Autowired
	JwtUserDetailsService userDetailsService;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private Gson gson;

	@MockBean
	JwtAuthService jwtAuthService;

	private JwtRequest jwtRequest = null;
	private JwtRequest errorJwtRequest = null;
	String requestTokenHeader = null;
	private JwtRequest jwtRequestRefresh = null;




    @Before
    public void setUp() throws Exception {
    	this.mvc = webAppContextSetup(webApplicationContext).build();
		jwtRequest = new JwtRequest();
		jwtRequest.setUsername("algorialdev@gmail.com");
		jwtRequest.setPassword("Test_1user");
		jwtRequest.setGrantType("Platform Admin");
		jwtRequest.setAgentRegNumber("MXAG01");

		errorJwtRequest = new JwtRequest();
		errorJwtRequest.setUsername("user@code.com");
		errorJwtRequest.setPassword("Test_1user");
		errorJwtRequest.setGrantType("Agent Admin");
		jwtRequest.setAgentRegNumber("MXAG01");

		jwtRequestRefresh = new JwtRequest();
		jwtRequestRefresh.setUsername("algorialdev@gmail.com");
		jwtRequestRefresh.setPassword("Test_1user");
		jwtRequestRefresh.setGrantType("Platform Admin");
		jwtRequestRefresh.setRefreshToken(
				"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbGdvcmlhbGRldkBnbWFpbC5jb20iLCJ1c2VyX2lkIjoxLCJyZWZyZXNoIjp0cnVlLCJleHAiOjE2NTI2NjY4NzksImlzX3RmYV9lbmFibGVkIjpudWxsLCJpYXQiOjE2NTIwNjIwNzksInRmYURlZmF1bHRUeXBlIjpudWxsLCJjbGllbnRfY29kZSI6Ik1OIn0.ZHbqyHO_40pNX4PX8FPZ8tREqke8N3XBc3o-adhn9iX-o4AlW16loBDlAqBUbZOJu2cgll1aFJxVFK9DebgvuQ");
	}

    @After
    public void tearDown() throws Exception {
    	jwtRequest = null;
		errorJwtRequest = null;
		jwtRequestRefresh = null;

    }

    @Test
	public void createAuthenticationToken() throws Exception {

		when(jwtAuthService.createAuthenticationToken(jwtRequest)).thenReturn(new Object());

		mvc.perform(MockMvcRequestBuilders.post("/v1/auth/authenticate").content(gson.toJson(jwtRequest))
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
				.andDo(print());

	}

	@Test
	public void createAuthenticationTokenFromRefreshToken() throws Exception {

		when(jwtAuthService.createAuthenticationTokenFromRefreshToken(jwtRequestRefresh)).thenReturn(new Object());

		mvc.perform(MockMvcRequestBuilders.post("/v1/auth/refresh").content(gson.toJson(jwtRequestRefresh))
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
				.andDo(print());

	}

}