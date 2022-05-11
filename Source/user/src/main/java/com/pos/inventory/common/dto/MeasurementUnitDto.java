package com.pos.inventory.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MeasurementUnitDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long unitId;
	private String unitCode;
	private String unitDescription;
	private Boolean isActive;

}
