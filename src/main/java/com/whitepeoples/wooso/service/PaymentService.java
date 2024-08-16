package com.whitepeoples.wooso.service;

import java.io.IOException;

import org.springframework.stereotype.Service;


@Service
public interface PaymentService {
    void cancelOrder(String accessToken, String merchantUid, String reason) throws IOException;
    String getToken(String apiKey, String secretKey) throws IOException;
}