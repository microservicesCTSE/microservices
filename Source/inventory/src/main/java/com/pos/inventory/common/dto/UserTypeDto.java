package com.pos.inventory.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserTypeDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userTypeId;
	private String userType;
	private String description;
	private Boolean isActive;

}
