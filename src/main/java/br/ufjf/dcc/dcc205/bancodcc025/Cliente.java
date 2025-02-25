package br.ufjf.dcc.dcc205.bancodcc025;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cliente extends Usuario{
    //Atributos da classe Cliente
    public String nome;//nome do usuário
    private int numConta;//numero da conta
    private String password;//senha da conta
    private double saldoAtual;

    //construtor
    public Cliente(String name, int conta, double saldo, String senha){
        super.setTipoDeUsuario("Cliente");
        this.nome = name;
        this.numConta = conta;
        this.saldoAtual = saldo;
        this.password = senha;
    }

    public double getSaldoAtual()
    {
        return this.saldoAtual;
    }

    public String getPassword()
    {
        return password;
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
                telaTransferencia.setSize(500, 600);
                telaTransferencia.setVisible(true);

                JPanel painelTrans = new JPanel();

                JLabel transConta = new JLabel("Quem vai receber: ");
                painelTrans.add(transConta);
                JTextField contaNova = new JTextField(15);
                painelTrans.add(contaNova);

                JLabel valorTransferido = new JLabel("Valor a ser transferido: ");
                painelTrans.add(valorTransferido);
                JTextField valorTf = new JTextField(15);
                painelTrans.add(valorTf);

                JLabel senhaTransferencia = new JLabel("Senha para confirmar a transferência: ");
                painelTrans.add(senhaTransferencia);
                JTextField senhaTf = new JTextField(15);
                painelTrans.add(senhaTf);

                //if(senhaTransferencia.getText().equals(getPassword())){
                    //realiza transferência
                    //pega saldo atual, dimunui o valor
                    //vai na conta-alvo e aumenta o valor lá
                //}

                JButton realizaTf = new JButton("Realiza Transferência");
                painelTrans.add(realizaTf);

                telaTransferencia.add(painelTrans);

            }
        });

        //botão saldo/extrato
        JButton op2 = new JButton("Ver Saldo e Extrato");
        painelCliente.add(op2);
        op2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent g) {
                JFrame telaSaldoExt = new JFrame("Saldo & Extrato");
                telaSaldoExt.setSize(500, 600);
                telaSaldoExt.setVisible(true);


                JPanel painelSE = new JPanel();

                //mostra saldo
                JLabel mostraExtrato = new JLabel("Saldo atual: "+getSaldoAtual());
                painelSE.add(mostraExtrato);

                //mostra extrato


                telaSaldoExt.add(painelSE);

            }
        });

        //botão investimento renda fixa
        JButton op3 = new JButton("Investimento renda fixa");
        painelCliente.add(op3);
        op3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent h) {
            }
        });

        //botão investimento renda variável
        JButton op4 = new JButton("Investimento renda variável");
        painelCliente.add(op4);
        op4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent i) {
            }
        });

        //botão solicita crédito
        JButton op5 = new JButton("Solicitar crédito");
        painelCliente.add(op5);
        op5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent j) {
            }
        });




        janelaCliente.add(painelCliente);


    }

}
