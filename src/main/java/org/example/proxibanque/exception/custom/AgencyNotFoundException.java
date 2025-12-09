package org.example.proxibanque.exception.custom;

import org.example.proxibanque.exception.CustomException;
import org.springframework.http.HttpStatus;

public class AgencyNotFoundException extends CustomException {

    public AgencyNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
