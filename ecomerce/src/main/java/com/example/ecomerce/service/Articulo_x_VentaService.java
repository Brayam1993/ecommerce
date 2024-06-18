package com.example.ecomerce.service;

import com.example.ecomerce.entity.Articulo_x_Venta;
import com.example.ecomerce.repository.Articulo_x_VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Articulo_x_VentaService {

    @Autowired
    private Articulo_x_VentaRepository articuloRepository;

    public Articulo_x_Venta crearArticulo(Articulo_x_Venta articulo){
        return articuloRepository.save(articulo);
    }

    public List<Articulo_x_Venta> obtenerTodosLosArticulos() {
        return articuloRepository.findAll();
    }

    public Optional<Articulo_x_Venta> obtenerArticulosPorId(Long id) {
        return articuloRepository.findById(id);
    }

    public List<Articulo_x_Venta> obtenerArticulosPorVentaId(Long ventaId) {
        return articuloRepository.findByVentaId(ventaId);
    }

    public List<Articulo_x_Venta> obtenerArticulosPorProductoId(Long productoId){
        return articuloRepository.findByProductoId(productoId);
    }

    public Articulo_x_Venta actualizarArticulo(Long id, Articulo_x_Venta articulo) {
        articulo.setArticulo_id(id);
        return articuloRepository.save(articulo);
    }

    public void eliminarArticulo(Long id) {
        articuloRepository.deleteById(id);
    }

}
