package br.ufjf.dcc.dcc205.bancodcc025;

public class Transacao {
    private String data;
    private int origem; //conta de origem
    private int destino; //conta de destino
    private double valor;
    private String tipoDeInvestimento;// ou instituição
    private String nomeDoFundo;

    //Construtor para entre contas
    public Transacao(int origem, int destino, double valor){
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
    }

    //Construtor para renda-fixa
    public Transacao(int origem, String tipoDeInvestimento, double valor){
        this.origem = origem;
        this.tipoDeInvestimento = tipoDeInvestimento;
        this.valor = valor;
    }
    //Construtor para renda-variável
    public Transacao(String nomeDoFundo, String tipoDeInvestimento, double valor){
        this.nomeDoFundo = nomeDoFundo;
        this.tipoDeInvestimento = tipoDeInvestimento;
        this.valor = valor;
    }

    //getters
    public String getData() {
        return data;
    }
    public int getOrigem() {return origem;}
    public double getValor() {return valor;}
    public int getDestino(){return destino;}
    //setters
    public void setDestino(int destino) {this.destino = destino;}
    public void setData(String data) {this.data = data;}
    public void setValor(double valor) {this.valor = valor;}
    public void setOrigem(int origem) {this.origem = origem;}

}