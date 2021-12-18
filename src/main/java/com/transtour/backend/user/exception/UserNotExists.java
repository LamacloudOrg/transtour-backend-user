package com.transtour.backend.user.exception;

public class UserNotExists extends RuntimeException {

    public UserNotExists() {
        super("Usuario no existe");
    }
}
