package br.ufjf.dcc.dcc205.bancodcc025.model;

public abstract class Usuario{
    private String tipoDeUsuario;
    private final String nome;//nome do usuário
    private final int numConta;//numero da conta
    private final String password;//senha da conta
    private final String cpf;

    //construtor da classe abstrata
    public Usuario(String nome, int numConta, String password, String cpf) {
        this.nome = nome;
        this.numConta = numConta;
        this.password = password;
        this.cpf = cpf;
    }

    //getters
    public String getNome(){
        return this.nome;
    }
    public int getNumConta(){
        return this.numConta;
    }
    public String getPassword(){
        return this.password;
    }
    public String getCpf() {
        return cpf;
    }

    //metodo que muda o tipo de usuário
    public void setTipoDeUsuario(String tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }
    //retorna tipo de usuário
    public String getTipoDeUsuario(){
        return this.tipoDeUsuario;
    }

    public void telaUsuario(){}
}
