package com.pos.inventory.repository.dao;

import java.util.List;
import com.pos.inventory.common.dto.MeasurementUnitDto;
import com.pos.inventory.repository.domain.MeasurementUnit;

public interface MeasurementUnitDao extends BaseDao<MeasurementUnit> {

	List<MeasurementUnitDto> getAllMeasurementUnitDetails();

	MeasurementUnitDto saveMeasurementUnitDetails(MeasurementUnitDto measurementUnitDto);

	MeasurementUnitDto updateMeasurementUnitDetailsStatus(MeasurementUnitDto measurementUnitDto);

	MeasurementUnitDto updateMeasurementUnitDetailsStatus(Long unitId, Boolean isActive);

	MeasurementUnitDto updateMeasurementUnitDetails(MeasurementUnitDto measurementUnitDto);
}
