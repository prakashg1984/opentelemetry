package com.pg.opentelemetry.exception;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(String priceNotFound) {
        super(priceNotFound);
    }
}
