package com.pos.inventory.service.BL;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.inventory.common.dto.SubCategoryDto;
import lombok.extern.slf4j.Slf4j;
import com.pos.inventory.repository.dao.SubCategoryDao;
import com.pos.inventory.service.client.AlgoriolFeignClient;

@Service
@Slf4j
public class SubCategoryServiceBL {
	@Autowired
	SubCategoryDao SubCategoryDao;

	@Autowired
	AlgoriolFeignClient algoriolFeignClient;

	public List<SubCategoryDto> getAllCSubCategoryDetails(Long categoryId) {
		log.info("SubCategoryServiceBL.getAllCSubCategoryDetails() invoked");
		return SubCategoryDao.getAllSubCategoryDetails(categoryId);

	}

	public SubCategoryDto saveSubCategory(SubCategoryDto subCategoryDto) {
		log.info("SubCategoryServiceBL.saveSubCategory() invoked.");
		SubCategoryDto sub = SubCategoryDao.saveSubCategory(subCategoryDto);
//		if (sub != null) {
//			algoriolFeignClient.saveSubCategory(subCategoryDto);
//		}
		return sub;
	}
}
