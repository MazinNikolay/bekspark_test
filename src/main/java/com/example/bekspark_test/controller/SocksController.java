package com.example.bekspark_test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/socks/")
@RequiredArgsConstructor
@Tag(name = "API for accounting of socks in a store warehouse")
public class SocksController {
    private final SocksService service;

    @Operation(summary = "Registration of arrival of socks")
    @PostMapping("/income")
    @ApiResponses(value = @ApiResponse(responseCode = "200",
            description = "ok",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SocksDto.class))}
    ))
    public ResponseEntity<SocksDto> incomeSocks(@RequestBody SocksDto socksDto) {
        service.income(socksDto);
        return ResponseEntity.ok().build();
    }
}
