package com.pos.inventory.repository.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Data;

/**
 *
 * @author HP
 */
@Data
@Entity
@Table(name = "payment_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentType.findAll", query = "SELECT p FROM PaymentType p")
    , @NamedQuery(name = "PaymentType.findByPaymentTypeId", query = "SELECT p FROM PaymentType p WHERE p.paymentTypeId = :paymentTypeId")
    , @NamedQuery(name = "PaymentType.findByPaymentTypeName", query = "SELECT p FROM PaymentType p WHERE p.paymentTypeName = :paymentTypeName")
    , @NamedQuery(name = "PaymentType.findByIsActive", query = "SELECT p FROM PaymentType p WHERE p.isActive = :isActive")})
public class PaymentType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "payment_type_id")
    private Long paymentTypeId;
    @Column(name = "payment_type_name")
    private String paymentTypeName;
    @Column(name = "is_active")
    private Boolean isActive;
//    @OneToMany(mappedBy = "paymentTypeId")
//    private List<DuePayment> duePaymentList;

}
