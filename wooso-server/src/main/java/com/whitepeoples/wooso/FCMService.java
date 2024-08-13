package com.whitepeoples.wooso;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FCMService {

    @PostConstruct
    public void initialize() throws IOException {
        // 서비스 계정 키 JSON 파일 경로 설정
        FileInputStream serviceAccount = new FileInputStream("/home/seungun/workspace/woorifias-servlet/woorifsaServlet/wooso-server/src/main/java/com/whitepeoples/wooso/wooso-12c05-ea37cdaffe8f.json");

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }

    public void sendNotification(String token, String title, String body) {
        // FCM 알림 객체 생성
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        // FCM 메시지 객체 생성
        Message message = Message.builder()
                .setToken(token) // 클라이언트의 FCM 토큰 설정
                .setNotification(notification) // 알림 설정
                .build();

        try {
            // 메시지 전송
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
