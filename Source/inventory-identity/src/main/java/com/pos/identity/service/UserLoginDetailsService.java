package com.pos.identity.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.pos.identity.dao.UserLoginDetailsRepository;
import com.pos.identity.dao.UserRepository;
import com.pos.identity.domain.User;
import com.pos.identity.domain.UserLoginDetails;
import com.pos.identity.dto.PaginatedResponseDto;
import com.pos.identity.dto.ResponseDto;
import com.pos.identity.dto.UserDto;
import com.pos.identity.dto.UserLoginDetailsDto;
import com.pos.identity.service.client.PosServiceFeignClient;
import com.pos.identity.transformer.UserLoginDetailsTransformer;
import com.pos.identity.transformer.UserTransformer;
import com.pos.utils.HttpReqRespUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Thilina
 *
 */
@Service
@Slf4j
public class UserLoginDetailsService {

	@Autowired
	UserLoginDetailsRepository userLoginDetailsRepository;

	@Autowired
	UserLoginDetailsTransformer userLoginDetailsTransformer;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserTransformer userTransformer;

	@Value("${lockuser.invalidcount}")
	private String invalidLoginBlockCount;
	
	@Autowired
	PosServiceFeignClient posServiceFeignClient;

	@Autowired
	Gson gson;

	/**
	 * save user login records and lock user by invalid login count
	 * 
	 * @param userName
	 * @param userIp
	 * @return UserLoginDetailsDto
	 */
	public UserLoginDetailsDto save(String userName, String userIp) {
		log.info("UserLoginDetailsService.save(userName, userIp) invoked.");
		UserLoginDetailsDto userLoginDetailsDtoResult = userLoginDetailsTransformer
				.tranform(userLoginDetailsRepository.findTopByUsernameOrderByUserLoginDetailsIdDesc(userName));
		UserLoginDetailsDto userLoginDetailsDto = null;
		UserLoginDetails savedUserLoginDetails = null;
		if (userLoginDetailsDtoResult != null) {
			if (userLoginDetailsDtoResult.getInvalidLoginCount() != null) {
				userLoginDetailsDtoResult.setInvalidLoginCount(userLoginDetailsDtoResult.getInvalidLoginCount() + 1);
			} else {
				userLoginDetailsDtoResult.setInvalidLoginCount(1);
			}
			userLoginDetailsDtoResult.setUserIp(userIp);
			userLoginDetailsDtoResult.setLoginDate(LocalDate.now());
			userLoginDetailsDtoResult.setLoginTime(LocalTime.now());
			userLoginDetailsDtoResult.setIsSuccess(Boolean.FALSE);
			UserLoginDetails userLoginDetails = userLoginDetailsTransformer.reverseTranform(userLoginDetailsDtoResult);
			savedUserLoginDetails = userLoginDetailsRepository.save(userLoginDetails);
			ResponseDto responseDto = null;
			if (userLoginDetailsDtoResult.getInvalidLoginCount() > Integer.parseInt(invalidLoginBlockCount)) {
				UserDto userDto = userTransformer.tranform(userRepository.findByUsername(userName));
				if (!Boolean.TRUE.equals(userDto.getIsLocked())) {
					userDto.setIsLocked(Boolean.TRUE);
					userDto.setLockedDateTime(LocalDateTime.now());
					User updatedUser = userRepository.save(userTransformer.reverseTranform(userDto));
					if (updatedUser != null) {
						updatedUser.setClientId(null);
//						ResponseEntity<Object> response = posServiceFeignClient
//								.updateLockedUser(userTransformer.tranform(updatedUser));
//						if (response != null) {
//							responseDto = gson.fromJson(gson.toJson(response.getBody()), ResponseDto.class);
//						}
						if (responseDto.getResponseDto() != null) {
							log.info("Pos service updated successfully.");
						}
					}
				}
			}
		} else {
			userLoginDetailsDto = new UserLoginDetailsDto();
			userLoginDetailsDto.setUsername(userName);
			userLoginDetailsDto.setUserIp(userIp);
			userLoginDetailsDto.setLoginDate(LocalDate.now());
			userLoginDetailsDto.setLoginTime(LocalTime.now());
			userLoginDetailsDto.setIsSuccess(Boolean.FALSE);
			userLoginDetailsDto.setInvalidLoginCount(1);
			UserLoginDetails userLoginDetails = userLoginDetailsTransformer.reverseTranform(userLoginDetailsDto);
			savedUserLoginDetails = userLoginDetailsRepository.save(userLoginDetails);
		}

//		Long invalidLoginCount = userLoginDetailsRepository.countByUsernameAndIsSuccessFalse(userName);
//		if (invalidLoginCount > Long.parseLong(invalidLoginBlockCount)) {
//			UserDto userDto = userTransformer.tranform(userRepository.findByUsername(userName));
//			if (!Boolean.TRUE.equals(userDto.getIsLocked())) {
//				userDto.setIsLocked(Boolean.TRUE);
//				userDto.setLockedDateTime(LocalDateTime.now());
//				userRepository.save(userTransformer.reverseTranform(userDto));
//			}
//		}

		return userLoginDetailsTransformer.tranform(savedUserLoginDetails);
	}

	/**
	 * update user login record
	 * 
	 * @param username
	 * @return UserLoginDetailsDto
	 */
	public UserLoginDetailsDto updateLastRecord(String username,Boolean isSuccess) {
		log.info("UserLoginDetailsService.updateLastRecord(userName) invoked.");
		UserLoginDetailsDto userLoginDetailsDto = userLoginDetailsTransformer
				.tranform(userLoginDetailsRepository.findTopByUsernameOrderByUserLoginDetailsIdDesc(username));
		userLoginDetailsDto.setIsSuccess(isSuccess);
		userLoginDetailsDto.setInvalidLoginCount(0);
		UserLoginDetails userLoginDetails = userLoginDetailsTransformer.reverseTranform(userLoginDetailsDto);
		UserLoginDetails updatedUserLoginDetails = userLoginDetailsRepository.save(userLoginDetails);
		return userLoginDetailsTransformer.tranform(updatedUserLoginDetails);
	}

	/**
	 * return last login date time
	 * 
	 * @param username
	 * @return UserLoginDetailsDto
	 */
	public UserLoginDetailsDto getLastLoginDetails(String username) {
		log.info("UserLoginDetailsService.getLastLoginDetails() invoked.");
		return userLoginDetailsTransformer
				.tranform(userLoginDetailsRepository.findTopByUsernameOrderByUserLoginDetailsIdDesc(username));
	}

	public PaginatedResponseDto getUserLoginDetails(int pageNumber, int pageSize) {
		log.info("UserLoginDetailsService.getUserLoginDetails() invoked.");
		PaginatedResponseDto paginatedResponse = null;
		Pageable paging = PageRequest.of((pageNumber - 1), pageSize);
		Page<UserLoginDetails> pageResponse = userLoginDetailsRepository
				.findAllByOrderByLoginDateDescInvalidLoginCountDesc(paging);

		paginatedResponse = HttpReqRespUtils.paginatedResponseMapper(pageResponse.getContent(), pageNumber, pageSize,
				Integer.parseInt(Long.toString(pageResponse.getTotalElements())));
		if (paginatedResponse != null) {
			paginatedResponse.setPayload(pageResponse.getContent().stream().map(userLoginDetails -> {
				return userLoginDetailsTransformer.tranform(userLoginDetails);
			}).collect(Collectors.toList()));
		}
		return paginatedResponse;
	}

}
