package com.pos.inventory.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.inventory.common.constants.ApplicationMessageConstants;
import com.pos.inventory.common.dto.MeasurementUnitDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.service.MeasurementUnitService;
import com.pos.inventory.service.BL.MeasurementUnitServiceBL;
import com.pos.inventory.service.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MeasurementUnitServiceImpl implements MeasurementUnitService {

	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	MeasurementUnitServiceBL measurementUnitServiceBL;

	@Override
	public ResponseDto getAllMeasurementUnitDeatails() {
		log.info("MeasurementUnitServiceImpl.getAllMeasurementUnitDeatails() invoked");
		ResponseDto responseDto = null;
		try {
			List<MeasurementUnitDto> measurementUnitDtoList = measurementUnitServiceBL.getAllMeasurementUnitDetails();
			if (measurementUnitDtoList != null && !measurementUnitDtoList.isEmpty()) {
				log.info("Retrieve All Measurementunit Details.");
				responseDto = serviceUtil.getServiceResponse(measurementUnitDtoList);
			} else {
				log.info("Unable to retrieve All Measurementunit details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_MEASUREMENT_UNIT_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Measurementunit details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_MEASUREMENT_UNIT_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto saveMeasurementUnitDetails(MeasurementUnitDto measurementUnitDto) {
		log.info("UnitsServiceImpl.saveUnits() invoked.");
		ResponseDto responseDto = null;
		try {
			MeasurementUnitDto saveMeasurementUnitDto = measurementUnitServiceBL
					.saveMeasurementUnitDetails(measurementUnitDto);
			if (saveMeasurementUnitDto != null) {
				log.info("Measurement unit details Saved.");
				responseDto = serviceUtil.getServiceResponse(saveMeasurementUnitDto);
			} else {
				log.info("Unable to saved measurement unit details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_MEASUREMENT_UNIT_DETAILS);
			}

		} catch (Exception e) {
			log.error("Exception occurs while saving units details.", e);
			responseDto = serviceUtil.getErrorServiceResponse(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_MEASUREMENT_UNIT_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto updateMeasurementUnitDetailsStatus(Long unitId, Boolean isActive) {
		log.info("UnitsServiceImpl.updateUnitsStatus() invoked.");
		ResponseDto responseDto = null;
		try {
			MeasurementUnitDto saveMeasurementUnitDto = measurementUnitServiceBL
					.updateMeasurementUnitDetailsStatus(unitId, isActive);
			if (saveMeasurementUnitDto != null) {
				log.info("Measurement unit details status updated.");
				responseDto = serviceUtil.getServiceResponse(saveMeasurementUnitDto);
			} else {
				log.info("Unable to update measurement unit details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_STATUS_MEASUREMENT_UNIT_DETAILS);
			}

		} catch (Exception e) {
			log.error("Exception occurs while updating measurement unit details.", e);
			responseDto = serviceUtil.getErrorServiceResponse(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_STATUS_MEASUREMENT_UNIT_DETAILS);
		}
		return responseDto;
	}

	@Transactional
	@Override
	public ResponseDto updateMeasurementUnitDetails(MeasurementUnitDto measurementUnitDto) {
		log.info("UnitsServiceImpl.updateMeasurementUnitDetails() invoked.");
		ResponseDto responseDto = null;
		try {
			MeasurementUnitDto updateMeasurementUnitDto = measurementUnitServiceBL
					.updateMeasurementUnitDetails(measurementUnitDto);
			if (updateMeasurementUnitDto != null) {
				log.info("MeasurementUnit details are updated.");
				responseDto = serviceUtil.getServiceResponse(updateMeasurementUnitDto);
			} else {
				log.info("Unable to update measurementUnit details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_MEASUREMENT_UNIT_DETAILS);
			}

		} catch (Exception e) {
			log.error("Exception occurs while updating product details.", e);
			responseDto = serviceUtil.getErrorServiceResponse(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_MEASUREMENT_UNIT_DETAILS);
		}
		return responseDto;
	}

}
