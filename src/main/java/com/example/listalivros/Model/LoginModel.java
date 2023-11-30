package com.example.listalivros.Model;

import com.example.listalivros.DataBase.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    public static boolean autenticarUsuario(String email, String senha) {
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usuario WHERE email = ? AND senha = ?")
        ) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Se houver um próximo, significa que o usuário foi autenticado
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Em caso de exceção, considere como não autenticado
        }
    }
}