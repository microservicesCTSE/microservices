package com.pos.inventory.common.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class SupplierDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Long supplierId;
	private String supplierName;
	private Integer phoneNumber;
	private String address;
	private String city;
	private String zipCode;
	private String email;
	private String comment;
	private Boolean isActive;
	private LocalDate addedDate;
	private String supplierRefNumber;
	private String companyName;
	private Long userID;

}
