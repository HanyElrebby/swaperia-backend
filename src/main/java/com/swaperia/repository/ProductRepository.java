package com.swaperia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swaperia.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
