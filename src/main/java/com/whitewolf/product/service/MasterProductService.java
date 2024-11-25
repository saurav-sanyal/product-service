package com.whitewolf.product.service;


import com.whitewolf.product.model.MasterProduct;
import com.whitewolf.product.repository.MasterProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MasterProductService {

    @Autowired
    private MasterProductRepository masterProductRepository;

    public MasterProduct createMasterProduct(MasterProduct masterProduct) {
        masterProduct.setCreatedAt(System.currentTimeMillis());
        masterProduct.setUpdatedAt(System.currentTimeMillis());
        return masterProductRepository.save(masterProduct);
    }

    public MasterProduct updateMasterProduct(Long productId, MasterProduct updatedProduct) {
        Optional<MasterProduct> existingProductOpt = masterProductRepository.findById(productId);
        if (!existingProductOpt.isPresent()) {
            throw new IllegalArgumentException("Product not found");
        }

        MasterProduct existingProduct = existingProductOpt.get();
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setUpdatedAt(System.currentTimeMillis());
        existingProduct.setUpdatedBy("admin"); // Or dynamically set from user context
        return masterProductRepository.save(existingProduct);
    }

    public MasterProduct getMasterProduct(Long productId) {
        Optional<MasterProduct> masterProductOpt = masterProductRepository.findById(productId);
        if (!masterProductOpt.isPresent()) {
            throw new IllegalArgumentException("Product not found");
        }
        return masterProductOpt.get();
    }

    public void deleteMasterProduct(Long productId) {
        masterProductRepository.deleteById(productId);
    }
}

