package com.companycore.shopping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("Product")
public class productController {
	
	@GetMapping("/getAllProductDetails")
	public String hello() {
		return "Keeka";
		
	}

}
