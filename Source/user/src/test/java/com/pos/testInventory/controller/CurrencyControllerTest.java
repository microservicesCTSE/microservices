package com.pos.testInventory.controller;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pos.inventory.common.dto.CurrencyDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.controller.CurrencyController;
import com.pos.testInventory.AbstractTest;

@Transactional
public class CurrencyControllerTest extends AbstractTest {

	@Autowired
	CurrencyController currencyController;

	CurrencyDto saveCurrencyDto = null;

	@Before
	public void setup() throws Exception {
		saveCurrencyDto = new CurrencyDto();
		saveCurrencyDto.setCurrencyId(3L);
		saveCurrencyDto.setCurrencyName("Canidan Dollar");
		saveCurrencyDto.setCurrencyCode("CAD");
		saveCurrencyDto.setIsActive(Boolean.TRUE);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testgetAllCurrency() {
		ResponseDto response = currencyController.getAllCurrencyDetails();
		Assert.assertNotNull("Not null1", response.getResponseDto());
		Assert.assertNull("null", response.getErrorDescription());
	}

	@Test
	public void testSaveCurrency() {
		ResponseDto response = currencyController.saveCurrency(saveCurrencyDto);
		Assert.assertNotNull("Not null1", response.getResponseDto());
		Assert.assertNull("null", response.getErrorDescription());
	}

}
