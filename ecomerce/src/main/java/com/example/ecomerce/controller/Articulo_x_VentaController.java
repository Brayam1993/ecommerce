package com.example.ecomerce.controller;

import com.example.ecomerce.entity.Articulo_x_Venta;
import com.example.ecomerce.service.Articulo_x_VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articulos")
public class Articulo_x_VentaController {

    @Autowired
    private Articulo_x_VentaService articuloService;

    @PostMapping
    public Articulo_x_Venta crearArticulo(@RequestBody Articulo_x_Venta articulo) {
        return articuloService.crearArticulo(articulo);
    }

    @GetMapping
    public List<Articulo_x_Venta> obtenerTodosLosArticulos() {
        return articuloService.obtenerTodosLosArticulos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articulo_x_Venta> obtenerArticuloPorId(@PathVariable Long id) {
        return articuloService.obtenerArticulosPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/venta/{ventaId}")
    public List<Articulo_x_Venta> obtenerArticulosPorVentaId(@PathVariable Long ventaId){
        return articuloService.obtenerArticulosPorVentaId(ventaId);
    }

    @GetMapping("/producto/{productoId}")
    public List<Articulo_x_Venta> obtenerArticulosPorProductoId(@PathVariable Long productoId) {
        return articuloService.obtenerArticulosPorProductoId(productoId);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Articulo_x_Venta> obtenerArticulosPorClienteId(@PathVariable Long clienteId) {
        return articuloService.obtenerArticulosPorVentaId(clienteId);
    }

    @PutMapping("/{id}")
    public Articulo_x_Venta actualizarArticulo(@PathVariable Long id, @RequestBody Articulo_x_Venta articulo) {
        return articuloService.actualizarArticulo(id, articulo);
    }

    @DeleteMapping("/{id}")
    public void eliminarArticulo(@PathVariable Long id) {
        articuloService.eliminarArticulo(id);
    }

}
