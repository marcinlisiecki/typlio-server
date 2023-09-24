package com.example.typlioserver.speedtest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeedTestRepository extends JpaRepository<SpeedTest, Long> {

    Page<SpeedTest> findByUserIdAndModeIn(Long userId, List<SpeedTestMode> modes, Pageable pageable);
}
