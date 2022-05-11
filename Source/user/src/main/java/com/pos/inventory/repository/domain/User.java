package com.pos.inventory.repository.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 *
 * @author HP
 */
@Data
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
		@NamedQuery(name = "User.findByFullName", query = "SELECT u FROM User u WHERE u.fullName = :fullName"),
		@NamedQuery(name = "User.findByUserName", query = "SELECT u FROM User u WHERE u.userName = :userName"),
		@NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
		@NamedQuery(name = "User.findByContactNo", query = "SELECT u FROM User u WHERE u.contactNo = :contactNo"),
		@NamedQuery(name = "User.findByCountry", query = "SELECT u FROM User u WHERE u.country = :country"),
		@NamedQuery(name = "User.findByIsApproved", query = "SELECT u FROM User u WHERE u.isApproved = :isApproved"),
		@NamedQuery(name = "User.findByApprovedDate", query = "SELECT u FROM User u WHERE u.approvedDate = :approvedDate"),
		@NamedQuery(name = "User.findByIsLocked", query = "SELECT u FROM User u WHERE u.isLocked = :isLocked"),
		@NamedQuery(name = "User.findByIsLockedDate", query = "SELECT u FROM User u WHERE u.isLockedDate = :isLockedDate"),
		@NamedQuery(name = "User.findByVerifyToken", query = "SELECT u FROM User u WHERE u.verifyToken = :verifyToken"),
		@NamedQuery(name = "User.findByIsActive", query = "SELECT u FROM User u WHERE u.isActive = :isActive") })
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "contact_no")
	private Integer contactNo;
	@Column(name = "country")
	private String country;
	@Column(name = "is_approved")
	private Boolean isApproved;
	@Column(name = "approved_date")
//    @Temporal(TemporalType.TIMESTAMP)
	private LocalDate approvedDate;
	@Column(name = "is_locked")
	private Boolean isLocked;
	@Column(name = "is_locked_date")
//    @Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime isLockedDate;
	@Column(name = "verify_token")
	private String verifyToken;
	@Column(name = "is_active")
	private Boolean isActive;
	@Column(name = "is_new_user")
	private Boolean isNewUser;
//	@OneToMany(mappedBy = "userId")
//	private List<UserRole> userRoleList;
	@JoinColumn(name = "user_type_id", referencedColumnName = "user_type_id")
	@ManyToOne
	private UserType userTypeId;
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	@ManyToOne
	private Client client_id;
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	@ManyToOne
	private Role roleId;
}
