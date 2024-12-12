package com.whitewolf.product.controller;

import com.whitewolf.product.common.ApiResponse;
import com.whitewolf.product.dto.MasterProductDto;
import com.whitewolf.product.dto.VariantProductDto;
import com.whitewolf.product.model.MasterProduct;
import com.whitewolf.product.model.VariantProduct;
import com.whitewolf.product.service.VariantProductService;
import com.whitewolf.product.utils.ProductServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/findByColor")
    public ResponseEntity<ApiResponse<List<VariantProductDto>>> getVariantProductByColor(@RequestParam String color) {
        List<VariantProduct> variantProductList = variantProductService.getVariantProductByColor(color);
        ApiResponse<List<VariantProductDto>> response = new ApiResponse<>(
                "success",
                "Variant Product list retrieved successfully",
                ProductServiceMapper.INSTANCE.toVariantDTOList(variantProductList)
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findBySize")
    public ResponseEntity<ApiResponse<List<VariantProductDto>>> getVariantProductBySize(@RequestParam String size) {
        List<VariantProduct> variantProductList = variantProductService.getVariantProductBySize(size);
        ApiResponse<List<VariantProductDto>> response = new ApiResponse<>(
                "success",
                "Variant Product list retrieved successfully",
                ProductServiceMapper.INSTANCE.toVariantDTOList(variantProductList)
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByColorAndSize")
    public ResponseEntity<ApiResponse<List<VariantProductDto>>> getVariantProductByColorAndSize(@RequestParam String color, @RequestParam String size) {
        List<VariantProduct> variantProductList = variantProductService.getVariantProductByColorAndSize(color, size);
        ApiResponse<List<VariantProductDto>> response = new ApiResponse<>(
                "success",
                "Variant Product list retrieved successfully",
                ProductServiceMapper.INSTANCE.toVariantDTOList(variantProductList)
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByColorAndSizeViaCriteriaWay")
    public ResponseEntity<ApiResponse<List<VariantProductDto>>> getVariantProductByColorAndSizeViaCriteriaWay(@RequestParam String color, @RequestParam String size) {
        List<VariantProduct> variantProductList = variantProductService.getVariantProductByColorAndSizeViaCriteriaWay(color, size);
        ApiResponse<List<VariantProductDto>> response = new ApiResponse<>(
                "success",
                "Variant Product list retrieved successfully",
                ProductServiceMapper.INSTANCE.toVariantDTOList(variantProductList)
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByMasterProductName")
    public ResponseEntity<ApiResponse<List<VariantProductDto>>> getVariantProductByColorAndSize(@RequestParam String name) {
        List<VariantProduct> variantProductList = variantProductService.getVariantProductByMasterProductName(name);
        ApiResponse<List<VariantProductDto>> response = new ApiResponse<>(
                "success",
                "Variant Product list retrieved successfully",
                ProductServiceMapper.INSTANCE.toVariantDTOList(variantProductList)
        );
        return ResponseEntity.ok(response);
    }

}
