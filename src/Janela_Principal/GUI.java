package Janela_Principal;

import Dados.Atendimentos.Atendimento;
import Dados.Atendimentos.JanelaCadastroAtendimento;
import Dados.Equipamento.Equipamento;
import Dados.Equipamento.Janela;
import Dados.Equipe.JanelaEquipe;
import Dados.Evento.JanelaEvento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI{
    private APP app = new APP();
    private JanelaEvento janelaEvento;
    private JanelaCadastroAtendimento janelaAtendimento;
    private JanelaEquipe janelaEquipe;
    private JPanel panel1;
    private JButton CadastrarAtendimento;
    private JButton CadastrarEquipe;
    private JButton CadastrarEvento;
    private JButton CadastrarEquipamento;
    private JButton Finalizar;
    private JPanel panel2;
    private JTextPane textPane1;
    private JButton Atualizar;
    private JComboBox AtendimentoCombo;
    private JTextField CodigoTField;
    private JTextField DataTField;
    private JTextField DuracaoTField;
    private JTextField StatusTField;
    private JTextField CodETField;
    private JLabel Código;
    private JLabel Data;
    private JLabel Duracao;
    private JLabel Status;
    private JLabel CodEvento;
    private JLabel CodiEquipe;
    private JTextField CodiETField;
    private JPanel panel3;
    private JButton Detalhe;
    private JButton CarregarDados;
    private JLabel Atendimento;
    private JButton SalvarDados;

    public GUI(){
        CadastrarEvento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaEventos();
            }
        });
        CadastrarAtendimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaAtendimentos();
            }
        });
        CadastrarEquipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaEquipe();
            }
        });
        Finalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        Atualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selecionaAtendimento();
            }
        });
        AtendimentoCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = AtendimentoCombo.getSelectedItem().toString();
                Atendimento atendimento = app.buscaAtendimento(Integer.parseInt(codigo));
                CodigoTField.setText(String.valueOf(atendimento.getCod()));
                DataTField.setText(atendimento.getData());
                DuracaoTField.setText(String.valueOf(atendimento.getDuracao()));
                StatusTField.setText(atendimento.getStatus().getDescricao());
                CodETField.setText(String.valueOf(atendimento.getCodEquipe()));
                CodiETField.setText(String.valueOf(atendimento.getCodEvento()));
            }
        });
        CadastrarEquipamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaEquipamentos();
            }
        });
        SalvarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.salvarDados();
            }
        });
        CarregarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.carregarDados();
            }
        });
    }

    private void selecionaAtendimento(){
        DefaultComboBoxModel<String> listarAtendimentos = new DefaultComboBoxModel<>();
        if(app.getAtendimentos().isEmpty()) JOptionPane.showMessageDialog(null, "Não há atendimentos cadastrados!");
        for (Atendimento a : app.getAtendimentos()) {
            listarAtendimentos.addElement(String.valueOf(a.getCod()));
        }
        AtendimentoCombo.setModel(listarAtendimentos);
    }

    private void JanelaEquipe() {
        janelaEquipe = new JanelaEquipe(this.app);
    }

    public JPanel getPainel(){
        return panel1;
    }
    public void JanelaEquipamentos(){
        Equipamento eq = new Equipamento();
        new Janela(eq);
    }

    public void JanelaEventos(){
        janelaEvento = new JanelaEvento(this.app);
    }

    public void JanelaAtendimentos(){
        janelaAtendimento = new JanelaCadastroAtendimento(this.app);
    }


    public static void FecharJanela(JFrame janela){
        janela.setVisible(false);
    }
}