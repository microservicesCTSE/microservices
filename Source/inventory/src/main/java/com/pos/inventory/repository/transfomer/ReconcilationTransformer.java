package com.pos.inventory.repository.transfomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pos.inventory.common.dto.ReconcilationDto;
import com.pos.inventory.repository.domain.Reconcilation;

@Component
public class ReconcilationTransformer implements BaseTransformer<Reconcilation, ReconcilationDto> {

	@Autowired
	ProductTransformer productTransformer;

	@Override
	public ReconcilationDto transform(Reconcilation reconcilation) {
		ReconcilationDto reconcilationDto = null;
		if (reconcilation != null) {
			reconcilationDto = new ReconcilationDto();
			reconcilationDto.setReconcilationId(reconcilation.getReconcilationId());
			reconcilationDto.setCountCurrentQuntaty(reconcilation.getCountCurrentQuntaty());
			reconcilationDto.setDifferenceQuntaty(reconcilation.getDifferenceQuntaty());
			reconcilationDto.setDate(reconcilation.getDate());
			reconcilationDto.setUserId(reconcilation.getUserId());
			if (reconcilation.getProductId() != null) {
				reconcilationDto.setProductDto(productTransformer.transform(reconcilation.getProductId()));
			}
		}
		return reconcilationDto;
	}

	@Override
	public Reconcilation reverseTransform(ReconcilationDto reconcilationDto) {
		Reconcilation reconcilation = null;
		if (reconcilationDto != null) {
			reconcilation = new Reconcilation();
			reconcilation.setReconcilationId(reconcilationDto.getReconcilationId());
			reconcilation.setCountCurrentQuntaty(reconcilationDto.getCountCurrentQuntaty());
			reconcilation.setDifferenceQuntaty(reconcilationDto.getDifferenceQuntaty());
			reconcilation.setDate(reconcilationDto.getDate());
			reconcilation.setUserId(reconcilationDto.getUserId());
			if (reconcilationDto.getProductDto() != null) {
				reconcilation.setProductId(productTransformer.reverseTransform(reconcilationDto.getProductDto()));
			}
		}
		return reconcilation;
	}
}
