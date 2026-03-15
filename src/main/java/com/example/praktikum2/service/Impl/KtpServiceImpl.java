package com.example.praktikum2.service.Impl;

import com.example.praktikum2.mapper.KtpMapper;
import com.example.praktikum2.model.dto.KtpAddRequest;
import com.example.praktikum2.model.dto.KtpDto;
import com.example.praktikum2.model.entity.Ktp;
import com.example.praktikum2.repository.KtpRepository;
import com.example.praktikum2.service.KtpService;
import com.example.praktikum2.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KtpServiceImpl implements KtpService {

    @Autowired
    private KtpRepository ktpRepository;

    @Autowired
    private ValidationUtil validationUtil;

    @Override
    public KtpDto addKtp(KtpAddRequest request) {
        System.out.println("[SERVICE] Mencoba menambah KTP: " + request.getNomorKtp());

        validationUtil.validate(request);

        if (ktpRepository.findByNomorKtp(request.getNomorKtp()).isPresent()) {
            System.out.println("[SERVICE] Error: Nomor KTP sudah terdaftar!");
            throw new RuntimeException("Nomor KTP sudah terdaftar!");
        }

        Ktp saveKtp = Ktp.builder()
                .nomorKtp(request.getNomorKtp())
                .namaLengkap(request.getNamaLengkap())
                .alamat(request.getAlamat())
                .tanggalLahir(request.getTanggalLahir())
                .jenisKelamin(request.getJenisKelamin())
                .build();

        System.out.println("[SERVICE] Menyimpan ke database...");
        ktpRepository.save(saveKtp);
        System.out.println("[SERVICE] Berhasil disimpan!");

        return KtpMapper.MAPPER.toKtpDtoData(saveKtp);
    }

    @Override
    public List<KtpDto> getAllKtp() {
        List<Ktp> ktps = ktpRepository.findAll();
        List<KtpDto> ktpDtos = new ArrayList<>();
        for (Ktp ktp : ktps) {
            ktpDtos.add(KtpMapper.MAPPER.toKtpDtoData(ktp));
        }
        return ktpDtos;
    }

    @Override
    public KtpDto getKtpById(Integer id) {
        Ktp ktp = ktpRepository.findById(id).orElseThrow(() -> new RuntimeException("KTP tidak ditemukan"));
        return KtpMapper.MAPPER.toKtpDtoData(ktp);
    }

    @Override
    public KtpDto updateKtp(Integer id, KtpAddRequest request) {
        validationUtil.validate(request);
        Ktp existingKtp = ktpRepository.findById(id).orElseThrow(() -> new RuntimeException("KTP tidak ditemukan"));
        if (!existingKtp.getNomorKtp().equals(request.getNomorKtp())) {
            if (ktpRepository.findByNomorKtp(request.getNomorKtp()).isPresent()) {
                throw new RuntimeException("Nomor KTP sudah terdaftar!");
            }
        }
        Ktp ktp = Ktp.builder()
                .id(existingKtp.getId())
                .nomorKtp(request.getNomorKtp())
                .namaLengkap(request.getNamaLengkap())
                .alamat(request.getAlamat())
                .tanggalLahir(request.getTanggalLahir())
                .jenisKelamin(request.getJenisKelamin())
                .build();
        ktpRepository.save(ktp);
        return KtpMapper.MAPPER.toKtpDtoData(ktp);
    }

    @Override
    public void deleteKtp(Integer id) {
        Ktp ktp = ktpRepository.findById(id).orElseThrow(() -> new RuntimeException("KTP tidak ditemukan"));
        ktpRepository.delete(ktp);
    }
}