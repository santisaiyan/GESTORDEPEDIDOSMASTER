package com.example.gestordepedidos.ui;
import com.example.gestordepedidos.MainAplication;
import com.example.gestordepedidos.modelos.usuario.UsuarioDAO;
import com.example.gestordepedidos.modelos.usuario.UsuarioDAOimpl;
import com.example.sesion.Sesion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.Data;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la vista del login
 */
@Data
public class LoginController implements Initializable {

    @FXML
    private TextField tfUsuario;
    @FXML
    private Button btnEntrar;
    @FXML
    private PasswordField tfPass;
    @FXML
    private Label info;

    /**
     * Inicializador
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Boton entrar
     */
    @FXML
        private void btnEntrar() {
        UsuarioDAO dao = new UsuarioDAOimpl();
        try{
            if (dao.isCorrectUser(tfUsuario.getText(), tfPass.getText())) {
//                Usuario.usuarioSesion = dao.loadLogin(tfUsuario.getText(), tfPass.getText());
                Sesion.setUsuarioActual(dao.loadLogin(tfUsuario.getText(), tfPass.getText() ));

                MainAplication.loadFXML("pedidos-view.fxml", "Pedidos de " + Sesion.getUsuarioActual().getNombre());
            } else {
                tfUsuario.setText("");
                tfPass.setText("");
                info.setText("Nombre de usuario o contraseña incorrecto(s)");
            }

        } catch (RuntimeException e){
        info.setText("Error de conexion con la base de datos");
    }
    }

}