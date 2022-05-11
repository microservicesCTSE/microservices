package com.pos.inventory.common.enums;

/**
 * contains reconciliation status for bank reconciliations
 * 
 * @author Thilina Madhusanka
 *
 */
public enum ReconciliationStatus {

	CREATED("CREATED"), RECONCILED("RECONCILED"), REJECTED("REJECTED"), ALL("ALL");

	private String status;

	private ReconciliationStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	void setStatus(String status) {
		this.status = status;
	}

}
