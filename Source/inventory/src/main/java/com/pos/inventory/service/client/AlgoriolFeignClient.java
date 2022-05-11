package com.pos.inventory.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pos.inventory.common.dto.BranchDto;
import com.pos.inventory.common.dto.BrandDto;
import com.pos.inventory.common.dto.CategoryDto;
import com.pos.inventory.common.dto.CurrencyDto;
import com.pos.inventory.common.dto.MeasurementUnitDto;
import com.pos.inventory.common.dto.ProductDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.common.dto.SubCategoryDto;
import com.pos.inventory.service.config.FeignClientInternalConfiguration;

@FeignClient(name = "stock-service", path = "/", configuration = FeignClientInternalConfiguration.class)
public interface AlgoriolFeignClient {

	@PostMapping("Product/addProductDetails")
	public ResponseDto addProductDetails(@RequestBody ProductDto productDto);

	@PutMapping("Product/updateProductDetails")
	public ResponseDto updateProductDetails(@RequestBody ProductDto productDto);

	@PostMapping("brand/save")
	public ResponseDto saveBrand(@RequestBody BrandDto brandDto);

	@PostMapping("category/save")
	public ResponseDto saveCategory(@RequestBody CategoryDto categoryDto);

	@PostMapping("SubCategory/save")
	public ResponseDto saveSubCategory(@RequestBody SubCategoryDto subCategoryDto);

	@PostMapping("currency/save")
	public ResponseDto saveCurrency(@RequestBody CurrencyDto currencyDto);

	@PostMapping("branch/save")
	public ResponseDto saveBranch(@RequestBody BranchDto brandDto);

	@PostMapping("measurementUnit/saveMeasurementUnitDetails")
	public ResponseDto saveMeasurementUnitDetails(@RequestBody MeasurementUnitDto measurementUnitDto);
}