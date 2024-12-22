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
public class SocksQueryDto {
    @Schema(description = "Socks color")
    private String socksColor;

    @Schema(description = "Compare command")
    private String compareCommand;

    @Schema(description = "Cotton part")
    private int socksCottonPart;
}
