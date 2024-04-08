package com.dev.specification.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producers")
@Data
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
