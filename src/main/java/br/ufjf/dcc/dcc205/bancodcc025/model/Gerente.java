/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc.dcc205.bancodcc025.model;

import br.ufjf.dcc.dcc205.bancodcc025.Transacao;
import br.ufjf.dcc.dcc205.bancodcc025.persistence.ClientePersistence;
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
    
    public void ApoioEmSaque (Cliente cliente)
    {
        JFrame telaAtendimento = new JFrame("Atendimento de Saque");
        telaAtendimento.setSize(500, 600);

        JPanel painelAtendimento = new JPanel();
        JTextField valorDoSaque = new JTextField(15);
        JPasswordField campoSenha = new JPasswordField(15);
        JButton botaoRealizarSaque = new JButton("Realizar Saque");

        JLabel nomeCliente  = new JLabel("Nome: "+cliente.getNome());
        painelAtendimento.add(nomeCliente);
        JLabel numeroCliente  = new JLabel("Numero: "+cliente.getNumConta());
        painelAtendimento.add(numeroCliente);
        JLabel saldoCliente  = new JLabel("Saldo: "+cliente.getSaldoAtual());
        painelAtendimento.add(saldoCliente);
        
        painelAtendimento.add(new JLabel("Valor do Saque:"));
        painelAtendimento.add(valorDoSaque);
        painelAtendimento.add(new JLabel("Senha para confirmação:"));
        painelAtendimento.add(campoSenha);
        painelAtendimento.add(botaoRealizarSaque);
        
        botaoRealizarSaque.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //inicializa cliente prestes a receber o saque
                ClientePersistence clientePersistence = new ClientePersistence();
                java.util.List<Cliente> clientes = clientePersistence.findAll();
                Cliente clienteSaque = null;
                for (Cliente c : clientes)
                {
                    if (c.getNumConta() == cliente.getNumConta())
                    {
                        clienteSaque = c;
                        break;
                    }
                }
                // Verifica se o cliente foi encontrado
                if (clienteSaque != null) {
                    try {
                        String num = valorDoSaque.getText().trim();
                        float numSaque = Float.parseFloat(num);

                        // Verifica se há saldo suficiente
                        if (clienteSaque.getSaldoAtual() >= numSaque) {
                            clienteSaque.setSaldoAtual(clienteSaque.getSaldoAtual() - numSaque);

                            // Atualiza e salva os dados no JSON
                            clientePersistence.save(clientes);

                            JOptionPane.showMessageDialog(null, "SAQUE REALIZADO!");
                            telaAtendimento.setVisible(false);
                        } 
                        else {
                            JOptionPane.showMessageDialog(null, "Saldo insuficiente!");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Valor inválido para saque.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
                }
            }
        });
                
        telaAtendimento.add(painelAtendimento);
        telaAtendimento.setVisible(true);
    }
    
    public void ApoioEmDepositos (Cliente cliente)
    {
        Frame telaDeposito = new JFrame("Processar Depósito");
        telaDeposito.setSize(500, 600);

        JPanel painelDeposito = new JPanel();
        JTextField valorDoDeposito = new JTextField(15);
        JPasswordField campoSenha = new JPasswordField(15);
        JButton botaoRealizarDeposito = new JButton("Realizar Depósito");

        JLabel nomeCliente  = new JLabel("Nome: "+cliente.getNome());
        painelDeposito.add(nomeCliente);
        JLabel numeroCliente  = new JLabel("Numero: "+cliente.getNumConta());
        painelDeposito.add(numeroCliente);
        JLabel saldoCliente  = new JLabel("Saldo: "+cliente.getSaldoAtual());
        painelDeposito.add(saldoCliente);
        
        painelDeposito.add(new JLabel("Valor do Depósito:"));
        painelDeposito.add(valorDoDeposito);
        painelDeposito.add(new JLabel("Senha para confirmação:"));
        painelDeposito.add(campoSenha);
        painelDeposito.add(botaoRealizarDeposito);
        
        botaoRealizarDeposito.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //inicializa cliente prestes a receber o saque
                ClientePersistence clientePersistence = new ClientePersistence();
                java.util.List<Cliente> clientes = clientePersistence.findAll();
                Cliente clienteDeposito = null;
                for (Cliente c : clientes)
                {
                    if (c.getNumConta() == cliente.getNumConta())
                    {
                        clienteDeposito = c;
                        break;
                    }
                }
                // Verifica se o cliente foi encontrado
                if (clienteDeposito != null) {
                    try {
                        String num = valorDoDeposito.getText().trim();
                        float numDeposito = Float.parseFloat(num);
                        clienteDeposito.setSaldoAtual(clienteDeposito.getSaldoAtual() + numDeposito);

                        // Atualiza e salva os dados no JSON
                        clientePersistence.save(clientes);

                        JOptionPane.showMessageDialog(null, "DEPÓSITO REALIZADO!");
                        telaDeposito.setVisible(false);
                        
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Valor inválido para depósito.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
                }
            }
        });
                
        telaDeposito.add(painelDeposito);
        telaDeposito.setVisible(true);
    }
    
    public void ApoioEmTransferencia (Cliente cliente)
    {
        JFrame telaTransferencia = new JFrame("Transferência");
        telaTransferencia.setSize(500, 600);
        telaTransferencia.setVisible(true);

        JPanel painelTrans = new JPanel();
        
        JLabel nomeCliente  = new JLabel("Nome: "+cliente.getNome());
        painelTrans.add(nomeCliente);
        JLabel numeroCliente  = new JLabel("Numero: "+cliente.getNumConta());
        painelTrans.add(numeroCliente);
        JLabel saldoCliente  = new JLabel("Saldo: "+cliente.getSaldoAtual());
        painelTrans.add(saldoCliente);

        JLabel transConta = new JLabel("Quem vai receber: [N° Conta]");
        painelTrans.add(transConta);
        JTextField tfContaDestino = new JTextField(15);
        painelTrans.add(tfContaDestino);

        JLabel valorTransferido = new JLabel("Valor a ser transferido: ");
        painelTrans.add(valorTransferido);
        JTextField valorTf = new JTextField(15);
        painelTrans.add(valorTf);

        JLabel senhaTransferencia = new JLabel("Senha para confirmar a transferência: ");
        JPasswordField senhaTf = new JPasswordField(15);
        painelTrans.add(senhaTransferencia);
        painelTrans.add(senhaTf);

        JButton realizaTf = new JButton("Realiza Transferência");
        painelTrans.add(realizaTf);
        realizaTf.addActionListener(e->{
            if(senhaTf.getText().equals(cliente.getPassword())){

                //pega valor em texto e converte em double
                String valorText = valorTf.getText();
                double valor = Double.parseDouble(valorText);

                //pega conta em texto e converte para int
                String contaDestinoText = tfContaDestino.getText();
                int contaDestino = Integer.parseInt(contaDestinoText);

                int numContaAtual = getNumConta();

                ClientePersistence clientePersistence = new ClientePersistence();
                java.util.List<Cliente> clientes = clientePersistence.findAll();

                //inicializa contas de origem e destino
                Cliente origemCliente = null;
                Cliente destinoCliente = null;

                // procura e seleciona contas respectivas adequadas
                for (Cliente c : clientes) {
                    if (c.getNumConta() == cliente.getNumConta()) {
                        origemCliente = c;
                    }
                    if (c.getNumConta() == contaDestino) {
                        destinoCliente = c;
                    }
                }

                if (origemCliente == null || destinoCliente == null) {
                    JOptionPane.showMessageDialog(null, "Uma das contas não foi encontrada!");
                } else {
                    // se o saldo da conta de origem é suficiente
                    if (origemCliente.getSaldoAtual() >= valor) {
                        //realiza transferência
                        origemCliente.setSaldoAtual(origemCliente.getSaldoAtual() - valor);  // subtrai o valor da conta origem
                        destinoCliente.setSaldoAtual(destinoCliente.getSaldoAtual() + valor);  // adiciona o valor à conta destino

                        // cria transação para a conta de origem e destino
                        Transacao transacaoOrigem = new Transacao(cliente.getNumConta(), contaDestino, valor);
                        Transacao transacaoDestino = new Transacao(cliente.getNumConta(), contaDestino, valor);

                        // adiciona a transação no extrato dos clientes
                        origemCliente.getExtratos().add(transacaoOrigem);
                        destinoCliente.getExtratos().add(transacaoDestino);

                        //salva os clientes atualizados
                        clientePersistence.save(clientes);

                        JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Saldo insuficiente para a transferência!");
                    }
                }

            }else{
                JOptionPane.showMessageDialog(null, "Senha errada.");
                return;
            }
        });
        telaTransferencia.add(painelTrans);
        
    }
    
    private void exibirTelaDeLogin(int n)
    {
        JFrame telaLogin = new JFrame("Login do Cliente - Atendimento de Saque");
        telaLogin.setSize(300, 200);

        JPanel painelLogin = new JPanel();
        JTextField campoUsuario = new JTextField(15);
        JPasswordField campoSenha = new JPasswordField(15);
        JButton botaoLogin = new JButton("Login");

        painelLogin.add(new JLabel("Nome do Cliente:"));
        painelLogin.add(campoUsuario);
        painelLogin.add(new JLabel("Senha:"));
        painelLogin.add(campoSenha);
        painelLogin.add(botaoLogin);

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeCliente = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());

                ClientePersistence clientePersistence = new ClientePersistence();
                java.util.List<Cliente> clientes = clientePersistence.findAll();
                
                // Verifica se o cliente atual está registrado
                for (Cliente c : clientes) {
                    if (c.getNome().equals(nomeCliente) && c.getPassword().equals(senha) && c.getTipoDeUsuario().equals("Cliente")) 
                    {
                        telaLogin.setVisible(false);
                        if (n == 1)
                        {
                            ApoioEmSaque(c);
                        }
                        else if (n == 2)
                        {
                            ApoioEmDepositos(c);
                        }
                        else if (n == 3)
                        {
                            ApoioEmTransferencia(c);
                        }
                    }
                }  
            }
        });

        telaLogin.add(painelLogin);
        telaLogin.setVisible(true);
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
        JButton op1 = new JButton("Apoio em Saque");
        painelGerente.add(op1);
        //botao de deposito
        JButton op2 = new JButton("Apoio em Depósito");
        painelGerente.add(op2);
        //botao de transferencia
        JButton op3 = new JButton("Apoio em Transferência");
        painelGerente.add(op3);

        //funcao dos botoes, leva a login com um direcionador especifico
        //atendimento de saque
        op1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                exibirTelaDeLogin(1);   
            }
        });
        //deposito
        op2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                exibirTelaDeLogin(2);   
            }
        });
        //transferencia
        op3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                exibirTelaDeLogin(3);   
            }
        });
        //
        
        //inserer janela inteira no painel de gerente
        janelaGerente.add(painelGerente);
    }
}
