package com.pos.inventory.repository.dao;

import java.util.List;
import com.pos.inventory.common.dto.CategoryDto;
import com.pos.inventory.repository.domain.Category;

public interface CategoryDao extends BaseDao<Category> {

	List<CategoryDto> getAllCategoryDetails();

	List<CategoryDto> getAllCategoryByBrand(Long brandId);

	CategoryDto saveCategory(CategoryDto categoryDto);

	List<CategoryDto> getAllCategoryByName(String categoryName);
}
