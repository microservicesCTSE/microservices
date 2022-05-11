package com.pos.inventory.service.BL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.inventory.woocommerce.EndpointBaseType;
import com.pos.inventory.common.dto.CategoryDto;
import com.pos.inventory.repository.dao.CategoryDao;
import com.pos.inventory.service.client.AlgoriolFeignClient;
import com.pos.inventory.woocommerce.ApiVersionType;
import com.pos.inventory.woocommerce.WooCommerce;
import com.pos.inventory.woocommerce.WooCommerceAPI;
import com.pos.inventory.woocommerce.oauth.OAuthConfig;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceBL {

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	AlgoriolFeignClient algoriolFeignClient;
	
	private WooCommerce wooCommerce;

	private static final String CONSUMER_KEY = "ck_30ceaf6fde67110878bf7a407340f0b5c5fd2624";
	private static final String CONSUMER_SECRET = "cs_d3ce99b088806cdc2ef1f3a059e2f2c4d41655b7";
	private static final String WC_URL = "http://inventory.codelantic-staging.club";

	public void setUp() {
		wooCommerce = new WooCommerceAPI(new OAuthConfig(WC_URL, CONSUMER_KEY, CONSUMER_SECRET), ApiVersionType.V3);
	}

	public List<CategoryDto> getAllCategoryDetails() {
		log.info("CategoryServiceBL.getAllCategoryDetails() invoked");
		return categoryDao.getAllCategoryDetails();
	}

	public List<CategoryDto> getAllCategoryByBrand(Long brandId) {
		log.info("CategoryServiceBL.getAllCategoryByBrand() invoked.");
		return categoryDao.getAllCategoryByBrand(brandId);
	}

	public CategoryDto saveCategory(CategoryDto categoryDto) {
		log.info("CategoryServiceBL.saveCategory() invoked.");
		setUp();
		CategoryDto category = categoryDao.saveCategory(categoryDto);
		if (category != null) {
//			algoriolFeignClient.saveCategory(categoryDto);
			
			//Add product category details in woocommerce
			Map<String, Object> categoryInfo = new HashMap<>();
			categoryInfo.put("name", categoryDto.getCategoryName());
			categoryInfo.put("description", categoryDto.getCategoryDescription());
	        wooCommerce.create(EndpointBaseType.PRODUCTS_CATEGORIES.getValue(), categoryInfo);
		}
		return category;
	}

	public List<CategoryDto> getAllCategoryByName(String categoryName) {
		log.info("CategoryServiceBL.getAllCategoryByName() invoked.");
		return categoryDao.getAllCategoryByName(categoryName);
	}
}
