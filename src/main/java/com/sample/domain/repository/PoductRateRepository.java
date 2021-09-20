package com.sample.domain.repository;

import com.sample.domain.model.ProductComment;
import com.sample.domain.model.ProductRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PoductRateRepository extends CrudRepository<ProductRate, Integer> {


}
