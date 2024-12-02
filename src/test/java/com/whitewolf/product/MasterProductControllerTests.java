package com.whitewolf.product;

import com.whitewolf.product.model.MasterProduct;
import com.whitewolf.product.repository.MasterProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MasterProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MasterProductRepository productRepository;

    @Test
    @Transactional
    void testCreateProduct() throws Exception {
        
        mockMvc.perform(post("/api/master-products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "name": "New Product",
                        "description": "A test product",
                        "price": 99.99,
                        "category": "Test Category",
                        "active": true,
                        "attributes":{
                            "key1":"value1"
                            }
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("New Product"));

        
        Assertions.assertEquals(1, productRepository.count());
    }

    @Test
    @Transactional
    void testGetProductById() throws Exception {
        
        MasterProduct savedProduct = productRepository.save(new MasterProduct(null, "Product A", "Description A", BigDecimal.valueOf(49.99), "Category A", null, true, "admin", "admin", null, null, Map.of()));

        
        mockMvc.perform(get("/api/master-products/" + savedProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedProduct.getId()))
                .andExpect(jsonPath("$.name").value("Product A"));
    }

    @Test
    @Transactional
    void testDeleteProduct() throws Exception {
        
        MasterProduct savedProduct = productRepository.save(new MasterProduct(null, "Product A", "Description A", BigDecimal.valueOf(49.99), "Category A", null, true, "admin", "admin", null, null, Map.of()));

        
        mockMvc.perform(delete("/api/master-products/" + savedProduct.getId()))
                .andExpect(status().isNoContent());


        Assertions.assertFalse(productRepository.findById(savedProduct.getId()).isPresent());
    }

}
