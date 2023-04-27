package com.swaperia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swaperia.config.DeliveryConstants;
import com.swaperia.model.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long>{
	Optional<Delivery> findByType(DeliveryConstants deliveryConstants);
}
