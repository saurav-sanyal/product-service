package com.whitewolf.product.dto;

import com.whitewolf.product.model.MasterProduct;
import com.whitewolf.product.model.VariantProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VariantProductDto {


    private String sku;
    private String color;
    private String size;
    private String material;
    private BigDecimal additionalPrice;
    private Integer quantity;
    private IdInput masterProduct;
    private boolean active;

}
