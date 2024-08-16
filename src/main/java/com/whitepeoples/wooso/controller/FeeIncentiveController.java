package com.whitepeoples.wooso.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.whitepeoples.wooso.service.IncentiveService;

@Controller
@RequestMapping("/incentive")
public class FeeIncentiveController {
	
	@Autowired
	IncentiveService incentiveService;
	
	 @GetMapping("")
	    public ResponseEntity<Map<String, Object>> incentiveReturn(@RequestParam("guarantorId") Integer guarantorId) {
	        
		 	Map<String, Object> response = incentiveService.calculateMatchingAndSendIncentive(guarantorId);
	        return ResponseEntity.ok(response);
	    }
}