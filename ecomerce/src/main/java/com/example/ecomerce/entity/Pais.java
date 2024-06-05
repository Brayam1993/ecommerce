package com.example.ecomerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "paises")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pais_id;

    private String nombre;

    @Column(unique = true)
    private String dominio;
}
