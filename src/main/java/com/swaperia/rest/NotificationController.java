package com.swaperia.rest;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swaperia.service.NotificationService;
import com.swaperia.service.dto.NotificationDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class NotificationController {
	
	private NotificationService notificationService;
	
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    // Mapped as /app/application
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public NotificationDTO send(@RequestBody NotificationDTO notificationDTO) throws Exception {
    	notificationService.saveNotification(notificationDTO);
        return notificationDTO;
    }

    // Mapped as /app/private
    @MessageMapping("/private")
    public void sendToSpecificUser(@RequestBody NotificationDTO notificationDTO, HttpServletRequest request) {
    	notificationService.saveNotification(notificationDTO);
    	System.out.println(notificationDTO.getReciever());
    	simpMessagingTemplate.convertAndSendToUser(notificationDTO.getReciever(), "/queue/notify", notificationDTO);

    }
}
