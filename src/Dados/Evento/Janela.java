package Dados.Evento;

import Janela_Principal.APP;

import javax.swing.*;

public class Janela extends JFrame {
    public Janela(APP app){
        super();
        JanelaEventos form = new JanelaEventos(app);
        this.add(form.getPainel());
        this.setTitle("Cadastro de Eventos");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
