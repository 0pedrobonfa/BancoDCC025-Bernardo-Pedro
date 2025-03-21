package br.ufjf.dcc.dcc205.bancodcc025.model;

import br.ufjf.dcc.dcc205.bancodcc025.model.Transacao;
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
import br.ufjf.dcc.dcc205.bancodcc025.persistence.CaixaPersistence;

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
        telaAtendimento.setLocationRelativeTo(null);

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
                        } else {
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

    public void processamentoDeDepositos (Cliente cliente)
    {
        Frame telaDeposito = new JFrame("Processar Depósito");
        telaDeposito.setSize(500, 600);
        telaDeposito.setLocationRelativeTo(null);

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

                        // Verifica se há saldo suficiente
                        if (numDeposito < 1000000.0) {
                            clienteDeposito.setSaldoAtual(clienteDeposito.getSaldoAtual() + numDeposito);

                            // Atualiza e salva os dados no JSON
                            clientePersistence.save(clientes);

                            JOptionPane.showMessageDialog(null, "DEPÓSITO REALIZADO!");
                            telaDeposito.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Valor inválido para depósito, apenas depósitos abaixo de um milhão de reais");
                        }
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
    
    public void transferencia (Cliente cliente)
    {
        JFrame telaTransferencia = new JFrame("Transferência");
        telaTransferencia.setSize(500, 600);
        telaTransferencia.setLocationRelativeTo(null);
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
                    else if (valor >= 1000000.0)
                    {
                        JOptionPane.showMessageDialog(null, "Não é possível realizar transacoes igual ou maiores que 1 milhão de reais");
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
        telaLogin.setLocationRelativeTo(null);

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
                            atendimentoDeSaque(c);
                        }
                        else if (n == 2)
                        {
                            processamentoDeDepositos(c);
                        }
                        else if (n == 3)
                        {
                            transferencia(c);
                        }
                    }
                }  
            }
        });

        telaLogin.add(painelLogin);
        telaLogin.setVisible(true);
    }
    
    public void removerConta ()
    {
        JFrame telaExclusao = new JFrame("Excluir conta");
        telaExclusao.setSize(500, 600);
        telaExclusao.setLocationRelativeTo(null);
        telaExclusao.setVisible(true);

        JPanel painelEx  = new JPanel();
        JLabel msgBoasVindas = new JLabel("Aqui você pode excluir sua conta!");

        String texto = "<html><center>Ao inserir a senha e apertar 'Confirmar' você está<br>" +
"                    excluido permanentemenete sua conta. Tem certeza disso?<center><html>";
        JLabel senhaLabel = new JLabel(texto);

        JPasswordField senhaField = new JPasswordField(15);

        painelEx.add(msgBoasVindas);
        painelEx.add(senhaLabel);
        painelEx.add(senhaField);

        JButton confirma = new JButton("Confirmar");
        confirma.addActionListener(f->{
            if (String.valueOf(senhaField.getPassword()).equals(getPassword())){

                CaixaPersistence caixaPersistence = new CaixaPersistence();
                java.util.List<Caixa> caixas = caixaPersistence.findAll();

                Caixa cli = null;

                for (Caixa c : caixas) {
                    if (c.getNumConta() == getNumConta()) {
                        cli = c;
                    }
                }
                if (cli != null) {
                    caixas.remove(cli); // Remove da lista
                    caixaPersistence.save(caixas); // Atualiza o JSON
                    JOptionPane.showMessageDialog(null, "Caixa removido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Caixa não encontrado!");
                }

                JOptionPane.showMessageDialog(null,"Conta excluida com sucesso!");
            }

        });

        painelEx.add(confirma);
        telaExclusao.add(painelEx);
    }

    @Override
    public void telaUsuario() {
        super.telaUsuario();
        //criar painel da tela do usuário com todas as opções
        JFrame janelaCaixa = new JFrame("Caixa");
        janelaCaixa.setSize(500, 600);
        janelaCaixa.setLocationRelativeTo(null);
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
        //botao de deposito
        JButton op2 = new JButton("Realizar Depósito");
        painelCaixa.add(op2);
        //botao de transferencia
        JButton op3 = new JButton("Realizar Transferência");
        painelCaixa.add(op3);
        //botao de remover conta
        JButton op4 = new JButton("Remover Conta");
        painelCaixa.add(op4);
        
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
        //remocao
        op4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent f) {
                removerConta();   
            }
        });
        //
        
        
        //inserer janela inteira no painel de caixa
        janelaCaixa.add(painelCaixa);
    }
    
    
}
