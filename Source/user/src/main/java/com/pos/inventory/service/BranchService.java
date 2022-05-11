package com.pos.inventory.service;

import com.pos.inventory.common.dto.BranchDto;
import com.pos.inventory.common.dto.ResponseDto;

public interface BranchService {
	ResponseDto getAllBranchDeatails();
	
	ResponseDto saveBranch(BranchDto branchDto);
}
