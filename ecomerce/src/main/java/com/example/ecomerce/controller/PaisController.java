package com.example.ecomerce.controller;

import com.example.ecomerce.entity.Pais;
import com.example.ecomerce.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paises")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @PostMapping
    public Pais crearPais(@RequestBody Pais pais){
        return paisService.crearPais(pais);
    }

    @GetMapping
    public List<Pais> obtenerTodosLosPaises(){
        return paisService.obtenerTodosLosPaises();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> obtenerPaisPorId(@PathVariable Long id){
        return paisService.obtenerPaisPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Pais actualizarPais(@PathVariable Long id, @RequestBody Pais pais){
        return paisService.actualizarPais(id,pais);
    }

    @DeleteMapping("/{id}")
    public void eliminarPais(@PathVariable Long id){
        paisService.eliminarPais(id);
    }

    @GetMapping(path = "/test")
    public String test() {
        return "getPaises";
    }

}