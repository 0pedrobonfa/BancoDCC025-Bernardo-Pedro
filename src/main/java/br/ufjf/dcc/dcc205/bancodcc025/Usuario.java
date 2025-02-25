package br.ufjf.dcc.dcc205.bancodcc025;

import javax.swing.*;

public abstract class Usuario extends JFrame {
    //"cargo?colocação?posição?hierarquia?" - gerente, caixa, cliente
    private String tipoDeUsuario;

    //metodo que muda o tipo de usuário
    public void setTipoDeUsuario(String tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    //retorna tipo de usuário
    public String getTipoDeUsuario(){
        return this.tipoDeUsuario;
    }
    
    public void telaUsuario(){}
    public void opercaoes(){};
}
