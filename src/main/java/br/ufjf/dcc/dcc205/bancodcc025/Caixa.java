package br.ufjf.dcc.dcc205.bancodcc025;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.ufjf.dcc.dcc205.bancodcc025.Cliente;

public class Caixa extends Usuario{
    //Atributos da classe Caixa

    //construtor
    public Caixa(String nome, int numConta, String password, String cpf){
        super(nome, numConta, password, cpf);
        super.setTipoDeUsuario("Caixa");
    }
    
    public void atendimentoDeSaque (Cliente cliente)
    {
    }

    public void processamentoDeDepósitos (Cliente cliente)
    {
    }
    
    public void transferencia (Cliente cliente)
    {
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
        
        //
        
        
        //inserer janela inteira no painel de caixa
        janelaCaixa.add(painelCaixa);
    }
    
    
}
