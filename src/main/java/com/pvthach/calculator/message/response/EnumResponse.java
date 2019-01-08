package com.pvthach.calculator.message.response;

/**
 * Created by THACH-PC
 */
public enum EnumResponse {
    USERNAME_NOT_FOUND("Username not found"),
    USERNAME_INVALID("Username must be greater than 3 and less than 50 characters"),
    USERNAME_EXIST("Username is already taken"),
    PASSWORD_INVALID("Password must be greater than 3 and less than 100 characters"),
    EMAIL_INVALID("Username must be greater than 3 and less than 50 characters"),
    EMAIL_EXIST("Email is already in use"),
    ROLE_NOT_FOUND("Role not found"),
    USER_REGISTERED_SUCCESS("User registered successfully"),
    UNAUTHORIZED("Unauthorized");

    private String description;

    private EnumResponse(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
