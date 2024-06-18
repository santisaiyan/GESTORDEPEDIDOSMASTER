package com.example.gestordepedidos.modelos.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un usuario del sistema
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
    private Integer id;
    private String nombre;
    private String pass;
    private String email;
}
