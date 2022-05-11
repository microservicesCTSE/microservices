package com.pos.inventory.common.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BranchDto implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long branchId;
    private String branchName;
    private String branchLocation;
    private Boolean isActive;

}
