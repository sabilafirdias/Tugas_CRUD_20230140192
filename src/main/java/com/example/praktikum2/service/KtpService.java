package com.example.praktikum2.service;

import com.example.praktikum2.model.dto.KtpDto;
import com.example.praktikum2.model.dto.KtpAddRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface KtpService {
    KtpDto addKtp(KtpAddRequest request);
    List<KtpDto> getAllKtp();
    KtpDto getKtpById(Integer id);
    KtpDto updateKtp(Integer id, KtpAddRequest request);
    void deleteKtp(Integer id);
}
