/**
 * 
 */
package com.pos.identity.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Thilina Madhusanka
 */
@Data
public class PaginatedResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private int pageNumber;
	private int pageSize;
	private int totalRecords;
	private Object payload;
}
