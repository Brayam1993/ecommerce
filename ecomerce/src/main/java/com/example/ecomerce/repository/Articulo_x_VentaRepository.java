package com.example.ecomerce.repository;

import com.example.ecomerce.entity.Articulo_x_Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Articulo_x_VentaRepository extends JpaRepository<Articulo_x_Venta, Long> {

    // Consulta SQL nativa
    @Query(value = "SELECT * FROM articulos_x_venta v WHERE v.producto_id = :productoId", nativeQuery = true)
    List<Articulo_x_Venta> findByProductoId(@Param("productoId") Long productoId);

    // Consulta SQL nativa
    @Query(value = "SELECT * FROM articulos_x_venta v WHERE v.venta_id = :ventaId", nativeQuery = true)
    List<Articulo_x_Venta> findByVentaId(@Param("ventaId") Long ventaId);
}