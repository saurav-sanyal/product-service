package com.whitewolf.product.repository;


import com.whitewolf.product.model.MasterProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterProductRepository extends JpaRepository<MasterProduct, Long>, JpaSpecificationExecutor<MasterProduct> {
    List<MasterProduct> findByName(String name);

    List<MasterProduct> findByCategory(String category);

    @Query("SELECT m FROM MasterProduct m WHERE m.description LIKE %?1%")
    List<MasterProduct> findByDescription(String description);

    Page<MasterProduct> findByNameContaining(String name, Pageable pageable);

}