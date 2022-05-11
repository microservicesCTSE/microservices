package com.pos.inventory.service;

import com.pos.inventory.common.dto.CurrencyDto;
import com.pos.inventory.common.dto.ResponseDto;

public interface CurrencyService {
	ResponseDto getAllCurrencyDeatails();

	ResponseDto saveCurrency(CurrencyDto currencyDto);
}
