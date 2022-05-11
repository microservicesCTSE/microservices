package com.pos.inventory.repository.transfomer;

import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.BankDto;
import com.pos.inventory.repository.domain.Bank;



@Component
public class BankTransformer implements BaseTransformer<Bank, BankDto > {

	@Override
	public BankDto transform(Bank bank) {
		BankDto bankDto = null;
		if(bank != null) {
			bankDto = new BankDto();
			bankDto.setBankId(bank.getBankId());
			bankDto.setBankName(bank.getBankName());
			bankDto.setLocation(bank.getLocation());
			bankDto.setIsActive(bank.getIsActive());
		}
		
		
		return bankDto;
	}

	@Override
	public Bank reverseTransform(BankDto bankDto) {
		Bank bank = null;
		if (bankDto != null) {
			bank = new Bank();
			bank.setBankId(bankDto.getBankId());
			bank.setBankName(bankDto.getBankName());
			bank.setLocation(bankDto.getLocation());
			bank.setIsActive(bankDto.getIsActive());
			
			
		}
		return bank;
	}

}
