package com.whitepeoples.wooso.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whitepeoples.wooso.model.entity.Payment;
import com.whitepeoples.wooso.service.PaymentServiceImpl;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentServiceImpl paymentServiceImpl;

    @Value("${imp.api.key}")
    private String apiKey;

    @Value("${imp.api.secretkey}")
    private String secretKey;

    @Value("${imp.init}")
    private String impCode;

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);


    PaymentController(PaymentServiceImpl paymentServiceImpl) {
        this.paymentServiceImpl = paymentServiceImpl;
    }


    @RequestMapping("")
    public String paymentPage(Model model){
       logger.info("paymentPage()----");
       model.addAttribute("impCode", impCode);
       
       return "payment";
    }

    @PostMapping("/success")
    @ResponseBody
    public String paymentSuccess(@RequestParam Map<String, String> params, Model model) {
        String impUid = params.get("imp_uid");
        String merchantUid = params.get("merchant_uid");

        logger.info("Payment Success: imp_uid={}, merchant_uid={}", impUid, merchantUid);

        model.addAttribute("impUid", impUid);
        model.addAttribute("merchantUid", merchantUid);

        return "confirmation";
    }

    @GetMapping("/fail")
    public String paymentFail(@RequestParam Map<String, String> params, Model model) {
        String impUid = params.get("imp_uid");
        String merchantUid = params.get("merchant_uid");

        logger.info("Payment Failed: imp_uid={}, merchant_uid={}", impUid, merchantUid);

        model.addAttribute("impUid", impUid);
        model.addAttribute("merchantUid", merchantUid);

        return "fail";
    }

    @GetMapping("/details")
    @ResponseBody
    public Map<String, Object> getUserDetails(){
        Map<String, Object> user = new HashMap<>();
        user.put("planName", "basic");
        user.put("plan", "100");
        user.put("email", "my@gmail.com");
        user.put("name", "고망고");
        user.put("phone", "010-1234-1234");
        user.put("address", "서울특별시 집");
        user.put("postcode", "03423");
        System.out.println(user);
        return user;
    }

    //ToDo : mId 파라미터 설정 
    @GetMapping("/refund")
    public String cancelPayment(String merchantUid) throws IOException {
        String token = paymentServiceImpl.getToken(apiKey,secretKey);
        paymentServiceImpl.cancelOrder(token,merchantUid,"테스트 결제");
        return "refund";
    }
}
