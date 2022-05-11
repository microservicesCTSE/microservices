package com.pos.inventory.service.BL;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.inventory.common.dto.BranchDto;
import com.pos.inventory.repository.dao.BranchDao;
import com.pos.inventory.service.client.AlgoriolFeignClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BranchServiceBL {

	@Autowired
	BranchDao branchDao;

	@Autowired
	AlgoriolFeignClient algoriolFeignClient;

	public List<BranchDto> getAllBranchDetails() {
		log.info("BranchServiceBL.getAllBranchDetails() invoked");
		return branchDao.getAllBranchDetails();
	}

	public BranchDto saveBranch(BranchDto branchDto) {
		log.info("BranchServiceBL.saveBranch() invoked.");
		BranchDto branch = branchDao.saveBranch(branchDto);
//		if (branch != null) {
//			algoriolFeignClient.saveBranch(branchDto);
//		}
		return branch;
	}
}