package com.pos.testInventory.controller;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pos.inventory.common.dto.MeasurementUnitDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.controller.MeasurementUnitController;
import com.pos.testInventory.AbstractTest;

@Transactional
public class MeasurementUnitControllerTest extends AbstractTest {

	@Autowired
	MeasurementUnitController measurementunitController;

	MeasurementUnitDto saveMeasurementUnitDto = null;
	MeasurementUnitDto updateMeasurementUnitDto = null;

	@Before
	public void setup() throws Exception {
		saveMeasurementUnitDto = new MeasurementUnitDto();
		saveMeasurementUnitDto.setUnitId(8L);
		saveMeasurementUnitDto.setUnitCode("T");
		saveMeasurementUnitDto.setUnitDescription("T");
		saveMeasurementUnitDto.setIsActive(Boolean.TRUE);

		updateMeasurementUnitDto = new MeasurementUnitDto();
		updateMeasurementUnitDto.setUnitId(1L);
		updateMeasurementUnitDto.setUnitCode("m");
		updateMeasurementUnitDto.setUnitDescription("Meter");
		updateMeasurementUnitDto.setIsActive(Boolean.TRUE);

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testgetAllMeasurementUnitDetails() {
		ResponseDto response = measurementunitController.gellAllMeasurementUnitDetails();
		Assert.assertNotNull("Not null", response.getResponseDto());
		Assert.assertNull("null", response.getErrorDescription());
	}

	@Test
	public void testSaveMeasurementUnitDetails() {
		ResponseDto response = measurementunitController.saveMeasurementUnitDetails(saveMeasurementUnitDto);
		Assert.assertNotNull("Not null", response.getResponseDto());
		Assert.assertNull("null", response.getErrorDescription());
	}

	@Test
	public void testUpdateMeasurementUnitDetails() {
		ResponseDto response = measurementunitController.updateMeasurementUnitDetails(updateMeasurementUnitDto);
		Assert.assertNotNull("Not null", response.getResponseDto());
		Assert.assertNull("null", response.getErrorDescription());
	}

	@Test
	public void testUpdateStatusMeasurementUnitDetails() {
		ResponseDto response = measurementunitController.updateMeasurementUnitDetailsStatus(1L, Boolean.TRUE);
		Assert.assertNotNull("Not null", response.getResponseDto());
		Assert.assertNull("null", response.getErrorDescription());
	}

}
