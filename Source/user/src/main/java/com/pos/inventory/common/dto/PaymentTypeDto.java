package com.pos.inventory.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PaymentTypeDto implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Long paymentTypeId;
	private String paymentTypeName;
	private Boolean isActive;

}
