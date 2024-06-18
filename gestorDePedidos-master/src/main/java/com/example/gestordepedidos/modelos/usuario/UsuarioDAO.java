package com.example.gestordepedidos.modelos.usuario;

import java.util.ArrayList;

/**
 * Representa un DAO de usuario
 */
public interface UsuarioDAO {
    /**
     * Usuario segun su id
     * @param id id del usuario
     * @return Devuelve el usuario con ese id
     */
    public Usuario load(Long id);

    /**
     * Todos los usuarios de la base de datos
     * @return Devuelve una lista con todos los usuarios
     */
    public ArrayList<Usuario> loadAll();
    /**
     * Comprobar en la base de datos si existe un usuario con ese nommbre y contraseña
     * @param user nombre de usuario
     * @param pass contraseña
     * @return devuelve true si el usuario existe
     */
    public boolean isCorrectUser(String user, String pass);

    /**
     * Devuelve el usuario con ese nombre y contraseña
     * @param user nombre de usuario
     * @param pass contraseña
     * @return devuelve el usuario con ese nombre y contraseña
     */
    public Usuario loadLogin(String user,String pass);
}
