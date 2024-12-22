package com.example.bekspark_test.service;

import com.example.bekspark_test.dto.SocksDto;
import com.example.bekspark_test.dto.SocksQueryDto;

public interface SocksService {
    /**
     * Создает запись о носках или добавляет количество.
     *
     * @param socksDto Данные о носках.
     */
    void income(SocksDto socksDto);

    /**
     * Убавляет количество носков.
     *
     * @param socksDto Данные о носках.
     */
    void outcome(SocksDto socksDto);

    /**
     * Возвращает количество носков по цвету и содержанию хлопка.
     *
     * @param socksQueryDto Данные для фильтрации.
     * @return количество носков на складе.
     */
    int getQuantity(SocksQueryDto socksQueryDto);

    /**
     * Обновляет данные о носках.
     *
     * @param socksDto Данные о носках.
     */
    void update(Long id, SocksDto socksDto);
}