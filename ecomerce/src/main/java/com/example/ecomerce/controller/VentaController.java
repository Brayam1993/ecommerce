package com.example.ecomerce.controller;

import com.example.ecomerce.controller.dto.VentaUpdateRequest;
import com.example.ecomerce.entity.Venta;
import com.example.ecomerce.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public List<Venta> obtenerTodasLasVentas() {
        return ventaService.obtenerTodasLasVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable Long id) {
        return ventaService.obtenerVentaPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{ventaId}/{monedaId}")
    public ResponseEntity<Venta> obtenerVentaPorIdMoneda(@PathVariable Long ventaId, @PathVariable Long monedaId ){
        return  ventaService.obtenerVentaPorIdTipoMoneda(ventaId,monedaId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Venta> obtenerVentasPorClienteId(@PathVariable Long clienteId){
        return ventaService.obtenerVentasPorClienteId(clienteId);
    }

    @GetMapping("/producto/{productoId}")
    public List<Venta> obtenerVentasPorProductoId(@PathVariable Long productoId) {
        return ventaService.obtenerVentasPorProductoId(productoId);
    }

    @PutMapping("/{id}")
    public Venta actualizarVenta(@PathVariable Long id, @RequestBody VentaUpdateRequest request){
        return ventaService.actualizarVenta(id, request.getVenta(),request.getArticulos());
    }

    @PostMapping
    public Venta crearVenta(@RequestBody VentaUpdateRequest request) {
        return ventaService.crearVenta(request.getVenta(), request.getArticulos());
    }

    @DeleteMapping("/{id}")
    public void eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
    }

}