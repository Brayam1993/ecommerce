package com.example.ecomerce.service;

import com.example.ecomerce.entity.Producto;
import com.example.ecomerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto crearProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerTodosLosProductos(){
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerProductoPorId(Long id){
        return productoRepository.findById(id);
    }

    public Producto actualizarProducto(Long id,Producto producto){
        producto.setProducto_id(id);
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }
}
