package com.example.sesion;

import com.example.gestordepedidos.modelos.usuario.Usuario;
import com.example.gestordepedidos.modelos.pedido.Pedido;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una sesion donde almacenar las variables de sesion
 */
@Data
public class Sesion {

    /**
     * Usuario logeado
     */
    @Setter
    @Getter
    private static Usuario usuarioActual;

    /**
     * Pedido pulsado por el usuario
     */
    @Setter
    @Getter
    private static Pedido pedidoPulsado;





}
