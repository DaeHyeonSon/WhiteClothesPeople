package com.whitepeoples.wooso.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whitepeoples.wooso.dao.GuarantorRepository;
import com.whitepeoples.wooso.dao.MatchingRepository;
import com.whitepeoples.wooso.dao.RequestMatchingRepository;
import com.whitepeoples.wooso.model.entity.Guarantor;
import com.whitepeoples.wooso.model.entity.Matching;
import com.whitepeoples.wooso.model.entity.RequestMatching;
import com.whitepeoples.wooso.model.entity.EnumTypes.MatchingRequestType;

@Service
public class IncentiveServiceImpl implements IncentiveService {

    @Autowired
    private RequestMatchingRepository requestMatchingRepository;
    @Autowired
    private GuarantorRepository guarantorRepository;

    @Override
    public Map<String, Object> calculateMatchingAndSendIncentive(Integer guarantorId) {
        Guarantor guarantor = guarantorRepository.findById(guarantorId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 존재하지 않습니다: " + guarantorId));

        int totalReqMatchCount = 0;

        // RequestMatching 엔티티를 가져와서 상태를 열거형 상수로 비교
        List<RequestMatching> reqmatchings = requestMatchingRepository.findByGuarantor(guarantor);
        for (RequestMatching m : reqmatchings) {
            if (m.getStatus() == MatchingRequestType.APPROVED) { // 열거형 상수로 비교
                totalReqMatchCount++;
            }
        }

        int ReqIncentiveAmount = totalReqMatchCount * 1449; // 매칭당 1449원

        // 은행 계좌로 돈을 전송하는 가상 메서드 호출
        boolean transferSuccessful = sendMoneyToBankAccount(guarantor.getBank(), guarantor.getAccountNumber(), ReqIncentiveAmount);

        // 결과를 JSON 형식으로 반환
        Map<String, Object> response = new HashMap<>();
        response.put("guarantorId", guarantorId);
        response.put("name", guarantor.getName());
        response.put("bank", guarantor.getBank());
        response.put("accountNumber", guarantor.getAccountNumber());
        response.put("totalReqMatchCount", totalReqMatchCount);
        response.put("incentiveAmount", ReqIncentiveAmount);
        response.put("transferSuccessful", transferSuccessful);

        return response;
    }

    private boolean sendMoneyToBankAccount(String bank, int accountNumber, int amount) {
        System.out.println("은행: " + bank + ", 계좌번호: " + accountNumber + ", 송금 금액: " + amount);
        return true;
    }
}