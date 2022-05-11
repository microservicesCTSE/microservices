package com.pos.testInventory.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.common.dto.SubCategoryDto;
import com.pos.inventory.controller.SubCategoryController;
import com.pos.inventory.service.ProductService;
import com.pos.inventory.service.SubCategoryService;
import com.pos.testInventory.AbstractTest;

@Transactional
@AutoConfigureMockMvc()
public class SubCategoryControllerTest extends AbstractTest {

	@Autowired
	SubCategoryController subCategoryController;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private SubCategoryService subCategoryService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private Gson gson;

	SubCategoryDto saveSubCategoryDto = null;

	@Before
	public void setup() throws Exception {
		this.mvc = webAppContextSetup(webApplicationContext).build();
		saveSubCategoryDto = new SubCategoryDto();
//		saveSubCategoryDto.setSubCategoryId(16L);
		saveSubCategoryDto.setSubCategoryName("SubCategory10");
		saveSubCategoryDto.setIsActive(Boolean.TRUE);

//		CategoryDto category = new CategoryDto();
//		category.setCategoryId(1L);
//
//		saveSubCategoryDto.setCategoryDto(category);
	}

	@After
	public void tearDown() throws Exception {

	}

//	@Test
//	public void testgetSubCategory() {
//		ResponseDto responseDto = subCategoryController.getAllSubCategoryDetails(1L);
//		Assert.assertNull("Failure Null: ", responseDto.getErrorDescription());
//		Assert.assertNotNull("Failure Not Null:", responseDto.getResponseDto());
//	}

	@Test
	public void testgetSubCategory() throws Exception {
		when(subCategoryService.getAllSubCategoryDeatails(1L)).thenReturn(new ResponseDto());
		mvc.perform(MockMvcRequestBuilders.get("/SubCategory/gellAllSubCategoryDetails/{categoryId}","1"))
				.andDo(print()).andExpect(status().isOk());
	}

//	@Test
//	public void testSaveSubCategory() {
//		ResponseDto responseDto = subCategoryController.saveSubCategory(saveSubCategoryDto);
//		Assert.assertNull("Failure Null: ", responseDto.getErrorDescription());
//		Assert.assertNotNull("Failure Not Null:", responseDto.getResponseDto());
//	}

	@Test
	public void testSaveSubCategory() throws Exception {
		when(subCategoryService.saveSubCategory(saveSubCategoryDto)).thenReturn(new ResponseDto());

		mvc.perform(MockMvcRequestBuilders.post("/SubCategory/save").content(gson.toJson(saveSubCategoryDto))
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
				.andDo(print()).andExpect(status().isOk());

	}

}
