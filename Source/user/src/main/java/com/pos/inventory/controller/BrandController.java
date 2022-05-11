package com.pos.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pos.inventory.common.dto.BrandDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.service.BrandService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("brand")
@Slf4j
public class BrandController {

	@Autowired
	BrandService brandService;

	@GetMapping("/gellAllBrandDetails")
	public ResponseDto getAllBrandDetails() {
		log.info("BrandController.getAllBrandDetails() invoked");
		return brandService.getAllBrandDetails();
	}

	@PostMapping("/save")
	public ResponseDto saveBrand(@RequestBody BrandDto brandDto) {
		log.info("BrandController.saveBrand() invoked");
		return brandService.saveBrand(brandDto);
	}

}
