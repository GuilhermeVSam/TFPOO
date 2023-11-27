package Dados.Equipamento;

import javax.swing.*;

public class JanelaDois extends JFrame {
    private CadastroCaminhao cadastroCaminhao;
    private CaminhaoTanque caminhaoTanque;
    private Equipamento equipamento;

    public JanelaDois(Equipamento equipamento) {
        super();
       // this.caminhaoTanque = (CaminhaoTanque) equipamento;
        this.equipamento=equipamento;
        cadastroCaminhao = new CadastroCaminhao(equipamento,this);
        this.setContentPane(cadastroCaminhao.getPainel());
        this.setTitle("Cadastro Caminhão");
        this.pack();
        this.setSize(800,300);
        this.setVisible(true);
    }
    public JanelaDois(){}
}