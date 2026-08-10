package com.example.ecommerceapi.modal;

public enum Permission {
    CUSTOMER_READ("customer:read"),
    CUSTOMER_SEARCH("customer:search"),

    ADMIN_READ("admin:read"),
    ADMIN_SEARCH("admin:search"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_UPDATE("admin:update");

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getpermission() {
        return this.permission;
    }
}
