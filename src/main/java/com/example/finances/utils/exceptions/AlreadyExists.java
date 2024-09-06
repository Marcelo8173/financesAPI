package com.example.finances.utils.exceptions;

public class AlreadyExists extends RuntimeException {
    public AlreadyExists(String msg) {
        super(msg);
    }
}
