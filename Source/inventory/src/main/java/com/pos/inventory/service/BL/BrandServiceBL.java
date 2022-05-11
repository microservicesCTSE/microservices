package com.pos.inventory.service.BL;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.inventory.common.dto.BrandDto;
import com.pos.inventory.repository.dao.BrandDao;
import com.pos.inventory.service.client.AlgoriolFeignClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandServiceBL {

	@Autowired
	BrandDao brandDao;

	@Autowired
	AlgoriolFeignClient algoriolFeignClient;

	public List<BrandDto> getAllBrandDetails() {
		log.info("BrandServiceBL.getAllBrandDetails() invoked");
		return brandDao.getAllBrandDetails();
	}

	public BrandDto saveBrand(BrandDto brandDto) {
		log.info("BrandServiceBL.saveBrand() invoked.");
		BrandDto brand = brandDao.saveBrand(brandDto);
//		if (brand != null) {
//			algoriolFeignClient.saveBrand(brandDto);
//		}
		return brand;
	}

}
