package com.example.praktikum2.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDate;

@Data
public class KtpDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nomorKtp")
    private String nomorKtp;

    @JsonProperty("namaLengkap")
    private String namaLengkap;

    @JsonProperty("alamat")
    private String alamat;

    @JsonProperty("tanggalLahir")
    private LocalDate tanggalLahir;

    @JsonProperty("jenisKelamin")
    private String jenisKelamin;
}