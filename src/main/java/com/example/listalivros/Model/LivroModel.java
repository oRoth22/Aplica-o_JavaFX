package com.example.listalivros.Model;

import com.example.listalivros.Controllers.Livro;
import com.example.listalivros.DataBase.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivroModel {

    public static ObservableList<Livro> carregarLivrosDoBanco() {
        ObservableList<Livro> dadosLivros = FXCollections.observableArrayList();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT id, nome, autor, dataPuBlicado, editora FROM livro";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nome = resultSet.getString("nome");
                        String autor = resultSet.getString("autor");
                        String dataPuBlicado = resultSet.getString("dataPuBlicado");
                        String editora = resultSet.getString("editora");

                        Livro livro = new Livro(id, nome, autor, dataPuBlicado, editora);
                        dadosLivros.add(livro);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Tratar exceções adequadamente
        }

        return dadosLivros;
    }
}
