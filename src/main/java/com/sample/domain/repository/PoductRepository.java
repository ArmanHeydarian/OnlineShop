package com.sample.domain.repository;

import java.util.List;
import com.sample.domain.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PoductRepository extends CrudRepository<Product , Integer> {
    List<Product> findByTitle(String title);

}
