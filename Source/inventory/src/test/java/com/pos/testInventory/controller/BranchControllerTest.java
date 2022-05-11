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
import com.pos.inventory.common.dto.BranchDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.controller.BranchController;
import com.pos.inventory.service.BranchService;
import com.pos.inventory.service.ProductService;
import com.pos.testInventory.AbstractTest;

@Transactional
@AutoConfigureMockMvc()
public class BranchControllerTest extends AbstractTest {
	@Autowired
	BranchController branchController;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private BranchService branchService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private Gson gson;

	BranchDto saveBranchDto = null;

	@Before
	public void setup() throws Exception {
		this.mvc = webAppContextSetup(webApplicationContext).build();

		saveBranchDto = new BranchDto();
		saveBranchDto.setBranchName("Thirunelvely");
		saveBranchDto.setBranchLocation("Thirunelvely");
		saveBranchDto.setIsActive(Boolean.TRUE);

	}

	@After
	public void tearDown() throws Exception {

	}

//	@Test
//	public void testgetAllBranch() {
//		ResponseDto responseDto = branchController.getAllBranchDetails();
//		Assert.assertNull("Failure Null: ", responseDto.getErrorDescription());
//		Assert.assertNotNull("Failure Not Null:", responseDto.getResponseDto());
//	}

	@Test
	public void testgetAllBranch() throws Exception {
		when(branchService.getAllBranchDeatails()).thenReturn(new ResponseDto());
		mvc.perform(MockMvcRequestBuilders.get("/branch/gellAllBranchDetails")).andDo(print())
				.andExpect(status().isOk());
	}

//	@Test
//	public void testSaveBranch() {
//		ResponseDto responseDto = branchController.saveBranch(saveBranchDto);
//		Assert.assertNull("Failure Null: ", responseDto.getErrorDescription());
//		Assert.assertNotNull("Failure Not Null:", responseDto.getResponseDto());
//	}
//	
	@Test
	public void testSaveBranch() throws Exception {
		when(branchService.saveBranch(saveBranchDto)).thenReturn(new ResponseDto());

		mvc.perform(MockMvcRequestBuilders.post("/branch/save").content(gson.toJson(saveBranchDto))
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
				.andDo(print()).andExpect(status().isOk());

	}

}
