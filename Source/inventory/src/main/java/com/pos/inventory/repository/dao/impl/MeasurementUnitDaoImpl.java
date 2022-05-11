package com.pos.inventory.repository.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.pos.inventory.common.dto.MeasurementUnitDto;
import com.pos.inventory.repository.dao.MeasurementUnitDao;
import com.pos.inventory.repository.domain.MeasurementUnit;
import com.pos.inventory.repository.transfomer.MeasurementUnitTransformer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MeasurementUnitDaoImpl extends BaseDaoImpl<MeasurementUnit> implements MeasurementUnitDao {

	@Autowired
	MeasurementUnitTransformer measurementUnitTransformer;

	@Transactional
	@Override
	public List<MeasurementUnitDto> getAllMeasurementUnitDetails() {
		log.info("MeasurementUnitDaoImpl.getAllMeasurementUnitDetails() invoked");
		Criteria criteria = getCurrentSession().createCriteria(MeasurementUnit.class);
		List<MeasurementUnitDto> measurementUnitDtoList = null;
		List<MeasurementUnit> measurementUnitList = (List<MeasurementUnit>) criteria.list();
		if (measurementUnitList != null && !measurementUnitList.isEmpty()) {
			measurementUnitDtoList = new ArrayList<>();
			for (MeasurementUnit measurementUnit : measurementUnitList) {
				measurementUnitDtoList.add(measurementUnitTransformer.transform(measurementUnit));
			}
		}
		return measurementUnitDtoList;
	}

	@Transactional
	@Override
	public MeasurementUnitDto saveMeasurementUnitDetails(MeasurementUnitDto measurementUnitDto) {
		log.info("MeasurementUnitDaoImpl.saveMeasurementUnitDetails() invoked.");
		Criteria criteria = getCurrentSession().createCriteria(MeasurementUnit.class, "MeasurementUnit");
		criteria.add(Restrictions.eq("MeasurementUnit.unitCode", measurementUnitDto.getUnitCode()));
		List<MeasurementUnit> unitList = (List<MeasurementUnit>) criteria.list();
		if (!(unitList != null && unitList.size() > 0)) {
			MeasurementUnit measurementUnit = measurementUnitTransformer.reverseTransform(measurementUnitDto);
			MeasurementUnit saveMeasurementUnit = saveOrUpdate(measurementUnit);
			return measurementUnitTransformer.transform(saveMeasurementUnit);
		}
		return null;
	}

	@Transactional
	@Override
	public MeasurementUnitDto updateMeasurementUnitDetailsStatus(MeasurementUnitDto measurementUnitDto) {
		log.info("MeasurementUnitDaoImpl.updateMeasurementUnitDetailsStatus() invoked.");
		MeasurementUnit measurementUnit = measurementUnitTransformer.reverseTransform(measurementUnitDto);
		MeasurementUnit saveMeasurementUnit = merge(measurementUnit);
		return measurementUnitTransformer.transform(saveMeasurementUnit);
	}

	@Override
	public MeasurementUnitDto updateMeasurementUnitDetailsStatus(Long unitId, Boolean isActive) {
		log.info("MeasurementUnitDaoImpl.getByUnitId() invoked.");
		Criteria criteria = getCurrentSession().createCriteria(MeasurementUnit.class, "MeasurementUnit");
		criteria.add(Restrictions.eq("MeasurementUnit.unitId", unitId));
		MeasurementUnit measurementUnit = (MeasurementUnit) criteria.uniqueResult();
		MeasurementUnitDto measurementUnitDto = null;
		if (measurementUnit != null) {
			measurementUnitDto = measurementUnitTransformer.transform(measurementUnit);
		}
		return measurementUnitDto;
	}

	@Transactional
	@Override
	public MeasurementUnitDto updateMeasurementUnitDetails(MeasurementUnitDto measurementUnitDto) {
		log.info("MeasurementUnitDaoImpl.updateMeasurementUnitDetails() invoked.");
		MeasurementUnit measurementUnit = measurementUnitTransformer.reverseTransform(measurementUnitDto);
		MeasurementUnit saveMeasurementUnit = merge(measurementUnit);
		return measurementUnitTransformer.transform(saveMeasurementUnit);
	}

}
