package com.example.ecomerce.service;

import com.example.ecomerce.entity.Moneda;
import com.example.ecomerce.repository.MonedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonedaService {

    @Autowired
    private MonedaRepository monedaRepository;

    public Moneda crearMoneda(Moneda moneda){
        return monedaRepository.save(moneda);
    }

    public List<Moneda> obtenerTodasLasMonedas(){
        return monedaRepository.findAll();
    }

    public Optional<Moneda> obtenerMonedaPorId(Long id){
        return monedaRepository.findById(id);
    }

    public Moneda actualizarMoneda(Long id, Moneda moneda){
        moneda.setMonedaId(id);
        return monedaRepository.save(moneda);
    }

    public void eliminarMoneda(Long id){
        monedaRepository.deleteById(id);
    }

}
