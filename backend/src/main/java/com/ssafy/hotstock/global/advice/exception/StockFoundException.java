package com.ssafy.hotstock.global.advice.exception;

public class StockFoundException extends RuntimeException {
    public StockFoundException(String message) {
        super(message);
    }
}
