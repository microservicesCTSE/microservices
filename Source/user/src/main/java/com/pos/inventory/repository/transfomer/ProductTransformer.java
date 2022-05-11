package com.pos.inventory.repository.transfomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.ProductDto;
import com.pos.inventory.repository.domain.Product;

@Component
public class ProductTransformer implements BaseTransformer<Product, ProductDto> {

	@Autowired
	BarcodeTransformer barcodeTransformer;
	@Autowired
	BrandTransformer brandTransformer;
	@Autowired
	CategoryTransformer categoryTransformer;
	@Autowired
	CountryTransformer countryTransformer;
	@Autowired
	CurrencyTransformer currencyTransformer;
	@Autowired
	MeasurementUnitTransformer measurementUnitTransformer;
	@Autowired
	SubCategoryTransformer subCategoryTransformer;
	@Autowired
	BranchTransformer branchTransformer;
	@Autowired
	UserTransformer userTransformer;
	@Autowired
	SupplierTransformer supplierTransformer;

	@Override
	public ProductDto transform(Product product) {
		ProductDto productDto = null;
		if (product != null) {
			productDto = new ProductDto();
			productDto.setProductId(product.getProductId());
			if (product.getUserId() != null) {
				productDto.setUserDto(userTransformer.transform(product.getUserId()));
			}
			productDto.setQuantity(product.getQuantity());
			productDto.setDiscount(product.getDiscount());
			productDto.setUpdatedDate(product.getUpdatedDate());
			productDto.setUpdatedTime(product.getUpdatedTime());
			productDto.setIsPercentage(product.getIsPercentage());
			if (product.getSupplierId() != null) {
				productDto.setSupplierDto(supplierTransformer.transform(product.getSupplierId()));
			}
			productDto.setExpiryDate(product.getExpiryDate());
//			if (product.getBarcodeId() != null) {
//				productDto.setBarcodeDto(barcodeTransformer.transform(product.getBarcodeId()));
//			}
			productDto.setBarcodeDigits(product.getBarcodeDigits());
			if (product.getBrandId() != null) {
				productDto.setBrandDto(brandTransformer.transform(product.getBrandId()));
			}
			if (product.getBranchId() != null) {
				productDto.setBranchDto(branchTransformer.transform(product.getBranchId()));
			}
			if (product.getCategoryId() != null) {
				productDto.setCategoryDto(categoryTransformer.transform(product.getCategoryId()));
			}
			productDto.setComments(product.getComments());
			if (product.getBranchId() != null) {
				productDto.setBranchDto(branchTransformer.transform(product.getBranchId()));
			}
			if (product.getCurrencyId() != null) {
				productDto.setCurrencyDto(currencyTransformer.transform(product.getCurrencyId()));
			}
			productDto.setDescription(product.getDescription());
			productDto.setGrossWeight(product.getGrossWeight());
			productDto.setIsActive(product.getIsActive());
			productDto.setItemCode(product.getItemCode());
			productDto.setMaximumStock(product.getMaximumStock());
			if (product.getUnitId() != null) {
				productDto.setMeasurementUnitDto(measurementUnitTransformer.transform(product.getUnitId()));
			}
			productDto.setMinimumStock(product.getMinimumStock());
			productDto.setNetWeight(product.getNetWeight());
			productDto.setPluCode(product.getPluCode());
			productDto.setProductName(product.getProductName());
			productDto.setRetailPrice(product.getRetailPrice());
			productDto.setStockLevel(product.getStockLevel());
			if (product.getSubCategoryId() != null) {
				productDto.setSubCategoryDto(subCategoryTransformer.transform(product.getSubCategoryId()));
			}
			productDto.setUnitPrice(product.getUnitPrice());
//			if (product.getUserId() != null) {
//				productDto.setUserDto(product.getUserId());			
//			}
		}
		return productDto;
	}

	@Override
	public Product reverseTransform(ProductDto productDto) {
		Product product = null;
		if (productDto != null) {
			product = new Product();
			product.setProductId(productDto.getProductId());
			product.setDescription(productDto.getDescription());
			product.setGrossWeight(productDto.getGrossWeight());
			product.setIsActive(productDto.getIsActive());
			product.setItemCode(productDto.getItemCode());
			product.setMaximumStock(productDto.getMaximumStock());
			product.setMinimumStock(productDto.getMinimumStock());
			product.setNetWeight(productDto.getNetWeight());
			product.setPluCode(productDto.getPluCode());
			product.setProductName(productDto.getProductName());
			product.setRetailPrice(productDto.getRetailPrice());
			product.setStockLevel(productDto.getStockLevel());
			product.setUnitPrice(productDto.getUnitPrice());
			if (productDto.getUserDto() != null) {
				product.setUserId(userTransformer.reverseTransform(productDto.getUserDto()));
			}
			product.setComments(productDto.getComments());
			product.setQuantity(productDto.getQuantity());
			product.setDiscount(productDto.getDiscount());
			product.setUpdatedDate(productDto.getUpdatedDate());
			product.setUpdatedTime(productDto.getUpdatedTime());
			product.setIsPercentage(productDto.getIsPercentage());
			if (productDto.getSupplierDto() != null) {
				product.setSupplierId(supplierTransformer.reverseTransform(productDto.getSupplierDto()));
			}
			product.setExpiryDate(productDto.getExpiryDate());
//			if (productDto.getBarcodeDto() != null) {
//				product.setBarcodeId(barcodeTransformer.reverseTransform(productDto.getBarcodeDto()));
//			}
			product.setBarcodeDigits(productDto.getBarcodeDigits());
			if (productDto.getBrandDto() != null) {
				product.setBrandId(brandTransformer.reverseTransform(productDto.getBrandDto()));
			}
			if (productDto.getCategoryDto() != null) {
				product.setCategoryId(categoryTransformer.reverseTransform(productDto.getCategoryDto()));
			}
			if (productDto.getCurrencyDto() != null) {
				product.setCurrencyId(currencyTransformer.reverseTransform(productDto.getCurrencyDto()));
			}
			if (productDto.getBranchDto() != null) {
				product.setBranchId(branchTransformer.reverseTransform(productDto.getBranchDto()));
			}
			if (productDto.getSubCategoryDto() != null) {
				product.setSubCategoryId(subCategoryTransformer.reverseTransform(productDto.getSubCategoryDto()));
			}
			if (productDto.getMeasurementUnitDto() != null) {
				product.setUnitId(measurementUnitTransformer.reverseTransform(productDto.getMeasurementUnitDto()));
			}
//			if (productDto.getUserDto() != null) {
//				product.setUserId(productDto.getUserDto());				
//			}

		}
		return product;
	}

}
