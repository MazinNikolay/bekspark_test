package com.example.bekspark_test.service.impl;

import com.example.bekspark_test.dto.SocksDto;
import com.example.bekspark_test.dto.SocksQueryDto;
import com.example.bekspark_test.entity.Socks;
import com.example.bekspark_test.repository.SocksRepository;
import com.example.bekspark_test.service.SocksService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {
    private final SocksRepository repository;
    private final Logger logger = LoggerFactory.getLogger(SocksServiceImpl.class);

    @Override
    @Transactional
    public void income(SocksDto socksDto) {
        logger.info("was invoked socks income method in service");
        socksDto.setSocksColor(socksDto.getSocksColor().toLowerCase());
        Socks socks = repository.findByData(socksDto.getSocksColor(),
                socksDto.getSocksCottonPart()).orElse(new Socks());
        socks.setSocksColor(socksDto.getSocksColor());
        socks.setSocksCottonPart(socksDto.getSocksCottonPart());
        socks.setSocksQuantity(socks.getSocksQuantity() + socksDto.getSocksQuantity());
        repository.save(socks);
    }

    @Override
    @Transactional
    public void outcome(SocksDto socksDto) {
        logger.info("was invoked socks outcome method in service");
        Socks socks = repository.findByData(socksDto.getSocksColor().toLowerCase(),
                socksDto.getSocksCottonPart()).orElseThrow(() ->
                new IllegalArgumentException("Those socks not found. Illegal argument"));
        if (socks.getSocksQuantity() >= socksDto.getSocksQuantity()) {
            socks.setSocksQuantity(socks.getSocksQuantity() - socksDto.getSocksQuantity());
            repository.save(socks);
        } else {
            throw new IllegalArgumentException("Quantity is less than input value. Illegal argument");
        }
    }

    @Override
    @Transactional
    public int getQuantity(SocksQueryDto socksQueryDto) {
        logger.info("was invoked socks get quantity method in service");
        socksQueryDto.setCompareCommand(socksQueryDto.getCompareCommand().toLowerCase());
        socksQueryDto.setSocksColor(socksQueryDto.getSocksColor().toLowerCase());
        return switch (socksQueryDto.getCompareCommand()) {
            case "equal" -> repository.getCottonPartEqual(socksQueryDto.getSocksColor(),
                    socksQueryDto.getSocksCottonPart());
            case "morethan" -> repository.getCottonPartGreater(socksQueryDto.getSocksColor(),
                    socksQueryDto.getSocksCottonPart());
            case "lessthan" -> repository.getCottonPartLess(socksQueryDto.getSocksColor(),
                    socksQueryDto.getSocksCottonPart());
            default -> throw new IllegalArgumentException("Incorrect compare command. Illegal argument");
        };
    }

    @Override
    @Transactional
    public void update(Long id, SocksDto socksDto) {
        logger.info("was invoked socks update method in service");
        Socks socks = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Those socks not found. Illegal argument"));
        socks.setSocksColor(socksDto.getSocksColor().toLowerCase());
        socks.setSocksCottonPart(socksDto.getSocksCottonPart());
        socks.setSocksQuantity(socksDto.getSocksQuantity());
        repository.save(socks);
    }
}