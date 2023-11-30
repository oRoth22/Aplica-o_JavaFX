package com.example.listalivros.Controllers;


import com.example.listalivros.Model.LoginModel;
import com.example.listalivros.app;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LoginController {

    @FXML
    private Label btnCadastrar;

    @FXML
    private Button btnLogar;

    @FXML
    private TextField getEmail;

    @FXML
    private PasswordField getSenha;

    private String email;

    @FXML
    private ImageView livroImageView;

    @FXML
    void efetuarCadastro() {
        abrirTelaCadastro();
    }

    public void initialize() {
        Image livroImage = new Image(getClass().getResource("/com/example/listalivros/img/livroCapa.png").toExternalForm());
        livroImageView.setImage(livroImage);
    }

    @FXML
    void login() {
        email = getEmail.getText();
        String senha = getSenha.getText();

        if (LoginModel.autenticarUsuario(email, senha)) {
            abrirTelaHome();
        } else {
            mostrarAlerta("Credenciais Incorretas", "Verifique seu e-mail e senha.");
        }
    }

    public void abrirTelaCadastro() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(app.class.getResource("Cadastro.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Cadastro");
            stage.setScene(scene);

            // Fechar a janela de login
            Stage LoginStage = (Stage) btnCadastrar.getScene().getWindow();
            LoginStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro ao carregar a tela", "Erro ao carregar a tela de cadastro.");
        }
    }

    public void abrirTelaHome() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(app.class.getResource("/com/example/listalivros/Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Home");
            stage.setScene(scene);

            Stage HomeStage = (Stage) btnLogar.getScene().getWindow();
            HomeStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro ao carregar a tela", "Erro ao carregar a tela de home.");
        }
    }

    public void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}