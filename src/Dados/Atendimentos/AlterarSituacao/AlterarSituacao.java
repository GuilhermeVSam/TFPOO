package Dados.Atendimentos.AlterarSituacao;

import Dados.Atendimentos.Atendimento;
import Dados.Atendimentos.STATUS;
import Janela_Principal.APP;
import Janela_Principal.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlterarSituacao {
    private APP app;
    private JPanel panel1;
    private JComboBox ListaAtendimentos;
    private JTextField CodEvento;
    private JTextField CodinomeEquipe;
    private JTextField CodEquipa;
    private JTextField DataAtendimento;
    private JComboBox ListaStatus;
    private JButton Confirmar;
    private JButton Fechar;

    public AlterarSituacao(APP app){
        this.app = app;
        ModelSelecionaAtendimento();
        selecionaAtendimento();
        ListaAtendimentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selecionaAtendimento();
            }
        });
        Confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = ListaAtendimentos.getSelectedItem().toString();
                Atendimento atendimento = app.buscaAtendimento(Integer.parseInt(codigo));
                String status = ListaStatus.getSelectedItem().toString();
                atendimento.setStatus(STATUS.getSTATUS(status));
                JOptionPane.showMessageDialog(null, "Status alterado com sucesso!");
            }
        });
        Fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.FecharJanela((JFrame) SwingUtilities.getWindowAncestor(panel1));
            }
        });
    }


    public JPanel getPanel1() {
        return panel1;
    }

    private void selecionaAtendimento(){
        String codigo = ListaAtendimentos.getSelectedItem().toString();
        Atendimento atendimento = app.buscaAtendimento(Integer.parseInt(codigo));
        String status = atendimento.getStatus().getDescricao();
        selecionaStatus(status);
        DataAtendimento.setText(atendimento.getData());
        try {
            CodinomeEquipe.setText(String.valueOf(atendimento.getCodEquipe()));
        }catch(NullPointerException exception){
            CodinomeEquipe.setText("Equipe Pendente");
        }
        CodEvento.setText(String.valueOf(atendimento.getCodEvento()));
    }

    private void ModelSelecionaAtendimento(){
        DefaultComboBoxModel<String> listarAtendimentos = new DefaultComboBoxModel<>();
        for (Atendimento a : app.getAtendimentos()) {
            listarAtendimentos.addElement(String.valueOf(a.getCod()));
        }
        ListaAtendimentos.setModel(listarAtendimentos);
    }

    private void selecionaStatus(String status){
        DefaultComboBoxModel<String> listarStatus = new DefaultComboBoxModel<>();
        for (STATUS s : STATUS.values()) {
            listarStatus.addElement(String.valueOf(s.getDescricao()));
        }
        ListaStatus.setModel(listarStatus);
        ListaStatus.setSelectedItem(status);
    }
}
