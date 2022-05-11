package com.pos.inventory.service.BL;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.inventory.common.dto.MeasurementUnitDto;
import com.pos.inventory.repository.dao.MeasurementUnitDao;
import com.pos.inventory.service.client.AlgoriolFeignClient;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MeasurementUnitServiceBL {

	@Autowired
	MeasurementUnitDao measurementUnitDao;

	@Autowired
	AlgoriolFeignClient algoriolFeignClient;

	public List<MeasurementUnitDto> getAllMeasurementUnitDetails() {
		log.info("MeasurementUnitServiceBL.getAllMeasurementUnitDetails() invoked");
		return measurementUnitDao.getAllMeasurementUnitDetails();

	}

	public MeasurementUnitDto saveMeasurementUnitDetails(MeasurementUnitDto measurementUnitDto) {
		log.info("MeasurementUnitServiceBL.saveMeasurementUnitDetails() invoked.");
		MeasurementUnitDto measurement = measurementUnitDao.saveMeasurementUnitDetails(measurementUnitDto);
//		if (measurement != null) {
//			algoriolFeignClient.saveMeasurementUnitDetails(measurementUnitDto);
//		}
		return measurement;
	}

	public MeasurementUnitDto updateMeasurementUnitDetailsStatus(Long unitId, Boolean isActive) {
		log.info("MeasurementUnitServiceBL.updateMeasurementUnitDetailsStatus() invoked.");
		MeasurementUnitDto measurementUnitDto = measurementUnitDao.updateMeasurementUnitDetailsStatus(unitId, isActive);
		if (measurementUnitDto != null) {
			measurementUnitDto.setIsActive(isActive);
			return measurementUnitDao.updateMeasurementUnitDetailsStatus(measurementUnitDto);
		} else {
			return null;
		}
	}

	public MeasurementUnitDto updateMeasurementUnitDetails(MeasurementUnitDto measurementUnitDto) {
		log.info(
				"MeasurementUnitServiceBL.updateMeasurementUnitDetails(MeasurementUnitDto measurementUnitDto) invoked");
		return measurementUnitDao.updateMeasurementUnitDetails(measurementUnitDto);
	}
}
