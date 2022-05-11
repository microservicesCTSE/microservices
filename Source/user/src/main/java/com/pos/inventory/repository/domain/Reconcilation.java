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

import lombok.Data;

/**
 *
 * @author Lathusan Thurairajah
 */

@Data
@Entity
@Table(name = "reconcilation")
@NamedQueries({
    @NamedQuery(name = "Reconcilation.findAll", query = "SELECT r FROM Reconcilation r"),
    @NamedQuery(name = "Reconcilation.findByReconcilationId", query = "SELECT r FROM Reconcilation r WHERE r.reconcilationId = :reconcilationId"),
    @NamedQuery(name = "Reconcilation.findByCountCurrentQuntaty", query = "SELECT r FROM Reconcilation r WHERE r.countCurrentQuntaty = :countCurrentQuntaty"),
    @NamedQuery(name = "Reconcilation.findByDifferenceQuntaty", query = "SELECT r FROM Reconcilation r WHERE r.differenceQuntaty = :differenceQuntaty"),
    @NamedQuery(name = "Reconcilation.findByDate", query = "SELECT r FROM Reconcilation r WHERE r.date = :date"),
    @NamedQuery(name = "Reconcilation.findByUserId", query = "SELECT r FROM Reconcilation r WHERE r.userId = :userId")})
public class Reconcilation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reconcilation_id")
    private Long reconcilationId;
    @Column(name = "count_current_quntaty")
    private Integer countCurrentQuntaty;
    @Column(name = "difference_quntaty")
    private Integer differenceQuntaty;
    @Column(name = "date")
//  @Temporal(TemporalType.DATE)
    private LocalDate date;
    @Column(name = "user_id")
    private Long userId;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne
    private Product productId;

}
