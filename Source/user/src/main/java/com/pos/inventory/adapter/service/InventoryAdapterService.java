package com.pos.inventory.adapter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import com.pos.inventory.common.dto.ProductDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.repository.domain.Product;
import com.pos.inventory.repository.transfomer.ProductTransformer;
import com.pos.inventory.service.util.LoggingUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
@Slf4j
public class InventoryAdapterService implements GenericAdapter {

	@Autowired
	LoggingUtil loggingUtil;

	@Autowired
	InventoryServiceAdapter serviceAdapter;
	
	@Autowired
	ProductTransformer productTransformer; 

	@Override
	public ProductDto updateProductDetails(ProductDto productDto) {
		log.info("InventoryAdapterService.addProductDetails(ProductDto productDto) invoked");
		loggingUtil.log(null, serviceAdapter.getLogPath(), true, "Product");
		ProductDto productDto1 = null;
		String url = "/Product/updateProductDetails";
		Object result = serviceAdapter.executeForSingleValue(url, productDto, HttpMethod.PUT, false);
		loggingUtil.log(result, serviceAdapter.getLogPath(), false, "ProductList");
		ObjectMapper mapper = new ObjectMapper();
		try {
			Product product = mapper.readValue((String) result, Product.class);
			productDto1 = productTransformer.transform(product);
		} catch (JsonProcessingException e) {
			log.error("Exception occur object mapper with update product", e);
		}
		return productDto1;
	}

}
