package com.pos.identity.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

/**
 * @author Thilina
 *
 */
@Data
public class UserLoginDetailsDto implements Serializable {

	private static final long serialVersionUID = 3567676183234444532L;
	private Long userLoginDetailsId;
	private String username;
	private String userIp;
	private LocalDate loginDate;
	private LocalTime loginTime;
	private Boolean isSuccess;
	private Integer invalidLoginCount;
}
