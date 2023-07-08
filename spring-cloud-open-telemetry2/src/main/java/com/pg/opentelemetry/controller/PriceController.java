package com.pg.opentelemetry.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pg.opentelemetry.api.client.PriceClient;
import com.pg.opentelemetry.model.Price;
import com.pg.opentelemetry.repository.PriceRepository;

@RestController
public class PriceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriceController.class);

    private final PriceRepository priceRepository;
    
    private final PriceClient priceClient;

    @Autowired
    public PriceController(PriceClient priceClient, PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
        this.priceClient = priceClient;
    }

    @GetMapping(path = "/price/{id}")
    public Price getPrice(@PathVariable("id") long productId) {
        LOGGER.info("Getting Price details for Product Id using PriceClient from 2 {}", productId);
        return priceClient.getPrice(productId);
  }
}
