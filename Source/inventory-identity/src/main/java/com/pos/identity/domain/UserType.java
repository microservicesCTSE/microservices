package com.pos.identity.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_type")
@Data
public class UserType implements Serializable{

	private static final long serialVersionUID = -3091852328137361173L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_type_id", nullable = false)
    private Long userTypeId;
    @Column(name = "user_type_desc", length = 500)
    private String userTypeDesc;

}