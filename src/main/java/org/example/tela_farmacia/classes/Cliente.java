package org.example.tela_farmacia.classes;

public class Cliente {
    private String nome;
    private int idade;
    private int id;

    //CONSTRUTOR
    public Cliente (String nome, int quantidade, int id) {
        this.nome = nome;
        this.idade = idade;
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setIdade (int idade) {
        this.idade = idade;
    }

    //GETTERS
    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getId() {return id;}


}
