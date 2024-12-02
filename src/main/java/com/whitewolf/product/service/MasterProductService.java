package com.whitewolf.product.service;

import com.whitewolf.product.dto.MasterProductInputDto;
import com.whitewolf.product.exception.ResourceNotFoundException;
import com.whitewolf.product.model.MasterProduct;
import com.whitewolf.product.repository.MasterProductRepository;
import com.whitewolf.product.utils.ProductServiceMapper;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class MasterProductService {

    private static final Logger logger = LoggerFactory.getLogger(MasterProductService.class);

    @Autowired
    private MasterProductRepository masterProductRepository;

    public MasterProduct createMasterProduct(MasterProductInputDto masterProductDto) {
        logger.info("Creating a new master product: {}", masterProductDto);
        MasterProduct masterProduct = ProductServiceMapper.INSTANCE.toEntity(masterProductDto);
        masterProduct.setCreatedAt(System.currentTimeMillis());
        masterProduct.setUpdatedAt(System.currentTimeMillis());
        masterProduct.setCreatedBy("admin");
        masterProduct.setUpdatedBy("admin");
        MasterProduct savedProduct = masterProductRepository.save(masterProduct);
        logger.info("Successfully created master product with ID: {}", savedProduct.getId());
        return savedProduct;
    }

    public MasterProduct updateMasterProduct(Long productId, MasterProductInputDto updatedProductDto) {
        logger.info("Updating master product with ID: {}", productId);

        Optional<MasterProduct> existingProductOpt = masterProductRepository.findById(productId);
        if (existingProductOpt.isEmpty()) {
            logger.warn("Product with ID: {} not found for update", productId);
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
        existingProduct.setUpdatedBy("admin");

        MasterProduct savedProduct = masterProductRepository.save(existingProduct);
        logger.info("Successfully updated product with ID: {}", savedProduct.getId());
        return savedProduct;
    }

    public MasterProduct getMasterProduct(Long productId) {
        logger.info("Fetching master product with ID: {}", productId);

        Optional<MasterProduct> masterProductOpt = masterProductRepository.findById(productId);
        if (masterProductOpt.isEmpty()) {
            logger.warn("Master product with ID: {} not found", productId);
            throw new ResourceNotFoundException("Master product not found");
        }

        logger.info("Successfully fetched master product with ID: {}", productId);
        return masterProductOpt.get();
    }

    public void deleteMasterProduct(Long productId) {
        logger.info("Deleting master product with ID: {}", productId);
        masterProductRepository.deleteById(productId);
        logger.info("Successfully deleted master product with ID: {}", productId);
    }
}
