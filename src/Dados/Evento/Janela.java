package Dados.Evento;

import javax.swing.*;

public class Janela extends JFrame {
    private JanelaEventos form;

    public Janela(){
        super();
        form = new JanelaEventos();
        this.add(form.getPainel());
        this.setTitle("Cadastro de Eventos");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
