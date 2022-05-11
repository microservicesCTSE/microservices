package com.pos.inventory.repository.dao;

import com.pos.inventory.common.dto.UserDto;
import com.pos.inventory.repository.domain.User;

public interface UserDao extends BaseDao<User> {

	UserDto getUserByEmailAddress(String username);

//	UserDto updateUser(UserDto userDto);
//
//	UserDto saveUser(UserDto userDto);
//
//	PaginatedResponseDto getAllUserDetails(int pageNumber, int pageSize, Map<String, String> searchParams);
//
//	List<UserDto> getUserById(Long userId);
}
