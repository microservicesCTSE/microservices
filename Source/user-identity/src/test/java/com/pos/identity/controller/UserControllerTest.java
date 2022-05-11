package com.pos.identity.controller;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.pos.identity.PosIdentityApplicationTests;
import com.pos.identity.dto.UserDto;
import com.pos.identity.dto.UserTypeDto;

@Transactional
public class UserControllerTest extends PosIdentityApplicationTests {

    @Autowired
    UserController userController;

    private UserDto userDto = null;
    private UserDto saveUser = null;
    private UserDto errorUserDto = null;
    private UserTypeDto userTypeDto = null;

    @Before
    public void setUp() throws Exception {
        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setEmailAddress("algorialdev@gmail.com");
//        userDto.setReferenceNo("SP");
        userDto.setUserName("algorialdev@gmail.com");
        userDto.setPassword("$2a$10$NQy9ZsISCM1rVX0K7IymKOUqcSz4xZb8vzdSiQK97pekqdISiIl5W");
        userDto.setClientId(1L);
        userDto.setIsApproved(Boolean.TRUE);
        userDto.setIsActive(Boolean.TRUE);
        userDto.setIsVerified(Boolean.TRUE);
//        userDto.setVerificationCode("4e9a985a-8946-4b24-999a-aa105fef430d");
        userDto.setExpiredAt(LocalDateTime.now());
        userDto.setVerifiedOn(LocalDateTime.now());
        userTypeDto = new UserTypeDto();
        userTypeDto.setUserTypeId(1L);
        userDto.setUserTypeDto(userTypeDto);

        errorUserDto = new UserDto();
        errorUserDto.setId(-1L);
        errorUserDto.setEmailAddress("user123456789@code.com");
        errorUserDto.setReferenceNo("SP");
        errorUserDto.setUserName("user123456@code.com");
        errorUserDto.setPassword("$2a$10$WE7En9THsCgHuv5q23b/A.oUdeMSRTJvnREqpOJaXP.1.wYYFiY3K4564");
        errorUserDto.setClientId(1L);
        errorUserDto.setIsApproved(Boolean.TRUE);
        errorUserDto.setIsActive(Boolean.TRUE);
        errorUserDto.setIsVerified(Boolean.TRUE);
        errorUserDto.setVerificationCode("4e9a985a-8946-4b24-999a-aa105fef430d");
        errorUserDto.setUserTypeDto(userTypeDto);

        saveUser = new UserDto();
        saveUser.setEmailAddress("test@code.com");
        saveUser.setReferenceNo("SP");
        saveUser.setUserName("test@code.com");
        saveUser.setPassword("$2a$10$WE7En9THsCgHuv5q23b/A.oUdeMSRTJvnREqpOJaXP.1.wYYFiY3K4564");
        saveUser.setIsApproved(Boolean.TRUE);
        saveUser.setIsActive(Boolean.TRUE);
        saveUser.setIsVerified(Boolean.TRUE);
        saveUser.setVerificationCode("4e9a985a-8946-4b24-999a-aa105fef430d");
        saveUser.setExpiredAt(LocalDateTime.now());
        saveUser.setVerifiedOn(LocalDateTime.now());
    }

    @After
    public void tearDown() throws Exception {
        userDto = null;
        errorUserDto = null;
        saveUser = null;
    }

    @Test
    public void updateUserPassword() {
        ResponseEntity<?> responseEntity = userController.updateUserPassword(userDto);
        Assert.assertNotNull("Null failure", responseEntity.getBody());

        responseEntity = userController.updateUserPassword(errorUserDto);
        Assert.assertNull("Null not failure", responseEntity.getBody());
    }

    @Test
    public void unlockUser() {
        ResponseEntity<?> responseEntity = userController.unlockUser(userDto);
        Assert.assertNotNull("Null failure", responseEntity.getBody());

        responseEntity = userController.unlockUser(errorUserDto);
        Assert.assertNull("Null not failure", responseEntity.getBody());
    }

    @Test
    public void approveUser() {
        ResponseEntity<?> responseEntity = userController.approveUser(userDto);
        Assert.assertNotNull("Null failure", responseEntity.getBody());

        responseEntity = userController.approveUser(errorUserDto);
        Assert.assertNull("Null not failure", responseEntity.getBody());
    }

    @Test
    public void verifyUser() {
        ResponseEntity<?> responseEntity = userController.verifyUser(userDto);
        Assert.assertNotNull("Null failure", responseEntity.getBody());

        responseEntity = userController.verifyUser(errorUserDto);
        Assert.assertNull("Null not failure", responseEntity.getBody());
    }

    @Test
    public void updateUserStatus() {
        ResponseEntity<?> responseEntity = userController.updateUserStatus(userDto);
        Assert.assertNotNull("Null failure", responseEntity.getBody());

        responseEntity = userController.updateUserStatus(errorUserDto);
        Assert.assertNull("Null not failure", responseEntity.getBody());
    }

    @Test
    public void saveUser() {
        ResponseEntity<?> responseEntity = userController.saveUser(saveUser, 1L);
        Assert.assertNotNull("Null failure", responseEntity.getBody());

//        responseEntity = userController.saveUser(errorUserDto, 1L);
//        Assert.assertNull("Null not failure", responseEntity.getBody());
    }

    @Test
    public void updateUser() {
        ResponseEntity<?> responseEntity = userController.updateUser(userDto, 1L);
        Assert.assertNotNull("Null failure", responseEntity.getBody());

        responseEntity = userController.updateUser(errorUserDto, 1L);
        Assert.assertNull("Null not failure", responseEntity.getBody());
    }
}