package br.ufjf.dcc.dcc205.bancodcc025.controller;


import br.ufjf.dcc.dcc205.bancodcc025.view.TelaLogin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarGerente implements ActionListener {

    private final TelaLogin tela;

    public AdicionarGerente(TelaLogin tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tela.addUsuario();
    }
}