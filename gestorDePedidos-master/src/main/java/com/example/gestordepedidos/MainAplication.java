package com.example.gestordepedidos;

import com.example.gestordepedidos.modelos.pedido.PedidoDAOImpl;
import com.example.gestordepedidos.modelos.usuario.Usuario;
import com.example.gestordepedidos.modelos.usuario.UsuarioDAOimpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Aplicacion principal
 */
public class MainAplication extends Application {

    /**
     * Escena
     */
    private static Stage myStage;

    /**
     * Starter de la aplicacion
     * @param stage Escena
     * @throws IOException Errores de entrada salida
     */
    @Override
    public void start(Stage stage) throws IOException {
        myStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainAplication.class.getResource("ui/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(true);
        stage.setMinWidth(695);
        stage.setMinHeight(475);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cargar nueva escena
     * @param fxml archivo fxml
     * @param titulo Titulo de la nueva escena
     */
    public static void loadFXML(String fxml, String titulo){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainAplication.class.getResource("ui/"+fxml));
            Scene scene = new Scene(fxmlLoader.load());
            myStage.setTitle(titulo);
            myStage.setScene(scene);
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo "+fxml);
            throw new RuntimeException(e.getMessage());
        }

    }

    /**
     * Metodo main
     */
    public static void main(String[] args) {
        launch();
    }
}