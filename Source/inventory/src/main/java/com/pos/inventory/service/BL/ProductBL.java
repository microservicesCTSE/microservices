/**
 * 
 */
package com.pos.inventory.service.BL;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import com.pos.inventory.adapter.service.GenericAdapter;
import com.pos.inventory.adapter.service.InventoryServiceAdapter;
import com.pos.inventory.common.dto.PaginatedResponseDto;
import com.pos.inventory.common.dto.ProductDto;
import com.pos.inventory.repository.dao.ProductDao;
import com.pos.inventory.service.client.AlgoriolFeignClient;
import com.pos.inventory.woocommerce.ApiVersionType;
import com.pos.inventory.woocommerce.EndpointBaseType;
import com.pos.inventory.woocommerce.WooCommerce;
import com.pos.inventory.woocommerce.WooCommerceAPI;
import com.pos.inventory.woocommerce.oauth.OAuthConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * @author RKeerthiga
 *
 */

@Service
@Slf4j
public class ProductBL {

	@Autowired
	ProductDao productDao;

	@Autowired
	AlgoriolFeignClient algoriolFeignClient;

	@Autowired
	InventoryServiceAdapter inventoryServiceAdapter;

	@Autowired
	GenericAdapter genericAdapter;

	private WooCommerce wooCommerce;

	private static final String CONSUMER_KEY = "ck_43b796f0f9fd9c5fcbc547f5461bd21222c06eb5";
	private static final String CONSUMER_SECRET = "cs_a5efa961b5676183c70425acf3253fc8f6835624";
	private static final String WC_URL = "http://inventory.codelantic-staging.club";

	public void setUp() {
		wooCommerce = new WooCommerceAPI(new OAuthConfig(WC_URL, CONSUMER_KEY, CONSUMER_SECRET), ApiVersionType.V3);
	}

	public PaginatedResponseDto getAllProductDetails(int pageNumber, int pageSize, Long productId, String productName,
			String barcode, String categoryName, Map<String, String> searchParams) {
		log.info("ProductkBL.getAllProductDetails() invoked.");
		return productDao.getAllProductDetails(pageNumber, pageSize, productId, productName, barcode, categoryName,
				searchParams);
	}

	public ProductDto addProductDetails(ProductDto productDto) {
		log.info("ProductBL.addProductDetails() invoked.");
		setUp();
		productDto.setUpdatedDate(LocalDate.now());
		productDto.setUpdatedTime(LocalTime.now());
		productDto.setStockLevel(productDto.getQuantity());
		ProductDto product = productDao.addProductDetails(productDto);
		if (product != null) {
			// Add product details in the woocommerce
			Map<String, Object> productInfo = new HashMap<>();
			productInfo.put("name", productDto.getProductName());
			productInfo.put("type", "simple");
			productInfo.put("sku", product.getProductId().toString());
			productInfo.put("sale_price", productDto.getRetailPrice().toString());
			productInfo.put("price", productDto.getUnitPrice().toString());
			productInfo.put("regular_price", productDto.getUnitPrice().toString());
			productInfo.put("description", productDto.getDescription());
			productInfo.put("manage_stock", "true");
			productInfo.put("stock_quantity", productDto.getQuantity().toString());
			productInfo.put("categories[id]", 66);
			Map products = wooCommerce.create(EndpointBaseType.PRODUCTS.getValue(), productInfo);
			log.info("Products are .............{}", products);
		}
		return product;
	}

