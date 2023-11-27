package Dados.Equipamento;

import javax.swing.*;

public class JanelaQuatro extends JFrame {


    private Equipamento equipamento;

    public JanelaQuatro(Equipamento equipamento) {
        super();

        this.equipamento = equipamento;
        CadastroEscavadeira cadastroEscavadeira = new CadastroEscavadeira(equipamento,this);
        this.setContentPane(cadastroEscavadeira.getPainel2());
        this.setTitle("Cadastro Escavadeira");
        this.pack();
        this.setSize(800, 300);
        this.setVisible(true);

    }
}