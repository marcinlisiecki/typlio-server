package com.example.typlioserver.speedtest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpeedTestRepository extends CrudRepository<SpeedTest, Long> {

    @Query("SELECT s FROM SpeedTest s WHERE s.user.id = :userId")
    List<SpeedTest> findAllByUserId(Long userId);
}
