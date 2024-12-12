package com.whitewolf.product.controller;

import com.whitewolf.product.common.ApiResponse;
import com.whitewolf.product.dto.MasterProductDto;
import com.whitewolf.product.dto.MasterProductInputDto;
import com.whitewolf.product.model.MasterProduct;
import com.whitewolf.product.service.MasterProductService;
import com.whitewolf.product.utils.ProductServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/findByName")
    public ResponseEntity<ApiResponse<List<MasterProductDto>>> getMasterProductByName(@RequestParam String name) {
        List<MasterProduct> masterProductList = masterProductService.getMasterProductByName(name);
        ApiResponse<List<MasterProductDto>> response = new ApiResponse<>(
                "success",
                "Master Product list retrieved successfully",
                ProductServiceMapper.INSTANCE.toMasterDTOList(masterProductList)
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByCategory")
    public ResponseEntity<ApiResponse<List<MasterProductDto>>> getMasterProductByCategory(@RequestParam String category) {
        List<MasterProduct> masterProductList = masterProductService.getMasterProductByCategory(category);
        ApiResponse<List<MasterProductDto>> response = new ApiResponse<>(
                "success",
                "Master Product list retrieved successfully",
                ProductServiceMapper.INSTANCE.toMasterDTOList(masterProductList)
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByDescription")
    public ResponseEntity<ApiResponse<List<MasterProductDto>>> getMasterProductByDescription(@RequestParam String description) {
        List<MasterProduct> masterProductList = masterProductService.getMasterProductByDescription(description);
        ApiResponse<List<MasterProductDto>> response = new ApiResponse<>(
                "success",
                "Master Product list retrieved successfully",
                ProductServiceMapper.INSTANCE.toMasterDTOList(masterProductList)
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findBySpecification")
    public ResponseEntity<ApiResponse<List<MasterProductDto>>> getMasterProductBySpecification(@RequestParam String name, @RequestParam String minPrice) {
        List<MasterProduct> masterProductList = masterProductService.findProducts(name, Double.valueOf(minPrice));
        ApiResponse<List<MasterProductDto>> response = new ApiResponse<>(
                "success",
                "Master Product list retrieved successfully",
                ProductServiceMapper.INSTANCE.toMasterDTOList(masterProductList)
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<MasterProduct>>> searchMasterProduct(@RequestParam String name,
                                                                                   @RequestParam int page,
                                                                                   @RequestParam int size
    ) {
        Page<MasterProduct> masterProductList = masterProductService.getPagedProducts(name, page, size);
        ApiResponse<Page<MasterProduct>> response = new ApiResponse<>(
                "success",
                "Master Product list retrieved successfully",
                masterProductList
        );
        return ResponseEntity.ok(response);
    }

}
