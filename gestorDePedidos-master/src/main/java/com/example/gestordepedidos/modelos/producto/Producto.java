package com.example.gestordepedidos.modelos.producto;

import lombok.Data;

/**
 *Representa un producto
 */
@Data
public class Producto {
    private String nombre;
    private Integer cantidad ;
    private Double precio;
}
