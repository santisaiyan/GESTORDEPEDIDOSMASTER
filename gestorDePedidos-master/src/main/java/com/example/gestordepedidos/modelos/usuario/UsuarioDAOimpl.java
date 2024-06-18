package com.example.gestordepedidos.modelos.usuario;

import com.example.db.Database;
import lombok.Data;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implementacion de UsuarioDAO
 */
@Data
public class UsuarioDAOimpl implements UsuarioDAO {

    /**
     * Conexion con la base de datos
     */
    private Connection connection = (new Database().getConnection());
    /**
     * Query de todos los usuarios
     */
    private final String QUERYLOADALL = "SELECT * FROM usuario";
    /**
     * Query de un usuario por su nombre y contraseña
     */
    private final String QUERYLOADLOGIN = "SELECT * FROM usuario WHERE nombreusuario = ? and pass = ?";

    /**
     * Usuario segun su id
     */
    @Override
    public Usuario load(Long id) {
        return null;
    }

    /**
     * Todos los usuarios de la base de datos con return
     */
     @Override
    public ArrayList<Usuario> loadAll() {
        var salida = new ArrayList<Usuario>();

        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(QUERYLOADALL);

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setPass(rs.getString("pass"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNombre(rs.getString("nombreusuario"));
                salida.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salida;
    }

    /**
     * Comprobar en la base de datos si existe un usuario con ese nombre y contraseña
    con user y pass @param
     */
    public boolean isCorrectUser(String user, String pass) throws NullPointerException{
        boolean salida = false;
        try(PreparedStatement pst = connection.prepareStatement(QUERYLOADLOGIN)){
            pst.setString(1,user);
            pst.setString(2,pass);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                salida=true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Runtime Exception");
        }
        return salida;
    }

    /**
     * Devuelve el usuario con ese nombre y contraseña
     */
    @Override
    public Usuario loadLogin(String user, String pass) {
        Usuario usuario = new Usuario();
        try(PreparedStatement pst = connection.prepareStatement(QUERYLOADLOGIN)){
            pst.setString(1,user);
            pst.setString(2,pass);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                usuario.setId(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombreusuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPass(rs.getString("pass"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
}
