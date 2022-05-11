package com.pos.inventory.repository.dao;

import java.util.List;
import com.pos.inventory.common.dto.SubCategoryDto;

public interface SubCategoryDao {
	List<SubCategoryDto> getAllSubCategoryDetails(Long categoryId);

	SubCategoryDto saveSubCategory(SubCategoryDto subCSategoryDto);
}
