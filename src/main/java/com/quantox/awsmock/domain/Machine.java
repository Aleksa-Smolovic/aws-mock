package com.quantox.awsmock.domain;

import com.quantox.awsmock.domain.enumeration.MachineStatus;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "machine")
@Data
@Where(clause = "is_active = true")
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String uid;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MachineStatus status;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(updatable = false)
    private User createdBy;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private boolean active;

    @NotNull
    @Column(name = "is_locked", nullable = false)
    private boolean locked;

}
