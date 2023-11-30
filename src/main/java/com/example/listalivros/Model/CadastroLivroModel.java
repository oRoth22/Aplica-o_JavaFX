package com.example.listalivros.Model;

import com.example.listalivros.Controllers.Livro;
import com.example.listalivros.DataBase.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroLivroModel {

    private Livro livro;

    public static boolean cadastrarLivro(String nome, String autor, String dataPublicacao, String editora) {
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO livro (nome, autor, dataPuBlicado, editora) VALUES (?, ?, ?, ?)"
                )
        ) {
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, autor);
            preparedStatement.setString(3, dataPublicacao);
            preparedStatement.setString(4, editora);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean editarLivro(Livro livro) {
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE livro SET nome=?, autor=?, dataPuBlicado=?, editora=? WHERE id=?"
                )
        ) {
            preparedStatement.setString(1, livro.getNome());
            preparedStatement.setString(2, livro.getAutor());
            preparedStatement.setString(3, livro.getDataPublicacao());
            preparedStatement.setString(4, livro.getEditora());
            preparedStatement.setInt(5, livro.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
