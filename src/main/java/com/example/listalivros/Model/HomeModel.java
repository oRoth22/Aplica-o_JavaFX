package com.example.listalivros.Model;

import com.example.listalivros.Controllers.Livro;
import com.example.listalivros.DataBase.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeModel {

    public static ObservableList<Livro> carregarDadosDoBanco() {
        ObservableList<Livro> dadosLivros = FXCollections.observableArrayList();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT id, nome, autor, dataPuBlicado, editora FROM livro";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nome = resultSet.getString("nome");
                        String autor = resultSet.getString("autor");
                        String dataPublicacao = resultSet.getString("dataPuBlicado");
                        String editora = resultSet.getString("editora");

                        Livro livro = new Livro(id, nome, autor, dataPublicacao, editora);
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

    public static void atualizarLivro(Livro livro) {
        // Lógica para atualizar o livro no banco de dados

        // Esta é uma simulação, substitua com a lógica real de atualização no seu banco de dados
        String sql = "UPDATE livros SET nome = ?, autor = ?, data_publicacao = ?, editora = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, livro.getNome());
            preparedStatement.setString(2, livro.getAutor());
            preparedStatement.setString(3, livro.getDataPublicacao());
            preparedStatement.setString(4, livro.getEditora());
            preparedStatement.setInt(5, livro.getId());

            // Execute a atualização
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Livro atualizado com sucesso!");
            } else {
                System.out.println("Nenhum livro foi atualizado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Trate a exceção adequadamente
        }
    }

    private static Connection getConnection() throws SQLException {
        // Lógica para obter uma conexão com o banco de dados
        return null;
    }
}