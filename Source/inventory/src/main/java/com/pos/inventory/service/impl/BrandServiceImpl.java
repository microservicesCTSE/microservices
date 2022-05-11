package com.pos.inventory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.inventory.common.constants.ApplicationMessageConstants;
import com.pos.inventory.common.dto.BrandDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.service.BrandService;
import com.pos.inventory.service.BL.BrandServiceBL;
import com.pos.inventory.service.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {
	@Autowired
	BrandServiceBL brandServiceBL;

	@Autowired
	ServiceUtil serviceUtil;

	@Override
	public ResponseDto getAllBrandDetails() {
		log.info("BrandServiceImpl.getAllBrandDetails() invoked");
		ResponseDto responseDto = null;
		try {
			List<BrandDto> brandDtoList = brandServiceBL.getAllBrandDetails();
			if (brandDtoList != null && !brandDtoList.isEmpty()) {
				log.info("Retrieve All Brand Details.");
				responseDto = serviceUtil.getServiceResponse(brandDtoList);
			} else {
				log.info("Unable to retrieve All Brand details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_BRAND_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Brand details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_BRAND_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto saveBrand(BrandDto brandDto) {
		log.info("BrandServiceImpl.saveBrand(BrandDto brandDto) invoked");
		ResponseDto responseDto = null;
		try {
			BrandDto savedBrandDto = brandServiceBL.saveBrand(brandDto);
			if (savedBrandDto != null) {
				log.info("Brand saved.");
				responseDto = serviceUtil.getServiceResponse(savedBrandDto);
			} else {
				log.info("Unable to save Brand.");
				responseDto = serviceUtil
						.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_BRAND);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Brand.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_BRAND);
		}
		return responseDto;
	}

}
