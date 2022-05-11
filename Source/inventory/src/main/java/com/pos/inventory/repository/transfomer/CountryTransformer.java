/**
 * 
 */
package com.pos.inventory.repository.transfomer;


import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.CountryDto;
import com.pos.inventory.repository.domain.Country;

//import com.poscore.domain.Currency;

/**
 * Transformer and Reverse Transformer of Country
 * 
 * @author Thilina Madhusanka
 */
@Component
public class CountryTransformer implements BaseTransformer<Country, CountryDto> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bankingcore.transformer.BaseTransformer#transform(java.lang.Object)
	 */

//	@Autowired
//	CurrencyTransformer currencyTransformer;

	@Override
	public CountryDto transform(Country country) {
		CountryDto countryDto = null;
		if (country != null) {
			countryDto = new CountryDto();
			countryDto.setCountryId(country.getCountryId());
			countryDto.setCountryName(country.getCountryName());
			countryDto.setReferenceCountryCode(country.getReferenceCountryCode());
			countryDto.setIsActive(country.getIsActive());
//			countryDto.setBankCodeLabel(country.getBankCodeLabel());
//			countryDto.setSwiftCodeLabel(country.getSwiftCodeLabel());
//			countryDto.setRoutingNumberLabel(country.getRoutingNumberLabel());
//			countryDto.setLabel4(country.getLabel4());
//			countryDto.setLabel5(country.getLabel5());
//			countryDto.setLabel6(country.getLabel6());
//			countryDto.setCountyCodeAlpha3(country.getCountyCodeAlpha3());
////			if (country.getCurrency() != null) {
//				if (country.getCurrency() != null && !country.getCurrency().isEmpty()) {
//					countryDto.setCurrencyDto(currencyTransformer.transform(country.getCurrency().get(0)));
//				}
//			}
		}
		return countryDto;
	}

	@Override
	public Country reverseTransform(CountryDto countryDto) {
		Country country = null;
//		List<Currency> currencyList = null;
		if (countryDto != null) {
			country = new Country();
			country.setCountryId(countryDto.getCountryId());
			country.setCountryName(countryDto.getCountryName());
			country.setReferenceCountryCode(countryDto.getReferenceCountryCode());
			country.setIsActive(countryDto.getIsActive());
//			country.setBankCodeLabel(countryDto.getBankCodeLabel());
//			country.setSwiftCodeLabel(countryDto.getSwiftCodeLabel());
//			country.setRoutingNumberLabel(countryDto.getRoutingNumberLabel());
//			country.setLabel4(countryDto.getLabel4());
//			country.setLabel5(countryDto.getLabel5());
//			country.setLabel6(countryDto.getLabel6());
////			if (countryDto.getCurrencyDto() != null) {
//				currencyList = new ArrayList<>();
//				currencyList.add(currencyTransformer.reverseTransform(countryDto.getCurrencyDto()));
//			}
//			country.setCurrency(currencyList);
		}
		return country;
	}

}
