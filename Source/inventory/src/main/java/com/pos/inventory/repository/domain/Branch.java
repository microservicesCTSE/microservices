package com.pos.inventory.repository.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import javax.persistence.NamedQueries;

@Data
@Entity
@Table(name = "branch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Branch.findAll", query = "SELECT b FROM Branch b")
    , @NamedQuery(name = "Branch.findByBranchId", query = "SELECT b FROM Branch b WHERE b.branchId = :branchId")
    , @NamedQuery(name = "Branch.findByBranchName", query = "SELECT b FROM Branch b WHERE b.branchName = :branchName")
    , @NamedQuery(name = "Branch.findByBranchLocation", query = "SELECT b FROM Branch b WHERE b.branchLocation = :branchLocation")
    , @NamedQuery(name = "Branch.findByIsActive", query = "SELECT b FROM Branch b WHERE b.isActive = :isActive")})
public class Branch  implements Serializable{
	  private static final long serialVersionUID = 1L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "branch_id")
	    private Long branchId;
	    @Column(name = "branch_name")
	    private String branchName;
	    @Column(name = "branch_location")
	    private String branchLocation;
	    @Column(name = "is_active")
	    private Boolean isActive;
}
