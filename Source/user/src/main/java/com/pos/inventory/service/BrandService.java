package com.pos.inventory.service;

import com.pos.inventory.common.dto.BrandDto;
import com.pos.inventory.common.dto.ResponseDto;

public interface BrandService {

	ResponseDto getAllBrandDetails();

	ResponseDto saveBrand(BrandDto brandDto);
}
