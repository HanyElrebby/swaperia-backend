package com.swaperia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swaperia.model.Notification;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long>{
	
}
