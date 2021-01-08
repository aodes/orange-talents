package com.zup.orangetalents.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DuplicateEmailAndCpfException extends Exception{

    public DuplicateEmailAndCpfException(String msg) {
        super(msg);
    }
}
