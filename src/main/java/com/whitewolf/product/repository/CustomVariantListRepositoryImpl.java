package com.whitewolf.product.repository;

import com.whitewolf.product.model.VariantProduct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomVariantListRepositoryImpl implements CustomVariantListRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<VariantProduct> criteriaWayFindByColorAndSize(String color, String size) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<VariantProduct> query = cb.createQuery(VariantProduct.class);
        Root<VariantProduct> root = query.from(VariantProduct.class);
        List<Predicate> predicates = new ArrayList<>();
        if (color != null) {
            predicates.add(cb.equal(root.get("color"), color));
        }
        if (size != null) {
            predicates.add(cb.equal(root.get("size"), size));
        }
        query.where(cb.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(query).getResultList();
    }

}
