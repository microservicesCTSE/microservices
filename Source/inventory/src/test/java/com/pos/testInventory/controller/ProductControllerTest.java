package com.pos.testInventory.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.WebRequest;

import com.google.gson.Gson;
import com.pos.inventory.common.dto.ProductDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.common.dto.UserDto;
import com.pos.inventory.controller.ProductController;
import com.pos.inventory.service.ProductService;
import com.pos.testInventory.AbstractTest;

/**
 * @author Shamali
 *
 */
@Transactional
@AutoConfigureMockMvc()
public class ProductControllerTest extends AbstractTest {
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	ProductDto productDto = null;
	ProductDto updateProductDto = null;
	ProductDto saveProductDto = null;

	@Autowired
	ProductController productController;

	@Mock
	WebRequest webRequest;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProductService productService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private Gson gson;

	@Before
	public void setup() throws Exception {
		this.mvc = webAppContextSetup(webApplicationContext).build();

		UserDto userDto = new UserDto();
		userDto.setUserId(1L);

		updateProductDto = new ProductDto();
		updateProductDto.setProductId(740L);
		updateProductDto.setProductName("pro68");
		updateProductDto.setDescription("Food");

		saveProductDto = new ProductDto();
		saveProductDto.setProductName("pro37");
		saveProductDto.setDescription("Food");

		Map<String, String[]> mockParameterMap = new HashMap<>();
		mockParameterMap.put("paginate", new String[] { "true" });
		// add all the parameters you want ...
		Mockito.when(webRequest.getParameterMap()).thenReturn(mockParameterMap);
	}

	@After
	public void tearDown() throws Exception {

	}

//	@Test
//	public void testgetAllProductDetails() {
//		ResponseDto responseDto = productController.getAllProductDetails(1, 1, null, null, null, null, webRequest);
//		Assert.assertNull("Failure Null:", responseDto.getErrorDescription());
//		Assert.assertNotNull("Failure Not Null:", responseDto.getResponseDto());
//
//	}
	
	@Test
	public void testgetAllProductDetails() throws Exception {
		when(productService.getAllProductDetails(1, 1, null, null, null, null, null)).thenReturn(new ResponseDto());
		mvc.perform(MockMvcRequestBuilders.get("/Product/getAllProductDetails").param("pageNumber", "1")
				.param("pageSize", "10")).andDo(print()).andExpect(status().isOk());
	}

//	@Test
//	public void testUpdateProductDetails() {
//		ResponseDto response = productController.updateProductDetails(updateProductDto);
//		Assert.assertNull("Failure Null:", response.getErrorDescription());
//		Assert.assertNotNull("Failure Not Null:", response.getResponseDto());
//	}

//	@Test
//	public void testAddProductDetails() {
//		ResponseDto response = productController.addProductDetails(saveProductDto);
//		Assert.assertNull("Failure Null:", response.getErrorDescription());
//		Assert.assertNotNull("Failure Not Null:", response.getResponseDto());
//	}
	
	@Test
	public void testAddProductDetails() throws Exception {
		when(productService.addProductDetails(saveProductDto)).thenReturn(new ResponseDto());

		mvc.perform(MockMvcRequestBuilders.post("/Product/addProductDetails").content(gson.toJson(saveProductDto))
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
				.andDo(print()).andExpect(status().isOk());

	}	

//	@Test
//	public void testupdateProductDetailsStatus() {
//		ResponseDto responseDto = productController.updateStatusProductDetails(706L, Boolean.TRUE);
//		Assert.assertNull("Failure Null: ", responseDto.getErrorDescription());
//		Assert.assertNotNull("Failure Not Null:", responseDto.getResponseDto());
//
//	}
	
	@Test
	public void testupdateProductDetailsStatus() throws Exception {
		when(productService.updateStatusProductDetails(324L, Boolean.FALSE)).thenReturn(new ResponseDto());
		mvc.perform(MockMvcRequestBuilders.put("/Product/updateProductDetailsStatus").param("productId", "324")
				.param("isActive", "false")).andDo(print()).andExpect(status().isOk());
	}	

	@Test
	public void testUpdateProductDetails() throws Exception {
		when(productService.updateProductDetails(updateProductDto)).thenReturn(new ResponseDto());

		mvc.perform(MockMvcRequestBuilders.put("/Product/updateProductDetails").content(gson.toJson(updateProductDto))
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
				.andDo(print()).andExpect(status().isOk());

	}
}
