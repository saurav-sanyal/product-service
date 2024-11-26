package com.whitewolf.product.service;

import com.whitewolf.product.model.MasterProduct;
import com.whitewolf.product.model.VariantProduct;
import com.whitewolf.product.repository.MasterProductRepository;
import com.whitewolf.product.repository.VariantProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VariantProductService {

    @Autowired
    private VariantProductRepository variantProductRepository;

    @Autowired
    private MasterProductRepository masterProductRepository;

    public VariantProduct createVariant(Long masterProductId, VariantProduct variantProduct) {
        Optional<MasterProduct> masterProductOpt = masterProductRepository.findById(masterProductId);
        if (!masterProductOpt.isPresent()) {
            throw new IllegalArgumentException("Master product not found");
        }

        MasterProduct masterProduct = masterProductOpt.get();
        variantProduct.setMasterProduct(masterProduct);
        variantProduct.setCreatedAt(System.currentTimeMillis());
        variantProduct.setUpdatedAt(System.currentTimeMillis());
        return variantProductRepository.save(variantProduct);
    }

    public VariantProduct updateVariant(Long variantId, VariantProduct updatedVariant) {
        Optional<VariantProduct> existingVariantOpt = variantProductRepository.findById(variantId);
        if (!existingVariantOpt.isPresent()) {
            throw new IllegalArgumentException("Variant not found");
        }

        VariantProduct existingVariant = existingVariantOpt.get();
        existingVariant.setColor(updatedVariant.getColor());
        existingVariant.setSize(updatedVariant.getSize());
        existingVariant.setMaterial(updatedVariant.getMaterial());
        existingVariant.setAdditionalPrice(updatedVariant.getAdditionalPrice());
        existingVariant.setQuantity(updatedVariant.getQuantity());
        existingVariant.setUpdatedAt(System.currentTimeMillis());
        existingVariant.setUpdatedBy("admin"); // Or dynamically set from user context
        return variantProductRepository.save(existingVariant);
    }

    public void deleteVariant(Long variantId) {
        variantProductRepository.deleteById(variantId);
    }

    public VariantProduct getVariant(Long variantId) {
        Optional<VariantProduct> variantProductOpt = variantProductRepository.findById(variantId);
        if (!variantProductOpt.isPresent()) {
            throw new IllegalArgumentException("Variant not found");
        }
        return variantProductOpt.get();
    }
}
