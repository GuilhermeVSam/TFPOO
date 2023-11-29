package Janela_Principal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaRelatorio {
    private JPanel panel1;
    private JButton Equipes;
    private JButton Equipamentos;
    private JButton Eventos;
    private JButton Atendimentos;
    private JTextArea textArea1;
    private JButton Fechar;

    public JanelaRelatorio(APP app){
        JFrame frame = new JFrame("Relatório");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Equipes.addActionListener(e -> {
            if(app.getEquipes().size()==0){
                JOptionPane.showMessageDialog(null,"\n Nenhuma equipe cadastrada!");
            }else {
                textArea1.append("\nEquipes: \n"+app.eqDescricao());
            }
        });
        Equipamentos.addActionListener(e -> {
            if(app.getEquipamentos().size()==0){
                JOptionPane.showMessageDialog(null,"Nenhum equipamento cadastrado!");
            }else {
                textArea1.append("\nEquipamento: \n"+app.listarEquipamentos());
            }
        });
        Eventos.addActionListener(e -> {
            if(app.getEventos().size()==0){
                JOptionPane.showMessageDialog(null,"Nenhum evento cadastrado!");
            }else {
                textArea1.append("\nEventos: \n"+app.listarEventos());
            }
        });
        Atendimentos.addActionListener(e -> {
            if(app.getAtendimentos().size()==0){
                JOptionPane.showMessageDialog(null,"Nenhum atendimento cadastrado!");
            }else {
                textArea1.append("\n"+app.listarAtendimentos());
            }
        });
        Fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }
}
