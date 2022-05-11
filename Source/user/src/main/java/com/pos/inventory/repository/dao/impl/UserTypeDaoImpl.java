///**
// * 
// */
//package com.pos.inventory.repository.dao.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.pos.inventory.common.dto.UserTypeDto;
//import com.pos.inventory.repository.dao.UserTypeDao;
//import com.pos.inventory.repository.domain.UserType;
//import com.pos.inventory.repository.transfomer.UserTypeTransformer;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author MDev
// *
// */
//@Repository
//@Slf4j
//public class UserTypeDaoImpl extends BaseDaoImpl<UserType> implements UserTypeDao {
//
//	@Autowired
//	UserTypeTransformer userTypeTransformer;
//
//	@Override
//	public UserTypeDto findById(Long userTypeId) {
//		log.info("UserTypeDaoImpl.findById(Long userTypeId) invoked.");
//		Criteria criteria = getCurrentSession().createCriteria(UserType.class);
//		criteria.add(Restrictions.eq("id", userTypeId));
//		UserTypeDto userTypeDto = null;
//		UserType userType = (UserType) criteria.uniqueResult();
//		if (userType != null) {
//			userTypeDto = new UserTypeDto();
//			userTypeDto = userTypeTransformer.transform(userType);
//		}
//		return userTypeDto;
//	}
//
//	@Override
//	public List<UserTypeDto> getAllUserTypes() {
//		log.info("UserTypeDaoImpl.getAllUserTypes() invoked.");
//		Criteria criteria = getCurrentSession().createCriteria(UserType.class);
//		List<UserTypeDto> userTypeDtos = null;
//		List<UserType> userTypes = criteria.list();
//		if (userTypes != null && !userTypes.isEmpty()) {
//			userTypeDtos = new ArrayList<>();
//			for (UserType userType : userTypes) {
//				userTypeDtos.add(userTypeTransformer.transform(userType));
//			}
//		}
//		return userTypeDtos;
//	}
//
//	@Override
//	public List<UserTypeDto> getUserTypeByUsername(String userName) {
//		log.info("UserTypeDaoImpl.getUserTypeByUsername(String userName) invoked.");
//		Criteria criteria = getCurrentSession().createCriteria(UserType.class, "UserType");
//		criteria.createAlias("UserType.userList", "User");
//		criteria.add(Restrictions.eq("User.userName", userName));
//		List<UserType> userTypeList = criteria.list();
//		List<UserTypeDto> userTypeDtoList = null;
//
//		if (userTypeList != null && !userTypeList.isEmpty()) {
//			userTypeDtoList = userTypeList.stream().map(x -> userTypeTransformer.transform(x))
//					.collect(Collectors.toList());
//		}
//
//		return userTypeDtoList;
//	}
//
//}
