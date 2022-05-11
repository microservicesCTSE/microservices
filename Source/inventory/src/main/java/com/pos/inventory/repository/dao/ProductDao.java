package com.pos.inventory.repository.dao;

import java.util.List;
import java.util.Map;
import com.pos.inventory.common.dto.PaginatedResponseDto;
import com.pos.inventory.common.dto.ProductDto;
import com.pos.inventory.repository.domain.Product;

/**
 * @author RKeerthiga
 *
 */

public interface ProductDao extends BaseDao<Product> {
	public ProductDto addProductDetails(ProductDto productDto);

	PaginatedResponseDto getAllProductDetails(int pageNumber, int pageSize, Long productId, String productName,
			String barcode, String categoryName, Map<String, String> searchParams);

	ProductDto updateProductDetails(ProductDto productDto);

	ProductDto updateStatusProductDetails(Long productId, Boolean isActive);

	List<ProductDto> getProductDetailsByBarcodeOrProductName(String categoryName, String productName);
	
	List<ProductDto> getProductById(Long productId);

}
