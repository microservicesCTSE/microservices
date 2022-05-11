package com.pos.inventory.service;

import java.util.Map;
import com.pos.inventory.common.dto.ProductDto;
import com.pos.inventory.common.dto.ResponseDto;

/**
 * @author RKeerthiga
 *
 */

public interface ProductService {

	ResponseDto getAllProductDetails(int pageNumber, int pageSize, Long productId, String productName, String barcode,
			String categoryName, Map<String, String> searchParams);

	ResponseDto addProductDetails(ProductDto productDto);

	public ResponseDto updateProductDetails(ProductDto productDto);

	public ResponseDto updateInventoryProductDetails(ProductDto productDto);

	public ResponseDto updateStatusProductDetails(Long productId, Boolean isActive);

	ResponseDto getProductDetailsByBarcodeOrProductName(String categoryName, String productName);
	
	public ResponseDto updateProductDetailsWoocommerce(ProductDto productDto);

}
