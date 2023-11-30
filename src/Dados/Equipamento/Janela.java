package Dados.Equipamento;
import javax.swing.*;
import Janela_Principal.APP;

public class Janela extends JFrame {
    private CadastroEquipamento cadastro;

    public Janela(APP app) {
        super();
        cadastro = new CadastroEquipamento(app);
        this.add(cadastro.getPainel());
        this.setTitle("Cadastro Equipamento");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setSize(800, 500);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}