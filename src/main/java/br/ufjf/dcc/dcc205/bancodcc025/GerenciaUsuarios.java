package br.ufjf.dcc.dcc205.bancodcc025;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GerenciaUsuarios {
    private List<Object> listaDeUsuarios;
    private ObjectMapper objectMapper = new ObjectMapper();

    //construtor da classe
    public GerenciaUsuarios() {}

    public void cadastraUsuario() {
        //tela de cadastro
        JFrame telaNovoCadastro = new JFrame("Novo Cadastro");
        telaNovoCadastro.setSize(500, 600);
        telaNovoCadastro.setVisible(true);

        //painel de cadastro
        JPanel painelCadastro = new JPanel();

        JLabel escolhaMsg = new JLabel("Escolha o tipo de usuário");
        painelCadastro.add(escolhaMsg);

        //Botão para cliente
        JButton cliente = new JButton("Cliente");
        painelCadastro.add(cliente);

        cliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove o painel da tela
                telaNovoCadastro.remove(painelCadastro);

                JLabel userNameLabel = new JLabel("Nome do Novo Usuário");
                JTextField userNameTextField = new JTextField(15);
                JLabel senhaLabel = new JLabel("Senha");
                JTextField senhaTextField = new JTextField(15);
                JLabel cpfLabel = new JLabel("CPF");
                JTextField cpfTextField = new JTextField(15);


                painelCadastro.removeAll(); // Limpa o painel

                //adiciona elemtentos
                painelCadastro.add(userNameLabel);
                painelCadastro.add(userNameTextField);
                painelCadastro.add(senhaLabel);
                painelCadastro.add(senhaTextField);
                painelCadastro.add(cpfLabel);
                painelCadastro.add(cpfTextField);

                JButton salvarButton = new JButton("Salvar Novo Cadastro");
                salvarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent f) {

                        //cria cliente
                        String nome = userNameTextField.getText();
                        String senha = senhaTextField.getText();
                        String cpf = cpfTextField.getText();

                        Cliente novoCliente = new Cliente(nome,1003,1000.00,senha, cpf);

                        //salva no json
                        try {
                            adicionaUsuarioJson(novoCliente);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }


                    }
                });
                painelCadastro.add(salvarButton);

                // Adiciona o painel atualizado de volta à janela
                telaNovoCadastro.add(painelCadastro);


                // Revalida e repinta a janela
                telaNovoCadastro.revalidate();
                telaNovoCadastro.repaint();
            }
        });

        //Botão para caixa
        JButton caixa = new JButton("Caixa");
        painelCadastro.add(caixa);

        //Botão para gerente
        JButton gerente = new JButton("Gerente");
        painelCadastro.add(gerente);

        telaNovoCadastro.add(painelCadastro);
    }

    public List<Object> getListaDeUsuarios() {
        return listaDeUsuarios;
    }

    private void adicionaUsuarioJson(Cliente novoCliente) throws IOException {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Caminho do arquivo
        String caminhoArquivo = "/home/pedrobonfa/Codes/java/BancoDCC025-Bernardo-Pedro/src/main/java/br/ufjf/dcc/dcc205/bancodcc025/Usuarios.json";

        // Ler o arquivo JSON
        JsonNode rootNode = objectMapper.readTree(new File(caminhoArquivo));

        // Converter o cliente em JsonNode
        JsonNode clienteNode = objectMapper.valueToTree(novoCliente);

        // Adicionar o cliente ao array "usuarios"
        ArrayNode usuariosArray = (ArrayNode) rootNode.get("usuarios");
        usuariosArray.add(clienteNode);

        // Escrever o JSON de volta no arquivo
        objectMapper.writeValue(new File(caminhoArquivo), rootNode);

    }
}