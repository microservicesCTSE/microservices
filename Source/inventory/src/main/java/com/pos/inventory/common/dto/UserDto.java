package com.pos.inventory.common.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String fullName;
	private String userName;
	private String password;
	private String email;
	private Integer contactNo;
	private String country;
	private Boolean isApproved;
	private LocalDate approvedDate;
	private Boolean isLocked;
	private LocalDateTime lockedDateTime;
	private String verifyToken;
	private Boolean isActive;
	private Boolean isNewUser;
	private UserTypeDto userTypeDto;
	private ClientDto client_id;
	private RoleDto roleId;

}
