package com.pos.inventory.repository.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 *
 * @author Sivaragavan
 */
@Data
@Entity
@Table(name = "barcode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Barcode.findAll", query = "SELECT b FROM Barcode b"),
    @NamedQuery(name = "Barcode.findByBarcodeId", query = "SELECT b FROM Barcode b WHERE b.barcodeId = :barcodeId"),
    @NamedQuery(name = "Barcode.findByBarcodeDigits", query = "SELECT b FROM Barcode b WHERE b.barcodeDigits = :barcodeDigits"),
    @NamedQuery(name = "Barcode.findByIsActive", query = "SELECT b FROM Barcode b WHERE b.isActive = :isActive")})
public class Barcode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "barcode_id")
    private Long barcodeId;
    @Column(name = "barcode_digits")
    private String barcodeDigits;
    @Column(name = "is_active")
    private Boolean isActive;

   
}
