package br.ufjf.dcc.dcc205.bancodcc025.model;

import br.ufjf.dcc.dcc205.bancodcc025.Transacao;
import br.ufjf.dcc.dcc205.bancodcc025.persistence.ClientePersistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    //Atributos da classe Cliente
    private double saldoAtual;
    private List<Transacao> extratos = new ArrayList<>();

    //construtor
    public Cliente(String nome, int numConta, double saldoAtual, String password, String cpf){
        super(nome, numConta, password, cpf);
        this.saldoAtual = saldoAtual;
        super.setTipoDeUsuario("Cliente");
    }

    public void setSaldoAtual(double novoSaldo){
        this.saldoAtual = novoSaldo;
    }
    @Override
    public int getNumConta() {
        return super.getNumConta();
    }

    public double getSaldoAtual()
    {
        return this.saldoAtual;
    }

    public List<Transacao> getExtratos() {
        return extratos;
    }

    @Override
    public void telaUsuario() {
        super.telaUsuario();
        //cria painel da tela do usuário com todas as opções
        JFrame janelaCliente = new JFrame("Cliente");
        janelaCliente.setSize(500, 600);
        janelaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaCliente.setVisible(true);

        JPanel painelCliente = new JPanel();
        //painelCliente.setSize(500/2, 600/2);

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
                    if(senhaTf.getText().equals(getPassword())){

                        //pega valor em texto e converte em double
                        String valorText = valorTf.getText();
                        double valor = Double.parseDouble(valorText);

                        //pega conta em texto e converte para int
                        String contaDestinoText = tfContaDestino.getText();
                        int contaDestino = Integer.parseInt(contaDestinoText);

                        int numContaAtual = getNumConta();


                        ClientePersistence clientePersistence = new ClientePersistence();
                        List<Cliente> clientes = clientePersistence.findAll();

                        //inicializa contas de origem e destino
                        Cliente origemCliente = null;
                        Cliente destinoCliente = null;

                        // procura e seleciona contas respectivas adequadas
                        for (Cliente c : clientes) {
                            if (c.getNumConta() == numContaAtual) {
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
                                Transacao transacaoOrigem = new Transacao(numContaAtual, contaDestino, valor);
                                Transacao transacaoDestino = new Transacao(numContaAtual, contaDestino, valor);

                                // adiciona a transação no extrato dos clientes
                                origemCliente.getExtratos().add(transacaoOrigem);
                                destinoCliente.getExtratos().add(transacaoDestino);

                                //salva os clientes atualizados
                                clientePersistence.save(clientes);

                                JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
                            } else {
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

                // cria painel com saldo e extrato
                JPanel painelSE = new JPanel();
                painelSE.setLayout(new BoxLayout(painelSE, BoxLayout.Y_AXIS));

                // mostra o saldo atual
                JLabel mostraExtrato = new JLabel("Saldo atual: " + getSaldoAtual());
                painelSE.add(mostraExtrato);

                //exibe o extrato
                List<Transacao> extrato = getExtratos();
                if (extrato.isEmpty()) {
                    JLabel noTransactions = new JLabel("Nenhuma transação registrada.");
                    painelSE.add(noTransactions);
                } else {
                    // exibe todas as transações
                    for (Transacao t : extrato) {
                        // formata a transação
                        JLabel transacaoLabel = new JLabel(
                                "Origem: " + t.getOrigem() +
                                " | Destino: " + t.getDestino() +
                                " | Valor: " + t.getValor()
                        );
                        painelSE.add(transacaoLabel);
                    }
                }

                //adiciona painel à janela
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

                // Seleção do tipo de CDB
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

                // Campo para "Valor a ser investido"
                JLabel valorLabel = new JLabel("Valor a ser investido:");
                painelCDB.add(valorLabel);
                JTextField valorTf = new JTextField(15);
                valorTf.setMaximumSize(new Dimension(Integer.MAX_VALUE, valorTf.getPreferredSize().height)); // limita altura
                painelCDB.add(valorTf);

                // Campo para "Senha"
                JLabel senhaLabel = new JLabel("Senha:");
                painelCDB.add(senhaLabel);
                JPasswordField senhaInserida = new JPasswordField(15);
                senhaInserida.setMaximumSize(new Dimension(Integer.MAX_VALUE, senhaInserida.getPreferredSize().height)); // limita altura
                painelCDB.add(senhaInserida);

                // botão para "Confirmar"
                JButton confirmar = new JButton("Confirmar");
                painelCDB.add(confirmar);
                confirmar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent h) {
                        if (getPassword().equals(senhaInserida.getText())) {

                            double valorInvestido;
                            valorInvestido = Double.parseDouble(valorTf.getText());

                            // verifica se o cliente tem saldo suficiente para realizar o investimento
                            if (getSaldoAtual() >= valorInvestido) {
                                ClientePersistence clientePersistence = new ClientePersistence();
                                List<Cliente> clientes = clientePersistence.findAll();

                                //inicializa conta de origem
                                Cliente origemCliente = null;

                                // procura e seleciona conta adequada
                                for (Cliente c : clientes) {
                                    if (c.getNumConta() == getNumConta()) {
                                        origemCliente = c;
                                    }
                                }
                                origemCliente.setSaldoAtual(origemCliente.getSaldoAtual() - valorInvestido); //subtrai valor investido da conta

                                Transacao transacao = new Transacao(getNumConta(), "CDB", valorInvestido);
                                origemCliente.getExtratos().add(transacao);

                                clientePersistence.save(clientes); //atualiza o arquivo com os dados modificados

                                // confirma a operação
                                JOptionPane.showMessageDialog(telaSaldoExt, "Ação confirmada! Investimento realizado.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            //se a senha estiver incorreta
                            JOptionPane.showMessageDialog(telaSaldoExt, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                telaSaldoExt.add(painelCDB);
                telaSaldoExt.revalidate(); //atualiza a interface
            }});


        //botão investimento renda variável
        JButton op4 = new JButton("Investimento renda variável");
        painelCliente.add(op4);
        op4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent i) {
                JFrame telaRendaVar = new JFrame("Investimento em Renda Variável");
                telaRendaVar.setSize(500, 600);
                telaRendaVar.setLayout(new GridLayout(3, 1)); // 3 linhas, 1 coluna

                // Mensagem de boas-vindas
                JLabel msgBoasVindas = new JLabel("Aqui você pode investir!");
                telaRendaVar.add(msgBoasVindas);

                // Cria um painel para as opções
                JPanel painelOpcoes = new JPanel();
                JLabel optLabel = new JLabel("Selecione seus ativos abaixo:");
                painelOpcoes.add(optLabel);

                JRadioButton fundo1 = new JRadioButton("Fundo XP Ações");
                JRadioButton fundo2 = new JRadioButton("Fundo Itaú Ações");
                JRadioButton fundo3 = new JRadioButton("Fundo BB Ações Internacionais");
                JRadioButton carteira1 = new JRadioButton("Carteira Conservadora");
                JRadioButton carteira2 = new JRadioButton("Carteira Moderada");
                JRadioButton carteira3 = new JRadioButton("Carteira Arrojada");

                ButtonGroup grupoInv = new ButtonGroup();
                grupoInv.add(fundo1);
                grupoInv.add(fundo2);
                grupoInv.add(fundo3);
                grupoInv.add(carteira1);
                grupoInv.add(carteira2);
                grupoInv.add(carteira3);

                JLabel valorAplicado = new JLabel("Valor que será Investido");
                JTextField valorAplicadotf = new JTextField(15);

                painelOpcoes.add(fundo1);
                painelOpcoes.add(fundo2);
                painelOpcoes.add(fundo3);
                painelOpcoes.add(carteira1);
                painelOpcoes.add(carteira2);
                painelOpcoes.add(carteira3);
                painelOpcoes.add(valorAplicado);
                painelOpcoes.add(valorAplicadotf);


                telaRendaVar.add(painelOpcoes);

                // Painel de senha
                JPanel painelSenha = new JPanel();
                JLabel senhaLabel = new JLabel("Para confirmar sua operação, digite sua senha: ");
                JPasswordField senhaPassField = new JPasswordField(15);
                painelSenha.add(senhaLabel);
                painelSenha.add(senhaPassField);


                JButton confirma = new JButton("Confirma");
                painelSenha.add(confirma);

                confirma.addActionListener(e -> {
                    if (String.valueOf(senhaPassField.getPassword()).equals(getPassword())) {
                        double valorInvestido;
                        try {
                            valorInvestido = Double.parseDouble(valorAplicadotf.getText());
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Valor inválido! Digite um número válido.");
                            return;
                        }

                        // Verifica se algum ativo foi selecionado
                        String ativoSelecionado = "";
                        if (fundo1.isSelected()) ativoSelecionado = "Fundo XP Ações";
                        else if (fundo2.isSelected()) ativoSelecionado = "Fundo Itaú Ações";
                        else if (fundo3.isSelected()) ativoSelecionado = "Fundo BB Ações Internacionais";
                        else if (carteira1.isSelected()) ativoSelecionado = "Carteira Conservadora";
                        else if (carteira2.isSelected()) ativoSelecionado = "Carteira Moderada";
                        else if (carteira3.isSelected()) ativoSelecionado = "Carteira Arrojada";

                        if (ativoSelecionado.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Selecione um ativo para investir!");
                            return;
                        }

                        if (getSaldoAtual() >= valorInvestido) {
                            //se o saldo for suficiente, retira o valor investido do saldo atual
                            setSaldoAtual(getSaldoAtual() - valorInvestido);

                            ClientePersistence clientePersistence = new ClientePersistence();
                            List<Cliente> clientes = clientePersistence.findAll();

                            Cliente cli = null;

                            for (Cliente c : clientes) {
                                if (c.getNumConta() == getNumConta()) {
                                    cli = c;
                                }
                            }
                            Transacao transacao = new Transacao(getNumConta(), ativoSelecionado, valorInvestido);

                            cli.getExtratos().add(transacao);

                            clientePersistence.save(clientes);

                            // Confirma a operação
                            JOptionPane.showMessageDialog(null, "Transação confirmada! Investimento realizado.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Saldo insuficiente!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha incorreta!");
                    }
                });


                telaRendaVar.add(painelSenha);

                // Exibe a janela
                telaRendaVar.setVisible(true);


            }
        });

        //botão solicita crédito
        JButton op5 = new JButton("Solicitar crédito");
        painelCliente.add(op5);
        op5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent j) {

                JFrame telaSolicitaCredito = new JFrame("Solicitar Crédito");
                telaSolicitaCredito.setSize(500,600);
                telaSolicitaCredito.setVisible(true);

                JPanel painelSolicitacao = new JPanel();
                JLabel contrato = new JLabel("Leia atentamente os termos abaixo: ");
                JLabel textoContrato = new JLabel("Como foi combinado anteriormente -> Aqui vai o texto para o cliente");
                painelSolicitacao.add(contrato);
                painelSolicitacao.add(textoContrato);

                JLabel senhaTxt = new JLabel("Ao inserir sua senha a seguir, você estará concordando com os termos:");
                JPasswordField senhaConfirmacao = new JPasswordField(15);
                JButton confirma = new JButton("Confirma Ação");

                painelSolicitacao.add(senhaTxt);
                painelSolicitacao.add(senhaConfirmacao);
                painelSolicitacao.add(confirma);
                int valor = 5;//recebe valor da função de gerente
                confirma.addActionListener(e->{
                    if (String.valueOf(senhaConfirmacao.getPassword()).equals(getPassword())) {
                        //adiciona valor a conta vindo de gerente
                        setSaldoAtual(getSaldoAtual() + valor);

                        ClientePersistence clientePersistence = new ClientePersistence();
                        List<Cliente> clientes = clientePersistence.findAll();

                        Cliente cli = null;

                        for (Cliente c : clientes) {
                            if (c.getNumConta() == getNumConta()) {
                                cli = c;
                            }
                        }
                        Transacao transacao = new Transacao(getNumConta(), "Crédito do Banco", valor);

                        cli.getExtratos().add(transacao);

                        clientePersistence.save(clientes);
                        JOptionPane.showMessageDialog(null, "Solicitação confirmada!");

                    }else {
                        JOptionPane.showMessageDialog(null, "Senha incorreta");
                    }

                });



                telaSolicitaCredito.add(painelSolicitacao);

            }
        });


        //botão para excluir conta do cliente (remoção)
        JButton op6 = new JButton("Excluir minha conta");
        painelCliente.add(op6);
        op6.addActionListener(e->{
            JFrame telaExclusao = new JFrame("Excluir conta");
            telaExclusao.setSize(500, 600);
            telaExclusao.setVisible(true);

            JPanel painelEx  = new JPanel();
            JLabel msgBoasVindas = new JLabel("Aqui você pode excluir sua conta!");

            JLabel senhaLabel = new JLabel("Ao inserir a senha e apertar 'Confirmar' você está " +
                    "excluido permanentemenete sua conta. Tem certeza disso?");

            JPasswordField senhaField = new JPasswordField(15);

            painelEx.add(msgBoasVindas);
            painelEx.add(senhaLabel);
            painelEx.add(senhaField);

            JButton confirma = new JButton("Confirmar");
            confirma.addActionListener(f->{
                if (String.valueOf(senhaField.getPassword()).equals(getPassword())){

                    ClientePersistence clientePersistence = new ClientePersistence();
                    List<Cliente> clientes = clientePersistence.findAll();

                    Cliente cli = null;

                    for (Cliente c : clientes) {
                        if (c.getNumConta() == getNumConta()) {
                            cli = c;
                        }
                    }
                    if (cli != null) {
                        clientes.remove(cli); // Remove da lista
                        clientePersistence.save(clientes); // Atualiza o JSON
                        JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
                    }

                    JOptionPane.showMessageDialog(null,"Conta excluida com sucesso!");
                }

            });

            painelEx.add(confirma);

            telaExclusao.add(painelEx);

        });

        janelaCliente.add(painelCliente);


    }
    @Override
    public String toString(){
        return "    {" +
                "\"tipoDeUsuario\":\"" + getTipoDeUsuario() + "\"," +
                "\"nome\":\"" + getNome() + "\"," +
                "\"senha\":\"" + getPassword() + "\"," +
                "\"numConta\":\"" + getNumConta() + "\"," +
                "\"cpf\":\"" + getCpf() + "\"," +
                "\"saldo\":\"" + getSaldoAtual() + "\"," +
//                "\"extrato\":" + getExtrato() +
                "}";
    }
}
