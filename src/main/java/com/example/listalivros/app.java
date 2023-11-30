package com.example.listalivros;

import com.example.listalivros.DataBase.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class app extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        testarConexao();
        FXMLLoader fxmlLoader = new FXMLLoader(app.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void testarConexao() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            System.out.println("Conexão bem-sucedida!");
            connection.close();
        } catch (SQLException e) {
            System.err.println("Erro na conexão com o banco de dados: " + e.getMessage());
        }
    }
}