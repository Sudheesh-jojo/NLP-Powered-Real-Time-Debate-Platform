package com.debate.backend.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "argument_links")
@Getter
@Setter
@NoArgsConstructor

public class ArgumentLink {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "from_argument_id", nullable = false)
    private Argument fromArgument;

    // The argument to which the link points
    @ManyToOne
    @JoinColumn(name = "to_argument_id", nullable = false)
    private Argument toArgument;

    private String linkType;

    private Double similarityScore;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

