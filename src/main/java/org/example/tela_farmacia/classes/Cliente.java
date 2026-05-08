package org.example.tela_farmacia.classes;

public class Cliente {
    private String nome;
    private Integer idade;
    private int id;

    //CONSTRUTOR
    public Cliente (String nome, Integer idade, int id) {
        this.nome = nome;
        this.idade = idade;
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setIdade (Integer idade) {
        this.idade = idade;
    }

    //GETTERS
    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public int getId() {return id;}


}
