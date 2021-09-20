package com.sample.domain.repository;

import com.sample.domain.model.Product;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepositoryCustom {
    List<Product> findProductsByTitleAndCost( String title , int cost);
}