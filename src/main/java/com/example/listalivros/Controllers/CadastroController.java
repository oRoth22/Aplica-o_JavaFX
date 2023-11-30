package com.example.listalivros.Controllers;

import com.example.listalivros.Model.CadastroModel;
import com.example.listalivros.app;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class CadastroController {
    @FXML
    private Button btnVoltar;

    @FXML
    private TextField postEmail;

    @FXML
    private PasswordField postSenha;

    @FXML
    private TextField postNome;

    @FXML
    private ImageView livroImageView;

    public void initialize() {
        Image livroImage = new Image(getClass().getResource("/com/example/listalivros/img/livroCapa.png").toExternalForm());
        livroImageView.setImage(livroImage);
    }

    @FXML
    void voltarLogin() {
        abrirTelaLogin();
    }

    @FXML
    void Cadastrar() {
        try {
            String email = postEmail.getText();
            String senha = postSenha.getText();
            String nome = postNome.getText();

            if (CadastroModel.cadastrarUsuario(email, senha, nome)) {
                mostrarAlerta("Cadastro Bem-Sucedido", "Usuário cadastrado com sucesso!");
                limparCampos();
                voltarParaTelaLogin();
            } else {
                mostrarAlerta("Erro no Cadastro", "Verifique os dados e tente novamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro no Cadastro", "Verifique os dados e tente novamente.");
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void limparCampos() {
        postEmail.clear();
        postSenha.clear();
        postNome.clear();
    }

    private void voltarParaTelaLogin() {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(app.class.getResource("Login.fxml"));
            Stage atualStage = (Stage) postEmail.getScene().getWindow();
            atualStage.close();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Login!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro ao carregar Login.fxml", e.getMessage());
        }
    }

    private void abrirTelaLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(app.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(scene);

            // Fechar a janela atual (CadastroController)
            Stage cadastroStage = (Stage) btnVoltar.getScene().getWindow();
            cadastroStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Trate o erro adequadamente
        }
    }
}