package com.example.bekspark_test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "socks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Socks {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long socksId;

    @Column(name = "color")
    private String socksColor;

    @Column(name = "cotton")
    private int socksCottonPart;

    @Column(name = "quantity")
    private int socksQuantity;
}
