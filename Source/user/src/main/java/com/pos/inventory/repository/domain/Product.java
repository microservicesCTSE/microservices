package com.pos.inventory.repository.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 *
 * @author S.Sivaragavan
 */
@Data
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
		@NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId"),
		@NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Product p WHERE p.productName = :productName"),
		@NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description = :description"),
		@NamedQuery(name = "Product.findByUnitPrice", query = "SELECT p FROM Product p WHERE p.unitPrice = :unitPrice"),
		@NamedQuery(name = "Product.findByRetailPrice", query = "SELECT p FROM Product p WHERE p.retailPrice = :retailPrice"),
		@NamedQuery(name = "Product.findByStockLevel", query = "SELECT p FROM Product p WHERE p.stockLevel = :stockLevel"),
		@NamedQuery(name = "Product.findByMinimumStock", query = "SELECT p FROM Product p WHERE p.minimumStock = :minimumStock"),
		@NamedQuery(name = "Product.findByMaximumStock", query = "SELECT p FROM Product p WHERE p.maximumStock = :maximumStock"),
		@NamedQuery(name = "Product.findByComments", query = "SELECT p FROM Product p WHERE p.comments = :comments"),
		@NamedQuery(name = "Product.findByNetWeight", query = "SELECT p FROM Product p WHERE p.netWeight = :netWeight"),
		@NamedQuery(name = "Product.findByGrossWeight", query = "SELECT p FROM Product p WHERE p.grossWeight = :grossWeight"),
		@NamedQuery(name = "Product.findByIsActive", query = "SELECT p FROM Product p WHERE p.isActive = :isActive"),
		@NamedQuery(name = "Product.findByPluCode", query = "SELECT p FROM Product p WHERE p.pluCode = :pluCode"),
		@NamedQuery(name = "Product.findByItemCode", query = "SELECT p FROM Product p WHERE p.itemCode = :itemCode"),
		@NamedQuery(name = "Product.findByBarcodeDigits", query = "SELECT p FROM Product p WHERE p.barcodeDigits = :barcodeDigits"),
		@NamedQuery(name = "Product.findByQuantity", query = "SELECT p FROM Product p WHERE p.quantity = :quantity"),
		@NamedQuery(name = "Product.findByDiscount", query = "SELECT p FROM Product p WHERE p.discount = :discount"),
		@NamedQuery(name = "Product.findByUpdatedDate", query = "SELECT p FROM Product p WHERE p.updatedDate = :updatedDate"),
		@NamedQuery(name = "Product.findByUpdatedTime", query = "SELECT p FROM Product p WHERE p.updatedTime = :updatedTime"),
		@NamedQuery(name = "Product.findByIsPercentage", query = "SELECT p FROM Product p WHERE p.isPercentage = :isPercentage"),
		@NamedQuery(name = "Product.findBySupplierId", query = "SELECT p FROM Product p WHERE p.supplierId = :supplierId"),
		@NamedQuery(name = "Product.findByExpiryDate", query = "SELECT p FROM Product p WHERE p.expiryDate = :expiryDate") })
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "product_id")
	private Long productId;
	@Basic(optional = false)
	@Column(name = "product_name")
	private String productName;
	@Column(name = "description")
	private String description;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "unit_price")
	private Double unitPrice;
	@Column(name = "retail_price")
	private Double retailPrice;
	@Column(name = "stock_level")
	private Integer stockLevel;
	@Column(name = "minimum_stock")
	private Integer minimumStock;
	@Column(name = "maximum_stock")
	private Integer maximumStock;
	@Column(name = "comments")
	private String comments;
	@Column(name = "net_weight")
	private Double netWeight;
	@Column(name = "gross_weight")
	private Double grossWeight;
	@Column(name = "is_active")
	private Boolean isActive;
	@Column(name = "plu_code")
	private String pluCode;
	@Column(name = "item_code")
	private String itemCode;
//	@JoinColumn(name = "barcode_number", referencedColumnName = "barcode_id")
//	@ManyToOne
//	private Barcode barcodeId;
	@Column(name = "barcode_digits")
	private String barcodeDigits;
	@JoinColumn(name = "brand_id", referencedColumnName = "brand_id")
	@ManyToOne
	private Brand brandId;
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	@ManyToOne
	private Category categoryId;
	@JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
	@ManyToOne
	private Branch branchId;
	@JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
	@ManyToOne
	private Currency currencyId;
	@JoinColumn(name = "unit_id", referencedColumnName = "unit_id")
	@ManyToOne
	private MeasurementUnit unitId;
	@JoinColumn(name = "sub_category_id", referencedColumnName = "sub_category_id")
	@ManyToOne
	private SubCategory subCategoryId;
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@ManyToOne
	private User userId;
	@Column(name = "quantity")
	private Integer quantity;
	@Column(name = "discount")
	private Double discount;
	@Column(name = "updated_date")
	private LocalDate updatedDate;
	@Column(name = "updated_time")
	private LocalTime updatedTime;
	@Column(name = "is_percentage")
	private Boolean isPercentage;
	@JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
	@ManyToOne
	private Supplier supplierId;
	@Column(name = "expiry_date")
	private LocalDate expiryDate;

}
