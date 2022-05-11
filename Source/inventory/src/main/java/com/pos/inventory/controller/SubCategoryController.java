package com.pos.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.common.dto.SubCategoryDto;
import com.pos.inventory.service.SubCategoryService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("SubCategory")
@Slf4j
public class SubCategoryController {

	@Autowired
	SubCategoryService subCategoryService;

	@GetMapping("/gellAllSubCategoryDetails/{categoryId}")
	public ResponseDto getAllSubCategoryDetails(@PathVariable("categoryId") Long categoryId) {
		log.info("SubCategoryController.gellAllSubCategoryDetails() invoked");
		return subCategoryService.getAllSubCategoryDeatails(categoryId);
	}

	@PostMapping("/save")
	public ResponseDto saveSubCategory(@RequestBody SubCategoryDto subCategoryDto) {
		log.info("SubCategoryController.saveSubCategory() invoked");
		return subCategoryService.saveSubCategory(subCategoryDto);
	}
}
