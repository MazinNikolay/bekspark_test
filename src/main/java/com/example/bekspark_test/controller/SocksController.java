package com.example.bekspark_test.controller;

import com.example.bekspark_test.dto.SocksDto;
import com.example.bekspark_test.dto.SocksQueryDto;
import com.example.bekspark_test.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/socks")
@RequiredArgsConstructor
@Tag(name = "API for accounting of socks in a store warehouse")
public class SocksController {
    private final SocksService service;
    private final Logger logger = LoggerFactory.getLogger(SocksController.class);

    @Operation(summary = "Registration of arrival of socks")
    @PostMapping("/income")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "ok",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input data")
    })
    public void incomeSocks(@RequestBody SocksDto socksDto) {
        logger.info("was invoked income method in controller");
        service.income(socksDto);
    }

    @Operation(summary = "Registration of outcome socks")
    @PostMapping("/outcome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "ok",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input data"),
            @ApiResponse(responseCode = "404",
                    description = "Socks not found")
    })
    public void outcomeSocks(@RequestBody SocksDto socksDto) {
        logger.info("was invoked outcome method in controller");
        service.outcome(socksDto);
    }

    @Operation(summary = "Getting information of socks with filtering")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "ok",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksQueryDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input data"),
            @ApiResponse(responseCode = "404",
                    description = "Socks not found")
    })
    public int getQuantitySocks(@RequestParam String socksColor,
                                @RequestParam String compareCommand,
                                @RequestParam int cottonPart) {
        logger.info("was invoked get quantity method in controller");
        SocksQueryDto socksQueryDto = SocksQueryDto.builder()
                .socksColor(socksColor)
                .compareCommand(compareCommand)
                .socksCottonPart(cottonPart)
                .build();
        return service.getQuantity(socksQueryDto);
    }

    @Operation(summary = "Update socks parameters")
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "ok",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = SocksDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Invalid input data"),
            @ApiResponse(responseCode = "404",
                    description = "Socks not found")
    })
    public void updateSocks(@PathVariable Long id,
                            @RequestBody SocksDto socksDto) {
        logger.info("was invoked update method in controller");
        service.update(id, socksDto);
    }
}