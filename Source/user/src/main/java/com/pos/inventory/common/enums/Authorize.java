package com.pos.inventory.common.enums;

public enum Authorize {
    CORE_ADMIN("Core Admin");
    private String authorizeType;

    private Authorize(String authorizeType) {
        this.authorizeType = authorizeType;
    }

    public String getAuthorizeType() {
        return authorizeType;
    }

}
