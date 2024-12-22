package com.example.bekspark_test.service;

import com.example.bekspark_test.dto.SocksDto;
import com.example.bekspark_test.dto.SocksQueryDto;

public interface SocksService {
    void income(SocksDto socksDto);

    void outcome(SocksDto socksDto);

    int getQuantity(SocksQueryDto socksQueryDto);

    void update(Long id, SocksDto socksDto);
}
