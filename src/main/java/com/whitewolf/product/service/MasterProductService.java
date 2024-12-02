package com.whitewolf.product.service;


import com.whitewolf.product.dto.MasterProductInputDto;
import com.whitewolf.product.exception.ResourceNotFoundException;
import com.whitewolf.product.model.MasterProduct;
import com.whitewolf.product.repository.MasterProductRepository;
import com.whitewolf.product.utils.ProductServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MasterProductService {

    @Autowired
    private MasterProductRepository masterProductRepository;

    public MasterProduct createMasterProduct(MasterProductInputDto masterProductDto) {
        MasterProduct masterProduct = ProductServiceMapper.INSTANCE.toEntity(masterProductDto);
        masterProduct.setCreatedAt(System.currentTimeMillis());
        masterProduct.setUpdatedAt(System.currentTimeMillis());
        return masterProductRepository.save(masterProduct);
    }

    public MasterProduct updateMasterProduct(Long productId, MasterProductInputDto updatedProductDto) {
        Optional<MasterProduct> existingProductOpt = masterProductRepository.findById(productId);
        if (existingProductOpt.isEmpty()) {
            throw new ResourceNotFoundException("Product not found");
        }

        MasterProduct existingProduct = existingProductOpt.get();
        MasterProduct updatedProduct = ProductServiceMapper.INSTANCE.toEntity(updatedProductDto);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setAttributes(updatedProductDto.getAttributes());
        existingProduct.setUpdatedAt(System.currentTimeMillis());
        existingProduct.setUpdatedBy("admin"); // Or dynamically set from user context
        return masterProductRepository.save(existingProduct);
    }

    public MasterProduct getMasterProduct(Long productId) {
        Optional<MasterProduct> masterProductOpt = masterProductRepository.findById(productId);
        if (masterProductOpt.isEmpty()) {
            throw new ResourceNotFoundException("Master product not found");
        }
        return masterProductOpt.get();
    }

    public void deleteMasterProduct(Long productId) {
        masterProductRepository.deleteById(productId);
    }
}

