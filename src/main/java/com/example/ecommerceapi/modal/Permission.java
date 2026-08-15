package com.example.ecommerceapi.modal;

public enum Permission {
    CUSTOMER_READ("product:read"),
    CUSTOMER_SEARCH("product:search"),

    ADMIN_READ("product:read"),
    ADMIN_SEARCH("product:search"),
    ADMIN_DELETE("product:delete"),
    ADMIN_UPDATE("product:update");

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getpermission() {
        return this.permission;
    }
}
