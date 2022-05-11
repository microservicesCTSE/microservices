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
 * @author S.Sivaragavn
 */
@Data
@Entity
@Table(name = "measurement_unit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeasurementUnit.findAll", query = "SELECT m FROM MeasurementUnit m")
    , @NamedQuery(name = "MeasurementUnit.findByUnitId", query = "SELECT m FROM MeasurementUnit m WHERE m.unitId = :unitId")
    , @NamedQuery(name = "MeasurementUnit.findByUnitCode", query = "SELECT m FROM MeasurementUnit m WHERE m.unitCode = :unitCode")
    , @NamedQuery(name = "MeasurementUnit.findByUnitDescription", query = "SELECT m FROM MeasurementUnit m WHERE m.unitDescription = :unitDescription")
    , @NamedQuery(name = "MeasurementUnit.findByIsActive", query = "SELECT m FROM MeasurementUnit m WHERE m.isActive = :isActive")})
public class MeasurementUnit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "unit_id")
    private Long unitId;
    @Column(name = "unit_code")
    private String unitCode;
    @Column(name = "unit_description")
    private String unitDescription;
    @Column(name = "is_active")
    private Boolean isActive;

}
