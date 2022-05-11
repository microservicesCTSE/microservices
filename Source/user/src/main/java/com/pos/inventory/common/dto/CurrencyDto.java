package com.pos.inventory.common.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * Currency DTO
 * @author Thilina Madhusanka
 */
@Data
public class CurrencyDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long currencyId;
	private String currencyName;
	private String currencyCode;
	private Boolean isActive;
    
}
