package Dados.Equipamento;

import Dados.Equipe.Cadastro;

import javax.swing.*;


public class Janela extends JFrame {
private Equipamento equipamento;
private CadastroEquipamento cadastro;
private CadastroCaminhao cadastroCaminhao;
public Janela(){}
    public Janela(Equipamento equipamento){
        super();
        this.equipamento=equipamento;
        cadastro = new CadastroEquipamento(equipamento,this);
        this.add(cadastro.getCadastroEquil());
        this.pack();
        this.setSize(800, 500);
        this.setTitle("Cadastro Equipamento");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

}
