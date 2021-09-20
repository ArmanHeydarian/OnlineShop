package com.sample.domain.repository;

import java.util.List;
import com.sample.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PoductRepository extends JpaRepository<Product , Integer> {
    List<Product> findByTitle(String title);

    @Query("SELECT p  FROM Product p  Left join fetch p.category c")
    public List<Product> GetAllRows();

}
