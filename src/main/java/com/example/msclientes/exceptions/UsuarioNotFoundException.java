package com.example.msclientes.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UsuarioNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
