package Dados.Equipamento;

import javax.swing.*;

public class JanelaTres extends JFrame {


    private Equipamento equipamento;

    public JanelaTres(Equipamento equipamento) {
        super();

        this.equipamento = equipamento;
        CadastroBarco cadastroBarco = new CadastroBarco(equipamento, this);
        this.setContentPane(cadastroBarco.getPainel2());
        this.setTitle("Cadastro Barco");
        this.pack();
        this.setSize(800, 300);
        this.setVisible(true);

    }
}
