package com.pos.inventory.repository.transfomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.TaxDto;
import com.pos.inventory.repository.domain.Tax;

@Component
public class TaxTransformer implements BaseTransformer<Tax, TaxDto> {

	@Autowired
	CategoryTransformer categoryTransformer;

	@Override
	public TaxDto transform(Tax tax) {
		TaxDto taxDto = null;
		if (tax != null) {
			taxDto = new TaxDto();
			taxDto.setTaxId(tax.getTaxId());
			taxDto.setTaxName(tax.getTaxName());
			taxDto.setTaxFees(tax.getTaxFees());
			taxDto.setIsActive(tax.getIsActive());
			taxDto.setTaxPercentage(tax.getTaxPercentage());
			taxDto.setNetProfit(tax.getNetProfit());
			taxDto.setTotalProfit(tax.getTotalProfit());
			taxDto.setIsPaid(tax.getIsPaid());
			if (tax.getCategoryId() != null) {
				taxDto.setCategoryDto(categoryTransformer.transform(tax.getCategoryId()));
			}

		}
		return taxDto;
	}

	@Override
	public Tax reverseTransform(TaxDto taxDto) {
		Tax tax = null;
		if (taxDto != null) {
			tax = new Tax();
			tax.setTaxId(taxDto.getTaxId());
			tax.setTaxName(taxDto.getTaxName());
			tax.setTaxFees(taxDto.getTaxFees());
			tax.setIsActive(taxDto.getIsActive());
			tax.setTaxPercentage(taxDto.getTaxPercentage());
			tax.setNetProfit(taxDto.getNetProfit());
			tax.setTotalProfit(taxDto.getTotalProfit());
			tax.setIsPaid(taxDto.getIsPaid());
			if (taxDto.getCategoryDto() != null) {
				tax.setCategoryId(categoryTransformer.reverseTransform(taxDto.getCategoryDto()));
			}

		}
		return tax;
	}

}
