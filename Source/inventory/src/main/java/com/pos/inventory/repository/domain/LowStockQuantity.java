package com.pos.inventory.repository.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author RKeerthiga
 */
@Data
@Entity
@Table(name = "low_stock_quantity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LowStockQuantity.findAll", query = "SELECT l FROM LowStockQuantity l"),
    @NamedQuery(name = "LowStockQuantity.findByLowStockQuantityId", query = "SELECT l FROM LowStockQuantity l WHERE l.lowStockQuantityId = :lowStockQuantityId"),
    @NamedQuery(name = "LowStockQuantity.findByIsLowStock", query = "SELECT l FROM LowStockQuantity l WHERE l.isLowStock = :isLowStock")})
public class LowStockQuantity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "low_stock_quantity_id")
    private Long lowStockQuantityId;
    @Column(name = "is_low_stock")
    private Boolean isLowStock;
    //@JoinColumn(name = "stock_id", referencedColumnName = "stock_id")
    //@ManyToOne
    @Column(name = "stock_id")
    private Long stockId;
    //@JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    //@ManyToOne
    @Column(name = "supplier_id")
    private Long supplierId;
}