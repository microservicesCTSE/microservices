package com.pos.inventory.repository.transfomer;

import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.BrandDto;
import com.pos.inventory.repository.domain.Brand;



@Component
public class BrandTransformer implements BaseTransformer<Brand, BrandDto>{

	@Override
	public BrandDto transform(Brand brand) {
		BrandDto brandDto = null;
		if (brand != null) {
			brandDto = new BrandDto();
			brandDto.setBrandId(brand.getBrandId());
			brandDto.setBrandName(brand.getBrandName());
			brandDto.setIsActive(brand.getIsActive());
			
		}
		return brandDto;
	}

	@Override
	public Brand reverseTransform(BrandDto brandDto) {
		Brand brand =null;
		if (brandDto != null) {
			brand = new Brand();
			brand.setBrandId(brandDto.getBrandId());
			brand.setBrandName(brandDto.getBrandName());
			brand.setIsActive(brandDto.getIsActive());
			
		}
		return brand;
	}

}