	public ProductDto updateProductDetails(ProductDto productDto) {
		log.info("ProductBL.updateProductDetails(ProductDto productDto) invoked");
		setUp();
		List<ProductDto> productList = productDao.getProductDetailsByBarcodeOrProductName(null,
				productDto.getProductName());
		ProductDto product = new ProductDto();
		ProductDto pDto = new ProductDto();
		for (ProductDto pro : productList) {
			Integer currentStockLevel = pro.getStockLevel();
			Integer finalStockLevel = currentStockLevel + productDto.getQuantity();

			pDto.setProductId(productDto.getProductId());
			pDto.setProductName(productDto.getProductName());
			pDto.setStockLevel(finalStockLevel);
			pDto.setUnitPrice(productDto.getUnitPrice());
			pDto.setBarcodeDigits(productDto.getBarcodeDigits());
			pDto.setBranchDto(productDto.getBranchDto());
			pDto.setBrandDto(productDto.getBrandDto());
			pDto.setCategoryDto(productDto.getCategoryDto());
			pDto.setComments(productDto.getComments());
			pDto.setCurrencyDto(productDto.getCurrencyDto());
			pDto.setDescription(productDto.getDescription());
			pDto.setGrossWeight(productDto.getGrossWeight());
			pDto.setIsActive(productDto.getIsActive());
			pDto.setItemCode(productDto.getItemCode());
			pDto.setMaximumStock(productDto.getMaximumStock());
			pDto.setMeasurementUnitDto(productDto.getMeasurementUnitDto());
			pDto.setMinimumStock(productDto.getMinimumStock());
			pDto.setNetWeight(productDto.getNetWeight());
			pDto.setPluCode(productDto.getPluCode());
			pDto.setRetailPrice(productDto.getRetailPrice());
			pDto.setQuantity(productDto.getQuantity());
			pDto.setDiscount(productDto.getDiscount());
			pDto.setSubCategoryDto(productDto.getSubCategoryDto());
			pDto.setUserDto(productDto.getUserDto());
			pDto.setExpiryDate(productDto.getExpiryDate());
			pDto.setUpdatedDate(LocalDate.now());
			pDto.setUpdatedTime(LocalTime.now());
			pDto.setSupplierDto(productDto.getSupplierDto());
			pDto.setIsPercentage(productDto.getIsPercentage());
			product = productDao.updateProductDetails(pDto);
			if (product != null) {
				genericAdapter.updateProductDetails(pDto);

				// Update product details in woocommerce
				Map<String, Object> productInfo = new HashMap<>();
				productInfo.put("name", productDto.getProductName());
				productInfo.put("type", "simple");
				productInfo.put("regular_price", productDto.getRetailPrice().toString());
				productInfo.put("sale_price", productDto.getRetailPrice().toString());
				productInfo.put("price", productDto.getUnitPrice().toString());
				productInfo.put("regular_price", productDto.getUnitPrice().toString());
				productInfo.put("description", productDto.getDescription());
				productInfo.put("manage_stock", "true");
				productInfo.put("stock_quantity", finalStockLevel.toString());
				wooCommerce.update(EndpointBaseType.PRODUCTS.getValue(), productDto.getProductId().intValue(),
						productInfo);
			}
		}

		return product;
	}

	public ProductDto updateInventoryProductDetails(ProductDto productDto) {
		log.info("ProductBL.updateProductDetails(ProductDto productDto) invoked");
		ProductDto product = new ProductDto();
		ProductDto pDto = new ProductDto();
		pDto.setProductId(productDto.getProductId());
		pDto.setProductName(productDto.getProductName());
		pDto.setStockLevel(productDto.getStockLevel());
		pDto.setUnitPrice(productDto.getUnitPrice());
		pDto.setBarcodeDigits(productDto.getBarcodeDigits());
		pDto.setBranchDto(productDto.getBranchDto());
		pDto.setBrandDto(productDto.getBrandDto());
		pDto.setCategoryDto(productDto.getCategoryDto());
		pDto.setComments(productDto.getComments());
		pDto.setCurrencyDto(productDto.getCurrencyDto());
		pDto.setDescription(productDto.getDescription());
		pDto.setGrossWeight(productDto.getGrossWeight());
		pDto.setIsActive(productDto.getIsActive());
		pDto.setItemCode(productDto.getItemCode());
		pDto.setMaximumStock(productDto.getMaximumStock());
		pDto.setMeasurementUnitDto(productDto.getMeasurementUnitDto());
		pDto.setMinimumStock(productDto.getMinimumStock());
		pDto.setNetWeight(productDto.getNetWeight());
		pDto.setPluCode(productDto.getPluCode());
		pDto.setRetailPrice(productDto.getRetailPrice());
		pDto.setQuantity(productDto.getQuantity());
		pDto.setDiscount(productDto.getDiscount());
		pDto.setSubCategoryDto(productDto.getSubCategoryDto());
		pDto.setUserDto(productDto.getUserDto());
		pDto.setExpiryDate(productDto.getExpiryDate());
		pDto.setUpdatedDate(LocalDate.now());
		pDto.setUpdatedTime(LocalTime.now());
		pDto.setSupplierDto(productDto.getSupplierDto());
		pDto.setIsPercentage(productDto.getIsPercentage());
		product = productDao.updateProductDetails(pDto);
		if (product != null) {
			// Update product details in woocommerce
			Map<String, Object> productInfo = new HashMap<>();
			productInfo.put("name", productDto.getProductName());
			productInfo.put("type", "simple");
			productInfo.put("regular_price", productDto.getRetailPrice().toString());
			productInfo.put("sale_price", productDto.getRetailPrice().toString());
			productInfo.put("price", productDto.getUnitPrice().toString());
			productInfo.put("regular_price", productDto.getUnitPrice().toString());
			productInfo.put("description", productDto.getDescription());
			productInfo.put("manage_stock", "true");
			productInfo.put("stock_quantity", productDto.getStockLevel().toString());
			wooCommerce.update(EndpointBaseType.PRODUCTS.getValue(), productDto.getProductId().intValue(), productInfo);
			genericAdapter.updateProductDetails(pDto);

		}
		return product;
	}

