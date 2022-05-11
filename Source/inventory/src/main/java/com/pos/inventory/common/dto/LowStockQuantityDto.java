/**
 * 
 */
package com.pos.inventory.common.dto;

import java.io.Serializable;



import lombok.Data;

/**
 * @author MDev
 *
 */
@Data
public class LowStockQuantityDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7225119301672392255L;
	private Long lowStockQuantityId;
	private Boolean isLowStock;
	private Long stockId;
	private Long supplierId;

}
