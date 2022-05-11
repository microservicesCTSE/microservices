package com.pos.inventory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.inventory.common.constants.ApplicationMessageConstants;
import com.pos.inventory.common.dto.CurrencyDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.service.CurrencyService;
import com.pos.inventory.service.BL.CurrencyServiceBL;
import com.pos.inventory.service.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	CurrencyServiceBL currencyServiceBL;

	@Override
	public ResponseDto getAllCurrencyDeatails() {
		log.info("CurrencyServiceImpl.getAllCurrencyDeatails() invoked");
		ResponseDto responseDto = null;
		try {
			List<CurrencyDto> currencyDtoList = currencyServiceBL.getAllCurrencyDetails();
			if (currencyDtoList != null && !currencyDtoList.isEmpty()) {
				log.info("Retrieve All currency Details.");
				responseDto = serviceUtil.getServiceResponse(currencyDtoList);
			} else {
				log.info("Unable to retrieve All currency details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_CURRENCY_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All currency details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_CURRENCY_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto saveCurrency(CurrencyDto currencyDto) {
		log.info("CurrencyServiceImpl.saveCurrency(CurrencyDto currencyDto) invoked");
		ResponseDto responseDto = null;
		try {
			CurrencyDto savedCurrencyDto = currencyServiceBL.saveCurrency(currencyDto);
			if (savedCurrencyDto != null) {
				log.info("Currency saved.");
				responseDto = serviceUtil.getServiceResponse(savedCurrencyDto);
			} else {
				log.info("Unable to save Currency.");
				responseDto = serviceUtil
						.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_CURRENCY);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving Currency.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_CURRENCY);
		}
		return responseDto;
	}

}
