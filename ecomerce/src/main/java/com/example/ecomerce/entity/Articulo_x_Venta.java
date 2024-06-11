package com.example.ecomerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "articulos_x_venta")
public class Articulo_x_Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articulo_id;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    private Integer cantidad;
}
