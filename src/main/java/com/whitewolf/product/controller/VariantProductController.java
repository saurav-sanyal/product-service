package com.whitewolf.product.controller;

import com.whitewolf.product.model.VariantProduct;
import com.whitewolf.product.service.VariantProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/variant-products")
public class VariantProductController {

    @Autowired
    private VariantProductService variantProductService;

    @PostMapping("/{masterProductId}")
    public VariantProduct createVariant(@PathVariable Long masterProductId, @RequestBody VariantProduct variantProduct) {
        return variantProductService.createVariant(masterProductId, variantProduct);
    }

    @PutMapping("/{variantId}")
    public VariantProduct updateVariant(@PathVariable Long variantId, @RequestBody VariantProduct variantProduct) {
        return variantProductService.updateVariant(variantId, variantProduct);
    }

    @GetMapping("/{variantId}")
    public VariantProduct getVariant(@PathVariable Long variantId) {
        return variantProductService.getVariant(variantId);
    }

    // Delete Variant Product
    @DeleteMapping("/{variantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVariant(@PathVariable Long variantId) {
        variantProductService.deleteVariant(variantId);
    }
}
