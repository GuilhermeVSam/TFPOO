package Janela_Principal.RelatorioGeral;

import Janela_Principal.APP;
import Janela_Principal.GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RelatorioGeral {
    private JPanel panel1;
    private JButton Limpar;
    private JButton Fechar;
    private JButton Atendimentos;
    private JButton Equipamentos;
    private JButton Equipes;
    private JButton Eventos;
    private JTextPane TextAtendimentos;
    private JTextPane TextEquipamentos;
    private JTextPane TextEquipes;
    private JTextPane TextEventos;

    public RelatorioGeral(APP app){

        Atendimentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TextAtendimentos.setText(app.listarAtendimentos());
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
        Fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.FecharJanela((JFrame) SwingUtilities.getWindowAncestor(panel1));
            }
        });
        Limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextAtendimentos.setText("");
                TextEquipamentos.setText("");
                TextEquipes.setText("");
                TextEventos.setText("");
            }
        });
        Equipamentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TextEquipamentos.setText(app.listarEquipamentos());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
        Equipes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TextEquipes.setText(app.listarEquipes());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
        Eventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TextEventos.setText(app.listarEventos());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
    }

    public JPanel getPainel(){
        return panel1;
    }
}
