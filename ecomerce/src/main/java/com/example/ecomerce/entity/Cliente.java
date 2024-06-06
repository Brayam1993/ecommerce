package com.example.ecomerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cliente_id;

    private String nombre;
    private String apellidos;

    @Column(unique = true)
    private String telefono;

    @Column(unique = true)
    private String email;

    private String direccion;
    private String cp;
    private String ciudad;

    @ManyToOne
    @JoinColumn(name = "pais", nullable = false)
    private Pais pais;
}
