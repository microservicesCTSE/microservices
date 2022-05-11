package com.pos.inventory.repository.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.pos.inventory.common.dto.CurrencyDto;
import com.pos.inventory.repository.dao.CurrencyDao;
import com.pos.inventory.repository.domain.Currency;
import com.pos.inventory.repository.transfomer.CurrencyTransformer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CurrencyDaoImpl extends BaseDaoImpl<Currency> implements CurrencyDao {

	@Autowired
	CurrencyTransformer currencyTransformer;

	@Transactional

	@Override
	public List<CurrencyDto> getAllCurrencyDetails() {
		log.info("CurrencyDaoImpl.getAllCurrencyDetails() invoked");
		Criteria criteria = getCurrentSession().createCriteria(Currency.class);
		List<CurrencyDto> currencyDtoList = null;
		List<Currency> currencyList = (List<Currency>) criteria.list();
		if (currencyList != null && !currencyList.isEmpty()) {
			currencyDtoList = new ArrayList<>();
			for (Currency currency : currencyList) {
				currencyDtoList.add(currencyTransformer.transform(currency));
			}
		}
		return currencyDtoList;
	}

	@Override
	public CurrencyDto saveCurrency(CurrencyDto currencyDto) {
		log.info("CurrencyDaoImpl.saveCurrency() invoked.");

		Criteria criteriaName = getCurrentSession().createCriteria(Currency.class, "Currency");
		criteriaName.add(Restrictions.eq("Currency.currencyName", currencyDto.getCurrencyName()));
		List<Currency> currencyNameList = (List<Currency>) criteriaName.list();

		Criteria criteriaCode = getCurrentSession().createCriteria(Currency.class, "Currency");
		criteriaCode.add(Restrictions.eq("Currency.currencyCode", currencyDto.getCurrencyCode()));
		List<Currency> currencyCodeList = (List<Currency>) criteriaCode.list();

		if (!(currencyNameList != null && currencyNameList.size() > 0
				|| currencyCodeList != null && currencyCodeList.size() > 0)) {

			Currency currency = currencyTransformer.reverseTransform(currencyDto);
			Currency saveCurrency = saveOrUpdate(currency);
			return currencyTransformer.transform(saveCurrency);
		}
		return null;
	}

}
