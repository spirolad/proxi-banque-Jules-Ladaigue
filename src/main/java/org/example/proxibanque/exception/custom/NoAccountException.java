package org.example.proxibanque.exception.custom;

import org.example.proxibanque.exception.CustomException;
import org.springframework.http.HttpStatus;

public class NoAccountException extends CustomException {

    public NoAccountException() {
        super(HttpStatus.NOT_FOUND);
    }
}
