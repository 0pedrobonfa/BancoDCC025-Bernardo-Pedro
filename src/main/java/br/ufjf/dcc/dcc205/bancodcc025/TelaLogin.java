package br.ufjf.dcc.dcc205.bancodcc025;

/**
 *
 * @author pedrobonfa
 */
import javax.swing.*;
import java.awt.*;

public class TelaLogin {
    //Atributos privados da classe TelaLogin
    private JFrame tela;
    private final int WIDTH = 500;
    private final int HEIGHT = 600;

    //metodo para iniciar tela de login
    public void iniciaTela() {
        tela = new JFrame("Login");
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
        JTextField userTextField = new JTextField(15);
        JLabel senhaLabel = new JLabel("Senha");
        JPasswordField senhaTextField = new JPasswordField(15);
        JButton entrarButton = new JButton("Entrar");

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

        return areaLoginContainer;
    }
}
