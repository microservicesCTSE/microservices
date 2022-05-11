package com.pos.inventory.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BrandDto  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long brandId;
	private String brandName;
	private Boolean isActive;


}
