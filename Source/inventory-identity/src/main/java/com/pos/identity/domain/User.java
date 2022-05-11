package com.pos.identity.domain;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "password")
	private String password;

	@Column(name = "username")
	private String username;

	@Column(name = "is_approved")
	private Boolean isApproved;

	@Column(name = "is_locked")
	private Boolean isLocked;

	@Column(name = "locked_date_time")
	private LocalDateTime lockedDateTime;

	@Column(name = "failed_attempt")
	private Integer failedAttempt;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "is_tfa_enabled")
	private Boolean isTfaEnabled;
	@Column(name = "tfa_code", length = 45)
	private String tfaCode;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "tfa_exp_time", precision = 22)
	private Double tfaExpTime;
	@Column(name = "tfa_default_type", length = 45)
	private String tfaDefaultType;

//    @Column(name = "user_type_id")
//    private Long userTypeFk;
	@JoinColumn(name = "user_type_id", referencedColumnName = "user_type_id")
	@ManyToOne
	private UserType userTypeId;

	@Column(name = "client_id")
	private Long clientId;

	@Column(name = "is_verified")
	private Boolean isVerified;

}