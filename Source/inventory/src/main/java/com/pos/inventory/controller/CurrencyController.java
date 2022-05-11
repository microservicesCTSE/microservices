package com.pos.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pos.inventory.common.dto.CurrencyDto;
import com.pos.inventory.common.dto.ResponseDto;
import com.pos.inventory.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("currency")
@Slf4j
public class CurrencyController {

	@Autowired
	CurrencyService currencyService;

	@GetMapping("/gellAllCurrencyDetails")
	public ResponseDto getAllCurrencyDetails() {
		log.info("CurrencyController.getAllCurrencyDetails() invoked");
		return currencyService.getAllCurrencyDeatails();
	}

	@PostMapping("/save")
	public ResponseDto saveCurrency(@RequestBody CurrencyDto currencyDto) {
		log.info("CurrencyController.saveCurrency() invoked");
		return currencyService.saveCurrency(currencyDto);
	}

}
