package com.pos.testInventory.controller;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.controller.UserController;
import com.pos.testInventory.AbstractTest;

@Transactional
public class UserControllerTest extends AbstractTest {

	@Autowired
	UserController userController;

	@Before
	public void setup() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testgetUserByUsername() {
		ResponseDto responseDto = userController.getUserByUsername("algorialdev@gmail.com");
		Assert.assertNull("Failure Null: ", responseDto.getErrorDescription());
		Assert.assertNotNull("Failure Not Null:", responseDto.getResponseDto());
	}

}
