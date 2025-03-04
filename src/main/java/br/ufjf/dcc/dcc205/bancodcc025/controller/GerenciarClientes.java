package br.ufjf.dcc.dcc205.bancodcc025.controller;


import br.ufjf.dcc.dcc205.bancodcc025.Cliente;
import br.ufjf.dcc.dcc205.bancodcc025.TelaLogin;
import br.ufjf.dcc.dcc205.bancodcc025.persistence.ClientePersistence;
import br.ufjf.dcc.dcc205.bancodcc025.persistence.Persistence;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class GerenciarClientes implements WindowListener {

    private final TelaLogin tela;

    public GerenciarClientes(TelaLogin tela) {
        this.tela = tela;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        Persistence<Cliente> contatoPersistence = new ClientePersistence();
        List<Cliente> all = contatoPersistence.findAll();
        tela.carregaClientes(all);

    }

    @Override
    public void windowClosing(WindowEvent e) {
        Persistence<Cliente> contatoPersistence = new ClientePersistence();
        contatoPersistence.save(tela.listaClientes());
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