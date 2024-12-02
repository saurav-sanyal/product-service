package com.whitewolf.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "variant_product", uniqueConstraints = @UniqueConstraint(columnNames = {"sku"}))
public class VariantProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String sku;

    @Column(length = 50)
    private String color;

    @Column(length = 50)
    private String size;

    @Column(length = 100)
    private String material;

    @Column(name = "additional_price", precision = 10, scale = 2)
    private BigDecimal additionalPrice;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master_product_id", nullable = false)
    private MasterProduct masterProduct;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "created_by", nullable = false, length = 50)
    private String createdBy;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    @ElementCollection
    @CollectionTable(name = "variant_product_attributes", joinColumns = @JoinColumn(name = "variant_product_id"))
    @MapKeyColumn(name = "key", length = 100)
    @Column(name = "value", length = 500)
    private Map<String, String> attributes;
}
