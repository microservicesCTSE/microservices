package com.pos.inventory.service.BL;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.inventory.common.dto.CurrencyDto;
import com.pos.inventory.repository.dao.CurrencyDao;
import com.pos.inventory.service.client.AlgoriolFeignClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CurrencyServiceBL {
	@Autowired
	CurrencyDao currencyDao;

	@Autowired
	AlgoriolFeignClient algoriolFeignClient;

	public List<CurrencyDto> getAllCurrencyDetails() {
		log.info("CurrencyServiceBL.getAllCurrencyDetails() invoked");
		return currencyDao.getAllCurrencyDetails();

	}

	public CurrencyDto saveCurrency(CurrencyDto currencyDto) {
		log.info("CurrencyServiceBL.saveCurrency() invoked.");
		CurrencyDto currency = currencyDao.saveCurrency(currencyDto);
//		if (currency != null) {
//			algoriolFeignClient.saveCurrency(currencyDto);
//		}
		return currency;
	}
}
