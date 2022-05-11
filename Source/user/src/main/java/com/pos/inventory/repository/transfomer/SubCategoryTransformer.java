package com.pos.inventory.repository.transfomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.SubCategoryDto;
import com.pos.inventory.repository.domain.SubCategory;


@Component
public class SubCategoryTransformer implements BaseTransformer<SubCategory, SubCategoryDto> {

	@Autowired
	CategoryTransformer categoryTransformer;

	@Override
	public SubCategoryDto transform(SubCategory subCategory) {
		SubCategoryDto subCategoryDto = null;
		if (subCategory != null) {
			subCategoryDto = new SubCategoryDto();
			subCategoryDto.setSubCategoryId(subCategory.getSubCategoryId());
			subCategoryDto.setSubCategoryName(subCategory.getSubCategoryName());
			subCategoryDto.setIsActive(subCategory.getIsActive());
			if (subCategory.getCategoryId() != null) {		
				subCategoryDto.setCategoryDto(categoryTransformer.transform(subCategory.getCategoryId()));
			}

		}
		return subCategoryDto;
	}

	@Override
	public SubCategory reverseTransform(SubCategoryDto subCategoryDto) {
		SubCategory subCategory = null;
		if (subCategoryDto != null) {
			subCategory = new SubCategory();
			subCategory.setSubCategoryId(subCategoryDto.getSubCategoryId());
			subCategory.setSubCategoryName(subCategoryDto.getSubCategoryName());
			subCategory.setIsActive(subCategoryDto.getIsActive());
			if (subCategoryDto.getCategoryDto() != null) {
				subCategory.setCategoryId(categoryTransformer.reverseTransform(subCategoryDto.getCategoryDto()));				
			}
		}
		return subCategory;
	}

}
