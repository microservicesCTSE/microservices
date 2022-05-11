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
 * @author S.Sivaragavn
 */
@Data
@Entity
@Table(name = "sub_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubCategory.findAll", query = "SELECT s FROM SubCategory s")
    , @NamedQuery(name = "SubCategory.findBySubCategoryId", query = "SELECT s FROM SubCategory s WHERE s.subCategoryId = :subCategoryId")
    , @NamedQuery(name = "SubCategory.findBySubCategoryName", query = "SELECT s FROM SubCategory s WHERE s.subCategoryName = :subCategoryName")
    , @NamedQuery(name = "SubCategory.findByIsActive", query = "SELECT s FROM SubCategory s WHERE s.isActive = :isActive")})
public class SubCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sub_category_id")
    private Long subCategoryId;
    @Column(name = "sub_category_name")
    private String subCategoryName;
    @Column(name = "is_active")
    private Boolean isActive;
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne
    private Category categoryId;

}
