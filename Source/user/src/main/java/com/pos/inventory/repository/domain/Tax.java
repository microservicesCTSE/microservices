package com.pos.inventory.repository.domain;

import java.io.Serializable;
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
@Table(name = "tax")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tax.findAll", query = "SELECT t FROM Tax t"),
		@NamedQuery(name = "Tax.findByTaxId", query = "SELECT t FROM Tax t WHERE t.taxId = :taxId"),
		@NamedQuery(name = "Tax.findByTaxName", query = "SELECT t FROM Tax t WHERE t.taxName = :taxName"),
		@NamedQuery(name = "Tax.findByTaxFees", query = "SELECT t FROM Tax t WHERE t.taxFees = :taxFees"),
		@NamedQuery(name = "Tax.findByIsActive", query = "SELECT t FROM Tax t WHERE t.isActive = :isActive"),
		@NamedQuery(name = "Tax.findByTaxPercentage", query = "SELECT t FROM Tax t WHERE t.taxPercentage = :taxPercentage"),
		@NamedQuery(name = "Tax.findByNetProfit", query = "SELECT t FROM Tax t WHERE t.totalProfit = :totalProfit"),
		@NamedQuery(name = "Tax.findByTotalProfit", query = "SELECT t FROM Tax t WHERE t.totalProfit = :totalProfit"),
		@NamedQuery(name = "Tax.findByIsPaid", query = "SELECT t FROM Tax t WHERE t.isPaid = :isPaid") })
public class Tax implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "tax_id")
	private Long taxId;
	@Column(name = "tax_name")
	private String taxName;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "tax_fees")
	private Double taxFees;
	@Column(name = "is_active")
	private Boolean isActive;
	@Column(name = "tax_percentage")
	private Double taxPercentage;
	@Column(name = "net_profit")
	private Double netProfit;
	@Column(name = "total_profit")
	private Double totalProfit;
	@Column(name = "is_paid")
	private Boolean isPaid;
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	@ManyToOne
	private Category categoryId;
}
