package com.swaperia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swaperia.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	List<Category> findAll();
	Category findByName(String name);
}
