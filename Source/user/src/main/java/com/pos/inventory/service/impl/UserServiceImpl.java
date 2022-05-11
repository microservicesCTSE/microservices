/**
 * 
 */
package com.pos.inventory.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.inventory.common.constants.ApplicationMessageConstants;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.common.dto.UserDto;
import com.pos.inventory.service.UserService;
import com.pos.inventory.service.BL.UserServiceBL;
import com.pos.inventory.service.util.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author MDev
 *
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	UserServiceBL userServiceBL;

	@Override
	public ResponseDto getUserByUsername(String username) {
		log.info("UserServiceImpl.getUserByUsername() invoked.");
		ResponseDto responseDto = null;
		try {
			UserDto userDto = userServiceBL.getUserByUsername(username);
			if (userDto != null) {
				log.info("user details retrive successfully.");
				responseDto = serviceUtil.getServiceResponse(userDto);
			} else {
				log.info("Unable to retrive user details.");
				responseDto = serviceUtil
						.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_USER_DETAILS);
			}

		} catch (Exception e) {
			log.error("Exception occurs while retriving user details.", e);
			responseDto = serviceUtil
					.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.EX_GET_USER_DETAILS);
		}
		return responseDto;
	}

//	@Override
//	public ResponseDto userValidation(String username) {
//		log.info("UserServiceImpl.userValidation() invoked.");
//		ResponseDto responseDto = null;
//		try {
//			UserDto userDto = userServiceBL.getUserByUsername(username);
//			if (userDto != null) {
//				log.info("user details retrive successfully.");
//				responseDto = serviceUtil.getServiceResponse(Boolean.TRUE);
//			} else {
//				log.info("Unable to retrive user details.");
//				responseDto = serviceUtil.getErrorServiceResponse(Boolean.FALSE);
//			}
//
//		} catch (Exception e) {
//			log.error("Exception occurs while retriving user details.", e);
//			responseDto = serviceUtil
//					.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.EX_GET_USER_DETAILS);
//		}
//		return responseDto;
//	}
//
//	@Override
//	public ResponseDto saveUser(UserDto userDto) {
//		log.info("UserServiceImpl.userValidation() invoked.");
//		ResponseDto responseDto = null;
//		try {
//			UserDto saveUserDto = userServiceBL.saveUser(userDto);
//			if (userDto != null) {
//				log.info("user Saved.");
//				responseDto = serviceUtil.getServiceResponse(saveUserDto);
//			} else {
//				log.info("Unable to saved user details.");
//				responseDto = serviceUtil
//						.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_USER_DETAILS);
//			}
//
//		} catch (Exception e) {
//			log.error("Exception occurs while saving user details.", e);
//			responseDto = serviceUtil
//					.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.EX_GET_USER_DETAILS);
//		}
//		return responseDto;
//	}
//
//	public ResponseDto getAllUserDetails(int pageNumber, int pageSize, Map<String, String> searchParams) {
//		log.info("UserServiceImpl.getAllUserDetails() invoked");
//		ResponseDto responseDto = null;
//		try {
//			PaginatedResponseDto paginatedResponseDto = userServiceBL.getAllUserDetails(pageNumber, pageSize,
//					searchParams);
//			if (paginatedResponseDto != null) {
//				log.info("Retrieve All User Details .");
//				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
//			} else {
//				log.info("Unable to retrieve All User details.");
//				responseDto = serviceUtil.getErrorServiceResponse(
//						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_USER_DETAILS);
//			}
//		} catch (Exception e) {
//			log.error("Exception occurs while retrieving All User details.", e);
//			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
//					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_USER_DETAILS);
//		}
//		return responseDto;
//	}
//
//	@Override
//	public ResponseDto updateUser(String email, Boolean status) {
//		log.info("UserServiceImpl.userValidation() invoked.");
//		ResponseDto responseDto = null;
//		try {
//			UserDto saveUserDto = userServiceBL.updateStatus(email, status);
//			if (saveUserDto != null) {
//				log.info("user Saved.");
//				responseDto = serviceUtil.getServiceResponse(saveUserDto);
//			} else {
//				log.info("Unable to saved user details.");
//				responseDto = serviceUtil
//						.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_GET_USER_DETAILS);
//			}
//
//		} catch (Exception e) {
//			log.error("Exception occurs while saving user details.", e);
//			responseDto = serviceUtil
//					.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.EX_GET_USER_DETAILS);
//		}
//		return responseDto;
//	}
//
//	@Override
//	public ResponseDto getAllPrivilegesByUsername(String username) {
//		log.info("UserServiceImpl.getAllPrivilegesByUserId(String username) invoked.");
//		ResponseDto responseDto = null;
//		try {
//			List<String> privileges = userServiceBL.getAllPrivilegesByUsername(username);
//			if (privileges != null && !privileges.isEmpty()) {
//				responseDto = serviceUtil.getServiceResponse(privileges);
//			} else {
//				log.info("Unable get the privileges for given user.");
//				responseDto = serviceUtil.getErrorServiceResponse(
//						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PRIVILEGES_BY_USERID);
//			}
//		} catch (Exception e) {
//			log.error("Exception occurs while checking user for password reset", e);
//			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
//					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PRIVILEGES_BY_USERID);
//		}
//		return responseDto;
//	}
//
//	@Override
//	public ResponseDto getUserById(Long userId) {
//		log.info("CustomerServiceImpl.getUserById() invoked");
//		ResponseDto responseDto = null;
//		try {
//			List<UserDto> userDtoList = userServiceBL.getUserById(userId);
//			if (userDtoList != null) {
//				log.info("Get user by user id.");
//				responseDto = serviceUtil.getServiceResponse(userDtoList);
//			} else {
//				log.info("Unable to get user by user id.");
//				responseDto = serviceUtil.getErrorServiceResponse(
//						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_CALCULATE_LOW_QUANTITY_DETAIL_BY_PRODUCT_ID);
//			}
//		} catch (Exception e) {
//			log.error("Exception occurs while get user by user id.", e);
//			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
//					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_CALCULATE_LOW_QUANTITY_DETAIL_BY_PRODUCT_ID);
//		}
//		return responseDto;
//	}

}
