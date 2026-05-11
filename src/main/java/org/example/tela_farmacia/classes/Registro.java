package org.example.tela_farmacia.classes;

public class Registro {

    private Integer id;
    private Cliente cliente;
    private Funcionario funcionario;
    private Remedio remedio;

    //CONSTRUTOR
    public Registro(Cliente cliente, Funcionario funcionario, Remedio remedio) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.remedio = remedio;
    }

    //SETTER
    public void setId(Integer id) {
        this.id = id;
    }


    //GETTERS
    public Integer getId() {
        return id;
    }

    public Cliente getCliente() {return cliente;}

    public Funcionario getFuncionario() {return funcionario;}

    public Remedio getRemedio() {return remedio;}
}