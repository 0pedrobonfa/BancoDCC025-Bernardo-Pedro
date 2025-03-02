package br.ufjf.dcc.dcc205.bancodcc025;

public class Transacao {
    private String data;
    private String descricao;
    private double valor;
    private String tipo;

    //getters
    public String getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    //setters
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData(String data) {this.data = data;}

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
