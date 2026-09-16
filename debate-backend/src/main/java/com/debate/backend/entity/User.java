package com.debate.backend.entity;
import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name="users")
@Getter
@Setter@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique=true,nullable=false)
    private String username;
    private String passwordHash;
    @Column(unique = true, nullable = false)
    private String email;

    private Double reputationScore = 5.0;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "createdBy")
    private List<Debate> debates;
    @OneToMany(mappedBy = "user")
    private List<Argument> arguments;



}

