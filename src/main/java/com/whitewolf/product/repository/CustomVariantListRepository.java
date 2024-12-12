package com.whitewolf.product.repository;

import com.whitewolf.product.model.VariantProduct;

import java.util.List;

public interface CustomVariantListRepository {

    List<VariantProduct> criteriaWayFindByColorAndSize(String color, String size);

}
