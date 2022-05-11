package com.pos.inventory.repository.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.pos.inventory.common.dto.CategoryDto;
import com.pos.inventory.repository.dao.CategoryDao;
import com.pos.inventory.repository.domain.Category;
import com.pos.inventory.repository.transfomer.CategoryTransformer;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {

	@Autowired
	CategoryTransformer categoryTransformer;

	@Transactional

	@Override
	public List<CategoryDto> getAllCategoryDetails() {
		log.info("CategoryDaoImpl.getAllCategoryDetails() invoked");
		Criteria criteria = getCurrentSession().createCriteria(Category.class);
		List<CategoryDto> categoryDtoList = null;
		List<Category> categoryList = (List<Category>) criteria.list();
		if (categoryList != null && !categoryList.isEmpty()) {
			categoryDtoList = new ArrayList<>();
			for (Category category : categoryList) {
				categoryDtoList.add(categoryTransformer.transform(category));
			}
		}
		return categoryDtoList;
	}

	@Override
	public List<CategoryDto> getAllCategoryByBrand(Long brandId) {
		log.info("CategoryDaoImpl.getAllCategoryByBrand() invoked");
		Criteria criteria = getCurrentSession().createCriteria(Category.class, "Category");
		criteria.createAlias("Category.brandId", "Brand");
		if (brandId != null) {
			criteria.add(Restrictions.eq("Brand.brandId", brandId));
		}
		List<CategoryDto> categoryDtoList = null;
		List<Category> categoryList = (List<Category>) criteria.list();
		if (categoryList != null && !categoryList.isEmpty()) {
			categoryDtoList = new ArrayList<>();
			for (Category category : categoryList) {
				categoryDtoList.add(categoryTransformer.transform(category));
			}
		}
		return categoryDtoList;
	}

	@Transactional
	@Override
	public CategoryDto saveCategory(CategoryDto categoryDto) {
		log.info("CategoryDaoImpl.saveCategory() invoked.");
		Criteria criteria = getCurrentSession().createCriteria(Category.class, "Category");
		criteria.add(Restrictions.eq("Category.categoryName", categoryDto.getCategoryName()));
		List<Category> categoryList = (List<Category>) criteria.list();
		if (!(categoryList != null && categoryList.size() > 0)) {
			log.info("Size....." + (categoryList.size()));
			Category category = categoryTransformer.reverseTransform(categoryDto);
			Category saveCategory = saveOrUpdate(category);
			return categoryTransformer.transform(saveCategory);
		}
		return null;
	}

	@Override
	public List<CategoryDto> getAllCategoryByName(String categoryName) {
		log.info("CategoryDaoImpl.getAllCategoryByName() invoked");
		Criteria criteria = getCurrentSession().createCriteria(Category.class, "Category");
		if (categoryName != null) {
			criteria.add(Restrictions.eq("Category.categoryName", categoryName));
		}
		List<CategoryDto> categoryDtoList = null;
		List<Category> categoryList = (List<Category>) criteria.list();
		if (categoryList != null && !categoryList.isEmpty()) {
			categoryDtoList = new ArrayList<>();
			for (Category category : categoryList) {
				categoryDtoList.add(categoryTransformer.transform(category));
			}
		}
		return categoryDtoList;
	}

}
