package com.example.ecomerce.controller;

import com.example.ecomerce.entity.Moneda;
import com.example.ecomerce.service.MonedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moneda")
public class MonedaController {

    @Autowired
    private MonedaService monedaService;

    @PostMapping
    public Moneda crearMoneda(@RequestBody Moneda moneda){
        return monedaService.crearMoneda(moneda);
    }

    @GetMapping
    public List<Moneda> obtenerTodasLasMonedas(){
        return monedaService.obtenerTodasLasMonedas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moneda> obtenerMonedaPorId(@PathVariable Long id){
        return monedaService.obtenerMonedaPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Moneda actualizarMoneda(@PathVariable Long id, @RequestBody Moneda moneda){
        return monedaService.actualizarMoneda(id,moneda);
    }

    @DeleteMapping("/{id}")
    public void eliminarMoneda(@PathVariable Long id){
        monedaService.eliminarMoneda(id);
    }

}
