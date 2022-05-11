package com.pos.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pos.inventory.common.dto.CategoryDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.service.CategoryService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("category")
@Slf4j
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/gellAllCategoryDetails")
	public ResponseDto getAllCategoryDetails() {
		log.info("CategoryController.gellAllCategoryDetails() invoked");
		return categoryService.getAllCategoryDeatails();
	}

	@GetMapping("/getAllCategoryByBrand")
	public ResponseDto getAllCategoryByBrand(@RequestParam("brandId") Long brandId) {
		log.info("CategoryController.getAllCategoriesByBrand() invoked");
		return categoryService.getAllCategoryByBrand(brandId);
	}

	@PostMapping("/save")
	public ResponseDto saveCategory(@RequestBody CategoryDto categoryDto) {
		log.info("BarcodeController.saveBarcode() invoked");
		return categoryService.saveCategory(categoryDto);
	}

	@GetMapping("/getAllCategoryByName")
	public ResponseDto getAllCategoryByName(@RequestParam("categoryName") String categoryName) {
		log.info("CategoryController.getAllCategoryByName() invoked");
		return categoryService.getAllCategoryByName(categoryName);
	}

}
