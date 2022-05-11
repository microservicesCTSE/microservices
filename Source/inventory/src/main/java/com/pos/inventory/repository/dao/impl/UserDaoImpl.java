package com.pos.inventory.repository.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.common.dto.UserDto;
import com.pos.inventory.repository.dao.UserDao;
import com.pos.inventory.repository.domain.User;
import com.pos.inventory.repository.transfomer.UserTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Autowired
	UserTransformer userTransformer;

//	@Autowired
//	UserReportTransformer userReportTransformer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserDto getUserByEmailAddress(String username) {
		log.info("UserDaoImpl.getUserByEmailAddress() invoked.");
		ResponseDto responseDto = null;
		log.info("UserDaoImpl.getUserByEmailAddress(ResetPasswordRequestDto username)");
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", username));
//		criteria.add(Restrictions.eq("isActive", Boolean.TRUE));
		UserDto userDto = null;
		User user = (User) criteria.uniqueResult();
		if (user != null) {
			userDto = userTransformer.transform(user);
		}
		return userDto;
	}

//	@Transactional
//	@Override
//	public UserDto updateUser(UserDto userDto) {
//		log.info("UserDaoImpl.updateUser(UserDto userDto)");
//		User user = userTransformer.reverseTransform(userDto);
//		User saveUser = merge(user);
//		return userTransformer.transform(saveUser);
//	}
//
//	@Transactional
//	@Override
//	public UserDto saveUser(UserDto userDto) {
//		log.info("UserDaoImpl.updateUser(UserDto userDto)");
//		User user = userTransformer.reverseTransform(userDto);
//		User saveUser = saveOrUpdate(user);
//		return userTransformer.transform(saveUser);
//	}
//
//	@Override
//	public PaginatedResponseDto getAllUserDetails(int pageNumber, int pageSize, Map<String, String> searchParams) {
//		log.info("UserDaoImpl.getAllUserDetails()invoked");
//		PaginatedResponseDto paginatedResponseDto = null;
//		List<User> AllUserDetailsList = null;
//		int recordCount = 0;
//		Criteria criteria = getCurrentSession().createCriteria(User.class, "User");
//		criteria.createAlias("User.userTypeId", "UserType");
//		if (searchParams.get("userType") != null && !searchParams.get("userType").isEmpty()) {
//			criteria.add(Restrictions.eq("UserType.userType", searchParams.get("userType")));
//		}
//		if (searchParams.get("userName") != null && !searchParams.get("userName").isEmpty()) {
//			criteria.add(Restrictions.like("User.userName", searchParams.get("userName"), MatchMode.ANYWHERE));
//		}
//		if (searchParams.get("email") != null && !searchParams.get("email").isEmpty()) {
//			criteria.add(Restrictions.eq("User.email", searchParams.get("email")));
//		}
//		if (searchParams.get("name") != null && !searchParams.get("name").isEmpty()) {
//			criteria.add(Restrictions.like("User.fullName", searchParams.get("name")));
//		}
//		if (searchParams.get("contactNo") != null && !searchParams.get("contactNo").isEmpty()) {
//			criteria.add(Restrictions.eq("User.contactNo", Integer.parseInt(searchParams.get("contactNo"))));
//		}
//		criteria.add(Restrictions.eq("User.isApproved", Boolean.TRUE));
//		criteria.addOrder(Order.desc("User.userId"));
//		criteria.setFirstResult((pageNumber - 1) * pageSize);
//		criteria.setMaxResults(pageSize);
//		AllUserDetailsList = criteria.list();
//		recordCount = AllUserDetailsList.size();
//
//		String countString = "SELECT COUNT(*) FROM user";
//		int count = jdbcTemplate.queryForObject(countString, Integer.class);
//
//		if (AllUserDetailsList != null && !AllUserDetailsList.isEmpty()) {
//			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(AllUserDetailsList, pageNumber, pageSize,
//					count);
//			paginatedResponseDto.setPayload(AllUserDetailsList.stream().map(User -> {
//				return userTransformer.transform(User);
//			}).collect(Collectors.toList()));
//		}
//		return paginatedResponseDto;
//	}
//
//	@Override
//	public List<UserDto> getUserById(Long userId) {
//		log.info("UserDaoImpl.getUserById() invoked");
//		Criteria criteria = getCurrentSession().createCriteria(User.class, "User");
//		if (userId != null) {
//			criteria.add(Restrictions.eq("User.userId", userId));
//		}
//		List<UserDto> userDtoList = null;
//		List<User> userList = (List<User>) criteria.list();
//		if (userList != null && !userList.isEmpty()) {
//			userDtoList = new ArrayList<>();
//			for (User user : userList) {
//				userDtoList.add(userReportTransformer.transform(user));
//			}
//		}
//		return userDtoList;
//	}

}
