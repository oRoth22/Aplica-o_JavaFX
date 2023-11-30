package com.example.listalivros.Controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Livro {
    private final SimpleIntegerProperty id;
    private final StringProperty nome;
    private final StringProperty autor;

    private final StringProperty dataPuBlicado;
    private final StringProperty editora;

    public Livro(int id, String nome, String autor, String dataPuBlicado, String editora) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.autor = new SimpleStringProperty(autor);
        this.dataPuBlicado = new SimpleStringProperty(dataPuBlicado);
        this.editora = new SimpleStringProperty(editora);
    }

    // Getter methods
    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getNome() {
        return nome.get();
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public String getAutor() {
        return autor.get();
    }

    public StringProperty autorProperty() {
        return autor;
    }

    public String getDataPublicacao() {
        return dataPuBlicado.get();
    }

    public StringProperty dataPublicacaoProperty() {
        return dataPuBlicado;
    }

    public String getEditora() {
        return editora.get();
    }

    public StringProperty editoraProperty() {
        return editora;
    }

    // Setter methods
    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public void setAutor(String autor) {
        this.autor.set(autor);
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPuBlicado.set(dataPublicacao);
    }

    public void setEditora(String editora) {
        this.editora.set(editora);
    }
}