package com.example.commhere.service;

import com.example.commhere.dto.PayDTO;
import com.example.commhere.dto.PayRequestDTO;
import com.example.commhere.entity.Pay;
import com.example.commhere.entity.User;
import com.example.commhere.repository.PayRepository;
import com.example.commhere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayService {

    private final PayRepository payRepository;
    private final UserRepository userRepository;

    public String addPay(PayRequestDTO payRequestDTO, String userId) {
        if(payRequestDTO != null){
            if(!payRequestDTO.getItems().equals("")){
                if(!payRequestDTO.getOption().equals("")){
                    if(payRequestDTO.getAmt() != 0){
                        User user = userRepository.findById(userId).orElseThrow(()->
                                new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
                        payRequestDTO.setConfirmation("N");
//                        payRepository.save(PayRequestDTO.toEntity(user));
                        return "결제 성공";
                    } else return "내부 오류가 발생했습니다. 상품을 재선택 후 다시 결제 해주세요.";
                }else return "결제 방법을 선택 후 재시도 바랍니다.";
            } else return "결제 상품을 다시 선택 후 재시도 바랍니다.";
        }
        return "결제 실패";
    }
    public String cancelPay(Long payId, String userId) {
        if(payId != null){
            Pay pay = payRepository.findById(payId).orElseThrow(()->
                    new IllegalArgumentException("해당 결제 내용이 존재하지 않습니다."));
            PayDTO payDTO = new PayDTO(pay);
            payDTO.setDeletedYN("Y");
            User user = userRepository.findById(userId).orElseThrow(()->
                    new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
            payRepository.save(payDTO.toEntity(user));
            return "결제 취소가 완료되었습니다.";
        }
        return "결제 취소가 완료되지 않았습니다. 재시도 바랍니다.";
    }

    public String confirm(Long payId, String userId) {
        if(payId != null){
            Pay pay = payRepository.findById(payId).orElseThrow(()->
                    new IllegalArgumentException("해당 결제 내용이 존재하지 않습니다."));
            PayDTO payDTO = new PayDTO(pay);
            payDTO.setConfirmation("Y");
            if(payDTO.getDeletedYN().equals("") || payDTO.getDeletedYN().equals("Y")) payDTO.setDeletedYN("N");
            User user = userRepository.findById(userId).orElseThrow(()->
                    new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
            payRepository.save(payDTO.toEntity(user));
            return "구매 확정이 완료되었습니다.";
        }
        return "구매 확정이 완료되지 않았습니다. 재시도 바랍니다.";
    }
}
