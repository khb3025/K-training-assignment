package com.example.assignment.global.exception;

import com.example.assignment.global.RsData.RsData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DomainException.class)
    public RsData<Void> handleDomainException(DomainException e){
        return new RsData<Void>("409-1",e.getMessage(),null);
    }
}
