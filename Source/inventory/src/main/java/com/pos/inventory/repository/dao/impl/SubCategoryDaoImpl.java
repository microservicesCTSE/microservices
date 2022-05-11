package com.pos.inventory.repository.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.pos.inventory.common.dto.SubCategoryDto;
import com.pos.inventory.repository.dao.SubCategoryDao;
import com.pos.inventory.repository.domain.SubCategory;
import com.pos.inventory.repository.transfomer.SubCategoryTransformer;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SubCategoryDaoImpl extends BaseDaoImpl<SubCategory> implements SubCategoryDao {

	@Autowired
	SubCategoryTransformer subCategoryTransformer;

	@Transactional
	@Override
	public List<SubCategoryDto> getAllSubCategoryDetails(Long categoryId) {
		log.info("SubCategoryDaoImpl.getAllSubCategoryDetails() invoked");
		Criteria criteria = getCurrentSession().createCriteria(SubCategory.class, "SubCategory");
		criteria.createAlias("SubCategory.categoryId", "Category");
		if (categoryId != null) {
			criteria.add(Restrictions.eq("Category.categoryId", categoryId));
		}
		List<SubCategoryDto> subCategoryDtoList = null;
		List<SubCategory> subCategoryList = (List<SubCategory>) criteria.list();
		if (subCategoryList != null && !subCategoryList.isEmpty()) {
			subCategoryDtoList = new ArrayList<>();
			for (SubCategory subCategory : subCategoryList) {
				subCategoryDtoList.add(subCategoryTransformer.transform(subCategory));
			}
		}
		return subCategoryDtoList;
	}

	@Transactional
	@Override
	public SubCategoryDto saveSubCategory(SubCategoryDto subCategoryDto) {
		log.info("subCategoryDaoImpl.saveCategory() invoked.");
//		Criteria criteria = getCurrentSession().createCriteria(SubCategory.class, "SubCategory");
//		criteria.add(Restrictions.eq("SubCategory.subCategoryName", subCategoryDto.getSubCategoryName()));
//		List<SubCategory> subCategoryList = (List<SubCategory>) criteria.list();
//		if (!(subCategoryList != null && subCategoryList.size() > 0)) {
//			log.info("Size....." + (subCategoryList.size()));
			SubCategory subCategory = subCategoryTransformer.reverseTransform(subCategoryDto);
			SubCategory saveSubCategory = saveOrUpdate(subCategory);
			return subCategoryTransformer.transform(saveSubCategory);
//		}
//		return null;
	}

	/*
	 * public SubCategoryDto saveSubCategory(SubCategoryDto subCategoryDto) {
	 * log.info("SubCategoryDaoImpl.saveSubCategory() invoked."); SubCategory
	 * subCategory = subCategoryTransformer.reverseTransform(subCategoryDto);
	 * SubCategory saveSubCategory = saveOrUpdate(subCategory); return
	 * subCategoryTransformer.transform(saveSubCategory); }
	 */

}
