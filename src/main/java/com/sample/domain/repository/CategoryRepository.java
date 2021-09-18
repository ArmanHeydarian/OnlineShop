package com.sample.domain.repository;

import com.sample.domain.model.Category;
import com.sample.domain.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends CrudRepository<Category , Integer> {
    List<Category> findByTitle(String title);

}
