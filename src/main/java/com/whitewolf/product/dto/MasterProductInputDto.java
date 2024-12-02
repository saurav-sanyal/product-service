package com.whitewolf.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MasterProductInputDto {

    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private boolean active;
    private Map<String, String> attributes;

}
