//package com.pos.inventory.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.pos.inventory.aop.Scope;
//import com.pos.inventory.common.dto.ResponseDto;
//import com.pos.inventory.common.enums.Authorize;
//import com.pos.inventory.service.UserTypeService;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestController
//@RequestMapping("UserType")
//public class UserTypeController {
//	@Autowired
//	UserTypeService userTypeService;
//
//	@Scope(value = { Authorize.CORE_ADMIN })
//	@GetMapping("/getAll")
//	public ResponseDto getAllUserType() {
//		log.info("UserTypeController.getAllUserType() invoked.");
//		return userTypeService.getAllUserType();
//	}
//
//}
