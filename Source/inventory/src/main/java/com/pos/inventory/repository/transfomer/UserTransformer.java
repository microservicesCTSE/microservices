package com.pos.inventory.repository.transfomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.UserDto;
import com.pos.inventory.repository.domain.User;

@Component
public class UserTransformer implements BaseTransformer<User, UserDto> {

	@Autowired
	UserTypeTransformer userTypeTransformer;

	@Autowired
	ClientTransformer clientTransformer;

	@Autowired
	RoleTransformer roleTransformer;

	@Override
	public UserDto transform(User user) {
		UserDto userDto = null;
		if (user != null) {
			userDto = new UserDto();
			userDto.setUserId(user.getUserId());
			userDto.setFullName(user.getFullName());
			userDto.setContactNo(user.getContactNo());
			userDto.setCountry(user.getCountry());
			userDto.setUserName(user.getUserName());
			userDto.setPassword(user.getPassword());
			userDto.setApprovedDate(user.getApprovedDate());
			userDto.setEmail(user.getEmail());
			userDto.setIsApproved(user.getIsApproved());
			userDto.setIsLocked(user.getIsLocked());
			userDto.setApprovedDate(user.getApprovedDate());
			userDto.setLockedDateTime(user.getIsLockedDate());
			userDto.setVerifyToken(user.getVerifyToken());
			userDto.setIsActive(user.getIsActive());
			userDto.setIsNewUser(user.getIsNewUser());
			if (user.getUserTypeId() != null) {
				userDto.setUserTypeDto(userTypeTransformer.transform(user.getUserTypeId()));
			}
			if (user.getClient_id() != null) {
				userDto.setClient_id(clientTransformer.transform(user.getClient_id()));
			}
			if (user.getRoleId() != null) {
				userDto.setRoleId(roleTransformer.transform(user.getRoleId()));
			}

		}
		return userDto;

	}

	@Override
	public User reverseTransform(UserDto userDto) {
		User user = null;
		if (userDto != null) {
			user = new User();
			user.setUserId(userDto.getUserId());
			user.setFullName(userDto.getFullName());
			user.setContactNo(userDto.getContactNo());
			user.setCountry(userDto.getCountry());
			user.setUserName(userDto.getUserName());
			user.setPassword(userDto.getPassword());
			user.setApprovedDate(userDto.getApprovedDate());
			user.setEmail(userDto.getEmail());
			user.setIsApproved(userDto.getIsApproved());
			user.setIsLocked(userDto.getIsLocked());
			user.setApprovedDate(userDto.getApprovedDate());
			user.setIsLockedDate(userDto.getLockedDateTime());
			user.setVerifyToken(userDto.getVerifyToken());
			user.setIsActive(userDto.getIsActive());
			user.setIsNewUser(userDto.getIsNewUser());
			if (userDto.getUserTypeDto() != null) {
				user.setUserTypeId(userTypeTransformer.reverseTransform(userDto.getUserTypeDto()));
			}
			if (userDto.getClient_id() != null) {
				user.setClient_id(clientTransformer.reverseTransform(userDto.getClient_id()));
			}
			if (userDto.getRoleId() != null) {
				user.setRoleId(roleTransformer.reverseTransform(userDto.getRoleId()));
			}
		}
		return user;
	}

}
