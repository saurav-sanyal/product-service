package com.whitewolf.product.controller;


import com.whitewolf.product.model.MasterProduct;
import com.whitewolf.product.repository.MasterProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class MasterProductController {

    private final MasterProductRepository productRepository;

    @GetMapping
    public List<MasterProduct> getAllProducts() {
        log.info("Fetching all products");
        return productRepository.findAll();
    }

    @PostMapping
    public String addProduct(@RequestBody MasterProduct product) {
        log.info("Setting id to null for incoming product request: {}", product);
        product.setId(null);
        productRepository.save(product);
        log.info("Product creation successfully for product: {} with id {}", product, product.getId());
        return "Product added successfully";
    }

}