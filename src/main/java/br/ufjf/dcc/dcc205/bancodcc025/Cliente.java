package br.ufjf.dcc.dcc205.bancodcc025;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cliente extends Usuario{
    //Atributos da classe Cliente
    private final double saldoAtual;

    //construtor
    public Cliente(String nome, int numConta, double saldoAtual, String password){
        super(nome, numConta, password);
        this.saldoAtual = saldoAtual;
        super.setTipoDeUsuario("Cliente");
    }

    public double getSaldoAtual()
    {
        return this.saldoAtual;
    }
    
    private void fazTransferencia(){
        int numContaDeDestino=0;
        double valor=0.0;

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

        JLabel nomeCliente  = new JLabel("Nome: "+getNome());
        painelCliente.add(nomeCliente);

        JLabel conta = new JLabel("Número da conta: "+getNumConta());
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
                //apresenta movimentações bancárias

                telaSaldoExt.add(painelSE);

            }
        });

        //botão investimento renda fixa
        JButton op3 = new JButton("Investimento renda fixa");
        painelCliente.add(op3);
        op3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent h) {
                JFrame telaSaldoExt = new JFrame("Renda Fixa");
                telaSaldoExt.setSize(500, 600);
                telaSaldoExt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // fecha apenas esta janela
                telaSaldoExt.setVisible(true);

                JPanel painelCDB = new JPanel();
                painelCDB.setLayout(new BoxLayout(painelCDB, BoxLayout.Y_AXIS)); // faz o layout ser vertical

                //seleção do tipo de cdb
                JLabel cdbLabel = new JLabel("Selecione o tipo de CDB:");
                painelCDB.add(cdbLabel);

                JRadioButton cdb100 = new JRadioButton("CDB 100% Pré-fixado");
                JRadioButton cdb110 = new JRadioButton("CDB 110% Pré-fixado");
                JRadioButton cdb120 = new JRadioButton("CDB 120% Pré-fixado");

                ButtonGroup grupoCDB = new ButtonGroup();
                grupoCDB.add(cdb100);
                grupoCDB.add(cdb110);
                grupoCDB.add(cdb120);

                painelCDB.add(cdb100);
                painelCDB.add(cdb110);
                painelCDB.add(cdb120);

                // campo para "Valor a ser investido"
                JLabel valorLabel = new JLabel("Valor a ser investido:");
                painelCDB.add(valorLabel);
                JTextField valorTf = new JTextField(15);
                valorTf.setMaximumSize(new Dimension(Integer.MAX_VALUE, valorTf.getPreferredSize().height)); // limita altura
                painelCDB.add(valorTf);

                // campo para "Senha"
                JLabel senhaLabel = new JLabel("Senha:");
                painelCDB.add(senhaLabel);
                JTextField senhaInserida = new JTextField(15);
                senhaInserida.setMaximumSize(new Dimension(Integer.MAX_VALUE, senhaInserida.getPreferredSize().height)); // limita altura
                painelCDB.add(senhaInserida);

                // botão para "Confirmar"
                JButton confirmar = new JButton("Confirmar");
                painelCDB.add(confirmar);
                confirmar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent h) {
                        if (getPassword().equals(senhaInserida.getText())) {
                            JOptionPane.showMessageDialog(telaSaldoExt, "Ação confirmada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(telaSaldoExt, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                telaSaldoExt.add(painelCDB);
                telaSaldoExt.revalidate(); // atualizar a interface
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
