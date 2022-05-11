package com.pos.inventory.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class RoleDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long roleId;
	private String roleName;
	private String roleDescription;
	private Boolean isActive;

}