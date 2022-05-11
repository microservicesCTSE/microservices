package com.pos.identity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pos.identity.dto.UserLoginDetailsDto;
import com.pos.identity.service.UserLoginDetailsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/auth")
@Slf4j
public class UserLoginDetailsController {

	@Autowired
	UserLoginDetailsService userLoginDetailsService;

	@GetMapping("/userLoginDetails/{username}")
	public ResponseEntity<?> getUserLoginDetailsByUserName(@PathVariable("username") String username) {
		log.info("UserLoginDetailsController.getUserLoginDetailsByUserName() invoked.");
		return new ResponseEntity<UserLoginDetailsDto>(userLoginDetailsService.getLastLoginDetails(username),
				HttpStatus.OK);
	}
	
	@GetMapping("/userLoginDetails")
    public ResponseEntity<?> getUserLoginDetails(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
		log.info(
				"UserLoginDetailsController.getUserLoginDetails(@RequestParam(pageNumber) int pageNumber,@RequestParam(pageSize) int pageSize) invoked");
		return new ResponseEntity<Object>(userLoginDetailsService.getUserLoginDetails(pageNumber, pageSize),HttpStatus.OK);
    }

}
