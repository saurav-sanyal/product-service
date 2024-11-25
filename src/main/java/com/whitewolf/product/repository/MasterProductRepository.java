package com.whitewolf.product.repository;


import com.whitewolf.product.model.MasterProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterProductRepository extends JpaRepository<MasterProduct, Long> {
}