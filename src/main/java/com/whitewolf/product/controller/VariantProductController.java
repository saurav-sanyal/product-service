package com.whitewolf.product.controller;

import com.whitewolf.product.common.ApiResponse;
import com.whitewolf.product.dto.VariantProductDto;
import com.whitewolf.product.model.VariantProduct;
import com.whitewolf.product.service.VariantProductService;
import com.whitewolf.product.utils.ProductServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/variant-products")
public class VariantProductController {

    @Autowired
    private VariantProductService variantProductService;

    @PostMapping
    public ResponseEntity<ApiResponse<VariantProductDto>> createVariant(@RequestBody VariantProductDto variantProductDto) {
        VariantProduct variant = variantProductService.createVariant(variantProductDto);
        ApiResponse<VariantProductDto> response = new ApiResponse<>("success", "Variant product created successfully", ProductServiceMapper.INSTANCE.toDTO(variant));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{variantId}")
    public ResponseEntity<ApiResponse<VariantProductDto>> updateVariant(@PathVariable Long variantId, @RequestBody VariantProductDto variantProductDto) {
        VariantProduct variant = variantProductService.updateVariant(variantId, variantProductDto);
        ApiResponse<VariantProductDto> response = new ApiResponse<>("success", "Variant product updated successfully", ProductServiceMapper.INSTANCE.toDTO(variant));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{variantId}")
    public ResponseEntity<ApiResponse<VariantProductDto>> getVariant(@PathVariable Long variantId) {
        VariantProduct variant = variantProductService.getVariant(variantId);
        ApiResponse<VariantProductDto> response = new ApiResponse<>("success", "Variant product retrieved successfully", ProductServiceMapper.INSTANCE.toDTO(variant));
        return ResponseEntity.ok(response);
    }

    // Delete Variant Product
    @DeleteMapping("/{variantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ApiResponse<VariantProductDto>> deleteVariant(@PathVariable Long variantId) {
        variantProductService.deleteVariant(variantId);
        ApiResponse<VariantProductDto> response = new ApiResponse<>("success", "Variant product deleted successfully", null);
        return ResponseEntity.ok(response);
    }
}
