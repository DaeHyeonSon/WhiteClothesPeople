package com.whitepeoples.wooso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FCMController {

    @Autowired
    private FCMService fcmService;

    @PostMapping("/sendNotification")
    public ResponseEntity<String> sendNotification(@RequestBody notificationRequests notificationRequest) {
        try {
            fcmService.sendNotification(notificationRequest.getToken(), notificationRequest.getTitle(), notificationRequest.getBody());
            return ResponseEntity.ok("Notification sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send notification: " + e.getMessage());
        }
    }
}
