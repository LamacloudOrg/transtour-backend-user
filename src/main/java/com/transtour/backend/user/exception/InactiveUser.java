package com.transtour.backend.user.exception;

public class InactiveUser extends RuntimeException {

    public InactiveUser() {
        super("Usuario Inactivo");
    }
}
