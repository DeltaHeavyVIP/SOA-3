package com.example.basic.repositories;

import com.example.basic.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    Integer countAllByPriceAfter(Long price);

    ArrayList<Product> findAllByNameContaining(String subString);

    @Query(value = "SELECT * FROM product ORDER BY ?1", nativeQuery = true)
    List<Product> findAllWithOrderBy(String orderBy);
}
