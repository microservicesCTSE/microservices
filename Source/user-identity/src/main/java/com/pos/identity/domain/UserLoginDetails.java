package com.pos.identity.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

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
 * @author Thilina
 */
@Data
@Entity
@Table(name = "user_login_details", schema = "")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "UserLoginDetails.findAll", query = "SELECT u FROM UserLoginDetails u"),
		@NamedQuery(name = "UserLoginDetails.findByUserLoginDetailsId", query = "SELECT u FROM UserLoginDetails u WHERE u.userLoginDetailsId = :userLoginDetailsId"),
		@NamedQuery(name = "UserLoginDetails.findByUsername", query = "SELECT u FROM UserLoginDetails u WHERE u.username = :username"),
		@NamedQuery(name = "UserLoginDetails.findByUserIp", query = "SELECT u FROM UserLoginDetails u WHERE u.userIp = :userIp"),
		@NamedQuery(name = "UserLoginDetails.findByLoginDate", query = "SELECT u FROM UserLoginDetails u WHERE u.loginDate = :loginDate"),
		@NamedQuery(name = "UserLoginDetails.findByLoginTime", query = "SELECT u FROM UserLoginDetails u WHERE u.loginTime = :loginTime"),
		@NamedQuery(name = "UserLoginDetails.findByIsSuccess", query = "SELECT u FROM UserLoginDetails u WHERE u.isSuccess = :isSuccess") })
public class UserLoginDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "user_login_details_id", nullable = false)
	private Long userLoginDetailsId;
	@Column(name = "username", length = 250)
	private String username;
	@Column(name = "user_ip", length = 20)
	private String userIp;
	@Column(name = "login_date")
	// @Temporal(TemporalType.DATE)
	private LocalDate loginDate;
	@Column(name = "login_time")
	// @Temporal(TemporalType.TIME)
	private LocalTime loginTime;
	@Column(name = "is_success")
	private Boolean isSuccess;
	@Column(name = "invalid_login_count")
	private Integer invalidLoginCount;

}
