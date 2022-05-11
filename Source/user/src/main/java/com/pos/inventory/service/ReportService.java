package com.pos.inventory.service;

import java.util.Map;
import com.pos.inventory.common.dto.ResponseDto;

public interface ReportService {
	ResponseDto productStockReport(String categoryName);
}
