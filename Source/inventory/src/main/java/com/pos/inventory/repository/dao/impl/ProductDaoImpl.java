package com.pos.inventory.repository.dao.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.pos.inventory.common.dto.PaginatedResponseDto;
import com.pos.inventory.common.dto.ProductDto;
import com.pos.inventory.repository.dao.ProductDao;
import com.pos.inventory.repository.domain.Product;
import com.pos.inventory.repository.transfomer.ProductTransformer;
import com.pos.inventory.service.util.HttpReqRespUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author RKeerthiga
 *
 */

@Repository
@Slf4j
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {
	@Autowired
	ProductTransformer productTransformer;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	@Override
	public ProductDto addProductDetails(ProductDto productDto) {
		log.info("ProductDaoImpl.addNewProduct()invoked");

		Criteria criteria = getCurrentSession().createCriteria(Product.class, "Product");
		criteria.add(Restrictions.eq("Product.pluCode", productDto.getPluCode()));
		criteria.add(Restrictions.eq("Product.barcodeDigits", productDto.getBarcodeDigits()));
		criteria.add(Restrictions.eq("Product.itemCode", productDto.getItemCode()));
		List<Product> productIList = (List<Product>) criteria.list();

		if (!(productIList != null && productIList.size() > 0)) {
			Product product = productTransformer.reverseTransform(productDto);
			Product saveProduct = saveOrUpdate(product);
			return productTransformer.transform(saveProduct);
		}
		return null;
	}

	@Override
	public PaginatedResponseDto getAllProductDetails(int pageNumber, int pageSize, Long productId, String productName,
			String barcode, String categoryName, Map<String, String> searchParams) {
		log.info("ProductDaoImpl.getAllProductDetails() invoked. {}", searchParams.get("paginate"));
		PaginatedResponseDto paginatedResponseDto = null;

		Criteria criteria = getCurrentSession().createCriteria(Product.class, "Product");
		criteria.addOrder(Order.desc("Product.productId"));

		if (productId != null) {
			criteria.add(Restrictions.eq("Product.productId", productId));
		}
		if (productName != null && !productName.isEmpty()) {
			criteria.add(Restrictions.like("Product.productName", productName, MatchMode.ANYWHERE));
		}
		if (barcode != null && !barcode.isEmpty()) {
			criteria.add(Restrictions.like("Product.barcodeDigits", barcode, MatchMode.ANYWHERE));
		}

		criteria.createAlias("Product.categoryId", "Category");

//		if (searchParams.get("categoryId") != null && !searchParams.get("categoryId").isEmpty()) {
//			criteria.add(Restrictions.eq("Category.categoryId", searchParams.get("categoryId")));
//		}

		if (categoryName != null && !categoryName.isEmpty()) {
			criteria.add(Restrictions.like("Category.categoryName", categoryName, MatchMode.ANYWHERE));
		}

		if (searchParams.get("paginate") != null && !searchParams.get("paginate").isEmpty()) {
			log.info("paginate invoked. {}", searchParams.get("paginate"));

			if (Boolean.parseBoolean(searchParams.get("paginate"))) {
				criteria.setFirstResult((pageNumber - 1) * pageSize);
				criteria.setMaxResults(pageSize);
			}
		}

		List<Product> productList = criteria.list();
		List<ProductDto> productDtoList = null;
		int recordCount = 0;

		recordCount = criteria.list().size();

		String countString = "SELECT COUNT(*) FROM product";
		int count = jdbcTemplate.queryForObject(countString, Integer.class);

		if (productList != null && !productList.isEmpty()) {

			paginatedResponseDto = HttpReqRespUtils.paginatedResponseMapper(productList, pageNumber, pageSize, count);

			paginatedResponseDto.setPayload(productList.stream().map(product -> {
				return productTransformer.transform(product);
			}).collect(Collectors.toList()));
		}
		log.info("paginatedResponseDto {}", paginatedResponseDto);
		return paginatedResponseDto;
	}

	@Transactional
	@Override
	public ProductDto updateProductDetails(ProductDto productDto) {
		log.info("ProductDaoImpl.updateProductDetails() invoked.");
		productDto.setUpdatedDate(LocalDate.now());
		productDto.setUpdatedTime(LocalTime.now());
		Product product = productTransformer.reverseTransform(productDto);
		Product saveProduct = merge(product);
		return productTransformer.transform(saveProduct);
	}

	@Transactional
	@Override
	public ProductDto updateStatusProductDetails(Long productId, Boolean isActive) {
		log.info("ProductDaoImpl.updateStatusProductDetails() invoked.");
		Criteria criteria = getCurrentSession().createCriteria(Product.class, "Product");
		criteria.add(Restrictions.eq("Product.productId", productId));
		Product product = (Product) criteria.uniqueResult();
		ProductDto productDto = null;
		if (product != null) {
			productDto = productTransformer.transform(product);
		}
		return productDto;
	}

	@Override
	public List<ProductDto> getProductDetailsByBarcodeOrProductName(String categoryName, String productName) {
		log.info("ProductDaoImpl.getProductDetailsByBarcodeOrProductName()invoked");
		Criteria criteria = getCurrentSession().createCriteria(Product.class, "Product");
		if (productName != null && !productName.isEmpty()) {
			criteria.add(Restrictions.like("Product.productName", productName, MatchMode.ANYWHERE));
		}
		if (categoryName != null && !categoryName.isEmpty()) {
			criteria.createAlias("Product.categoryId", "Category");
			criteria.add(Restrictions.eq("Category.categoryName", categoryName));
		}

		criteria.add(Restrictions.eq("Product.isActive", true));
		List<Product> productsList = criteria.list();
		List<ProductDto> productDtosList = null;
		if (productsList != null && !productsList.isEmpty()) {
			productDtosList = productsList.stream().map(productDto -> {
				return productTransformer.transform(productDto);
			}).collect(Collectors.toList());

		}
		return productDtosList;
	}

	@Override
	public List<ProductDto> getProductById(Long productId) {
		log.info("ProductDaoImpl.getProductById()invoked");
		Criteria criteria = getCurrentSession().createCriteria(Product.class, "Product");
		if (productId != null) {
			criteria.add(Restrictions.eq("Product.productId", productId));
		}
		List<Product> productsList = criteria.list();
		List<ProductDto> productDtosList = null;
		if (productsList != null && !productsList.isEmpty()) {
			productDtosList = productsList.stream().map(productDto -> {
				return productTransformer.transform(productDto);
			}).collect(Collectors.toList());

		}
		return productDtosList;
	}

}
