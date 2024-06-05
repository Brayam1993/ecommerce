package com.example.ecomerce.service;

import com.example.ecomerce.entity.Pais;
import com.example.ecomerce.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public Pais crearPais(Pais pais){
        return paisRepository.save(pais);
    }

    public List<Pais> obtenerTodosLosPaises() {
        return paisRepository.findAll();
    }

    public Optional<Pais> obtenerPaisPorId(Long id){
        return paisRepository.findById(id);
    }

    public Pais actualizarPais(Long id, Pais pais){
        pais.setPais_id(id);
        return paisRepository.save(pais);
    }

    public void eliminarPais(Long id){
        paisRepository.deleteById(id);
    }
}
