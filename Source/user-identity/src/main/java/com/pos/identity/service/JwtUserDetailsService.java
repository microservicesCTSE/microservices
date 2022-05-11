package com.pos.identity.service;


import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pos.identity.dao.ClientRepository;
import com.pos.identity.dao.UserRepository;
import com.pos.identity.domain.Client;
import com.pos.identity.domain.UserType;
import com.pos.identity.dto.ClientDto;
import com.pos.identity.dto.UserDto;
import com.pos.identity.transformer.ClientTransformer;
import com.pos.identity.transformer.UserTransformer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private UserTransformer userTransformer;
	@Autowired
	private ClientTransformer clientTransformer;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	UserLoginDetailsService userLoginDetailsService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		com.pos.identity.domain.User user = userRepository
				.findByUsernameAndIsActiveIsTrueAndIsApprovedTrueAndIsLockedFalseAndIsVerifiedTrueOrUsernameAndIsActiveIsTrueAndIsApprovedTrueAndIsLockedNullAndIsVerifiedTrue(
						username, username);

		if (user == null) {
			log.warn("User not found with username: " + username);
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
//      username and password hardcoded
//        if ("javainuse".equals(username)) {
//            return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
	}

	public com.pos.identity.domain.User getUserByUserName(String username) {
		return userRepository
				.findByUsernameAndIsActiveIsTrueAndIsApprovedTrueAndIsLockedFalseAndIsVerifiedTrueOrUsernameAndIsActiveIsTrueAndIsApprovedTrueAndIsLockedNullAndIsVerifiedTrue(
						username, username);
	}

	public ClientDto getClientCode(String username) {

		com.pos.identity.domain.User user = userRepository
				.findByUsernameAndIsActiveIsTrueAndIsApprovedTrueAndIsLockedFalseAndIsVerifiedTrueOrUsernameAndIsActiveIsTrueAndIsApprovedTrueAndIsLockedNullAndIsVerifiedTrue(
						username, username);
		Client client = clientRepository.findById(user.getClientId()).get();
		return clientTransformer.tranform(client);
	}

	public UserDto save(UserDto userDto) {

		com.pos.identity.domain.User user = userTransformer.reverseTranform(userDto);
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		com.pos.identity.domain.User savedUser = userRepository.save(user);

		return userTransformer.tranform(savedUser);
	}

	public UserDto getUserByUsername(String username) {
		com.pos.identity.domain.User user = userRepository.findByUsername(username);
		return userTransformer.tranform(user);
	}

	public UserDto updateUserPassword(UserDto userDto) {
		com.pos.identity.domain.User user = userRepository.findByUsername(userDto.getUserName());
		if (user != null) {
			user.setPassword(userDto.getPassword());
			com.pos.identity.domain.User savedUser = userRepository.save(user);
			savedUser.setClientId(null);
			return userTransformer.tranform(savedUser);
		}
		return null;
	}

	public UserDto unlockUser(UserDto userDto) {
		log.info("UserController.unlockUser() invoked.");
		com.pos.identity.domain.User user = userRepository.findByUsername(userDto.getUserName());
		if (user != null) {
			user.setIsLocked(null);
			user.setLockedDateTime(null);
			com.pos.identity.domain.User savedUser = userRepository.save(user);
			//savedUser.setClientId(null);
			if (savedUser != null) {
				userLoginDetailsService.updateLastRecord(userDto.getUserName(), Boolean.FALSE);
			}
			return userTransformer.tranform(savedUser);
		}
		return null;
	}

	public UserDto approveUser(UserDto userDto) {
		log.info("UserController.approveUser() invoked.");
		com.pos.identity.domain.User user = userRepository.findByUsername(userDto.getUserName());
		if (user != null) {
			user.setIsApproved(userDto.getIsApproved());
			user.setIsActive(userDto.getIsActive());
			com.pos.identity.domain.User savedUser = userRepository.save(user);
			savedUser.setClientId(null);
			return userTransformer.tranform(savedUser);
		}
		return null;
	}

	public UserDto verifyUser(UserDto userDto) {
		log.info("UserController.verifyUser() invoked.");
		com.pos.identity.domain.User user = userRepository.findByUsername(userDto.getUserName());
		if (user != null) {
			user.setIsVerified(userDto.getIsVerified());
			com.pos.identity.domain.User savedUser = userRepository.save(user);
			savedUser.setClientId(null);
			return userTransformer.tranform(savedUser);
		}
		return null;
	}

	public UserDto updateUserStatus(UserDto userDto) {
		log.info("UserController.updateUserStatus() invoked.");
		com.pos.identity.domain.User user = userRepository.findByUsername(userDto.getUserName());
		if (user != null) {
			user.setIsActive(userDto.getIsActive());
			user.setIsApproved(userDto.getIsApproved());
			user.setIsVerified(userDto.getIsVerified());
			com.pos.identity.domain.User savedUser = userRepository.save(user);
			savedUser.setClientId(null);
			return userTransformer.tranform(savedUser);
		}
		return null;
	}

	public UserDto saveUser(UserDto userDto, Long clientId) {
		log.info("UserController.saveUser() invoked.");
		if (userDto != null) {
			userDto.setClientId(clientId);
			userDto.setIsVerified(Boolean.TRUE);
			userDto.setVerifiedOn(LocalDateTime.now());
			com.pos.identity.domain.User savedUser = userRepository
					.save(userTransformer.reverseTranform(userDto));
			savedUser.setClientId(null);
			return userTransformer.tranform(savedUser);
		}
		return null;
	}

	public UserDto updateUser(UserDto userDto, Long clientId) {
		log.info("UserController.updateUser() invoked.");
		com.pos.identity.domain.User user = userRepository.findByUsername(userDto.getUserName());
		if (user != null) {
			user.setClientId(userDto.getClientId());
			user.setFailedAttempt(userDto.getFailedAttempt());
			user.setClientId(clientId);
			user.setIsActive(userDto.getIsActive());
			user.setIsApproved(userDto.getIsApproved());
			user.setIsLocked(userDto.getIsLocked());
			user.setIsTfaEnabled(userDto.getIsTfaEnabled());
			user.setIsVerified(Boolean.TRUE);
			user.setLockedDateTime(userDto.getLockedDateTime());
			user.setTfaCode(userDto.getTfaCode());
			user.setTfaExpTime(userDto.getTfaExpTime());
			user.setTfaDefaultType(userDto.getTfaDefaultType());
			user.setUsername(userDto.getUserName());
			UserType userType = new UserType();
			userType.setUserTypeId(userDto.getUserTypeDto().getUserTypeId());
			user.setUserTypeId(userType);
			if (userDto.getPassword() != null && !userDto.getPassword().equals("")) {
				user.setPassword(userDto.getPassword());
			}
			com.pos.identity.domain.User savedUser = userRepository.save(user);
			savedUser.setClientId(null);
			return userTransformer.tranform(savedUser);
		}
		return null;
	}

}
