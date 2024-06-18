module com.example.gestordepedidos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;

    opens com.example.gestordepedidos to javafx.fxml;
    exports com.example.gestordepedidos;
    exports com.example.gestordepedidos.ui;
    opens com.example.gestordepedidos.ui to javafx.fxml;


}