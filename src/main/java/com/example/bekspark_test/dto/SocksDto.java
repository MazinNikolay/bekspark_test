package com.example.bekspark_test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Color cannot be blank")
    private String socksColor;

    @Schema(description = "Cotton part")
    @Min(value = 0, message = "Cotton part must be greater than or equal to 0")
    private int socksCottonPart;

    @Schema(description = "Socks quantity")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private int socksQuantity;
}
