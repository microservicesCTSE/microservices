package com.pos.testInventory.controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.pos.inventory.controller.ReportController;
import com.pos.testInventory.AbstractTest;

public class ReportControllerTest extends AbstractTest {

	@Autowired
	ReportController reportController;

	@Before
	public void setup() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testDownloadProductReport() throws Exception {
		ResponseEntity<byte[]> response = reportController.productStockReport("Food");

		if (response != null) {
			Assert.assertNotNull("faliure null", response);
		}

		response = reportController.productStockReport("bun");

		Assert.assertNull("faliure not null", response);

	}

}
