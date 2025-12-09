package org.example.proxibanque.exception.custom;

import org.example.proxibanque.exception.CustomException;
import org.springframework.http.HttpStatus;

public class InvalidDataException extends CustomException {

    public InvalidDataException() {
        super(HttpStatus.BAD_REQUEST);
    }
}
