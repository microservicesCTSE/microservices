package com.pos.inventory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.inventory.common.constants.ApplicationMessageConstants;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.common.dto.SubCategoryDto;
import com.pos.inventory.service.SubCategoryService;
import com.pos.inventory.service.BL.SubCategoryServiceBL;
import com.pos.inventory.service.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	SubCategoryServiceBL subCategoryServiceBL;

	@Autowired
	ServiceUtil serviceUtil;

	@Override
	public ResponseDto getAllSubCategoryDeatails(Long categoryId) {
		log.info("SubCategoryServiceImpl.getAllSubCategoryDeatails() invoked");
		ResponseDto responseDto = null;
		try {
			List<SubCategoryDto> subCategoryDtoList = subCategoryServiceBL.getAllCSubCategoryDetails(categoryId);
			if (subCategoryDtoList != null && !subCategoryDtoList.isEmpty()) {
				log.info("Retrieve All SubCategory Details.");
				responseDto = serviceUtil.getServiceResponse(subCategoryDtoList);
			} else {
				log.info("Unable to retrieve All SubCategory details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_SUBCATEGORY_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All SubCategory details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_SUBCATEGORY_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto saveSubCategory(SubCategoryDto subCategoryDto) {
		log.info("SubCategoryServiceImpl.saveSubCategory(SubCategoryDto subCategoryDto) invoked");
		ResponseDto responseDto = null;
		try {
			SubCategoryDto savedSubCategoryDto = subCategoryServiceBL.saveSubCategory(subCategoryDto);
			if (savedSubCategoryDto != null) {
				log.info("Subcategory saved.");
				responseDto = serviceUtil.getServiceResponse(savedSubCategoryDto);
			} else {
				log.info("Unable to save subCategory.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_SUB_CATEGORY);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving subCategory.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_SUB_CATEGORY);
		}
		return responseDto;
	}

}
