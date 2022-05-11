package com.pos.identity.transformer;

import org.springframework.stereotype.Component;

import com.pos.identity.domain.UserLoginDetails;
import com.pos.identity.dto.UserLoginDetailsDto;

@Component
public class UserLoginDetailsTransformer implements BaseTransformer<UserLoginDetails, UserLoginDetailsDto> {

	@Override
	public UserLoginDetailsDto tranform(UserLoginDetails userLoginDetails) {
		UserLoginDetailsDto userLoginDetailsDto = null;
		if (userLoginDetails != null) {
			userLoginDetailsDto = new UserLoginDetailsDto();
			userLoginDetailsDto.setUserLoginDetailsId(userLoginDetails.getUserLoginDetailsId());
			userLoginDetailsDto.setUsername(userLoginDetails.getUsername());
			userLoginDetailsDto.setUserIp(userLoginDetails.getUserIp());
			userLoginDetailsDto.setLoginDate(userLoginDetails.getLoginDate());
			userLoginDetailsDto.setLoginTime(userLoginDetails.getLoginTime());
			userLoginDetailsDto.setIsSuccess(userLoginDetails.getIsSuccess());
			userLoginDetailsDto.setInvalidLoginCount(userLoginDetails.getInvalidLoginCount());
		}
		return userLoginDetailsDto;
	}

	@Override
	public UserLoginDetails reverseTranform(UserLoginDetailsDto userLoginDetailsDto) {
		UserLoginDetails userLoginDetails = null;
		if (userLoginDetailsDto != null) {
			userLoginDetails = new UserLoginDetails();
			userLoginDetails.setUserLoginDetailsId(userLoginDetailsDto.getUserLoginDetailsId());
			userLoginDetails.setUsername(userLoginDetailsDto.getUsername());
			userLoginDetails.setUserIp(userLoginDetailsDto.getUserIp());
			userLoginDetails.setLoginDate(userLoginDetailsDto.getLoginDate());
			userLoginDetails.setLoginTime(userLoginDetailsDto.getLoginTime());
			userLoginDetails.setIsSuccess(userLoginDetailsDto.getIsSuccess());
			userLoginDetails.setInvalidLoginCount(userLoginDetailsDto.getInvalidLoginCount());
		}
		return userLoginDetails;
	}

}
