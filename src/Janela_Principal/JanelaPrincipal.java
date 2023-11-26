package Janela_Principal;

import Dados.Evento.JanelaEventos;

import javax.swing.*;

public class JanelaPrincipal extends JFrame {
    public JanelaPrincipal(){
        super();
        GUI form = new GUI();
        this.add(form.getPainel());
        this.setTitle("Cadastro de Eventos");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
