package Janela_Principal;

import javax.swing.*;

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
            textArea1.setText(app.listarEventos());
        });
        Equipamentos.addActionListener(e -> {
            textArea1.setText(app.listarAtendimentos());
        });
        Eventos.addActionListener(e -> {
            textArea1.setText(app.getEquipes().toString());
        });
        Atendimentos.addActionListener(e -> {
            textArea1.setText(app.listarEquipamentos());
        });
    }
}
