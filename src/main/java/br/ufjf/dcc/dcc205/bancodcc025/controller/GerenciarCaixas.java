package br.ufjf.dcc.dcc205.bancodcc025.controller;


import br.ufjf.dcc.dcc205.bancodcc025.model.Caixa;
import br.ufjf.dcc.dcc205.bancodcc025.view.TelaLogin;
import br.ufjf.dcc.dcc205.bancodcc025.persistence.CaixaPersistence;
import br.ufjf.dcc.dcc205.bancodcc025.persistence.Persistence;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class GerenciarCaixas implements WindowListener {

    private final TelaLogin tela;

    public GerenciarCaixas(TelaLogin tela) {
        this.tela = tela;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        Persistence<Caixa> contatoPersistence = new CaixaPersistence();
        List<Caixa> all = contatoPersistence.findAll();
        tela.carregaCaixas(all);

    }

    @Override
    public void windowClosing(WindowEvent e) {
        Persistence<Caixa> caixaPersistence = new CaixaPersistence();
        caixaPersistence.save(tela.listaCaixas());
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}