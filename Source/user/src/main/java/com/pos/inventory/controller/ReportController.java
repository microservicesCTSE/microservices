package com.pos.inventory.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("reports")
@Slf4j
public class ReportController {
	@Autowired
	ReportService reportService;

	@GetMapping("/productStockReport")
	public ResponseEntity<byte[]> productStockReport(@RequestParam("categoryName") String categoryName) {
		log.info("ReportsController.productStockReport(pageNumber,pageSize,webRequest) invoked.");

		ResponseDto excelReport = reportService.productStockReport(categoryName);

		String fileName = "summery".concat("report")
				+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xlsx";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
		headers.add("Content-Disposition", "attachment; filename=" + fileName);

		ResponseEntity<byte[]> response = null;
		if (excelReport.getResponseDto() != null) {
			response = new ResponseEntity<byte[]>((byte[]) excelReport.getResponseDto(), headers, HttpStatus.OK);
		}
		return response;

	}
}
