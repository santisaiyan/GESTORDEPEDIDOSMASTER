package com.example.db;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Clase encargada de conectar y configurar la bbdd
 */
public class Database {

    /**
     * Conexion
     */
    private static Connection connection;

    /**
     * Logger
     */
    private final static Logger logger;

    static{
        InputStream is = Database.class.getClassLoader().getResourceAsStream("database.properties");
        Properties config = new Properties();
        logger = Logger.getLogger(Database.class.getName());
        try {
            config.load(is);

        } catch (IOException e) {
            System.out.println("Error de conexion con la base de datos");

        }
        String host = (String) config.get("host");
        String name = (String) config.get("name");
        String pass = (String) config.get("pass");
        String port = (String) config.get("port");
        String user = (String) config.get("user");
        String url = (String) config.get("url");
        String urlCompleta = url + host + ":" + port + "/" + name;
        try {
            connection = DriverManager.getConnection(urlCompleta,user,pass);
            logger.info("Successful connection to database");
        } catch (SQLException e) {
            System.out.println("Error de conexion con la base de datos");
        }
    }

    /**
     * Deuelve la conexion
     * @return conexion
     */
    public Connection getConnection(){
        return connection;
    }
}
