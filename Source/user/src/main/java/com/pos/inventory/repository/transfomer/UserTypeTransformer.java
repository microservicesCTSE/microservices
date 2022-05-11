package com.pos.inventory.repository.transfomer;

import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.UserTypeDto;
import com.pos.inventory.repository.domain.UserType;

@Component
public class UserTypeTransformer implements BaseTransformer<UserType, UserTypeDto> {

	@Override
	public UserTypeDto transform(UserType userType) {
		UserTypeDto userTypeDto = null;
		if (userType != null) {
			userTypeDto = new UserTypeDto();
			userTypeDto.setUserTypeId(userType.getUserTypeId());
			userTypeDto.setUserType(userType.getUserType());
			userTypeDto.setDescription(userType.getDescription());
			userTypeDto.setIsActive(userType.getIsActive());

		}
		return userTypeDto;
	}

	@Override
	public UserType reverseTransform(UserTypeDto userTypeDto) {
		UserType userType = null;
		if (userTypeDto != null) {
			userType = new UserType();
			userType.setUserTypeId(userTypeDto.getUserTypeId());
			userType.setUserType(userTypeDto.getUserType());
			userType.setDescription(userTypeDto.getDescription());
			userType.setIsActive(userTypeDto.getIsActive());

		}
		return userType;
	}

}
