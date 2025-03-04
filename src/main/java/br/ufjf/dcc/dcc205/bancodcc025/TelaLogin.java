package br.ufjf.dcc.dcc205.bancodcc025;

/**
 *
 * @author pedrobonfa
 */
import br.ufjf.dcc.dcc205.bancodcc025.controller.AdicionarCliente;
import br.ufjf.dcc.dcc205.bancodcc025.controller.GerenciarClientes;
import br.ufjf.dcc.dcc205.bancodcc025.exception.ClienteException;

import br.ufjf.dcc.dcc205.bancodcc025.persistence.ClientePersistence;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin{
    //Atributos - vindos de gerenciar user
    private int numContasCliente;
    private int numContasCaixa;
    private int numContasGerente;
    //Atributos privados da classe TelaLogin
    private JFrame tela;
    private JList<Cliente> jlClientes;
    private final int WIDTH = 500;
    private final int HEIGHT = 600;
    //construtor
    public TelaLogin(){
        this.numContasCliente = 1002;
        this.numContasCaixa = 100;
        this.numContasGerente= 200;
        this.jlClientes = new JList<>(new DefaultListModel<>());
    }
    //getters
    public int getNumContasCliente() {return numContasCliente;}
    public int getNumContasCaixa() {return numContasCaixa;}
    public int getNumContasGerente() {return numContasGerente;}
    //setters
    public void setNumContasCliente(int numContasCliente) {this.numContasCliente = numContasCliente;}
    public void setNumContasCaixa(int numContasCaixa) {this.numContasCaixa = numContasCaixa;}
    public void setNumContasGerente(int numContasGerente) {this.numContasGerente = numContasGerente;}
    //metodos para calculo do numéro da conta do usuário
    private int calculaNumContaCliente(){

        int numAtual = getNumContasCliente();
        setNumContasCliente(numAtual+1);
        return numAtual+1;

    }
    private int calculaNumContaCaixa(){
        int numAtual = getNumContasCaixa();
        setNumContasCaixa(numAtual+1);
        return numAtual+1;
    }
    private int calculaNumContaGerente(){
        int numAtual = getNumContasGerente();
        setNumContasGerente(numAtual+1);
        return numAtual+1;
    }



    //metodo para iniciar tela de login
    public void iniciaTela() {
        tela = new JFrame("Login");
        tela.addWindowListener(new GerenciarClientes(this));
        tela.setSize(WIDTH, HEIGHT);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tela.setLayout(new BorderLayout());
        tela.add(desenhaAreaLogin(), BorderLayout.CENTER);

        tela.setVisible(true);
    }

    //metodo para desenhar as areas de 'usuario' e 'senha'
    private JPanel desenhaAreaLogin() {
        JPanel areaLoginContainer = new JPanel(new GridBagLayout());

        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.HORIZONTAL;//Faz ele preencher o espaço na horizontal
        grid.insets = new Insets(5, 5, 5, 5);//Espaçamento entre os componentes

        JLabel userLabel = new JLabel("Login");
        JTextField userTextField = new JTextField("cliente");
        JLabel senhaLabel = new JLabel("Senha");
        JPasswordField senhaTextField = new JPasswordField("cliente");
        JButton entrarButton = new JButton("Entrar");
        JButton novoCadastroButton = new JButton("Novo Cadastro");

        //Aloca cada componente em seu lugar
        grid.gridx = 0;
        grid.gridy = 0;
        areaLoginContainer.add(userLabel, grid);

        grid.gridx = 1;
        grid.gridy = 0;
        areaLoginContainer.add(userTextField, grid);

        grid.gridx = 0;
        grid.gridy = 1;
        areaLoginContainer.add(senhaLabel, grid);

        grid.gridx = 1;
        grid.gridy = 1;
        areaLoginContainer.add(senhaTextField, grid);

        grid.gridx = 1;
        grid.gridy = 2;
        areaLoginContainer.add(entrarButton, grid);

        grid.gridx = 2;
        grid.gridy = 2;
        areaLoginContainer.add(novoCadastroButton,grid);

        //adiciona lógica do botão
        entrarButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userTextField.getText();
                String senha = new String(senhaTextField.getPassword());

                //Validação simples
                if ("cliente".equals(user) && "cliente".equals(senha)) {
                    //JOptionPane.showMessageDialog(desenhaAreaLogin(),"CLIENTE, seja bem vindo!");
                    // Abrir nova janela para Cliente
                    tela.setVisible(false);//fecha janela antiga
                    //cria usuário e abre tela para ele
                    Cliente cliente = new Cliente("Arthur", 1001,10000.00,"admin", "123.456.789-01");
                    cliente.telaUsuario();
                }
                else if ("caixa".equals(user) && "caixa".equals(senha)){
                    JOptionPane.showMessageDialog(desenhaAreaLogin(),"CAIXA, seja bem vindo!");
                }
                else if ("gerente".equals(user) && "gerente".equals(senha)) {
                    JOptionPane.showMessageDialog(desenhaAreaLogin(),"GERENTE, seja bem vindo!");

                }else {
                    JOptionPane.showMessageDialog(desenhaAreaLogin(),"Usuário ou senha incorretos!");
                }}});

        novoCadastroButton.addActionListener(new AdicionarCliente(this));

        return areaLoginContainer;
    }

    public void addCliente() {
        JFrame telaCadastro = new JFrame("Tela de Cadastro");
        telaCadastro.setSize(WIDTH, HEIGHT); // Evite constantes indefinidas como WIDTH e HEIGHT
        telaCadastro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painelDeCadastro = new JPanel();
        painelDeCadastro.setLayout(new GridLayout(0, 2, 5, 5)); // Melhor organização

        JLabel userType = new JLabel("Selecione o tipo de usuário:");
        painelDeCadastro.add(userType);

        JRadioButton cliente = new JRadioButton("Cliente");
        JRadioButton caixa = new JRadioButton("Caixa");
        JRadioButton gerente = new JRadioButton("Gerente");

        ButtonGroup grupoUserType = new ButtonGroup();
        grupoUserType.add(cliente);
        grupoUserType.add(caixa);
        grupoUserType.add(gerente);

        painelDeCadastro.add(cliente);
        painelDeCadastro.add(caixa);
        painelDeCadastro.add(gerente);

        // Campos de entrada
        painelDeCadastro.add(new JLabel("Nome:"));
        JTextField tfUserName = new JTextField(15);
        painelDeCadastro.add(tfUserName);

        painelDeCadastro.add(new JLabel("CPF:"));
        JTextField cpfTextField = new JTextField(15);
        painelDeCadastro.add(cpfTextField);

        painelDeCadastro.add(new JLabel("Senha:"));
        JTextField tfPassword = new JTextField(15);
        painelDeCadastro.add(tfPassword);

        JButton registra = new JButton("Registrar Usuário");
        painelDeCadastro.add(registra);

        telaCadastro.add(painelDeCadastro);
        telaCadastro.setVisible(true);

        // Lógica de cadastro ao pressionar o botão
        registra.addActionListener(e -> {
            // Verifica se os campos estão preenchidos antes de registrar
            String nome = tfUserName.getText().trim();
            String cpf = cpfTextField.getText().trim();
            String senha = tfPassword.getText().trim();

            if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(telaCadastro, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verifica se `jlClientes` foi inicializado corretamente
            if (jlClientes == null || !(jlClientes.getModel() instanceof DefaultListModel<?>)) {
                JOptionPane.showMessageDialog(telaCadastro, "Erro ao acessar a lista de clientes!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                DefaultListModel<Cliente> model = (DefaultListModel<Cliente>) jlClientes.getModel();
                Cliente novoCliente = new Cliente(nome, calculaNumContaCliente(), 0.0, senha, cpf);

                model.addElement(novoCliente);

                // Criando instância do ClientePersistence
                ClientePersistence clientePersistence = new ClientePersistence();

                // Carregar lista de clientes existente
                List<Cliente> clientes = clientePersistence.findAll();

                // Adiciona o novo cliente à lista
                clientes.add(novoCliente);

                // Salvar a lista atualizada no JSON
                clientePersistence.save(clientes);

                JOptionPane.showMessageDialog(telaCadastro, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                telaCadastro.dispose(); // Fecha a tela após o cadastro
            } catch (ClienteException ex) {
                JOptionPane.showMessageDialog(telaCadastro, "O CPF do Novo Cliente " + cpf + " é inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        telaCadastro.revalidate();
        telaCadastro.repaint();
    }

    public void carregaClientes(List<Cliente> clientes) {
        DefaultListModel<Cliente> model = new DefaultListModel<>();

        for (Cliente c : clientes) {
            model.addElement(c);
        }

        jlClientes.setModel(model); // Atualiza o modelo da JList
    }

    public List<Cliente> listaClientes(){
        DefaultListModel<Cliente> model = (DefaultListModel<Cliente>)jlClientes.getModel();
        List<Cliente> contatos = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            contatos.add(model.get(i));
        }

        return contatos;
    }

}

