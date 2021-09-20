package com.sample.data;

import com.sample.domain.model.Product;
import com.sample.domain.repository.ProductRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PoductRepositoryImp implements ProductRepositoryCustom {

    EntityManager em;
    // constructor
    @Override
    public  List<Product> findProductsByTitleAndCost( String title ,int cost) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> products = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        if (title != null) {
            predicates.add(cb.like(products.get("title"), "%" + title + "%"));
        }
        if (cost >0) {
            predicates.add(cb.equal(products.get("cost"), cost));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();

    }


}
