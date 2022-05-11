package com.pos.inventory.common.enums;

public enum SenderRegisteredFrom {

	CORE_PLATFORM("CORE_PLATFORM"), CUSTOMER_PORTAL("CUSTOMER_PORTAL"), ALL("ALL");

	private String registeredFrom;

	private SenderRegisteredFrom(String registeredFrom) {
		this.registeredFrom = registeredFrom;
	}

	public String getStatus() {
		return registeredFrom;
	}

	void setStatus(String registeredFrom) {
		this.registeredFrom = registeredFrom;
	}
}
