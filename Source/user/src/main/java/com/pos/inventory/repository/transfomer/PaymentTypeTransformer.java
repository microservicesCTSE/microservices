package com.pos.inventory.repository.transfomer;

import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.PaymentTypeDto;
import com.pos.inventory.repository.domain.PaymentType;

@Component
public class PaymentTypeTransformer  implements BaseTransformer<PaymentType, PaymentTypeDto>{

	@Override
	public PaymentTypeDto transform(PaymentType paymentType) {
		PaymentTypeDto paymentTypeDto = null;
		if(paymentType != null) {
			paymentTypeDto = new PaymentTypeDto();
			paymentTypeDto.setPaymentTypeId(paymentType.getPaymentTypeId());
			paymentTypeDto.setPaymentTypeName(paymentType.getPaymentTypeName());
			paymentTypeDto.setIsActive(paymentType.getIsActive());
		}
		
		return paymentTypeDto;
	}

	@Override
	public PaymentType reverseTransform(PaymentTypeDto paymentTypeDto) {
		PaymentType paymentType = null;
		if(paymentTypeDto != null) {
			paymentType = new PaymentType();
			paymentType.setPaymentTypeId(paymentTypeDto.getPaymentTypeId());
			paymentType.setPaymentTypeName(paymentTypeDto.getPaymentTypeName());
			paymentType.setIsActive(paymentTypeDto.getIsActive());
		}
		
		return paymentType;
	}

}
