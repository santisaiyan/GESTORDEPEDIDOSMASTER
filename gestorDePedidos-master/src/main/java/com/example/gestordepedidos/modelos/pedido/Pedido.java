package com.example.gestordepedidos.modelos.pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un pedido
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pedido {

    private Integer id;
    private String codigo;
    private String fecha;
    private Integer usuarioId;
    private String total;

    /**
     * Contructor solo con el parametro id
     * @param id
     */
    public Pedido(int id){
        this.id=id;
    }
}
