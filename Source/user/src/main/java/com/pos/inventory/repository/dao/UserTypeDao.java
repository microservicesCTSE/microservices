///**
// * 
// */
//package com.pos.inventory.repository.dao;
//
//import java.util.List;
//import java.util.Map;
//
//import com.pos.inventory.common.dto.UserTypeDto;
//import com.pos.inventory.repository.domain.UserType;
//
///**
// * @author MDev
// *
// */
//public interface UserTypeDao extends BaseDao<UserType> {
//
//	UserTypeDto findById(Long userTypeId);
//
//	/**
//	 * return all user types
//	 * 
//	 * @return List&ltUserTypeDto&gt
//	 */
//	List<UserTypeDto> getAllUserTypes();
//
//	/**
//	 * get User Type By Username
//	 * 
//	 * @param userName
//	 * @return List<UserTypeDto>
//	 */
//	List<UserTypeDto> getUserTypeByUsername(String userName);
//
//}