package com.example.ecomerce.service;

import com.example.ecomerce.entity.Articulo_x_Venta;
import com.example.ecomerce.entity.Producto;
import com.example.ecomerce.repository.Articulo_x_VentaRepository;
import com.example.ecomerce.repository.ProductoRepository;
import com.example.ecomerce.repository.VentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ecomerce.entity.Venta;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private Articulo_x_VentaRepository articuloRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public List<Venta> obtenerVentasPorClienteId(Long clienteId) {
        return ventaRepository.findVentasByClienteIdNative(clienteId);
    }

    public List<Venta> obtenerVentasPorProductoId(Long productoId) {
        return articuloRepository.findByProductoId(productoId).stream()
                .map(Articulo_x_Venta::getVenta)
                .distinct()
                .toList();
    }

    @Transactional
    public Venta actualizarVenta(Long id, Venta venta, List<Articulo_x_Venta> articulos) {
        // Revertir las cantidades de los productos anteriores
        List<Articulo_x_Venta> articulosExistentes = articuloRepository.findByVentaId(id);
        for (Articulo_x_Venta articulo : articulosExistentes) {
            Producto producto = articulo.getProducto();
            if (producto != null) {
                producto.setCantidad(producto.getCantidad() + articulo.getCantidad());
                productoRepository.save(producto);
            } else {
                throw new RuntimeException("Producto no encontrado para el artículo existente con id: " + articulo.getArticulo_id());
            }
        }

        // Actualizar la venta
        venta.setVenta_id(id);
        Venta ventaActualizada = ventaRepository.save(venta);

        // Actualizar los artículos y restar las nuevas cantidades
        for(Articulo_x_Venta articulo : articulos) {
            articulo.setVenta(ventaActualizada);
            articuloRepository.save(articulo);

            Producto producto = articulo.getProducto();
            if (producto != null) {
                producto.setCantidad(producto.getCantidad() - articulo.getCantidad());
                productoRepository.save(producto);
            } else {
                throw new RuntimeException("Producto no encontrado para el artículo con id: " + articulo.getArticulo_id());
            }
        }
        return ventaActualizada;
    }

    @Transactional
    public Venta crearVenta(Venta venta, List<Articulo_x_Venta> articulos) {
        Venta nuevaVenta = ventaRepository.save(venta);
        for(Articulo_x_Venta articulo: articulos) {
            articulo.setVenta(nuevaVenta);
            articuloRepository.save(articulo);

            Optional<Producto> productoOpt = productoRepository.findById(articulo.getProducto().getProducto_id());
            if(productoOpt.isPresent()) {
                Producto producto = productoOpt.get();
                producto.setCantidad(producto.getCantidad() - articulo.getCantidad());
                productoRepository.save(producto);
            } else {
                throw new RuntimeException("Producto no encontrado para el artículo con id: " + articulo.getArticulo_id());
            }
        }
        return nuevaVenta;
    }

    @Transactional
    public void eliminarVenta(Long id) {
        List<Articulo_x_Venta> articulos = articuloRepository.findByVentaId(id);
        for(Articulo_x_Venta articulo : articulos) {
            Optional<Producto> productoOpt = productoRepository.findById(articulo.getProducto().getProducto_id());
            if (productoOpt.isPresent()){
                Producto producto = productoOpt.get();
                producto.setCantidad(producto.getCantidad() + articulo.getCantidad());
                productoRepository.save(producto);
            }
            articuloRepository.delete(articulo);
        }
        ventaRepository.deleteById(id);
    }

}
