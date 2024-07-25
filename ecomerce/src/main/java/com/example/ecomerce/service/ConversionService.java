package com.example.ecomerce.service;

import org.springframework.stereotype.Service;

@Service
public class ConversionService {

    public float obtenerValorComvertidoAmonedaSolicitada(Float cantidad, Float taseDeCambio){
        return cantidad * taseDeCambio;
    }

}
