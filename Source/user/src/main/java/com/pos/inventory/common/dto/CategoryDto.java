package com.pos.inventory.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategoryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long categoryId;
	private String categoryName;
	private String categoryDescription;
	private Boolean isActive;
	private BrandDto brandDto;
}
