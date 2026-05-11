package org.example.tela_farmacia.classes;

public class Remedio {
    private String nome;
    private String tipo;
    private Integer quantidade;
    private int id;


    //CONSTRUTOR
    public Remedio (String nome, String tipo, Integer quantidade, int id) {
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.id = id;
    }

    //SETTERS
    public void setNome (String nome) {
        this.nome = nome;
    }

    public void  setTipo (String tipo) {
        this.tipo = tipo;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    //GETTERS
    public String getNome() {
        return nome;
    }
    public String getTipo() {
        return tipo;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public int getId() {return id;}
}
