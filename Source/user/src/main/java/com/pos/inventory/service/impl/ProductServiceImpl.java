/**
 * 
 */
package com.pos.inventory.service.impl;

import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.inventory.common.constants.ApplicationMessageConstants;
import com.pos.inventory.common.dto.PaginatedResponseDto;
import com.pos.inventory.common.dto.ProductDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.service.ProductService;
import com.pos.inventory.service.BL.ProductBL;
import com.pos.inventory.service.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author RKeerthiga
 *
 */

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	ProductBL productBL;

	@Override
	public ResponseDto getAllProductDetails(int pageNumber, int pageSize, Long productId, String productName,
			String barcode, String categoryName, Map<String, String> searchParams) {
		log.info("ProductServiceImpl.getAllProductDetails() invoked.");
		ResponseDto responseDto = null;
		try {
			PaginatedResponseDto paginatedResponseDto = productBL.getAllProductDetails(pageNumber, pageSize, productId,
					productName, barcode, categoryName, searchParams);
			if (paginatedResponseDto != null) {
				log.info("Product details are retrived.");
				responseDto = serviceUtil.getServiceResponse(paginatedResponseDto);
			} else {
				log.info("Unable to retrive product details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_DETAILS);
			}

		} catch (Exception e) {
			log.error("Exception occurs while retriving product details.", e);
			responseDto = serviceUtil.getErrorServiceResponse(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto addProductDetails(ProductDto productDto) {
		log.info("ProductServiceImpl.addProductDetails(ProductDto productDto) invoked");
		ResponseDto responseDto = null;
		try {
			ProductDto addProductDetails = productBL.addProductDetails(productDto);
			if (addProductDetails != null) {
				log.info("Products are saved.");
				responseDto = serviceUtil.getServiceResponse(addProductDetails);
			} else {
				log.info("Unable to save products.");
				responseDto = serviceUtil
						.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_PRODUCTS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving products.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_PRODUCTS);
		}
		return responseDto;
	}

	@Transactional
	@Override
	public ResponseDto updateProductDetails(ProductDto productDto) {
		log.info("ProductServiceImpl.updateProductDetails() invoked.");
		ResponseDto responseDto = null;
		try {
			ProductDto updateProductDetails = productBL.updateProductDetails(productDto);
			if (updateProductDetails != null) {
				log.info("Product details are updated.");
				responseDto = serviceUtil.getServiceResponse(updateProductDetails);
			} else {
				log.info("Unable to update product details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_DETAILS);
			}

		} catch (Exception e) {
			log.error("Exception occurs while updating product details.", e);
			responseDto = serviceUtil.getErrorServiceResponse(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_DETAILS);
		}
		return responseDto;
	}

	@Transactional
	@Override
	public ResponseDto updateStatusProductDetails(Long productId, Boolean isActive) {
		log.info("ProductServiceImpl.updateStatusProductDetails() invoked.");
		ResponseDto responseDto = null;
		try {
			ProductDto saveProductDto = productBL.updateStatusProductDetails(productId, isActive);
			if (saveProductDto != null) {
				log.info("Product details status is updated.");
				responseDto = serviceUtil.getServiceResponse(saveProductDto);
			} else {
				log.info("Unable to update product details status.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_STATUS_PRODUCT_DETAILS);
			}

		} catch (Exception e) {
			log.error("Exception occurs while updating product details status.", e);
			responseDto = serviceUtil.getErrorServiceResponse(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_STATUS_PRODUCT_DETAILS);
		}
		return responseDto;
	}

	@Override
	public ResponseDto getProductDetailsByBarcodeOrProductName(String categoryName, String productName) {
		log.info("StockServiceImpl.getProductDetailsByBarcodeOrProductName() invoked");
		ResponseDto responseDto = null;
		try {
			List<ProductDto> productDtosList = productBL.getProductDetailsByBarcodeOrProductName(categoryName,
					productName);
			if (productDtosList != null) {
				log.info("Stock Details retrive Details.");
				responseDto = serviceUtil.getServiceResponse(productDtosList);
			} else {
				log.info("Unable to retrive Stock details by product name or barcode.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_PRODUCT_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retriving Stock details by product name or barcode..", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_PRODUCT_DETAILS);
		}
		return responseDto;
	}

	@Transactional
	@Override
	public ResponseDto updateInventoryProductDetails(ProductDto productDto) {
		log.info("ProductServiceImpl.updateProductDetails() invoked.");
		ResponseDto responseDto = null;
		try {
			ProductDto updateProductDetails = productBL.updateInventoryProductDetails(productDto);
			if (updateProductDetails != null) {
				log.info("Product details are updated.");
				responseDto = serviceUtil.getServiceResponse(updateProductDetails);
			} else {
				log.info("Unable to update product details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_DETAILS);
			}

		} catch (Exception e) {
			log.error("Exception occurs while updating product details.", e);
			responseDto = serviceUtil.getErrorServiceResponse(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_DETAILS);
		}
		return responseDto;
	}

	@Transactional
	@Override
	public ResponseDto updateProductDetailsWoocommerce(ProductDto productDto) {
		log.info("ProductServiceImpl.updateProductDetails() invoked.");
		ResponseDto responseDto = null;
		try {
			ProductDto updateProductDetails = productBL.updateProductDetailsWoocommerce(productDto);
			if (updateProductDetails != null) {
				log.info("Product details are updated.");
				responseDto = serviceUtil.getServiceResponse(updateProductDetails);
			} else {
				log.info("Unable to update product details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_UPDATE_PRODUCT_DETAILS);
			}

		} catch (Exception e) {
			log.error("Exception occurs while updating product details.", e);
			responseDto = serviceUtil.getErrorServiceResponse(
					ApplicationMessageConstants.ServiceErrorMessages.EX_UPDATE_PRODUCT_DETAILS);
		}
		return responseDto;
	}

}
