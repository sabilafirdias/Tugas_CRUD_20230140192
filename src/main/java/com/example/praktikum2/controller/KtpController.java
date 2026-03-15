package com.example.praktikum2.controller;

import com.example.praktikum2.model.dto.KtpAddRequest;
import com.example.praktikum2.model.dto.KtpDto;
import com.example.praktikum2.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ktp")
@CrossOrigin(origins = "*")
public class KtpController {

    @Autowired
    private KtpService ktpService;

    @PostMapping(
            path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> AddKtp(@RequestBody KtpAddRequest request) {
        try {
            System.out.println("=== DEBUG REQUEST ===");
            System.out.println("Nomor KTP: " + request.getNomorKtp());
            System.out.println("Nama: " + request.getNamaLengkap());
            System.out.println("====================");

            KtpDto result = ktpService.addKtp(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "data", result
            ));
        } catch (Exception e) {
            System.out.println("=== ERROR ===");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.out.println("=============");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

    @GetMapping(
            path = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> getAllKtp() {
        List<KtpDto> result = ktpService.getAllKtp();
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "status", "success",
                "data", result
        ));
    }

    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> getKtpById(@PathVariable("id") Integer id) {
        try {
            KtpDto result = ktpService.getKtpById(id);

            // Cek jika data null
            if (result == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "status", "error",
                        "message", "Data KTP dengan ID " + id + " tidak ditemukan"
                ));
            }

            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status", "success",
                    "data", result
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "Terjadi kesalahan: " + e.getMessage()
            ));
        }
    }

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> UpdateKtp(
            @PathVariable("id") Integer id,
            @RequestBody KtpAddRequest request
    ) {
        KtpDto result = ktpService.updateKtp(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "status", "success",
                "message", "Data berhasil diperbarui",
                "data", result
        ));
    }

    @DeleteMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, Object>> DeleteKtp(@PathVariable("id") Integer id) {
        ktpService.deleteKtp(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "status", "success delete ktp with id " + id
        ));
    }
}