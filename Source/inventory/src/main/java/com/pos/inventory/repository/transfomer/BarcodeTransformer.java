package com.pos.inventory.repository.transfomer;

import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.BarcodeDto;
import com.pos.inventory.repository.domain.Barcode;


@Component
public class BarcodeTransformer implements BaseTransformer<Barcode, BarcodeDto>{

	@Override
	public BarcodeDto transform(Barcode barcode) {
		BarcodeDto barcodeDto = null;
		if (barcode != null) {
			barcodeDto = new BarcodeDto();
			barcodeDto.setBarcodeId(barcode.getBarcodeId());
			barcodeDto.setBarcodeDigits(barcode.getBarcodeDigits());
			barcodeDto.setIsActive(barcode.getIsActive());
		}
		return barcodeDto;
	}

	@Override
	public Barcode reverseTransform(BarcodeDto barcodeDto) {
		Barcode barcode = null;
		if (barcodeDto != null) {
			barcode = new Barcode();
			barcode.setBarcodeId(barcodeDto.getBarcodeId());
			barcode.setBarcodeDigits(barcodeDto.getBarcodeDigits());
			barcode.setIsActive(barcodeDto.getIsActive());
			
		}
		
		return barcode;
	}

}
