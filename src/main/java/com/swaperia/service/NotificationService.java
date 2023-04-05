package com.swaperia.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.swaperia.model.Notification;
import com.swaperia.model.User;
import com.swaperia.repository.NotificationRepository;
import com.swaperia.repository.UserRepository;
import com.swaperia.service.dto.NotificationDTO;

@Service
public class NotificationService {
	private NotificationRepository notificationRepository;
	
	private UserRepository userRepository;

	public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
		this.notificationRepository = notificationRepository;
		this.userRepository = userRepository;
	}
	
	
	public Notification saveNotification(NotificationDTO notificationDTO) {
		Notification notification = new Notification();
		
		notification.setMessage(notificationDTO.getMessage());
		notification.setCreatedAt(Instant.now());
		notification.setStatus(notificationDTO.getStatus());
		
		User sender = userRepository.findOneByEmailIgnoreCase(notificationDTO.getSender()).get();
		User reciever = userRepository.findOneByEmailIgnoreCase(notificationDTO.getReciever()).get();
		
		notification.setFromUser(sender);
		notification.setToUser(reciever);
		
		return notificationRepository.save(notification);
	}
}
