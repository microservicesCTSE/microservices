/**
 * 
 */
package com.pos.inventory.repository.transfomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.LowStockQuantityDto;
import com.pos.inventory.repository.domain.LowStockQuantity;

/**
 * @author MDev
 *
 */
@Component
public class LowStockQuantityTransformer implements BaseTransformer<LowStockQuantity, LowStockQuantityDto> {

	@Override
	public LowStockQuantityDto transform(LowStockQuantity lowStockQuantity) {
		LowStockQuantityDto lowStockQuantityDto = null;
		if (lowStockQuantity != null) {
			lowStockQuantityDto = new LowStockQuantityDto();
			lowStockQuantityDto.setLowStockQuantityId(lowStockQuantity.getLowStockQuantityId());
			lowStockQuantityDto.setIsLowStock(lowStockQuantity.getIsLowStock());
			lowStockQuantityDto.setStockId(lowStockQuantity.getStockId());
			lowStockQuantityDto.setSupplierId(lowStockQuantity.getSupplierId());

			/*
			 * if (lowStockQuantity.getStockId() != null) {
			 * lowStockQuantityDto.setStockDto(stockTransformer.transform(lowStockQuantity.
			 * getStockId())); } if (lowStockQuantity.getSupplierId() != null) {
			 * lowStockQuantityDto.setSupplierDto(supplierTransformer.transform(
			 * lowStockQuantity.getSupplierId())); }
			 */
		}
		return lowStockQuantityDto;
	}

	@Override
	public LowStockQuantity reverseTransform(LowStockQuantityDto lowStockQuantityDto) {
		LowStockQuantity lowStockQuantity = null;
		if (lowStockQuantityDto != null) {
			lowStockQuantity = new LowStockQuantity();
			lowStockQuantity.setLowStockQuantityId(lowStockQuantityDto.getLowStockQuantityId());
			lowStockQuantity.setIsLowStock(lowStockQuantityDto.getIsLowStock());
			lowStockQuantity.setStockId(lowStockQuantityDto.getStockId());
			lowStockQuantity.setSupplierId(lowStockQuantityDto.getSupplierId());

			/*
			 * if (lowStockQuantityDto.getStockDto() != null) {
			 * lowStockQuantity.setStockId(stockTransformer.reverseTransform(
			 * lowStockQuantityDto.getStockDto())); } if
			 * (lowStockQuantityDto.getSupplierDto() != null) {
			 * lowStockQuantity.setSupplierId(supplierTransformer.reverseTransform(
			 * lowStockQuantityDto.getSupplierDto())); }
			 */
		}
		return lowStockQuantity;
	}

}
