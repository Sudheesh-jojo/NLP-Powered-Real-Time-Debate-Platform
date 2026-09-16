package com.debate.backend.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "argument_strength")
@Getter
@Setter
@NoArgsConstructor

public class ArgumentStrength {
    @Id
    @GeneratedValue
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "argument_id", nullable = false)
    private Argument argument;


    private Double score;

    private LocalDateTime calculatedAt;
}
