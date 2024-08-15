package com.whitepeoples.wooso.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.whitepeoples.wooso.controller.PaymentController;

@Service
public class PaymentServiceImpl implements PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Override
    public void cancelOrder(String accessToken, String merchantUid, String reason) throws IOException {
        URL url = new URL("https://api.iamport.kr/payments/cancel");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        // 요청 방식을 POST로 설정
        conn.setRequestMethod("POST");

        // 요청의 Content-Type, Accept, Authorization 헤더 설정
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", accessToken);

        // 해당 연결을 출력 스트림(요청)으로 사용
        conn.setDoOutput(true);

        // JSON 객체에 해당 API가 필요로 하는 데이터 추가
        JsonObject json = new JsonObject();
        json.addProperty("merchant_uid", merchantUid);
        json.addProperty("reason", reason);

        // 출력 스트림으로 해당 conn에 요청
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()))) {
            bw.write(json.toString());
            bw.flush();
        }

        // 입력 스트림으로 conn 요청에 대한 응답 반환
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String response = br.readLine();
        }

        conn.disconnect();

        logger.info("결제 취소 완료 : 주문 번호 {}", merchantUid);
    }

    @Override
    public String getToken(String apiKey, String secretKey) throws IOException {
        URL url = new URL("https://api.iamport.kr/users/getToken");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        // 요청 방식을 POST로 설정
        conn.setRequestMethod("POST");

        // 요청의 Content-Type과 Accept 헤더 설정
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");

        // 해당 연결을 출력 스트림(요청)으로 사용
        conn.setDoOutput(true);

        // JSON 객체에 해당 API가 필요로 하는 데이터 추가
        JsonObject json = new JsonObject();
        json.addProperty("imp_key", apiKey);
        json.addProperty("imp_secret", secretKey);

        // 출력 스트림으로 해당 conn에 요청
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()))) {
            bw.write(json.toString());
            bw.flush();
        }

        // 입력 스트림으로 conn 요청에 대한 응답 반환
        String response;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            response = br.readLine();
        }

        Map<String, Object> responseMap = new Gson().fromJson(response, Map.class);
        String accessToken = ((Map<String, String>) responseMap.get("response")).get("access_token");
        conn.disconnect();

        logger.info("Iamport 엑세스 토큰 발급 성공 : {}", accessToken);
        return accessToken;
    } 
}