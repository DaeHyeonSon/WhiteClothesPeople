package com.whitepeoples.wooso.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whitepeoples.wooso.dao.ProfileRepository;
import com.whitepeoples.wooso.dao.UserRepository;
import com.whitepeoples.wooso.model.dto.SessionDTO;
import com.whitepeoples.wooso.model.entity.Profile;
import com.whitepeoples.wooso.model.entity.Subscription;
import com.whitepeoples.wooso.model.entity.User;
import com.whitepeoples.wooso.model.entity.EnumTypes.PlanType;
import com.whitepeoples.wooso.model.entity.EnumTypes.UserType;
import com.whitepeoples.wooso.service.PaymentService;
import com.whitepeoples.wooso.service.ProfileService;
import com.whitepeoples.wooso.service.SubscriptionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	private final PaymentService paymentServiceImpl;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private ProfileService profileService;

	@Value("${imp.api.key}")
	private String apiKey;

	@Value("${imp.api.secretkey}")
	private String secretKey;

	@Value("${imp.init}")
	private String impCode;

	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	// 요기서 PaymentServiceImpl 타입을 PaymentService로 바꿨으..
	PaymentController(PaymentService paymentServiceImpl) {
		this.paymentServiceImpl = paymentServiceImpl;
	}

	@RequestMapping("")
	public String paymentPage(Model model) {
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
//	public Map<String, Object> getUserDetails() {
//		Integer userId = 1;
	public Map<String, Object> getUserDetails(HttpSession httpSession) {
		SessionDTO sessionDTO = (SessionDTO) httpSession.getAttribute("SessionDTO");
		Integer userId = sessionDTO.getUserId();	
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 존재하지 않습니다: " + userId));

		Profile profile = profileRepository.findByEntityIdAndEntityType(userId, UserType.USER)
				.orElseThrow(() -> new IllegalArgumentException("해당 Profile 존재 X: " + userId));
		Optional<Subscription> currentSubscription = subscriptionService.findByUser(user);
		// Subscription subscription = currentSubscription.get();

		Map<String, Object> dt = new HashMap<>();
		dt.put("email", user.getEmail());
		dt.put("name", user.getUsername());
		dt.put("phone", user.getPhoneNumber());
		dt.put("address", profile.getUserAddress());
		dt.put("postcode", "00000");
		System.out.println(dt);
		return dt;
	}

	// ToDo : mId 파라미터 설정
	@PostMapping("/refund")
	@ResponseBody
	public String cancelPayment(@RequestParam("userId") Integer userId,
	                            @RequestParam("merchant_uid") String merchantId) throws IOException {
	    String token = paymentServiceImpl.getToken(apiKey, secretKey);
	    paymentServiceImpl.cancelOrder(token, merchantId, "테스트 결제");
	    return "refund";
	}

}
