package com.pos.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.pos.inventory.aop.Scope;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/getUserByUsername")
	public ResponseDto getUserByUsername(@RequestParam("username") String username) {
		log.info("UserController.getUserByUsername() invoked.");
		return userService.getUserByUsername(username);
	}

//	@Scope(value = {
//			Authorize.CORE_ADMIN })
//	@GetMapping("/userValidation")
//	public ResponseDto userValidation(@RequestParam("username") String username) {
//		log.info("UserController.userValidation() invoked.");
//		return userService.userValidation(username);
//	}
//
//	@Scope(value = { Authorize.CORE_ADMIN })
//	@PostMapping("/save")
//	public ResponseDto userSave(@RequestBody UserDto userDto) {
//		log.info("UserController.userSave() invoked.");
//		return userService.saveUser(userDto);
//	}
//
//	@Scope(value = { Authorize.CORE_ADMIN })
//	@GetMapping("/getAllUserDetails")
//	public ResponseDto getAllUserDetails(@RequestParam("pageNumber") int pageNumber,
//			@RequestParam("pageSize") int pageSize, WebRequest webRequest) {
//		log.info("UserController.getAllUserDetails() invoked.");
//		return userService.getAllUserDetails(pageNumber, pageSize, HttpReqRespUtils.getSearchParameters(webRequest));
//	}
//
//	@Scope(value = { Authorize.CORE_ADMIN })
//	@PutMapping("/updateStatus")
//	public ResponseDto updateStatus(@RequestParam("email") String email, @RequestParam("status") Boolean status) {
//		log.info("UserController.updateStatus() invoked.");
//		return userService.updateUser(email, status);
//	}
//
//	@Scope(value = { Authorize.CORE_ADMIN })
//	@GetMapping("/privileges/{username}")
//	public ResponseDto getAllPrivilegesByUsername(@PathVariable("username") String username) {
//		log.info("UserController.getAllPrivilegesByUsername() invoked.");
//		return userService.getAllPrivilegesByUsername(username);
//	}
}
