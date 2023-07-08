package com.pg.opentelemetry.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pg.opentelemetry.api.client.PriceClient;
import com.pg.opentelemetry.model.Product;
import com.pg.opentelemetry.repository.ProductRepository;

import io.opentelemetry.api.trace.Span;

@RestController
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private final PriceClient priceClient;

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(PriceClient priceClient, ProductRepository productRepository) {
        this.priceClient = priceClient;
        this.productRepository = productRepository;
    }

    @GetMapping(path = "/product/{id}")
    public Product getProductDetails(@PathVariable("id") long productId){
    	String traceIdValue = Span.current().getSpanContext().getTraceId();
    	String traceIdHexString = traceIdValue.substring(traceIdValue.length() - 16 );
    	long datadogTraceId = Long.parseUnsignedLong(traceIdHexString, 16);
    	String datadogTraceIdString = Long.toUnsignedString(datadogTraceId);

    	String spanIdValue = Span.current().getSpanContext().getSpanId();
    	String spanIdHexString = spanIdValue.substring(spanIdValue.length() - 16 );
    	long datadogSpanId = Long.parseUnsignedLong(spanIdHexString, 16);
    	String datadogSpanIdString = Long.toUnsignedString(datadogSpanId);
    	
    	System.out.println("datadogTraceIdString "+datadogTraceIdString);
    	System.out.println("datadogSpanIdString "+datadogSpanIdString);
    	
    	MDC.put("datadogTraceIdString", datadogTraceIdString);
    	MDC.put("datadogSpanIdString", datadogSpanIdString);

        LOGGER.info("Getting Product and Price Details With Product Id {}", productId);
        Product product = productRepository.getProduct(productId);
        product.setPrice(priceClient.getPrice(productId));

        return product;
    }
}
