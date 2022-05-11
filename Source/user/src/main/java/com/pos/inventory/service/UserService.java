/**
 * 
 */
package com.pos.inventory.service;

import java.util.Map;

import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.common.dto.UserDto;

/**
 * @author MDev
 *
 */
public interface UserService {

	ResponseDto getUserByUsername(String username);

//	ResponseDto userValidation(String username);
//
//	ResponseDto saveUser(UserDto userDto);
//
//	ResponseDto getAllUserDetails(int pageNumber, int pageSize, Map<String, String> searchParams);
//
//	ResponseDto updateUser(String email, Boolean status);
//
//	ResponseDto getAllPrivilegesByUsername(String username);
//
//	ResponseDto getUserById(Long userId);
}
