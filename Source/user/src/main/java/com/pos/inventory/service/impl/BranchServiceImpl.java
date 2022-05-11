package com.pos.inventory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pos.inventory.common.constants.ApplicationMessageConstants;
import com.pos.inventory.common.dto.BranchDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.service.BranchService;
import com.pos.inventory.service.BL.BranchServiceBL;
import com.pos.inventory.service.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BranchServiceImpl implements BranchService {
	@Autowired
	ServiceUtil serviceUtil;

	@Autowired
	BranchServiceBL branchServiceBL;

	@Override
	public ResponseDto getAllBranchDeatails() {

		log.info("BranchServiceImpl.getAllBranchDeatails() invoked");
		ResponseDto responseDto = null;
		try {
			List<BranchDto> branchDtoList = branchServiceBL.getAllBranchDetails();
			if (branchDtoList != null && !branchDtoList.isEmpty()) {
				log.info("Retrieve All Branch Details.");
				responseDto = serviceUtil.getServiceResponse(branchDtoList);
			} else {
				log.info("Unable to retrieve All Branch details.");
				responseDto = serviceUtil.getErrorServiceResponse(
						ApplicationMessageConstants.ServiceErrorMessages.ERR_RETRIEVE_ALL_BRANCH_DETAILS);
			}
		} catch (Exception e) {
			log.error("Exception occurs while retrieving All Branch details.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_RETRIEVE_ALL_BRANCH_DETAILS);
		}
		return responseDto;

	}

	@Override
	public ResponseDto saveBranch(BranchDto branchDto) {
		log.info("BranchServiceImpl.saveBranch(BranchDto branchDto) invoked");
		ResponseDto responseDto = null;
		try {
			BranchDto savedBranchDto = branchServiceBL.saveBranch(branchDto);
			if (savedBranchDto != null) {
				log.info("branch saved.");
				responseDto = serviceUtil.getServiceResponse(savedBranchDto);
			} else {
				log.info("Unable to save branch.");
				responseDto = serviceUtil
						.getErrorServiceResponse(ApplicationMessageConstants.ServiceErrorMessages.ERR_SAVE_BRANCH);
			}
		} catch (Exception e) {
			log.error("Exception occurs while saving branch.", e);
			responseDto = serviceUtil.getExceptionServiceResponseByProperties(
					ApplicationMessageConstants.ServiceErrorMessages.EX_SAVE_BRANCH);
		}
		return responseDto;
	}

}
