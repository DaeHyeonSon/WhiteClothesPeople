package com.whitepeoples.wooso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentController {
    @RequestMapping(value="/main")
    public String m1(){
        System.out.println("m1()");
        return "payment";
    }
}