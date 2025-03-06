
package br.ufjf.dcc.dcc205.bancodcc025;
import br.ufjf.dcc.dcc205.bancodcc025.model.Cliente;
import br.ufjf.dcc.dcc205.bancodcc025.model.Caixa;
import br.ufjf.dcc.dcc205.bancodcc025.model.Transacao;
import br.ufjf.dcc.dcc205.bancodcc025.persistence.ClientePersistence;
import br.ufjf.dcc.dcc205.bancodcc025.view.TelaLogin;
import br.ufjf.dcc.dcc205.bancodcc025.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BancoDCC025Test {
    private JList<Cliente> jlClientes;
    private int verifica;
    public BancoDCC025Test(){
        this.jlClientes = new JList<>(new DefaultListModel<>());
    }

    private void cadastraNovoCliente(Cliente novoCliente){
        DefaultListModel<Cliente> model = (DefaultListModel<Cliente>) jlClientes.getModel();
        model.addElement(novoCliente);
        ClientePersistence clientePersistence = new ClientePersistence();
        // Carrega lista de clientes existente
        List<Cliente> clientes = clientePersistence.findAll();
        // Adiciona o novo cliente à lista
        clientes.add(novoCliente);
        // Salva a lista atualizada no JSON
        clientePersistence.save(clientes);
    }


    @Test
    public void testaCadastroCliente()
    {
        //olha no json e se tiver com todos os campos, tá certo

        //cria cliente
        Cliente novoCliente = new Cliente("Cabral", 1099, 5000.00,"passa","98765432101");
        //adiciona cliente na lista e salva

        cadastraNovoCliente(novoCliente);;
        ClientePersistence clientePersistence = new ClientePersistence();
        List<Cliente> clientes = clientePersistence.findAll();


        //se achar o cliente e ele tiver uma conta no documento, ele foi criado
        for (Cliente c : clientes){
            if(novoCliente.getNumConta() == c.getNumConta()){
                //se achou o cliente, verifica os campos
                Assertions.assertEquals(novoCliente.getPassword(),c.getPassword(), "Senha Compatível");
                Assertions.assertEquals(novoCliente.getNumConta(),c.getNumConta(), "Número da Conta Compatível");
                Assertions.assertEquals(novoCliente.getNome(),c.getNome(), "Nome Compatível");
                Assertions.assertEquals(novoCliente.getTipoDeUsuario(),c.getTipoDeUsuario(), "Tipo de Usuário Compatível");
                Assertions.assertEquals(novoCliente.getCpf(), c.getCpf(), "CPF compatível");
                Assertions.assertEquals(novoCliente.getExtratos(), c.getExtratos(), "Extratos compatível");
            }
        }

        clientes.remove(novoCliente);

        clientePersistence.save(clientes);

    }

    @Test
    public void testaTransferenciaCliente() {

        //cria dois clientes para checar a transferência
        Cliente origem = new Cliente("Abner", 1007, 4000.00, "5555", "14523698801");
        Cliente destino = new Cliente("Juanita", 1012, 800.00, "222", "45678912302");

        DefaultListModel<Cliente> model = (DefaultListModel<Cliente>) jlClientes.getModel();
        model.addElement(origem);

        ClientePersistence clientePersistence = new ClientePersistence();

        List<Cliente> clientes = clientePersistence.findAll();

        clientes.add(origem);
        clientes.add(destino);

        clientePersistence.save(clientes);

        double valor = 1000.00;

        origem.setSaldoAtual(origem.getSaldoAtual() - valor);
        destino.setSaldoAtual(destino.getSaldoAtual() + valor);

        Transacao transacaoOrigem = new Transacao(origem.getNumConta(), destino.getNumConta(), valor);
        Transacao transacaoDestino = new Transacao(origem.getNumConta(), destino.getNumConta(), valor);

        origem.getExtratos().add(transacaoOrigem);
        destino.getExtratos().add(transacaoDestino);

        clientePersistence.save(clientes);


        for(Cliente c: clientes){
            if(c.getNumConta() == 1007)
            {//acha a conta de origem no vetor e confere o saldo
                Assertions.assertEquals(3000.00, c.getSaldoAtual());
            }
            if(c.getNumConta() == 1012){
                //acha a conta de destino no vetor e confere o saldo
                Assertions.assertEquals(1800.00, c.getSaldoAtual());

            }
        }
    }

    @Test
    public void testaConsultaDeSaldoCliente ()
    {

        //cria cliente; ve se saldo no json bate com o saldo iniciado
        Cliente novoCliente = new Cliente("Umberto",1006,50000,"senha","12345678970");
        cadastraNovoCliente(novoCliente);

        ClientePersistence clientePersistence = new ClientePersistence();
        List<Cliente> clientes = clientePersistence.findAll();

        for(Cliente c: clientes){
            if(c.getNumConta() == novoCliente.getNumConta()){
                Assertions.assertEquals(50000, c.getSaldoAtual());
            }
        }
    }

    @Test
    public void testaInvestimentoEmRendaFixaCliente() {

        Cliente novoCliente = new Cliente("Thiago", 1047, 2000, "senha", "78549621369");

        DefaultListModel<Cliente> model = (DefaultListModel<Cliente>) jlClientes.getModel();
        model.addElement(novoCliente);
        ClientePersistence clientePersistence = new ClientePersistence();
        List<Cliente> clientes = clientePersistence.findAll();
        clientes.add(novoCliente);
        clientePersistence.save(clientes);

        Transacao investeRendaFixa = new Transacao(1047, "CDB 110%", 200.00);

        novoCliente.getExtratos().add(investeRendaFixa);

        clientePersistence.save(clientes);

        for(Cliente c: clientes)
        {
            if(c.getNumConta() == novoCliente.getNumConta())
            {
                Assertions.assertEquals(novoCliente.getExtratos(), c.getExtratos());
            }
        }

        clientes.remove(novoCliente);
        clientePersistence.save(clientes);
    }



    @Test
    public void testaInvestimentoEmRendaVariavelCliente ()
    {

        Cliente novoCliente = new Cliente("José", 1049, 3000, "senha", "78549745369");

        DefaultListModel<Cliente> model = (DefaultListModel<Cliente>) jlClientes.getModel();
        model.addElement(novoCliente);
        ClientePersistence clientePersistence = new ClientePersistence();
        List<Cliente> clientes = clientePersistence.findAll();
        clientes.add(novoCliente);
        clientePersistence.save(clientes);

        Transacao investeRendaFixa = new Transacao(1049, "Fundo BB Ações Internacionais", 300.00);

        novoCliente.getExtratos().add(investeRendaFixa);

        clientePersistence.save(clientes);

        for(Cliente c: clientes)
        {
            if(c.getNumConta() == novoCliente.getNumConta())
            {
                Assertions.assertEquals(novoCliente.getExtratos(), c.getExtratos());
            }
        }
        clientes.remove(novoCliente);
        clientePersistence.save(clientes);
    }

    @Test
    public void testaSolicitacaoDeCreditoCliente()
    {

        Cliente novoCliente = new Cliente("Evandro", 1039, 4000, "senha", "78549745369");

        DefaultListModel<Cliente> model = (DefaultListModel<Cliente>) jlClientes.getModel();
        model.addElement(novoCliente);
        ClientePersistence clientePersistence = new ClientePersistence();
        List<Cliente> clientes = clientePersistence.findAll();
        clientes.add(novoCliente);
        clientePersistence.save(clientes);

        Transacao investeRendaFixa = new Transacao(1039, "Solicitação de Crédito", 500.00);

        novoCliente.getExtratos().add(investeRendaFixa);

        clientePersistence.save(clientes);

        for(Cliente c: clientes)
        {
            if(c.getNumConta() == novoCliente.getNumConta())
            {
                Assertions.assertEquals(novoCliente.getExtratos(), c.getExtratos());
            }
        }

        clientes.remove(novoCliente);
        clientePersistence.save(clientes);
    }

    @Test
    public void testaAtendimentoDeSaqueCaixa ()
    {
    }

    @Test
    public void testaProcessamentoDeDepositosCaixa ()
    {
    }

    @Test
    public void testaTransferenciaCaixa ()
    {
    }

    @Test
    public void testaApoioEmMovimentacoesFinanceirasGerente ()
    {
    }

    @Test
    public void testaCadastroDeOpcoesDeRendaFixaGerente ()
    {
    }

    @Test
    public void testaCadastroDeOpcoesDeRendaVariavelGerente ()
    {
    }

    @Test
    public void testaAvaliacaoDeCreditoGerente ()
    {
    }
}
