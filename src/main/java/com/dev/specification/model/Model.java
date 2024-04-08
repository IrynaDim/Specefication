package com.dev.specification.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "models")
@Data
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "producer_id", nullable = false)
    private Producer producer;

}
