package com.pos.identity.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.pos.identity.PosIdentityApplicationTests;

@Transactional
public class UserLoginDetailsControllerTest extends PosIdentityApplicationTests {

    @Autowired
    UserLoginDetailsController userLoginDetailsController;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUserLoginDetailsByUserName() {
        ResponseEntity<?> responseEntity = userLoginDetailsController.getUserLoginDetailsByUserName("user@code.com");
        Assert.assertNotNull("Null failure", responseEntity.getBody());

        responseEntity = userLoginDetailsController.getUserLoginDetailsByUserName("test123456@code.com");
        Assert.assertNull("Null not failure", responseEntity.getBody());
    }

    @Test
    public void getUserLoginDetails() {
        ResponseEntity<?> responseEntity = userLoginDetailsController.getUserLoginDetails(1,3);
        Assert.assertNotNull("Null failure", responseEntity.getBody());

    }
}