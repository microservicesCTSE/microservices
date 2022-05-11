package com.pos.identity.transformer;

import com.pos.identity.domain.UserType;
import com.pos.identity.dto.UserTypeDto;

import org.springframework.stereotype.Component;

@Component
public class UserTypeTransformer implements BaseTransformer<UserType, UserTypeDto> {

    @Override
    public UserTypeDto tranform(UserType userType) {

        UserTypeDto userTypeDto = new UserTypeDto();
        userTypeDto.setUserTypeId(userType.getUserTypeId());
        userTypeDto.setUserTypeDesc(userType.getUserTypeDesc());

        return userTypeDto;
    }

    @Override
    public UserType reverseTranform(UserTypeDto userTypeDto) {

        UserType userType = new UserType();
        userType.setUserTypeId(userTypeDto.getUserTypeId());
        userType.setUserTypeDesc(userTypeDto.getUserTypeDesc());

        return userType;
    }
}
