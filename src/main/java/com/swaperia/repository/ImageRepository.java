package com.swaperia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swaperia.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
