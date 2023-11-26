package Dados.Evento;

import Janela_Principal.APP;

import javax.swing.*;

public class JanelaEvento extends JFrame {
    public JanelaEvento(APP app){
        super();
        JanelaEventos form = new JanelaEventos(app);
        this.add(form.getPainel());
        this.setTitle("Cadastro de Eventos");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
