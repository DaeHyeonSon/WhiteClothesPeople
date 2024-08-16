package com.whitepeoples.wooso.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whitepeoples.wooso.model.dto.SessionDTO;
import com.whitepeoples.wooso.service.MatchingService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
@RequestMapping("/matching")
public class MatchingController {
	private final MatchingService matchingService;
	
	@GetMapping
	public String showMatchingPage(HttpSession session, Model model) throws Exception {
		// 현재 사용자 정보를 세션에서 가져와서 프로필 편집 폼에 전달
		SessionDTO sessionDTO = (SessionDTO) session.getAttribute("SessionDTO");
		if (sessionDTO != null && matchingService.isUserMastchingAvailable(sessionDTO)) {

			return "redirect:/mainpage";
		} else {
			return "redirect:/subscription"; // 세션에 사용자 정보가 없으면 로그인 페이지로 리다이렉트
		}
	}

	
	
}