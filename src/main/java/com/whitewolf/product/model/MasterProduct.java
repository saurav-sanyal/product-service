package com.whitewolf.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "master_product", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class MasterProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, length = 100)
    private String category;

    @OneToMany(mappedBy = "masterProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<VariantProduct> variants;

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
    @CollectionTable(name = "master_product_attributes", joinColumns = @JoinColumn(name = "master_product_id"))
    @MapKeyColumn(name = "attribute_key")
    @Column(name = "attribute_value", length = 1000)
    private Map<String, String> attributes;
}
