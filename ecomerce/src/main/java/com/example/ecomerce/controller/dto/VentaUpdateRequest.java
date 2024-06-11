package com.example.ecomerce.controller.dto;

import com.example.ecomerce.entity.Articulo_x_Venta;
import com.example.ecomerce.entity.Venta;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;


import java.util.List;

@Data
public class VentaUpdateRequest {

    @NotNull
    private Venta venta;
    @NotNull
    private List<Articulo_x_Venta> articulos;

}
