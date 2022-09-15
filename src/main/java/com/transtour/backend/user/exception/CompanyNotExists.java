package com.transtour.backend.user.exception;

public class CompanyNotExists extends RuntimeException {

    public CompanyNotExists() {
        super("La Empresa no existe");
    }

}