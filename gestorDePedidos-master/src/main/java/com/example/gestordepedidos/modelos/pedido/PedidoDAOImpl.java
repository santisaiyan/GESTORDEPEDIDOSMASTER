package com.example.gestordepedidos.modelos.pedido;

import com.example.db.Database;
import com.example.gestordepedidos.modelos.producto.Producto;
import com.example.gestordepedidos.modelos.usuario.Usuario;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class PedidoDAOImpl implements PedidoDAO {

    /**
     * Conetar con la base de datos
     */
    private final Connection connection = (new Database().getConnection());
    /**
     * Query de todos los pedidos de un usuario
     */
    private final String QUERYLOADPEDIDOSDEUSUARIO = "SELECT * FROM pedido WHERE usuario_id = ?";
    /**
     * Query de los productos de un pedido
     */
    private final String QUERYDETALLESDEUNPEDIDO = "SELECT producto.nombre,item.cantidad,producto.precio FROM (" +
            "(pedido INNER JOIN item ON pedido.id_pedido = item.pedido_id)" +
            " INNER JOIN producto ON producto.id_producto = item.producto_id)" +
            " INNER JOIN usuario ON usuario.id_usuario = pedido.usuario_id" +
            " WHERE pedido.id_pedido = ?";
    /**
     * Query de la suma total de lo que cuesta un pedido
     */
    private final String QUERYSUMATOTALDEUNPEDIDO = "SELECT SUM(item.cantidad * producto.precio) AS suma FROM " +
            "(pedido INNER JOIN item ON pedido.id_pedido = item.pedido_id)" +
            " INNER JOIN producto ON producto.id_producto = item.producto_id" +
            " WHERE pedido.id_pedido = ?";

    /**
     * Busca el pedido con el id en la base de datos
     */
    @Override
    public Pedido load(Long id) {
        return null;
    }

    /**
     * Devuele todos los pedidos de la base de datos
     * @return Lista de todos los pedidos
     */
    @Override
    public ArrayList<Pedido> loadAll() {
        return null;
    }
    /**
     * Devolver todos los pedidos que ha realizado un usuario

     */
    @Override
    public ArrayList<Pedido> pedidosDeUnUsuario(Usuario user) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement(QUERYLOADPEDIDOSDEUSUARIO)) {
            pst.setInt(1,user.getId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setCodigo(rs.getString("codigo"));
                pedido.setFecha(rs.getString("fecha"));
                pedido.setUsuarioId(rs.getInt("usuario_id"));
                pedido.setTotal(this.sumaTotalDeUnPedido(pedido.getId()));
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }

    /**
     * Buscar todos los productos de un pedido
     */
    public ArrayList<Producto> detallesDeUnPedido(Pedido pedido) {
        ArrayList<Producto> items = new ArrayList<>();
        Producto item;
        try (PreparedStatement pst = connection.prepareStatement(QUERYDETALLESDEUNPEDIDO)) {
            pst.setInt(1, pedido.getId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                item = new Producto();
                item.setNombre(rs.getString("nombre"));
                item.setCantidad(rs.getInt("cantidad"));
                item.setPrecio(rs.getDouble("precio"));
                items.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    /**
     * La suma total de lo que cuesta un pedido
     */
    public String sumaTotalDeUnPedido(int idPedido){
        double resultado = 0.0;
        try (PreparedStatement pst = connection.prepareStatement(QUERYSUMATOTALDEUNPEDIDO)) {
            pst.setInt(1, idPedido);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                resultado = rs.getDouble("suma");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DecimalFormat formato = new DecimalFormat("#.00");
        return formato.format(resultado);
    }
}



