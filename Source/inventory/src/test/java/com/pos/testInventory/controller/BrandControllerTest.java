package com.pos.testInventory.controller;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pos.inventory.common.dto.BrandDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.controller.BrandController;
import com.pos.testInventory.AbstractTest;

@Transactional
public class BrandControllerTest extends AbstractTest {

	@Autowired
	BrandController brandController;

	BrandDto saveBrandDto = null;

	@Before
	public void setup() throws Exception {
		saveBrandDto = new BrandDto();
		saveBrandDto.setBrandId(2L);
		saveBrandDto.setBrandName("Maliban");
		saveBrandDto.setIsActive(Boolean.TRUE);

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testgetAllBrand() {
		ResponseDto responseDto = brandController.getAllBrandDetails();
		Assert.assertNull("Failure Null: ", responseDto.getErrorDescription());
		Assert.assertNotNull("Failure Not Null:", responseDto.getResponseDto());
	}

	@Test
	public void testSaveProductDetails() {
		ResponseDto responseDto = brandController.saveBrand(saveBrandDto);
		Assert.assertNull("Failure Null: ", responseDto.getErrorDescription());
		Assert.assertNotNull("Failure Not Null:", responseDto.getResponseDto());
	}
}
