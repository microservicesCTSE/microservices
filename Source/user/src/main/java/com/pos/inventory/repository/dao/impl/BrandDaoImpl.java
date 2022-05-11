package com.pos.inventory.repository.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.pos.inventory.common.dto.BrandDto;
import com.pos.inventory.repository.dao.BrandDao;
import com.pos.inventory.repository.domain.Brand;
import com.pos.inventory.repository.transfomer.BrandTransformer;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BrandDaoImpl extends BaseDaoImpl<Brand> implements BrandDao {

	@Autowired
	BrandTransformer brandTransformer;

	@Transactional
	@Override
	public List<BrandDto> getAllBrandDetails() {
		log.info("BrandDaoImpl.getAllBrandDetails() invoked");
		Criteria criteria = getCurrentSession().createCriteria(Brand.class);
		List<BrandDto> brandDtoList = null;
		List<Brand> brandList = (List<Brand>) criteria.list();
		if (brandList != null && !brandList.isEmpty()) {
			brandDtoList = new ArrayList<>();
			for (Brand brand : brandList) {
				brandDtoList.add(brandTransformer.transform(brand));
			}
		}
		return brandDtoList;
	}

	@Override
	public BrandDto saveBrand(BrandDto brandDto) {
		log.info("BrandDaoImpl.saveBrand() invoked.");
		Criteria criteria = getCurrentSession().createCriteria(Brand.class, "Brand");
		criteria.add(Restrictions.eq("Brand.brandName", brandDto.getBrandName()));
		List<Brand> brandList = (List<Brand>) criteria.list();
		if (!(brandList != null && brandList.size() > 0)) {
			log.info("Size....." + (brandList.size()));
			Brand brand = brandTransformer.reverseTransform(brandDto);
			Brand saveBrand = saveOrUpdate(brand);
			return brandTransformer.transform(saveBrand);
		}
		return null;
	}

	/*
	 * public BrandDto saveBrand(BrandDto brandDto) {
	 * log.info("BrandDaoImpl.saveBrand() invoked."); Brand brand =
	 * brandTransformer.reverseTransform(brandDto); Brand saveBrand =
	 * saveOrUpdate(brand); return brandTransformer.transform(saveBrand); }
	 */

}
