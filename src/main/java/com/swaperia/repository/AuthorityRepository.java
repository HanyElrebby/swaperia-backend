package com.swaperia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swaperia.model.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
