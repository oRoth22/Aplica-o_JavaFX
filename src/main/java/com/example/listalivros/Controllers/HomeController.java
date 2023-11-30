package com.example.listalivros.Controllers;

import com.example.listalivros.Controllers.Livro;
import com.example.listalivros.Model.HomeModel;
import com.example.listalivros.app;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class HomeController {

    @FXML
    TableView<Livro> tableData;

    @FXML
    private TableColumn<Livro, Integer> idColumn;

    @FXML
    private TableColumn<Livro, String> nomeColumn;

    @FXML
    private TableColumn<Livro, String> autorColumn;

    @FXML
    private TableColumn<Livro, String> dataPublicadoColumn;

    @FXML
    private TableColumn<Livro, String> editoraColumn;

    @FXML
    private Button btnCadastroLivro;

    @FXML
    private Button btnSair;

    @FXML
    void talaCadastrarLivro() {
        abrirTelaCadastroLivro();
    }

    public void initData(String email) {
        // Lógica para inicialização com dados, se necessário
    }

    public void sair() {
        // Lógica para fechar a aplicação ou fazer logout, se necessário
        voltarTelaLogin();
    }

    @FXML
    void initialize() {
        System.out.println("cheguei");
        // Configurar colunas da tabela
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomeColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        autorColumn.setCellValueFactory(cellData -> cellData.getValue().autorProperty());
        dataPublicadoColumn.setCellValueFactory(cellData -> cellData.getValue().dataPublicacaoProperty());
        editoraColumn.setCellValueFactory(cellData -> cellData.getValue().editoraProperty());

        // Carregar dados do banco e exibir na tabela
        tableData.setItems(HomeModel.carregarDadosDoBanco());
        tableData.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Quando uma linha é selecionada, abra a tela de edição com o livro selecionado
                abrirTelaEdicaoLivro(newSelection);
            }
        });
    }

    private void abrirTelaCadastroLivro() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(app.class.getResource("CadastroLivro.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Cadastro Livro");
            stage.setScene(scene);

            // Fechar a janela de login
            Stage CadastroLivroStage = (Stage) btnCadastroLivro.getScene().getWindow();
            CadastroLivroStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro ao carregar a tela", "Erro ao carregar a tela de cadastro.");
        }
    }

    private void voltarTelaLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(app.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(scene);

            // Fechar a janela de login
            Stage LogoutStage = (Stage) btnSair.getScene().getWindow();
            LogoutStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro ao carregar a tela", "Erro ao carregar a tela de login.");
        }
    }

    private void abrirTelaEdicaoLivro(Livro livro) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(app.class.getResource("AlteracaoLivro.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Editar Livro");
            stage.setScene(scene);

            // Passe o livro selecionado e a instância de HomeController
            AlteracaoLivroController alteracaoLivroController = fxmlLoader.getController();
            alteracaoLivroController.initData(livro, this);

            // Set a instância de HomeController na AlteracaoLivroController
            alteracaoLivroController.setHomeController(this);

            // Fechar a janela atual (HomeController)
            Stage homeStage = (Stage) tableData.getScene().getWindow();
            homeStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Trate o erro adequadamente
        }
    }

    private void abrirTelaHome() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(app.class.getResource("Home.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Home");
            stage.setScene(scene);

            // Fechar a janela atual (AlteracaoLivroController)
            Stage alteracaoLivroStage = (Stage) tableData.getScene().getWindow();
            alteracaoLivroStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Trate o erro adequadamente
        }
    }

    public void mostrarTelaHome() {
        // Lógica para mostrar a tela inicial (Home)
        // Por exemplo, você pode carregar o FXML e exibir a cena

        // Exemplo:
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Home");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Trate o erro adequadamente
        }
    }


    public void atualizarTabela() {
        tableData.refresh();
        abrirTelaHome();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
