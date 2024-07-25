package com.example.ecomerce.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long producto_id;

    private String nombre;
    private String descripcion;
    private String foto;
    private Integer cantidad;
    private Float precioBase;

    @ManyToOne
    @JoinColumn(name = "moneda_base_id")
    private Moneda monedaBase;

}
