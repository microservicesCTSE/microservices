package com.pos.inventory.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BarcodeDto implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 private Long barcodeId;
	 private String barcodeDigits;
	 private Boolean isActive;

}
