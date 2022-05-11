package com.pos.inventory.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class TaxDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Long taxId;
	private String taxName;
	private Double taxFees;
	private Boolean isActive;
	private Double taxPercentage;
	private Double netProfit;
	private Double totalProfit;
	private Boolean isPaid;
	private CategoryDto categoryDto;
}
