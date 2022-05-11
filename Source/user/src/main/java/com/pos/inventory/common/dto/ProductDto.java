package com.pos.inventory.common.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;

import lombok.Data;

@Data
public class ProductDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long productId;
	private String productName;
	private String description;
	private Double unitPrice;
	private Double retailPrice;
	private Integer stockLevel;
	private Integer minimumStock;
	private Integer maximumStock;
	private String comments;
	private Double netWeight;
	private Double grossWeight;
	private Boolean isActive;
	private String pluCode;
	private String itemCode;
	private String barcodeDigits;
//	private BarcodeDto barcodeDto;
	private BrandDto brandDto;
	private CategoryDto categoryDto;
	private BranchDto branchDto;
	private CurrencyDto currencyDto;
	private MeasurementUnitDto measurementUnitDto;
	private SubCategoryDto subCategoryDto;
	private UserDto userDto;
	private Integer quantity;
	private Double discount;
	private LocalDate updatedDate;
	private LocalTime updatedTime;
	private Boolean isPercentage;
	private SupplierDto supplierDto;
	private LocalDate expiryDate;
	private String consumerKey;
	private String consumerSecret;
	private Integer sales;
}
