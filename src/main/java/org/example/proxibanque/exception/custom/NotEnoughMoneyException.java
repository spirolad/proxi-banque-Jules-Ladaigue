package org.example.proxibanque.exception.custom;

import org.example.proxibanque.exception.CustomException;
import org.springframework.http.HttpStatus;

public class NotEnoughMoneyException extends CustomException {

    public NotEnoughMoneyException() {
        super(HttpStatus.BAD_REQUEST);
    }
}
