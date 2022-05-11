package com.pos.inventory.service;

import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.common.dto.SubCategoryDto;

public interface SubCategoryService {

	ResponseDto getAllSubCategoryDeatails(Long categoryId);

	ResponseDto saveSubCategory(SubCategoryDto subCategoryDto);

}