	public ProductDto updateStatusProductDetails(Long productId, Boolean isActive) {
		log.info("ProductBL.updateStatusProductDetails() invoked.");
		ProductDto productDto = productDao.updateStatusProductDetails(productId, isActive);
		if (productDto != null) {
			productDto.setIsActive(isActive);
			return productDao.updateProductDetails(productDto);
		} else {
			return null;
		}

	}

	public List<ProductDto> getProductDetailsByBarcodeOrProductName(String categoryName, String productName) {
		log.info("ProductBL.getProductDetailsByBarcodeOrProductName() invoked");
		return productDao.getProductDetailsByBarcodeOrProductName(categoryName, productName);
	}

	public ProductDto updateProductDetailsWoocommerce(ProductDto productDto) {
		ProductDto product = new ProductDto();
		log.info("ProductBL.updateProductDetailsWoocommerce(ProductDto productDto) invoked");
		setUp();
		if (CONSUMER_KEY.equals(productDto.getConsumerKey())
				&& CONSUMER_SECRET.equals(productDto.getConsumerSecret())) {

			List<ProductDto> productList = productDao.getProductById(productDto.getProductId());

			ProductDto pDto = new ProductDto();
			for (ProductDto pro : productList) {
				Integer currentStockLevel = pro.getStockLevel();
				Integer finalStockLevel = currentStockLevel - productDto.getSales();

				pDto.setProductId(pro.getProductId());
				pDto.setProductName(pro.getProductName());
				pDto.setStockLevel(finalStockLevel);
				pDto.setUnitPrice(pro.getUnitPrice());
				pDto.setBarcodeDigits(pro.getBarcodeDigits());
				pDto.setBranchDto(pro.getBranchDto());
				pDto.setBrandDto(pro.getBrandDto());
				pDto.setCategoryDto(pro.getCategoryDto());
				pDto.setComments(pro.getComments());
				pDto.setCurrencyDto(pro.getCurrencyDto());
				pDto.setDescription(pro.getDescription());
				pDto.setGrossWeight(pro.getGrossWeight());
				pDto.setIsActive(pro.getIsActive());
				pDto.setItemCode(pro.getItemCode());
				pDto.setMaximumStock(pro.getMaximumStock());
				pDto.setMeasurementUnitDto(pro.getMeasurementUnitDto());
				pDto.setMinimumStock(pro.getMinimumStock());
				pDto.setNetWeight(pro.getNetWeight());
				pDto.setPluCode(pro.getPluCode());
				pDto.setRetailPrice(pro.getRetailPrice());
				pDto.setQuantity(pro.getQuantity());
				pDto.setDiscount(pro.getDiscount());
				pDto.setSubCategoryDto(pro.getSubCategoryDto());
				pDto.setUserDto(pro.getUserDto());
				pDto.setExpiryDate(pro.getExpiryDate());
				pDto.setUpdatedDate(LocalDate.now());
				pDto.setUpdatedTime(LocalTime.now());
				pDto.setSupplierDto(pro.getSupplierDto());
				pDto.setIsPercentage(pro.getIsPercentage());
				product = productDao.updateProductDetails(pDto);
				if (product != null) {
					genericAdapter.updateProductDetails(pDto);
				}

			}

		}
		return product;
	}
}
