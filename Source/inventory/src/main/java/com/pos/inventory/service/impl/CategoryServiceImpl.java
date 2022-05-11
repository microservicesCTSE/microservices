package com.pos.inventory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.inventory.common.constants.ApplicationMessageConstants;
import com.pos.inventory.common.dto.CategoryDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.service.CategoryService;
import com.pos.inventory.service.BL.CategoryServiceBL;
import com.pos.inventory.service.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryServiceBL categoryServiceBL;

	@Autowired
	ServiceUtil serviceUtil;

	@Override
	public ResponseDto getAllCategoryDeatails() {
		log.info("CategoryServiceImpl.getAllCategoryDeatails() invoked");
		ResponseDto responseDto = null;
		try {
			List<CategoryDto> categoryDtoList = categoryServiceBL.getAllCategoryDetails();
			if (categoryDtoList != null && !categoryDtoList.isEmpty()) {
				log.info("Retrieve All Category Details.");
				responseDto = serviceUtil.getServiceResponse(categoryDtoList);
			} else {
				log.info("Unable to retrieve All Category details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_CATEGORY_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Category details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_CATEGORY_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto getAllCategoryByBrand(Long brandId) {
		log.info("CategoryServiceImpl.getAllCategoryByBrand() invoked");
		ResponseDto responseDto = null;
		try {
			List<CategoryDto> categoryDtoList = categoryServiceBL.getAllCategoryByBrand(brandId);
			if (categoryDtoList != null && !categoryDtoList.isEmpty()) {
				log.info("Category Details retrive by Brand Id.");
				responseDto = serviceUtil.getServiceResponse(categoryDtoList);
			} else {
				log.info("Unable to retrive Category details by Brand Id.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_CATEGORY_DETAILS_BY_BRAND_ID);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retriving Category details by Brand Id.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_CATEGORY_DETAILS_BY_BRAND_ID);
		}
		return responseDto;
	}

	@Override
	public ResponseDto saveCategory(CategoryDto categoryDto) {
		log.info("CategoryServiceImpl.saveCategory(CategoryDto categoryDto) invoked");
		ResponseDto responseDto = null;
		try {
			CategoryDto savedcategoryDto = categoryServiceBL.saveCategory(categoryDto);
			if (savedcategoryDto != null) {
				log.info("category saved.");
				responseDto = serviceUtil.getServiceResponse(savedcategoryDto);
			} else {
				log.info("Unable to save category.");
				responseDto = serviceUtil
						.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_CATEGORY);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving category.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_CATEGORY);
		}
		return responseDto;
	}

	@Override
	public ResponseDto getAllCategoryByName(String categoryName) {
		log.info("CategoryServiceImpl.getAllCategoryByName() invoked");
		ResponseDto responseDto = null;
		try {
			List<CategoryDto> categoryDtoList = categoryServiceBL.getAllCategoryByName(categoryName);
			if (categoryDtoList != null && !categoryDtoList.isEmpty()) {
				log.info("Category Details retrive by name.");
				responseDto = serviceUtil.getServiceResponse(categoryDtoList);
			} else {
				log.info("Unable to retrive Category details by name.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_CATEGORY_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retriving Category details by name.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_CATEGORY_DETAILS);
		}
		return responseDto;
	}

}
