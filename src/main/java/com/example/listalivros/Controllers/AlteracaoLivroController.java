package com.example.listalivros.Controllers;

import com.example.listalivros.DataBase.DatabaseConnection;
import com.example.listalivros.Model.CadastroLivroModel;
import com.example.listalivros.Model.HomeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class AlteracaoLivroController {

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField setAutor;

    @FXML
    private TextField setDatePub;

    @FXML
    private TextField setEditora;

    @FXML
    private TextField setNome;

    @FXML
    void voltarParaHome() {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
        homeController.mostrarTelaHome();  // Adicione este método ao HomeController
    }

    private Livro livro;
    private HomeController homeController;

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    public void initData(Livro livro, HomeController homeController) {
        this.livro = livro;
        this.homeController = homeController;
        setNome.setText(livro.getNome());
        setAutor.setText(livro.getAutor());
        setDatePub.setText(livro.getDataPublicacao());
        setEditora.setText(livro.getEditora());
    }

    @FXML
    public void handleEditarLivro() {
        try {
            int id = livro.getId();
            String nome = setNome.getText();
            String autor = setAutor.getText();
            String dataPublicacao = setDatePub.getText();
            String editora = setEditora.getText();

            Livro livroAtualizado = new Livro(id, nome, autor, dataPublicacao, editora);

            if (CadastroLivroModel.editarLivro(livroAtualizado)) {
                mostrarAlerta("Edição bem-sucedida", "Livro editado com sucesso!");
                fecharJanelaEdicao();
            } else {
                mostrarAlerta("Erro na edição", "Ocorreu um erro ao editar o livro.");
            }
            homeController.atualizarTabela();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro na edição", "Ocorreu um erro ao editar o livro.");
        }
    }

    @FXML
    public void handleExcluirLivro() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de Exclusão");
            alert.setHeaderText(null);
            alert.setContentText("Tem certeza de que deseja excluir este livro?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (excluirLivro()) {
                    mostrarAlerta("Exclusão bem-sucedida", "Livro excluído com sucesso!");
                    fecharJanelaEdicao();
                    homeController.atualizarTabela();
                } else {
                    mostrarAlerta("Erro na exclusão", "Ocorreu um erro ao excluir o livro.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Erro na exclusão", "Ocorreu um erro ao excluir o livro.");
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    void salvarEdicao() {
        // Lógica para salvar as alterações no livro
        Livro livroEditado = obterLivroEditado();
        // Atualiza o livro no banco de dados
        HomeModel.atualizarLivro(livroEditado);
        // Fecha a janela de edição
        fecharJanelaEdicao();
        // Atualiza a tabela na tela de Home para refletir as alterações
        homeController.tableData.refresh();
    }

    private Livro obterLivroEditado() {
        int id = livro.getId();
        String nome = setNome.getText();
        String autor = setAutor.getText();
        String dataPublicacao = setDatePub.getText();
        String editora = setEditora.getText();

        return new Livro(id, nome, autor, dataPublicacao, editora);
    }

    public void fecharJanelaEdicao() {
        Stage stage = (Stage) btnSalvar.getScene().getWindow();
        stage.close();
    }

    private boolean excluirLivro() {
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM livro WHERE id=?"
                )
        ) {
            preparedStatement.setInt(1, livro.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}