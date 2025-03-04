package br.ufjf.dcc.dcc205.bancodcc025;

public class Transacao {
    private String data;
    private String origem; //conta de origem
    private String destino; //conta de destino
    private double valor;

    //getters
    public String getData() {
        return data;
    }
    public String getOrigem() {return origem;}
    public double getValor() {return valor;}
    public String getDestino(){return destino;}
    //setters
    public void setDestino(String destino) {this.destino = destino;}
    public void setData(String data) {this.data = data;}
    public void setValor(double valor) {this.valor = valor;}
    public void setOrigem(String origem) {this.origem = origem;}
}
