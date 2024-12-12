package com.whitewolf.product.service;

import com.whitewolf.product.dto.VariantProductDto;
import com.whitewolf.product.exception.ResourceNotFoundException;
import com.whitewolf.product.model.MasterProduct;
import com.whitewolf.product.model.VariantProduct;
import com.whitewolf.product.repository.MasterProductRepository;
import com.whitewolf.product.repository.VariantProductRepository;
import com.whitewolf.product.utils.ProductServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VariantProductService {

    private static final Logger logger = LoggerFactory.getLogger(VariantProductService.class);

    @Autowired
    private VariantProductRepository variantProductRepository;

    @Autowired
    private MasterProductRepository masterProductRepository;

    public VariantProduct createVariant(VariantProductDto variantProductDto) {
        logger.info("Creating a variant product: {}", variantProductDto);

        Optional<MasterProduct> masterProductOpt = masterProductRepository.findById(variantProductDto.getMasterProduct().getId());
        if (masterProductOpt.isEmpty()) {
            logger.warn("Master product not found for ID: {}", variantProductDto.getMasterProduct().getId());
            throw new ResourceNotFoundException("Master product not found");
        }

        MasterProduct masterProduct = masterProductOpt.get();
        VariantProduct variantProduct = ProductServiceMapper.INSTANCE.toEntity(variantProductDto);

        variantProduct.setMasterProduct(masterProduct);
        variantProduct.setCreatedAt(System.currentTimeMillis());
        variantProduct.setUpdatedAt(System.currentTimeMillis());
        variantProduct.setCreatedBy("admin");
        variantProduct.setUpdatedBy("admin");
        VariantProduct savedVariant = variantProductRepository.save(variantProduct);

        logger.info("Successfully created variant product with ID: {}", savedVariant.getId());
        return savedVariant;
    }

    public VariantProduct updateVariant(Long variantId, VariantProductDto updatedVariant) {
        logger.info("Updating variant product with ID: {}", variantId);

        Optional<VariantProduct> existingVariantOpt = variantProductRepository.findById(variantId);
        if (existingVariantOpt.isEmpty()) {
            logger.warn("Variant product not found for ID: {}", variantId);
            throw new IllegalArgumentException("Variant not found");
        }

        VariantProduct existingVariant = existingVariantOpt.get();
        existingVariant.setColor(updatedVariant.getColor());
        existingVariant.setSize(updatedVariant.getSize());
        existingVariant.setMaterial(updatedVariant.getMaterial());
        existingVariant.setAdditionalPrice(updatedVariant.getAdditionalPrice());
        existingVariant.setQuantity(updatedVariant.getQuantity());
        existingVariant.setUpdatedAt(System.currentTimeMillis());
        existingVariant.setUpdatedBy("admin");
        VariantProduct savedVariant = variantProductRepository.save(existingVariant);
        logger.info("Successfully updated variant product with ID: {}", savedVariant.getId());
        return savedVariant;
    }

    public void deleteVariant(Long variantId) {
        logger.info("Deleting variant product with ID: {}", variantId);
        variantProductRepository.deleteById(variantId);
        logger.info("Successfully deleted variant product with ID: {}", variantId);
    }

    public VariantProduct getVariant(Long variantId) {
        logger.info("Fetching variant product with ID: {}", variantId);
        Optional<VariantProduct> variantProductOpt = variantProductRepository.findById(variantId);
        if (variantProductOpt.isEmpty()) {
            logger.warn("Variant product not found for ID: {}", variantId);
            throw new IllegalArgumentException("Variant not found");
        }
        logger.info("Successfully fetched variant product with ID: {}", variantId);
        return variantProductOpt.get();
    }

    public List<VariantProduct> getVariantProductByColor(String color) {
        return variantProductRepository.findByColor(color);
    }

    public List<VariantProduct> getVariantProductBySize(String size) {
        return variantProductRepository.findBySize(size);
    }

    public List<VariantProduct> getVariantProductByColorAndSize(String color, String size) {
        return variantProductRepository.findByColorAndSize(color,size);
    }

    public List<VariantProduct> getVariantProductByColorAndSizeViaCriteriaWay(String color, String size) {
        return variantProductRepository.criteriaWayFindByColorAndSize(color,size);
    }

    public List<VariantProduct> getVariantProductByMasterProductName(String name) {
        return variantProductRepository.findByMasterProductName(name);
    }
}
