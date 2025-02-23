package br.ufjf.dcc.dcc205.bancodcc025;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cliente extends Usuario{
    //Atributos da classe Cliente
    public String nome;//nome do usuário
    private int numConta;//numero da conta
    private String password;//senha da conta

    //construtor
    public Cliente(String name, int conta){
        super.setTipoDeUsuario("Cliente");
        this.nome = name;
        this.numConta = conta;
    }

    private void fazTransferencia(){
        int numContaDeDestino=0;
        double valor=0.0;

    }

    @Override
    public void opercaoes() {
        super.opercaoes();
        fazTransferencia();
    }

    @Override
    public void telaUsuario() {
        super.telaUsuario();
        //criar painel da tela do usuário com todas as opções
        JFrame janelaCliente = new JFrame("Cliente");
        janelaCliente.setSize(500, 600);
        janelaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaCliente.setVisible(true);

        JPanel painelCliente = new JPanel();
        painelCliente.setSize(500/2, 600/2);

        JLabel nomeCliente  = new JLabel("Nome: "+this.nome);
        painelCliente.add(nomeCliente);

        JLabel conta = new JLabel("Número da conta: "+numConta);
        painelCliente.add(conta);

        //botão de transferência
        JButton op1 = new JButton("Transferência");
        painelCliente.add(op1);
        op1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                JFrame telaTransferencia = new JFrame("Transferência");
                telaTransferencia.setSize(500,600);
                telaTransferencia.setVisible(true);

                JPanel painelTrans = new JPanel();

                JLabel transConta = new JLabel("Quem vai receber: ");
                painelTrans.add(transConta);
                JTextField contaNova = new JTextField(15);
                painelTrans.add(contaNova);

                telaTransferencia.add(painelTrans);
            }
        });

        //botão saldo/extrato
        JButton op2 = new JButton("Ver Saldo e Extrato");
        painelCliente.add(op2);


        janelaCliente.add(painelCliente);


    }
}
