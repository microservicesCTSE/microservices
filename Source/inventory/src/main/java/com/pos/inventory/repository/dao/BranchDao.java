package com.pos.inventory.repository.dao;

import java.util.List;
import com.pos.inventory.common.dto.BranchDto;

public interface BranchDao {
	List<BranchDto> getAllBranchDetails();

	BranchDto saveBranch(BranchDto branchDto);
}
