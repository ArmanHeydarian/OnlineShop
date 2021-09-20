package com.sample.domain.repository;

import com.sample.domain.model.Product;
import com.sample.domain.model.ProductComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PoductCommentRepository extends CrudRepository<ProductComment, Integer> {


}
