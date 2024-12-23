package com.example.bekspark_test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocksDto {
    @Schema(description = "Socks color")
    private String socksColor;

    @Schema(description = "Cotton part", minimum = "0")
    private int socksCottonPart;

    @Schema(description = "Socks quantity", minimum = "1")
    private int socksQuantity;
}
