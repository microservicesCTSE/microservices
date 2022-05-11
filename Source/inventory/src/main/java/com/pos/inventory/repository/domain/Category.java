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
@Table(name = "category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c"),
    @NamedQuery(name = "Category.findByCategoryId", query = "SELECT c FROM Category c WHERE c.categoryId = :categoryId"),
    @NamedQuery(name = "Category.findByCategoryName", query = "SELECT c FROM Category c WHERE c.categoryName = :categoryName"),
    @NamedQuery(name = "Category.findByCategoryDescription", query = "SELECT c FROM Category c WHERE c.categoryDescription = :categoryDescription"),
    @NamedQuery(name = "Category.findByIsActive", query = "SELECT c FROM Category c WHERE c.isActive = :isActive"),
    @NamedQuery(name = "Category.findByBrandId", query = "SELECT c FROM Category c WHERE c.brandId = :brandId"),})
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "category_description")
    private String categoryDescription;
    @Column(name = "is_active")
    private Boolean isActive;
    @JoinColumn(name = "brand_id", referencedColumnName = "brand_id")
    @ManyToOne
    private Brand brandId;
}
