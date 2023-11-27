package Dados.Equipamento;
import javax.swing.*;
import Janela_Principal.APP;

public class Janela extends JFrame {
private Equipamento equipamento;
private CadastroEquipamento cadastro;
public Janela(){}
    public Janela(Equipamento equipamento){
        super();
        this.equipamento=equipamento;
        cadastro = new CadastroEquipamento(equipamento,this);
        this.add(cadastro.getPainel());
        this.setTitle("Cadastro Equipamento");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setSize(800, 500);
        this.setVisible(true);
    }

}
//package Dados.Evento;
//
//        import Janela_Principal.APP;
//
//        import javax.swing.*;
//
//public class JanelaEvento extends JFrame {
//    public JanelaEvento(APP app){
//        super();
//        JanelaEventos form = new JanelaEventos(app);
//        this.add(form.getPainel());
//        this.setTitle("Cadastro de Eventos");
//        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//        this.pack();
//        this.setVisible(true);
//    }
//}
