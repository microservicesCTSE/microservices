package com.pos.inventory.repository.dao;

import java.util.List;
import com.pos.inventory.common.dto.BrandDto;
import com.pos.inventory.repository.domain.Brand;

public interface BrandDao extends BaseDao<Brand> {
	List<BrandDto> getAllBrandDetails();

	BrandDto saveBrand(BrandDto brandDto);

}
