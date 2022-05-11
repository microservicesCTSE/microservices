package com.pos.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pos.inventory.common.dto.MeasurementUnitDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.service.MeasurementUnitService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("measurementUnit")
@Slf4j

public class MeasurementUnitController {

	@Autowired
	MeasurementUnitService measurementUnitService;

	@GetMapping("/gellAllMeasurementUnitDetails")
	public ResponseDto gellAllMeasurementUnitDetails() {
		log.info("MeasurementUnitController.getAllMeasurementUnitDeatails() invoked");
		return measurementUnitService.getAllMeasurementUnitDeatails();
	}

	@PostMapping("/saveMeasurementUnitDetails")
	public ResponseDto saveMeasurementUnitDetails(@RequestBody MeasurementUnitDto measurementUnitDto) {
		log.info("MeasurementUnitController.saveUnits() invoked.");
		return measurementUnitService.saveMeasurementUnitDetails(measurementUnitDto);
	}

	@PutMapping("/updateMeasurementUnitDetails")
	public ResponseDto updateMeasurementUnitDetails(@RequestBody MeasurementUnitDto measurementUnitDto) {
		log.info("MeasurementUnitController.updateUnits() invoked.");
		return measurementUnitService.updateMeasurementUnitDetails(measurementUnitDto);
	}

	@PutMapping("/updateStatusMeasurementUnitDetails")
	public ResponseDto updateMeasurementUnitDetailsStatus(@RequestParam("unitId") Long unitId,
			@RequestParam("isActive") Boolean isActive) {
		log.info("MeasurementUnitController.updateUnitsStatus() invoked.");
		return measurementUnitService.updateMeasurementUnitDetailsStatus(unitId, isActive);
	}
}
