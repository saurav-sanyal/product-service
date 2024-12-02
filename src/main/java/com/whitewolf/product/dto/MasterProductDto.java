package com.whitewolf.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.whitewolf.product.model.VariantProduct;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterProductDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private boolean active;
    private Map<String, String> attributes;

}
