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

import lombok.Data;

/**
 *
 * @author S.Sivaragavan
 */
@Data
@Entity
@Table(name = "user_type")
@XmlRootElement
public class UserType implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "user_type_id")
	private Long userTypeId;
	@Column(name = "user_type")
	private String userType;
	@Column(name = "description")
	private String description;
	@Column(name = "is_active")
	private Boolean isActive;
	@OneToMany(mappedBy = "userTypeId")
	private List<User> userList;

}
