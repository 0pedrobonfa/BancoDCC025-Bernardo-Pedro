package br.ufjf.dcc.dcc205.bancodcc025.controller;


import br.ufjf.dcc.dcc205.bancodcc025.model.Gerente;
import br.ufjf.dcc.dcc205.bancodcc025.view.TelaLogin;
import br.ufjf.dcc.dcc205.bancodcc025.persistence.GerentePersistence;
import br.ufjf.dcc.dcc205.bancodcc025.persistence.Persistence;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class GerenciarGerentes implements WindowListener {

    private final TelaLogin tela;

    public GerenciarGerentes(TelaLogin tela) {
        this.tela = tela;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        Persistence<Gerente> contatoPersistence = new GerentePersistence();
        List<Gerente> all = contatoPersistence.findAll();
        tela.carregaGerentes(all);

    }

    @Override
    public void windowClosing(WindowEvent e) {
        Persistence<Gerente> gerentePersistence = new GerentePersistence();
        gerentePersistence.save(tela.listaGerentes());
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