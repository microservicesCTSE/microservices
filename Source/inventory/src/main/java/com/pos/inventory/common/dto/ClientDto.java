package com.pos.inventory.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ClientDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long clientId;
	private String firstName;
	private String lastName;
	private Boolean isActive;
	private String logo;
	private String encodedString;
}