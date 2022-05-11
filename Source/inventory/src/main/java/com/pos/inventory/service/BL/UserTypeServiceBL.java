///**
// * 
// */
//package com.pos.inventory.service.BL;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.pos.inventory.common.dto.UserTypeDto;
//import com.pos.inventory.repository.dao.UserTypeDao;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author MDev
// *
// */
//@Service
//@Slf4j
//public class UserTypeServiceBL {
//
//	@Autowired
//	UserTypeDao userTypeDao;
//
//	public List<UserTypeDto> getAllUserTypes() {
//		log.info("UserTypeServiceBL.getAllUserTypes() invoked");
//		return userTypeDao.getAllUserTypes();
//	}
//
//	public List<UserTypeDto> getUserTypeByUsername(String userName) {
//		log.info("UserTypeServiceBL.getUserTypeByUsername() invoked");
//		return userTypeDao.getUserTypeByUsername(userName);
//	}
//}
