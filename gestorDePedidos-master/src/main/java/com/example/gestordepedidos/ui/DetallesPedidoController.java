package com.example.gestordepedidos.ui;

import com.example.gestordepedidos.MainAplication;
import com.example.gestordepedidos.modelos.producto.Producto;
import com.example.gestordepedidos.modelos.pedido.PedidoDAOImpl;
import com.example.sesion.Sesion;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controlador de la vista de los detalles de un pedido
 */
public class DetallesPedidoController implements Initializable
{

    @FXML
    private TableView<Producto> tablaDetallesPedido;
    @FXML
    private TableColumn<Producto,String>  cNombre;
    @FXML
    private TableColumn<Producto,String>  cPrecio;
    @FXML
    private TableColumn<Producto,String>  cCantidad;
    @FXML
    private Label labelTitulo;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnAtras;

    /**
     * Inicializador
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

       PedidoDAOImpl dao = new PedidoDAOImpl();
        ArrayList<Producto> items = dao.detallesDeUnPedido(Sesion.getPedidoPulsado());

             //Cambiar titulo
        labelTitulo.setText("Pedido número " + Sesion.getPedidoPulsado().getId());

            //Rellenar la tabla
        cNombre. setCellValueFactory( (fila) -> {
            String nombre = fila.getValue().getNombre();
            return new SimpleStringProperty(nombre);
        });
        cCantidad. setCellValueFactory( (fila) -> {
            int cantidad = fila.getValue().getCantidad();
            return new SimpleStringProperty(Integer.toString(cantidad));
        });
        cPrecio. setCellValueFactory( (fila) -> {
            double precio = fila.getValue().getPrecio();
            return new SimpleStringProperty(Double.toString(precio));
        });
        ObservableList<Producto> observableList = FXCollections.observableArrayList();
        observableList.addAll(items);
        tablaDetallesPedido.setItems(observableList);
}

    /**
     * Boton atras
     */
    @FXML
    public void atras() {
        MainAplication.loadFXML("pedidos-view.fxml", "Pedidos de " + Sesion.getUsuarioActual().getNombre());
    }

    /**
     * Boton logout
     */
    @FXML
    public void logout() {
        Sesion.setUsuarioActual(null);
        Sesion.setPedidoPulsado(null);
        MainAplication.loadFXML("login-view.fxml", "Iniciar Sesión");
    }
}