package com.whitewolf.product.utils;

import com.whitewolf.product.dto.MasterProductDto;
import com.whitewolf.product.dto.MasterProductInputDto;
import com.whitewolf.product.dto.VariantProductDto;
import com.whitewolf.product.model.MasterProduct;
import com.whitewolf.product.model.VariantProduct;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper
public interface ProductServiceMapper {

    ProductServiceMapper INSTANCE = Mappers.getMapper(ProductServiceMapper.class);

    MasterProductDto toDTO(MasterProduct product);
    MasterProduct toEntity(MasterProductDto dto);

    VariantProductDto toDTO(VariantProduct product);
    VariantProduct toEntity(VariantProductDto dto);

    MasterProduct toEntity(MasterProductInputDto masterProductDto);
}