module com.example.listalivros {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.listalivros to javafx.fxml;
    exports com.example.listalivros;
    //exports com.example.listalivros.Controllers.LoginController;
    opens com.example.listalivros.Controllers to javafx.fxml;
    exports com.example.listalivros.Controllers;
    //opens com.example.listalivros.Controllers to javafx.fxml;
}