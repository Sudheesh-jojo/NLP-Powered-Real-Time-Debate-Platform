package com.debate.backend.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "arguments")
@Getter
@Setter
@NoArgsConstructor

public class Argument {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "debate_id", nullable = false)
    private Debate debate;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String messageText;

    private String argumentType;

    private Double nlpConfidence;

    @CreationTimestamp
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "fromArgument")
    private List<ArgumentLink> outgoingLinks;

    @OneToMany(mappedBy = "toArgument")
    private List<ArgumentLink> incomingLinks;


    @OneToMany(mappedBy = "argument")
    private List<ArgumentStrength> strengths;
}

