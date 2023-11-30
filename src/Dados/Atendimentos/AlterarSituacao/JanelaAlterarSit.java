package Dados.Atendimentos.AlterarSituacao;

import Dados.Equipamento.CadastroEquipamento;
import Dados.Equipamento.Equipamento;
import Janela_Principal.APP;

import javax.swing.*;

public class JanelaAlterarSit extends JFrame {
    public JanelaAlterarSit(APP app){
        super();
        AlterarSituacao form = new AlterarSituacao(app);
        this.add(form.getPanel1());
        this.pack();
        this.setTitle("Alterar Situação do Atendimento");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
