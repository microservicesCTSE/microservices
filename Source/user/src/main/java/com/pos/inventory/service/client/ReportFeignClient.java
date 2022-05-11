package com.pos.inventory.service.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.pos.inventory.service.config.FeignClientInternalConfiguration;

@FeignClient(name = "inventory-reports", path = "v1", configuration = FeignClientInternalConfiguration.class)
public interface ReportFeignClient {
	@GetMapping("/report/")
	public ResponseEntity<byte[]> Report(@RequestParam("reportName") String reportName,
			@RequestBody List<String[]> dataList, @RequestParam("colums") String[] columns,
			@RequestParam("logoName") String logoName);
}
