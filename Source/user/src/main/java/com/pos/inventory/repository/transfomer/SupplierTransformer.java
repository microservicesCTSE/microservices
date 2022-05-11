package com.pos.inventory.repository.transfomer;

import org.springframework.stereotype.Component;
import com.pos.inventory.common.dto.SupplierDto;
import com.pos.inventory.repository.domain.Supplier;

@Component
public class SupplierTransformer implements BaseTransformer<Supplier, SupplierDto> {

//	@Autowired
//	UserTransformer userTransformer;

	@Override
	public SupplierDto transform(Supplier supplier) {
		SupplierDto supplierDto = null;
		if (supplier != null) {
			supplierDto = new SupplierDto();
			supplierDto.setSupplierId(supplier.getSupplierId());
			supplierDto.setSupplierName(supplier.getSupplierName());
			supplierDto.setPhoneNumber(supplier.getPhoneNumber());
			supplierDto.setAddress(supplier.getAddress());
			supplierDto.setCity(supplier.getCity());
			supplierDto.setZipCode(supplier.getZipCode());
			supplierDto.setEmail(supplier.getEmail());
			supplierDto.setComment(supplier.getComment());
			supplierDto.setComment(supplier.getComment());
			supplierDto.setIsActive(supplier.getIsActive());
			supplierDto.setAddedDate(supplier.getAddedDate());
			supplierDto.setCompanyName(supplier.getCompanyName());
			supplierDto.setSupplierRefNumber(supplier.getSupplierRefNumber());
			supplierDto.setUserID(supplier.getUserId());
		}
		return supplierDto;
	}

	@Override
	public Supplier reverseTransform(SupplierDto supplierDto) {
		Supplier supplier = null;
		if (supplierDto != null) {
			supplier = new Supplier();
			supplier.setSupplierId(supplierDto.getSupplierId());
			supplier.setSupplierName(supplierDto.getSupplierName());
			supplier.setPhoneNumber(supplierDto.getPhoneNumber());
			supplier.setAddress(supplierDto.getAddress());
			supplier.setCity(supplierDto.getCity());
			supplier.setZipCode(supplierDto.getZipCode());
			supplier.setEmail(supplierDto.getEmail());
			supplier.setComment(supplierDto.getComment());
			supplier.setComment(supplierDto.getComment());
			supplier.setIsActive(supplierDto.getIsActive());
			supplier.setAddedDate(supplierDto.getAddedDate());
			supplier.setCompanyName(supplierDto.getCompanyName());
			supplier.setSupplierRefNumber(supplierDto.getSupplierRefNumber());
			supplier.setUserId(supplierDto.getUserID());
		}
		return supplier;
	}

}
