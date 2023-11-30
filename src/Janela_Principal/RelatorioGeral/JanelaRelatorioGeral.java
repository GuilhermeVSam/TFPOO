package Janela_Principal.RelatorioGeral;

import Janela_Principal.APP;

import javax.swing.*;

public class JanelaRelatorioGeral extends JFrame {
    public JanelaRelatorioGeral(APP app){
        super();
        RelatorioGeral form = new RelatorioGeral(app);
        this.add(form.getPainel());
        this.setTitle("Cadastro de Eventos");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
