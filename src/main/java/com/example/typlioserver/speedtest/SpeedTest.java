package com.example.typlioserver.speedtest;

import com.example.typlioserver.common.IntArrayToStringConverter;
import com.example.typlioserver.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpeedTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private SpeedTestMode mode;

    @NotNull
    private Integer time;

    @NotNull
    private Integer cpm;

    @NotNull
    private Integer mistakes;

    @NotNull
    private Float accuracy;

    @NotNull
    @Column(length = 1000)
    @Convert(converter = IntArrayToStringConverter.class)
    private List<Integer> wpmHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Histogram> histogram = new ArrayList<>();

    @ManyToOne
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
