package com.pos.inventory.repository.transfomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.CategoryDto;
import com.pos.inventory.repository.domain.Category;


@Component
public class CategoryTransformer implements BaseTransformer<Category, CategoryDto> {

	@Autowired
	BrandTransformer brandTransformer;
	
	@Override
	public CategoryDto transform(Category category) {
		CategoryDto categoryDto = null;
		if (category != null) {
			categoryDto = new CategoryDto();
			categoryDto.setCategoryId(category.getCategoryId());
			categoryDto.setCategoryName(category.getCategoryName());
			categoryDto.setCategoryDescription(category.getCategoryDescription());
			categoryDto.setIsActive(category.getIsActive());
			if(category.getBrandId() != null) {
				categoryDto.setBrandDto(brandTransformer.transform(category.getBrandId()));
			}
		}
		return categoryDto;
	}

	@Override
	public Category reverseTransform(CategoryDto categoryDto) {
		Category category = null;
		if (categoryDto != null) {
			category = new Category();
			category.setCategoryId(categoryDto.getCategoryId());
			category.setCategoryName(categoryDto.getCategoryName());
			category.setCategoryDescription(categoryDto.getCategoryDescription());
			category.setIsActive(categoryDto.getIsActive());
			if(categoryDto.getBrandDto() != null) {
				category.setBrandId(brandTransformer.reverseTransform(categoryDto.getBrandDto()));
			}
		}
		return category;
	}

}
