package org.example.proxibanque.exception.custom;

import org.example.proxibanque.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ClientNotFoundException extends CustomException {

    public ClientNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
