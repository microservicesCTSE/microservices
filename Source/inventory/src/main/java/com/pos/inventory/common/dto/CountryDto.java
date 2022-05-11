package com.pos.inventory.common.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * Country DTO
 * 
 * @author Thilina Madhusanka
 */
@Data
public class CountryDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long countryId;
	private String countryName;
	private String referenceCountryCode;
	private Boolean isActive;
	
}
