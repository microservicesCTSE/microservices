package com.pos.inventory.common.dto;

import java.io.Serializable;



import lombok.Data;

@Data
public class SubCategoryDto implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long subCategoryId;
	private String subCategoryName;
	private Boolean isActive;
	private CategoryDto categoryDto;

}
