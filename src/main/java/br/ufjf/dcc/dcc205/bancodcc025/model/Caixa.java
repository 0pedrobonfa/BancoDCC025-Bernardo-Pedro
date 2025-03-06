package br.ufjf.dcc.dcc205.bancodcc025.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import br.ufjf.dcc.dcc205.bancodcc025.model.Cliente;
import br.ufjf.dcc.dcc205.bancodcc025.persistence.ClientePersistence;

public class Caixa extends Usuario{
    //Atributos da classe Caixa

    //construtor
    public Caixa(String nome, int numConta, String password, String cpf){
        super(nome, numConta, password, cpf);
        super.setTipoDeUsuario("Caixa");
    }
    
    public void atendimentoDeSaque (Cliente cliente)
    {
        JFrame telaAtendimento = new JFrame("Atendimento de Saque");
        telaAtendimento.setSize(500, 600);

        JPanel painelAtendimento = new JPanel();
        JTextField valorDoSaque = new JTextField(15);
        JPasswordField campoSenha = new JPasswordField(15);
        JButton botaoRealizarSaque = new JButton("RealizarSaque");

        painelAtendimento.add(new JLabel("Valor do Saque:"));
        painelAtendimento.add(valorDoSaque);
        painelAtendimento.add(new JLabel("Senha para confirmação:"));
        painelAtendimento.add(campoSenha);
        painelAtendimento.add(botaoRealizarSaque);
        
        botaoRealizarSaque.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(null, "SAQUE REALIZADO!"); 
            }
        });
                
        telaAtendimento.add(painelAtendimento);
        telaAtendimento.setVisible(true);
    }

    public void processamentoDeDepósitos (Cliente cliente)
    {
    }
    
    public void transferencia (Cliente cliente)
    {
    }
    
    private void exibirTelaDeLogin()
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
                        atendimentoDeSaque(c);
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
        JFrame janelaCaixa = new JFrame("Caixa");
        janelaCaixa.setSize(500, 600);
        janelaCaixa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaCaixa.setVisible(true);

        JPanel painelCaixa = new JPanel();
        painelCaixa.setSize(500/2, 600/2);

        JLabel nomeCaixa  = new JLabel("Nome: "+getNome());
        painelCaixa.add(nomeCaixa);

        JLabel conta = new JLabel("Número da conta: "+getNumConta());
        painelCaixa.add(conta);

        //botão de atendimento de saque
        JButton op1 = new JButton("Atendimento de Saque");
        painelCaixa.add(op1);
        //funcao do atendimento de saque
        op1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                exibirTelaDeLogin();
                
            }
        });
        //
        
        
        //inserer janela inteira no painel de caixa
        janelaCaixa.add(painelCaixa);
    }
    
    
}
