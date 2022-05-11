/**
 * 
 */
package com.pos.inventory.service.BL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pos.inventory.common.dto.UserDto;
import com.pos.inventory.repository.dao.UserDao;

import lombok.extern.slf4j.Slf4j;

/**
 * @author MDev
 *
 */
@Service
@Slf4j
public class UserServiceBL {

	@Autowired
	UserDao userDao;

//	@Autowired
//	AuthenticationFeignClient authenticationFeignClient;
//
//	@Autowired
//	private PasswordEncoder bcryptEncoder;
//
//	@Autowired
//	EmailFeignClient emailFeignClient;
//
//	@Autowired
//	UserRoleServiceBL userRoleServiceBL;
//
//	@Autowired
//	RolePrivilegeServiceBL rolePrivilegeServiceBL;
//
//	@Autowired
//	UserRoleDao userRoleDao;

	public UserDto getUserByUsername(String username) {
		log.info("UserServiceBL.getUserByUsername() invoked.");
		return userDao.getUserByEmailAddress(username);
	}

//	public UserDto saveUser(UserDto userDto) {
//		log.info("UserServiceImpl.saveUser() invoked.");
//		UserDto saveUserDto = null;
//		if (userDto.getUserId() != null) {
//			userDto.setPassword(bcryptEncoder.encode(userDto.getPassword()));
//			authenticationFeignClient.update(userDto, userDto.getClient_id().getClientId());
//			saveUserDto = userDao.updateUser(userDto);
//			List<UserRoleDto> userRoleDtoList = userRoleDao.getUserRoleDetailsByUserId(saveUserDto.getUserId());
//			userRoleDtoList.get(0).setRoleDto(userDto.getRoleId());
//			userRoleDao.updateUserRoleDetails(userRoleDtoList.get(0));
//
//			return saveUserDto;
//		} else {
//			userDto.setPassword(bcryptEncoder.encode(userDto.getPassword()));
//			userDto.setApprovedDate(LocalDate.now());
//			userDto.setIsNewUser(Boolean.TRUE);
//			authenticationFeignClient.save(userDto, userDto.getClient_id().getClientId());
//			saveUserDto = userDao.saveUser(userDto);
//			UserRoleDto userRoleDto = new UserRoleDto();
//			userRoleDto.setIsActive(Boolean.TRUE);
//			userRoleDto.setUserDto(saveUserDto);
//			userRoleDto.setRoleDto(saveUserDto.getRoleId());
////			userRoleDto.setRoleDescription(saveUserDto.getRoleId().getRoleDescription());
//			userRoleDao.saveUserRoleDetails(userRoleDto);
//			return saveUserDto;
//		}
//	}
//
//	public PaginatedResponseDto getAllUserDetails(int pageNumber, int pageSize, Map<String, String> searchParams) {
//		log.info("UserServiceBL.getAllUserDetails()invoked");
//		return userDao.getAllUserDetails(pageNumber, pageSize, searchParams);
//	}
//
//	public UserDto updateStatus(String email, Boolean status) {
//		UserDto userDto = userDao.getUserByEmailAddress(email);
//		userDto.setIsActive(status);
//		authenticationFeignClient.upadteStatus(userDto);
//		return userDao.updateUser(userDto);
//	}
//
//	public List<String> getAllPrivilegesByUsername(String username) {
//		UserDto userDto = userDao.getUserByEmailAddress(username);
//		List<UserRoleDto> userRoleDtos = userRoleServiceBL.getUserRoleDetailsByUserId(userDto.getUserId());
//		Set<String> privilegeCodes = new HashSet<>();
//		for (UserRoleDto userRoleDto : userRoleDtos) {
//			List<RolePrivilegeDto> rolePrivilegeDtos = rolePrivilegeServiceBL
//					.getRolePrivilegeDetailsByRoleId(userRoleDto.getRoleDto().getRoleId());
//			for (RolePrivilegeDto rolePrivilegeDto : rolePrivilegeDtos) {
//				privilegeCodes.add(rolePrivilegeDto.getPrivilegeDto().getPrivilegeCode());
//			}
//		}
//		List<String> result = new ArrayList<>();
//		result.addAll(privilegeCodes);
//		return result;
//	}
//
//	public List<UserDto> getUserById(Long userId) {
//		log.info("UserServiceBL.getUserById() invoked.");
//		return userDao.getUserById(userId);
//	}
}
