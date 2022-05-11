package com.pos.inventory.repository.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.pos.inventory.common.dto.BranchDto;
import com.pos.inventory.repository.dao.BranchDao;
import com.pos.inventory.repository.domain.Branch;
import com.pos.inventory.repository.transfomer.BranchTransformer;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BranchDaoImpl extends BaseDaoImpl<Branch> implements BranchDao {

	@Autowired
	BranchTransformer branchTransformer;

	@Transactional

	@Override
	public List<BranchDto> getAllBranchDetails() {
		log.info("BranchDaoImpl.getAllBranchDetails() invoked");
		Criteria criteria = getCurrentSession().createCriteria(Branch.class);
		List<BranchDto> branchDtoList = null;
		List<Branch> branchList = (List<Branch>) criteria.list();
		if (branchList != null && !branchList.isEmpty()) {
			branchDtoList = new ArrayList<>();
			for (Branch branch : branchList) {
				branchDtoList.add(branchTransformer.transform(branch));
			}
		}
		return branchDtoList;
	}

	@Transactional
	@Override
	public BranchDto saveBranch(BranchDto branchDto) {
		log.info("BranchDaoImpl.saveBranch() invoked.");
		Criteria criteria = getCurrentSession().createCriteria(Branch.class, "Branch");
		criteria.add(Restrictions.eq("Branch.branchName", branchDto.getBranchName()));
		List<Branch> branchList = (List<Branch>) criteria.list();
		if (!(branchList != null && branchList.size() > 0)) {
			Branch branch = branchTransformer.reverseTransform(branchDto);
			Branch saveBranch = saveOrUpdate(branch);
			return branchTransformer.transform(saveBranch);
		}
		return null;
	}
}
