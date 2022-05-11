package com.pos.identity.transformer;

import com.pos.identity.domain.User;
import com.pos.identity.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer implements BaseTransformer<User, UserDto> {

	@Autowired
	UserTypeTransformer userTypeTransformer;

	@Override
	public UserDto tranform(User user) {

		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setPassword(user.getPassword());
		userDto.setUserName(user.getUsername());
		userDto.setIsApproved(user.getIsApproved());
		userDto.setIsLocked(user.getIsLocked());
		userDto.setLockedDateTime(user.getLockedDateTime());
		userDto.setFailedAttempt(user.getFailedAttempt());
		userDto.setIsActive(user.getIsActive());
		if (user.getUserTypeId() != null) {
			userDto.setUserTypeDto(userTypeTransformer.tranform(user.getUserTypeId()));
		}
		userDto.setClientId(user.getClientId());
		userDto.setIsVerified(user.getIsVerified());
		return userDto;
	}

	@Override
	public User reverseTranform(UserDto userDto) {

		User user = new User();
		user.setId(userDto.getId());
		user.setPassword(userDto.getPassword());
		user.setUsername(userDto.getUserName());
		user.setIsApproved(userDto.getIsApproved());
		user.setIsLocked(userDto.getIsLocked());
		user.setLockedDateTime(userDto.getLockedDateTime());
		user.setFailedAttempt(userDto.getFailedAttempt());
		user.setIsActive(userDto.getIsActive());
		if (userDto.getUserTypeDto() != null) {
			user.setUserTypeId(userTypeTransformer.reverseTranform(userDto.getUserTypeDto()));
		}
		user.setClientId(userDto.getClientId());
		user.setIsVerified(userDto.getIsVerified());
		return user;
	}

}
