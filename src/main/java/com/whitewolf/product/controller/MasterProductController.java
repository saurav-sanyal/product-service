package com.whitewolf.product.controller;

import com.whitewolf.product.model.MasterProduct;
import com.whitewolf.product.service.MasterProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/master-products")
public class MasterProductController {

    @Autowired
    private MasterProductService masterProductService;

    @PostMapping
    public MasterProduct createMasterProduct(@RequestBody MasterProduct masterProduct) {
        return masterProductService.createMasterProduct(masterProduct);
    }

    @PutMapping("/{productId}")
    public MasterProduct updateMasterProduct(@PathVariable Long productId, @RequestBody MasterProduct masterProduct) {
        return masterProductService.updateMasterProduct(productId, masterProduct);
    }

    @GetMapping("/{productId}")
    public MasterProduct getMasterProduct(@PathVariable Long productId) {
        return masterProductService.getMasterProduct(productId);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMasterProduct(@PathVariable Long productId) {
        masterProductService.deleteMasterProduct(productId);
    }
}
