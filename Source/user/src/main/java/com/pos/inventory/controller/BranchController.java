package com.pos.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pos.inventory.common.dto.BranchDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.service.BranchService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("branch")
@Slf4j
public class BranchController {

	@Autowired
	BranchService branchService;

	@GetMapping("/gellAllBranchDetails")
	public ResponseDto getAllBranchDetails() {
		log.info("BranchController.getAllBranchDetails() invoked");
		return branchService.getAllBranchDeatails();

	}

	@PostMapping("/save")
	public ResponseDto saveBranch(@RequestBody BranchDto brandDto) {
		log.info("BranchController.saveBranch() invoked");
		return branchService.saveBranch(brandDto);
	}
}
