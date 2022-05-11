//package com.pos.inventory.service.impl;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.pos.inventory.common.dto.UserTypeDto;
//import com.pos.inventory.common.constants.ApplicationMessageConstants;
//import com.pos.inventory.common.dto.ResponseDto;
//import com.pos.inventory.service.UserTypeService;
//import com.pos.inventory.service.BL.UserTypeServiceBL;
//import com.pos.inventory.service.util.ServiceUtil;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class UserTypeServiceImpl implements UserTypeService {
//
//	@Autowired
//	ServiceUtil serviceUtil;
//
//	@Autowired
//	UserTypeServiceBL userTypeServiceBL;
//
//	@Override
//	public ResponseDto getAllUserType() {
//		log.info("UserTypeServiceImpl.getAllUserTypes() invoked");
//		ResponseDto responseDto = null;
//		try {
//			List<UserTypeDto> userTypeDtoList = userTypeServiceBL.getAllUserTypes();
//			if (userTypeDtoList != null && !userTypeDtoList.isEmpty()) {
//				log.info("Retrieve All UserType Details.");
//				responseDto = serviceUtil.getServiceResponse(userTypeDtoList);
//			} else {
//				log.info("Unable to retrieve All UserType details.");
//				responseDto = serviceUtil.getErrorServiceResponse(
//						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_USER_TYPE_DETAILS);
//			}
//		} catch (Exception e) {
//			log.error("Exception occurs while retrieving All UserType details.", e);
//			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
//					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_USER_TYPE_DETAILS);
//		}
//		return responseDto;
//	}
//}
