package com.whitewolf.product.controller;

import com.whitewolf.product.common.ApiResponse;
import com.whitewolf.product.dto.MasterProductDto;
import com.whitewolf.product.dto.MasterProductInputDto;
import com.whitewolf.product.model.MasterProduct;
import com.whitewolf.product.service.MasterProductService;
import com.whitewolf.product.utils.ProductServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/master-products")
public class MasterProductController {

    @Autowired
    private MasterProductService masterProductService;

    @PostMapping
    public ResponseEntity<ApiResponse<MasterProductDto>> createMasterProduct(@RequestBody MasterProductInputDto masterProductDto) {
        MasterProduct masterProduct = masterProductService.createMasterProduct(masterProductDto);
        ApiResponse<MasterProductDto> response = new ApiResponse<>(
                "success",
                "Product created successfully",
                ProductServiceMapper.INSTANCE.toDTO(masterProduct)
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse<MasterProductDto>> updateMasterProduct(@PathVariable Long productId, @RequestBody MasterProductInputDto masterProductDto) {
        MasterProduct masterProduct = masterProductService.updateMasterProduct(productId, masterProductDto);
        ApiResponse<MasterProductDto> response = new ApiResponse<>(
                "success",
                "Product updated successfully",
                ProductServiceMapper.INSTANCE.toDTO(masterProduct)
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<MasterProductDto>> getMasterProduct(@PathVariable Long productId) {
        MasterProduct masterProduct = masterProductService.getMasterProduct(productId);
        ApiResponse<MasterProductDto> response = new ApiResponse<>(
                "success",
                "Product retrieved successfully",
                ProductServiceMapper.INSTANCE.toDTO(masterProduct)
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ApiResponse<MasterProductDto>> deleteMasterProduct(@PathVariable Long productId) {
        masterProductService.deleteMasterProduct(productId);
        ApiResponse<MasterProductDto> response = new ApiResponse<>(
                "success",
                "Product deleted successfully",
                null
        );
        return ResponseEntity.ok(response);
    }
}
