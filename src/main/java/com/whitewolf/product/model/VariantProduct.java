package com.whitewolf.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class VariantProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;
    private String color;
    private String size;
    private String material;
    private BigDecimal additionalPrice;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "master_product_id", nullable = false)
    private MasterProduct masterProduct;

    private boolean active;
    private String createdBy;
    private String updatedBy;

    @Column(name = "created_at", updatable = false)
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    @ElementCollection
    private Map<String, String> attributes;

}

