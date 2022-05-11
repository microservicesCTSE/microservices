package com.pos.inventory.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BankDto  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long bankId;
	private String bankName;
	private String location;
	private Boolean isActive;
	
}
