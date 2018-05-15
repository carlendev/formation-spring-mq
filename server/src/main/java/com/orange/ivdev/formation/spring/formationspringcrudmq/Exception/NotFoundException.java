package com.orange.ivdev.formation.spring.formationspringcrudmq.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(final String msg) {
        super(msg);
    }

}