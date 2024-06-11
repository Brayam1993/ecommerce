package com.example.ecomerce.repository;

import com.example.ecomerce.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta,Long> {

    // Consulta SQL nativa
    @Query(value = "SELECT * FROM ventas v WHERE v.cliente_id = :clienteId", nativeQuery = true)
    List<Venta> findVentasByClienteIdNative(@Param("clienteId") Long clienteId);
}
