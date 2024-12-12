package com.whitewolf.product.repository;

import com.whitewolf.product.model.MasterProduct;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;


public class MasterProductSpecifications {

    public static Specification<MasterProduct> nameContains(String name) {
        return (Root<MasterProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (name == null || name.isEmpty()) {
                return null;
            }
            return cb.like(root.get("name"), "%" + name + "%");
        };
    }

    public static Specification<MasterProduct> priceGreaterThan(Double price) {
        return (Root<MasterProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (price == null) {
                return null;
            }
            return cb.greaterThan(root.get("price"), price);
        };
    }
}

