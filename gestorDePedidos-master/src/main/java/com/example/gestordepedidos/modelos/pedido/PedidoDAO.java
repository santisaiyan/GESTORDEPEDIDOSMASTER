package com.example.gestordepedidos.modelos.pedido;

import com.example.gestordepedidos.modelos.usuario.Usuario;

import java.util.ArrayList;

/**
 * Iterfaz par el DAO de un pedido
 */
public interface PedidoDAO {
    /**
     * Busca el pedido con el id en la base de datos
     * @param id id del pedido
     * @return Devuelve el pedido que corresponde a ese id
     */
    public Pedido load(Long id);

    /**
     * Devuele todos los pedidos de la base de datos
     * @return Lista de todos los pedidos
     */
    public ArrayList<Pedido> loadAll();

    /**
     * Devuelve todos los pedidos que ha hrealizado un usuario
     * @param user usuario
     * @return Lista de todos los pedidos de un usuario
     */
    public ArrayList<Pedido> pedidosDeUnUsuario(Usuario user);
}
