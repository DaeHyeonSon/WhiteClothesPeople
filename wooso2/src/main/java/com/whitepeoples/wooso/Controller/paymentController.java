package com.whitepeoples.wooso.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class paymentController {
    @RequestMapping(value="/main")
    public String m1(){
        System.out.println("m1()");
        return "payment";
    }
}
