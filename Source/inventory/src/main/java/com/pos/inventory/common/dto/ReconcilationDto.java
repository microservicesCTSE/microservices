package com.pos.inventory.common.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ReconcilationDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long reconcilationId;
	private Integer countCurrentQuntaty;
	private Integer differenceQuntaty;
	private LocalDate date;
	private Long userId;
	private ProductDto productDto;
	
}
