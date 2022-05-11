package com.pos.inventory.repository.domain;

import java.io.Serializable;
import java.time.LocalDate;

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
 * @author HP
 */
@Data
@Entity
@Table(name = "supplier")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s"),
		@NamedQuery(name = "Supplier.findBySupplierId", query = "SELECT s FROM Supplier s WHERE s.supplierId = :supplierId"),
		@NamedQuery(name = "Supplier.findBySupplierName", query = "SELECT s FROM Supplier s WHERE s.supplierName = :supplierName"),
		@NamedQuery(name = "Supplier.findByPhoneNumber", query = "SELECT s FROM Supplier s WHERE s.phoneNumber = :phoneNumber"),
		@NamedQuery(name = "Supplier.findByAddress", query = "SELECT s FROM Supplier s WHERE s.address = :address"),
		@NamedQuery(name = "Supplier.findByCity", query = "SELECT s FROM Supplier s WHERE s.city = :city"),
		@NamedQuery(name = "Supplier.findByZipCode", query = "SELECT s FROM Supplier s WHERE s.zipCode = :zipCode"),
		@NamedQuery(name = "Supplier.findByEmail", query = "SELECT s FROM Supplier s WHERE s.email = :email"),
		@NamedQuery(name = "Supplier.findByComment", query = "SELECT s FROM Supplier s WHERE s.comment = :comment"),
		@NamedQuery(name = "Supplier.findByIsActive", query = "SELECT s FROM Supplier s WHERE s.isActive = :isActive") })
public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "supplier_id")
	private Long supplierId;
	@Column(name = "supplier_name")
	private String supplierName;
	@Column(name = "phone_number")
	private Integer phoneNumber;
	@Column(name = "address")
	private String address;
	@Column(name = "city")
	private String city;
	@Column(name = "zip_code")
	private String zipCode;
	@Column(name = "email")
	private String email;
	@Column(name = "comment")
	private String comment;
	@Column(name = "is_active")
	private Boolean isActive;
	@Column(name = "added_date")
	private LocalDate addedDate;
	@Column(name = "supplier_ref_number")
	private String supplierRefNumber;
	@Column(name = "company_name")
	private String companyName;
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//    @ManyToOne
	private Long userId;
}
