package com.example.listalivros.Controllers;

import com.example.listalivros.Model.CadastroLivroModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroLivroController {

    @FXML
    private Button btnCadastrarLivro;

    @FXML
    private TextField getNome;

    @FXML
    private TextField getAutor;

    @FXML
    private TextField getDatePub;

    @FXML
    private TextField getEditora;

    @FXML
    private Button btnVoltar;

    @FXML
    public void initialize() {
        btnCadastrarLivro.setOnAction(event -> handleCadastrarLivro());
        btnVoltar.setOnAction(event -> voltarTelaHome());
    }

    @FXML
    public void handleCadastrarLivro() {
        String nome = getNome.getText();
        String autor = getAutor.getText();
        String dataPublicacao = getDatePub.getText();
        String editora = getEditora.getText();

        if (nome.isEmpty() || autor.isEmpty() || dataPublicacao.isEmpty() || editora.isEmpty()) {
            mostrarAlerta("Campos obrigatórios", "Preencha todos os campos.");
        } else {
            boolean sucesso = CadastroLivroModel.cadastrarLivro(nome, autor, dataPublicacao, editora);

            if (sucesso) {
                mostrarAlerta("Cadastro bem-sucedido", "Livro cadastrado com sucesso!");
                // Limpar os campos ou fazer qualquer outra ação necessária após o cadastro
            } else {
                mostrarAlerta("Erro no cadastro", "Ocorreu um erro ao cadastrar o livro.");
            }
        }
    }

    public void voltarTelaHome() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/listalivros/Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Home");
            stage.setScene(scene);

            // Fechar a janela atual (CadastroLivro)
            Stage cadastroLivroStage = (Stage) btnVoltar.getScene().getWindow();
            cadastroLivroStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro ao carregar a tela", "Erro ao voltar para a tela de Home.");
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void initData(Livro livro) {
    }
}
