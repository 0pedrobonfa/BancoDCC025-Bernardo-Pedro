package br.ufjf.dcc.dcc205.bancodcc025;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GerenciaUsuarios {

    //construtor
    public GerenciaUsuarios(){}

    //metodo que vai cadastrar novos usuários
    // - Escrevendo eles no json
    public void cadastraUsuarios(){
        JFrame telaCadastro = new JFrame("Tela de Cadastro");
        telaCadastro.setSize(500,600);
        telaCadastro.setVisible(true);

        JPanel painelDeCadastro = new JPanel();

        //radiobutton pa
        JLabel userType = new JLabel("Selecione o tipo de usuário:");
        painelDeCadastro.add(userType);

        JRadioButton cliente = new JRadioButton("Cliente");
        JRadioButton caixa = new JRadioButton("Caixa");
        JRadioButton gerente = new JRadioButton("Gerente");

        //adiciona area do radiobutton no painel
        ButtonGroup grupoUserType = new ButtonGroup();

        grupoUserType.add(cliente);
        grupoUserType.add(caixa);
        grupoUserType.add(gerente);

        painelDeCadastro.add(cliente);
        painelDeCadastro.add(caixa);
        painelDeCadastro.add(gerente);

        JLabel novoUserName = new JLabel("Nome");
        JTextField userName = new JTextField(15);

        JLabel novoCPF = new JLabel("CPF");
        JTextField cpfTextField = new JTextField(15);

        JLabel senhaUsuario = new JLabel("Senha");
        JTextField newPassword = new JTextField(15);

        JButton registra = new JButton("Registra Usuário");

        painelDeCadastro.add(novoUserName);
        painelDeCadastro.add(userName);

        painelDeCadastro.add(novoCPF);
        painelDeCadastro.add(cpfTextField);

        painelDeCadastro.add(senhaUsuario);
        painelDeCadastro.add(newPassword);


        painelDeCadastro.add(registra);
        registra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = userName.getText();
                String senha = newPassword.getText();
                String cpf = cpfTextField.getText();
                String tipoUsuario = "";

                if (cliente.isSelected()) {
                    tipoUsuario = "cliente";
                } else if (caixa.isSelected()) {
                    tipoUsuario = "caixa";
                } else if (gerente.isSelected()) {
                    tipoUsuario = "gerente";
                }

                if (nome.isEmpty() || senha.isEmpty() || tipoUsuario.isEmpty()) {
                    JOptionPane.showMessageDialog(telaCadastro, "Preencha todos os campos!");
                    return;
                }

                // Criando um novo usuário conforme o tipo
                Usuario novoUsuario;
                if (tipoUsuario.equals("cliente")) {
                    novoUsuario = new Cliente(nome, 1008, 0.0,senha, cpf);

                    String filePath = "/home/pedrobonfa/Codes/java/BancoDCC025-Bernardo-Pedro/src/main/java/br/ufjf/dcc/dcc205/bancodcc025/Usuarios.json";
                    Arquivo arquivo = new Arquivo();
                    arquivo.adicionaUsuario(filePath, novoUsuario);
                } else if (tipoUsuario.equals("caixa")) {
                    //novoUsuario = new Caixa(nome, senha);
                } else {
                    //novoUsuario = new Gerente(nome, senha);
                }

                JOptionPane.showMessageDialog(telaCadastro, "Usuário cadastrado com sucesso!");
            }
        });




        telaCadastro.add(painelDeCadastro);


    }

}
