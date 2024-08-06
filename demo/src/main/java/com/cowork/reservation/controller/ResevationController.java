package com.cowork.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ResevationController {
	@GetMapping("/schedule")
	public String scheduleList() {
		return "all good all fine";
	}
	
	@PostMapping("/schedule")
	public String createSchedule() {
		return "all good all fine";
	}
	
	@PutMapping("/schedule")
	public String updateSchedule() {
		return "all good all fine";
	}
	
	@DeleteMapping("/schedule")
	public String deletechedule() {
		return "all good all fine";
	}
}
