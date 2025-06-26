package com.deepak.controller;

import com.deepak.service.ProductService;
import com.deepak.service.ProductServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    private final ProductServiceV2 productServiceV2;

    public ProductController(ProductService productService, ProductServiceV2 productServiceV2) {
        this.productService = productService;
        this.productServiceV2 = productServiceV2;
    }

    // this endpoint for testing
    @GetMapping("/ids")
    public ResponseEntity<List<Long>> getIds() {
        return ResponseEntity.ok(productService.getProductIds());
    }

    // this endpoint for data reset
    @PostMapping("/reset")
    public ResponseEntity<String> resetProductRecords() {
        String response = productService.resetRecords();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/process")
    public ResponseEntity<String> processProductIds(@RequestBody List<Long> productIds) {
        productService.processProductIds(productIds);
        return ResponseEntity.ok("Products processed and events published.");
    }

    @PostMapping("/process/v2")
    public ResponseEntity<String> processProductIdsV2(@RequestBody List<Long> productIds) {
        productServiceV2.executeProductIds(productIds);
        return ResponseEntity.ok("Products processed and events published.");
    }

}
