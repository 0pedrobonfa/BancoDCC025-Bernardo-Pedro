/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc.dcc205.bancodcc025.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class Gerente extends Usuario{
    //Atributos da classe Caixa

    //construtor
    public Gerente(String nome, int numConta, String password, String cpf){
        super(nome, numConta, password, cpf);
        super.setTipoDeUsuario("Gerente");
    }
    
     @Override
    public void telaUsuario() {
        super.telaUsuario();
        //criar painel da tela do usuário com todas as opções
        JFrame janelaGerente = new JFrame("Gerente");
        janelaGerente.setSize(500, 600);
        janelaGerente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaGerente.setVisible(true);

        JPanel painelGerente = new JPanel();
        painelGerente.setSize(500/2, 600/2);

        JLabel nomeGerente  = new JLabel("Nome: "+getNome());
        painelGerente.add(nomeGerente);

        JLabel conta = new JLabel("Número da conta: "+getNumConta());
        painelGerente.add(conta);

        //botão de ativacao da funcao
        JButton op1 = new JButton("Funcao do Gerente");
        painelGerente.add(op1);

        
        
        //inserer janela inteira no painel de gerente
        janelaGerente.add(painelGerente);
    }
}
