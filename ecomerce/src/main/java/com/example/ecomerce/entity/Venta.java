package com.example.ecomerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venta_id;

    @ManyToOne
    @JoinColumn(name = "cliente_id",nullable = false)
    private Cliente cliente;

    private LocalDateTime fecha;
    private Float monto;

    @ManyToOne
    @JoinColumn(name = "moneda_id")
    private Moneda moneda;

}
