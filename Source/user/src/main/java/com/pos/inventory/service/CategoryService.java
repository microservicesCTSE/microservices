package com.pos.inventory.service;

import com.pos.inventory.common.dto.CategoryDto;
import com.pos.inventory.common.dto.ResponseDto;

public interface CategoryService {

	ResponseDto getAllCategoryDeatails();

	ResponseDto getAllCategoryByBrand(Long brandId);

	ResponseDto saveCategory(CategoryDto categoryDto);

	ResponseDto getAllCategoryByName(String categoryName);

}
