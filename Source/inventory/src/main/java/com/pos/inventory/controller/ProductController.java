/**
 * 
 */
package com.pos.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import com.pos.inventory.common.dto.ProductDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.service.ProductService;
import com.pos.inventory.service.util.HttpReqRespUtils;
import org.springframework.lang.Nullable;
import lombok.extern.slf4j.Slf4j;

/**
 * @author RKeerthiga
 *
 */

@Slf4j
@RestController
@RequestMapping("Product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/getAllProductDetails")
	public ResponseDto getAllProductDetails(@RequestParam("pageNumber") int pageNumber,
			@RequestParam("pageSize") int pageSize, @Nullable @RequestParam("productId") Long productId,
			@Nullable @RequestParam("productName") String productName,
			@Nullable @RequestParam("barcode") String barcode,
			@Nullable @RequestParam("categoryName") String categoryName, WebRequest webRequest) {
		log.info("ProductController.getAllProductDetails() invoked.");
		return productService.getAllProductDetails(pageNumber, pageSize, productId, productName, barcode, categoryName,
				HttpReqRespUtils.getSearchParameters(webRequest));
	}

	@PostMapping("/addProductDetails")
	public ResponseDto addProductDetails(@RequestBody ProductDto productDto) {
		log.info("ProductController.addProductDetails() invoked");
		return productService.addProductDetails(productDto);
	}

	@PutMapping("/updateProductDetails")
	public ResponseDto updateProductDetails(@RequestBody ProductDto productDto) {
		log.info("ProductController.updateProductDetails() invoked.");
		return productService.updateProductDetails(productDto);
	}

	@PutMapping("/updateInventoryProductDetails")
	public ResponseDto updateInventoryProductDetails(@RequestBody ProductDto productDto) {
		log.info("ProductController.updateProductDetails() invoked.");
		return productService.updateInventoryProductDetails(productDto);
	}

	@PutMapping("/updateProductDetailsStatus")
	public ResponseDto updateStatusProductDetails(@RequestParam("productId") Long productId,
			@RequestParam("isActive") Boolean isActive) {
		log.info("ProductController.updateStatusProductDetails() invoked.");
		return productService.updateStatusProductDetails(productId, isActive);
	}

	@GetMapping("/getProductDetailsByBarcodeOrProductName")
	public ResponseDto getStockDetailsByBarcodeOrProductName(
			@Nullable @RequestParam("categoryName") String categoryName,
			@Nullable @RequestParam("productName") String productName) {
		log.info("ProductController.getStockDetailsByBarcodeOrProductName() invoked");
		return productService.getProductDetailsByBarcodeOrProductName(categoryName, productName);
	}
	
	@PutMapping("/updateProductDetailsWoocommerce")
	public ResponseDto updateProductDetailsWoocommerce(@RequestBody ProductDto productDto) {
		log.info("ProductController.updateProductDetailsWoocommerce() invoked.");
		return productService.updateProductDetailsWoocommerce(productDto);
	}

}
