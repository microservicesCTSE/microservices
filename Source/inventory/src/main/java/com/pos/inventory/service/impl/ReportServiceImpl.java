package com.pos.inventory.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.inventory.common.constants.ApplicationMessageConstants;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.service.ReportService;
import com.pos.inventory.service.BL.ReportServiceBL;
import com.pos.inventory.service.util.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {
	@Autowired
	ReportServiceBL reportServiceBL;

	@Autowired
	ServiceUtil serviceUtil;

	@Override
	public ResponseDto productStockReport(String categoryName) {
		log.info("ReportServiceImpl.productStockReport(pageNumber,origins,pageSize,searchParameters) invoked.");
		ResponseDto responseDto = null;
		try {
			byte[] reportData = reportServiceBL.productStockReport(categoryName);
			if (reportData != null) {
				responseDto = serviceUtil.getServiceResponse(reportData);
			} else {
				log.info("Unable to generate product stock reports excel by criteria.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_PRODUCT_STOCK_REPORT_EXCEL_BY_CRITERIA);
			}
		} catch (Exception e) {
			log.error("Exception occurs while generating product stock reports by criteria.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_PRODUCT_STOCK_REPORT_EXCEL_BY_CRITERIA);
		}
		return responseDto;
	}

}
