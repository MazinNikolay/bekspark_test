package com.example.bekspark_test.controller;

import com.example.bekspark_test.dto.SocksDto;
import com.example.bekspark_test.service.SocksService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


@RestController
@RequestMapping("api/socks/batch")
@RequiredArgsConstructor
@Tag(name = "API for downloading socks data")
public class SocksBathController {
    private final SocksService service;
    private final Logger logger = LoggerFactory.getLogger(SocksBathController.class);

    @PostMapping
    public void downloadBath(@RequestParam("file") MultipartFile file) {
        logger.info("was invoked download bath method in controller");
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream()))) {
            List<String> lines = br.lines().toList();
            lines.forEach(e -> {
                String[] parts = e.split(",");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Invalid CSV format " + e);
                }
                String color = parts[0].trim();
                int cottonPart = Integer.parseInt(parts[1].trim());
                int quantity = Integer.parseInt(parts[2].trim());
                SocksDto socksDto = SocksDto.builder()
                        .socksColor(color)
                        .socksCottonPart(cottonPart)
                        .socksQuantity(quantity)
                        .build();
                service.income(socksDto);
            });
        } catch (Exception e) {
            throw new RuntimeException("Error processing file", e);
        }
    }
}