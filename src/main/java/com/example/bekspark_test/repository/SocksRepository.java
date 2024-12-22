package com.example.bekspark_test.repository;

import com.example.bekspark_test.entity.Socks;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SocksRepository extends JpaRepository<Socks, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Socks s WHERE s.socksColor = :socksColor AND " +
            "s.socksCottonPart = :socksCottonPart")
    Optional<Socks> findByData(@Param("socksColor") String socksColor,
                               @Param("socksCottonPart") int socksCottonPart);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT SUM(s.socksQuantity) FROM Socks s WHERE s.socksColor = :socksColor " +
            "AND s.socksCottonPart = :socksCottonPart")
    Integer getCottonPartEqual(@Param("socksColor") String socksColor,
                              @Param("socksCottonPart") int socksCottonPart);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT SUM(s.socksQuantity) FROM Socks s WHERE s.socksColor = :socksColor " +
            "AND s.socksCottonPart < :socksCottonPart")
    Integer getCottonPartLess(@Param("socksColor") String socksColor,
                              @Param("socksCottonPart") int socksCottonPart);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT SUM(s.socksQuantity) FROM Socks s WHERE s.socksColor = :socksColor " +
            "AND s.socksCottonPart > :socksCottonPart")
    Integer getCottonPartGreater(@Param("socksColor") String socksColor,
                          @Param("socksCottonPart") int socksCottonPart);
}