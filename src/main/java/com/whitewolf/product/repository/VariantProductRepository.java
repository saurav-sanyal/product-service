package com.whitewolf.product.repository;


import com.whitewolf.product.model.VariantProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantProductRepository extends JpaRepository<VariantProduct, Long>, CustomVariantListRepository {
    List<VariantProduct> findByColor(String color);

    List<VariantProduct> findBySize(String size);

    @Query("SELECT v FROM VariantProduct v WHERE v.color = ?1 AND v.size = ?2")
    List<VariantProduct> findByColorAndSize(String color, String size);

    @Query(value = "SELECT v.id AS id, v.sku AS sku, v.color AS color, v.size AS size, " +
            "v.material AS material, v.additional_price AS additional_price, v.quantity AS quantity, " +
            "v.master_product_id AS master_product_id, v.active AS active, v.created_at AS created_at, " +
            "v.updated_at AS updated_at, v.created_by AS created_by, v.updated_by AS updated_by " +
            "FROM variant_product v " +
            "JOIN master_product m ON v.master_product_id = m.id " +
            "WHERE m.name = ?1",
            nativeQuery = true)
    List<VariantProduct> findByMasterProductName(String name);


}