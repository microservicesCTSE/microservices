package com.pos.inventory.repository.dao;

import java.util.List;
import com.pos.inventory.common.dto.CurrencyDto;

public interface CurrencyDao {
	List<CurrencyDto> getAllCurrencyDetails();

	CurrencyDto saveCurrency(CurrencyDto currencyDto);
}
