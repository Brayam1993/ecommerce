package com.example.ecomerce.service;

import com.example.ecomerce.entity.Moneda;
import com.example.ecomerce.entity.Producto;
import com.example.ecomerce.repository.MonedaRepository;
import com.example.ecomerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private MonedaRepository monedaRepository;

    public Producto crearProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerTodosLosProductos(){
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerProductoPorId(Long id){
        return productoRepository.findById(id);
    }

    public Optional<Producto> obtenerProductoPorIdTipoMoneda(Long id,Long monedaId) {

        Optional<Moneda> monedaOpt = monedaRepository.findById(monedaId);
        Optional<Producto> productoOpt = productoRepository.findById(id);

        if(productoOpt.isPresent() && monedaOpt.isPresent()) {
            Producto producto = productoOpt.get();
            Moneda moneda = monedaOpt.get();

            // Obtener la tasa de cambio entre la moneda base y la moneda solicitada
            double tasaCambio = moneda.getTasaCambio();

            // Calcular el precio en la moneda solicitada
            float precioConvertido = (float) (producto.getPrecioBase() * tasaCambio);

            // Crea uan copia del producto para no modificar el original
            Producto productoComvertido = new Producto();
            productoComvertido.setProducto_id(producto.getProducto_id());
            productoComvertido.setNombre(producto.getNombre());
            productoComvertido.setDescripcion(producto.getDescripcion());
            productoComvertido.setFoto(producto.getFoto());
            productoComvertido.setCantidad(producto.getCantidad());
            productoComvertido.setPrecioBase(precioConvertido);
            productoComvertido.setMonedaBase(moneda);

            return Optional.of(productoComvertido);
        } else {
            return Optional.empty();
        }
    }


    public Producto actualizarProducto(Long id,Producto producto){
        producto.setProducto_id(id);
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }

}
