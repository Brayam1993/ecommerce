package com.example.ecomerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "monedas")
@Data
public class Moneda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monedaId;
    private String nombre;
    private String simbolo;
    private Float tasaCambio;

}
