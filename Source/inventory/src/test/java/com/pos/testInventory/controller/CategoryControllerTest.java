package com.pos.testInventory.controller;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pos.inventory.common.dto.BrandDto;
import com.pos.inventory.common.dto.CategoryDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.controller.CategoryController;
import com.pos.testInventory.AbstractTest;

@Transactional
public class CategoryControllerTest extends AbstractTest {

	@Autowired
	CategoryController categoryController;

	CategoryDto saveCategoryDto = null;

	@Before
	public void setup() throws Exception {

		saveCategoryDto = new CategoryDto();
		saveCategoryDto.setCategoryId(20L);
		saveCategoryDto.setCategoryName("Category");
		saveCategoryDto.setCategoryDescription("Description");
		saveCategoryDto.setIsActive(Boolean.TRUE);

		BrandDto brand = new BrandDto();
		brand.setBrandId(1L);

		saveCategoryDto.setBrandDto(brand);
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testSaveCategoryDetails() {
		ResponseDto response = categoryController.saveCategory(saveCategoryDto);
		Assert.assertNotNull("Not null", response.getResponseDto());
		Assert.assertNull("null", response.getErrorDescription());
	}

	@Test
	public void testGetAllCategoryDetails() {
		ResponseDto response = categoryController.getAllCategoryDetails();
		Assert.assertNotNull("Not null", response.getResponseDto());
		Assert.assertNull("null", response.getErrorDescription());
	}

	@Test
	public void testGetAllCategoryByBrand() {
		ResponseDto response = categoryController.getAllCategoryByBrand(1L);
		Assert.assertNotNull("Not null", response.getResponseDto());
		Assert.assertNull("null", response.getErrorDescription());
	}

	@Test
	public void testGetAllCategoryByName() {
		ResponseDto response = categoryController.getAllCategoryByName("Food");
		Assert.assertNotNull("Not null", response.getResponseDto());
		Assert.assertNull("null", response.getErrorDescription());
	}

}
