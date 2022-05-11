package com.pos.inventory.service;

import com.pos.inventory.common.dto.MeasurementUnitDto;
import com.pos.inventory.common.dto.ResponseDto;

public interface MeasurementUnitService {

	ResponseDto getAllMeasurementUnitDeatails();

	public ResponseDto saveMeasurementUnitDetails(MeasurementUnitDto measurementUnitDto);

	public ResponseDto updateMeasurementUnitDetailsStatus(Long unitId, Boolean isActive);

	public ResponseDto updateMeasurementUnitDetails(MeasurementUnitDto measurementUnitDto);
}
