/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author HP
 */
@Data
@Entity
@Table(name = "country")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c"),
    @NamedQuery(name = "Country.findByCountryId", query = "SELECT c FROM Country c WHERE c.countryId = :countryId"),
    @NamedQuery(name = "Country.findByCountryName", query = "SELECT c FROM Country c WHERE c.countryName = :countryName"),
    @NamedQuery(name = "Country.findByReferenceCountryCode", query = "SELECT c FROM Country c WHERE c.referenceCountryCode = :referenceCountryCode"),
    @NamedQuery(name = "Country.findByIsActive", query = "SELECT c FROM Country c WHERE c.isActive = :isActive")})
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "country_id")
    private Long countryId;
    @Column(name = "country_name")
    private String countryName;
    @Column(name = "reference_country_code")
    private String referenceCountryCode;
    @Column(name = "is_active")
    private Boolean isActive;
    
}