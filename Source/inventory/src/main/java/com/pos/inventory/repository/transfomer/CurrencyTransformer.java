package com.pos.inventory.repository.transfomer;

import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.CurrencyDto;
import com.pos.inventory.repository.domain.Currency;


@Component
public class CurrencyTransformer implements BaseTransformer<Currency, CurrencyDto>{

	@Override
	public CurrencyDto transform(Currency currency) {
		CurrencyDto currencyDto = null;
		if (currency != null) {
			currencyDto = new CurrencyDto();
			currencyDto.setCurrencyId(currency.getCurrencyId());
			currencyDto.setCurrencyCode(currency.getCurrencyCode());
			currencyDto.setCurrencyName(currency.getCurrencyName());
			currencyDto.setIsActive(currency.getIsActive());
		}
		return currencyDto;
	}

	@Override
	public Currency reverseTransform(CurrencyDto currencyDto) {
		Currency currency = null;
		if (currencyDto != null) {
			currency = new Currency();
			currency.setCurrencyId(currencyDto.getCurrencyId());
			currency.setCurrencyCode(currencyDto.getCurrencyCode());
			currency.setCurrencyName(currencyDto.getCurrencyName());
			currency.setIsActive(currencyDto.getIsActive());
		}
		return currency;
	}

}
