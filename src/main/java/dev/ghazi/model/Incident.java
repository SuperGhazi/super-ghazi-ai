package dev.ghazi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "application_incident")
@Getter
@Setter
public class Incident extends AbstractEntity {

    @ManyToOne
    private User user;

    private String reason;

    private LocalDateTime timestamp;
}
