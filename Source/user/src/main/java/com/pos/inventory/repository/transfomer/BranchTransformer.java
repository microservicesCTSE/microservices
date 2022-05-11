package com.pos.inventory.repository.transfomer;

import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.BranchDto;
import com.pos.inventory.repository.domain.Branch;


@Component
public class BranchTransformer implements BaseTransformer<Branch, BranchDto>{

	@Override
	public BranchDto transform(Branch branch) {
		BranchDto branchDto = null;
		if (branch != null) {
			branchDto = new BranchDto();
			branchDto.setBranchId(branch.getBranchId());
			branchDto.setBranchLocation(branch.getBranchLocation());
			branchDto.setBranchName(branch.getBranchName());
			branchDto.setIsActive(branch.getIsActive());

		}
		return branchDto;
	}

	@Override
	public Branch reverseTransform(BranchDto branchDto) {
		Branch branch = null;
		if (branchDto != null) {
			branch = new Branch();
			branch.setBranchId(branchDto.getBranchId());
			branch.setBranchLocation(branchDto.getBranchLocation());
			branch.setBranchName(branchDto.getBranchName());
			branch.setIsActive(branchDto.getIsActive());
	}
		return branch;

}
}
