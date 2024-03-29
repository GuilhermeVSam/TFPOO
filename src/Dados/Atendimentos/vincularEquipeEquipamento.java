package Dados.Atendimentos;

import Dados.Equipamento.CaminhaoTanque;
import Dados.Equipamento.Equipamento;
import Dados.Equipe.ListaEquipes;
import Dados.Equipe.Equipe;
import Janela_Principal.APP;
import Janela_Principal.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class vincularEquipeEquipamento extends JFrame implements ActionListener {

    private JComboBox campoEquipe;
    private JPanel container;
    private JComboBox campoEquipamento;
    private JButton vincular;
    private JButton voltar;
    private APP app;
    private ListaEquipes listaEquipes;
    private CaminhaoTanque cadastroEq;

    public vincularEquipeEquipamento(APP app) {
        super();
        this.app = app;
        this.setTitle("Vincular Equipamentos a Equipe");

        JPanel textop = new JPanel(new GridLayout(6, 2));
        textop.add(new JLabel("Equipes cadastradas:"));
        campoEquipe = new JComboBox();
        for (Equipe e : app.getEquipes()) {
            campoEquipe.addItem(e.getCodinome());
        }
        textop.add(campoEquipe);
        textop.add(new JLabel("Equipamentos cadastrados:"));
        campoEquipamento = new JComboBox();
        for (Equipamento eq : app.getEquipamento()) {
            campoEquipamento.addItem(eq.getNome()+"("+eq.getId()+")");
        }
        textop.add(campoEquipamento);
        vincular = new JButton("Vincular equipamento");
        voltar = new JButton("Voltar");
        JPanel botoesPanel = new JPanel();
        botoesPanel.add(vincular);
        botoesPanel.add(voltar);

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(textop);
        container.add(botoesPanel);

        vincular.addActionListener(this);
        voltar.addActionListener(this);

        this.add(container);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == voltar) {
            GUI.FecharJanela(this);
        } else if (e.getSource() == vincular) {
            vincularEquipamento();
        }
    }

    public void vincularEquipamento() {
        try {
            Equipe equipeS = app.buscaEquipe(campoEquipe.getSelectedItem().toString());
            Equipamento equipamentoS = app.buscaEquipamento(campoEquipamento.getSelectedItem().toString().split("\\(")[1].split("\\)")[0].trim());
            System.out.println(equipeS.getCodinome() + equipamentoS.getNome());
            if (equipamentoS.getEquipe() != null) {
                JOptionPane.showMessageDialog(this, "Erro: Equipamento já vinculado a outra equipe.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            equipeS.addEquipamento(equipamentoS);
            equipamentoS.setEquipe(equipeS);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar: Certifique-se de selecionar um evento e uma equipe!" ,"Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: Dados inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}