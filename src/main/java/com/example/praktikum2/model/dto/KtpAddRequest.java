package com.example.praktikum2.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
public class KtpAddRequest {
    @NotBlank(message = "Nomor KTP tidak boleh kosong")
    @Size(min = 16, max = 16, message = "Nomor KTP harus tepat 16 digit")
    @JsonProperty("nomorKtp")
    private String nomorKtp;

    @NotBlank(message = "Nama Lengkap tidak boleh kosong")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Nama harus diisi huruf")
    @JsonProperty("namaLengkap")
    private String namaLengkap;

    @NotBlank(message = "Alamat tidak boleh kosong")
    @JsonProperty("alamat")
    private String alamat;

    @NotNull(message = "Tanggal Lahir tidak boleh kosong")
    @JsonProperty("tanggalLahir")
    private LocalDate tanggalLahir;

    @NotBlank(message = "Jenis Kelamin tidak boleh kosong")
    @JsonProperty("jenisKelamin")
    private String jenisKelamin;
}
