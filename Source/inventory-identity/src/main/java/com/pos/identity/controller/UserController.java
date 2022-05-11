package com.pos.identity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pos.identity.dto.UserDto;
import com.pos.identity.service.JwtUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/auth")
@Slf4j
public class UserController {

	@Autowired
	JwtUserDetailsService userDetailsService;

	@PutMapping("/user/auth")
	public ResponseEntity<?> updateUserPassword(@RequestBody UserDto userDto) {
		log.info("UserController.updateUserPassword() invoked.");
		return new ResponseEntity<UserDto>(userDetailsService.updateUserPassword(userDto), HttpStatus.OK);
	}

	@PutMapping("/user/unlock")
	public ResponseEntity<?> unlockUser(@RequestBody UserDto userDto) {
		log.info("UserController.unlockUser() invoked.");
		return new ResponseEntity<UserDto>(userDetailsService.unlockUser(userDto), HttpStatus.OK);
	}

	@PutMapping("/user/approve")
	public ResponseEntity<?> approveUser(@RequestBody UserDto userDto) {
		log.info("UserController.approveUser() invoked.");
		return new ResponseEntity<UserDto>(userDetailsService.approveUser(userDto), HttpStatus.OK);
	}

	@PutMapping("/user/verify")
	public ResponseEntity<?> verifyUser(@RequestBody UserDto userDto) {
		log.info("UserController.approveUser() invoked.");
		return new ResponseEntity<UserDto>(userDetailsService.verifyUser(userDto), HttpStatus.OK);
	}

	@PutMapping("/user/status")
	public ResponseEntity<?> updateUserStatus(@RequestBody UserDto userDto) {
		log.info("UserController.updateUserStatus() invoked.");
		return new ResponseEntity<UserDto>(userDetailsService.updateUserStatus(userDto), HttpStatus.OK);
	}

	@PostMapping("/user")
	public ResponseEntity<?> saveUser(@RequestBody UserDto userDto, @RequestParam("clientId") Long clientId) {
		log.info("UserController.updateUserPassword() invoked.");
		return new ResponseEntity<UserDto>(userDetailsService.saveUser(userDto, clientId), HttpStatus.OK);
	}

	@PutMapping("/user")
	public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @RequestParam("clientId") Long clientId) {
		log.info("UserController.updateUser() invoked.");
		return new ResponseEntity<UserDto>(userDetailsService.updateUser(userDto, clientId), HttpStatus.OK);
	}

}
