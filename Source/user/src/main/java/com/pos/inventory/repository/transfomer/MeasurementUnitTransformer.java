package com.pos.inventory.repository.transfomer;

import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.MeasurementUnitDto;
import com.pos.inventory.repository.domain.MeasurementUnit;


@Component
public class MeasurementUnitTransformer implements BaseTransformer<MeasurementUnit, MeasurementUnitDto>{

	@Override
	public MeasurementUnitDto transform(MeasurementUnit measurementUnit	) {
		MeasurementUnitDto measurementUnitDto = null;
		if (measurementUnit != null) {
			measurementUnitDto = new MeasurementUnitDto();
			measurementUnitDto.setUnitId(measurementUnit.getUnitId());
			measurementUnitDto.setUnitCode(measurementUnit.getUnitCode());
			measurementUnitDto.setUnitDescription(measurementUnit.getUnitDescription());
			measurementUnitDto.setIsActive(measurementUnit.getIsActive());
		}
		return measurementUnitDto;
	}

	@Override
	public MeasurementUnit reverseTransform(MeasurementUnitDto measurementUnitDto) {
		MeasurementUnit measurementUnit = null;
		if (measurementUnitDto != null) {
			measurementUnit = new MeasurementUnit();
			measurementUnit.setUnitId(measurementUnitDto.getUnitId());
			measurementUnit.setUnitCode(measurementUnitDto.getUnitCode());
			measurementUnit.setUnitDescription(measurementUnitDto.getUnitDescription());
			measurementUnit.setIsActive(measurementUnitDto.getIsActive());
		}
		return measurementUnit;
	}

}
