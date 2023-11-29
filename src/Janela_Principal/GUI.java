package Janela_Principal;

import Dados.Atendimentos.AlterarSituacao.AlterarSituacao;
import Dados.Atendimentos.AlterarSituacao.JanelaAlterarSit;
import Dados.Atendimentos.Atendimento;
import Dados.Atendimentos.JanelaCadastroAtendimento;
import Dados.Atendimentos.vincularEquipeEquipamento;
import Dados.Equipamento.CadastroEquipamento;
import Dados.Equipamento.Equipamento;
import Dados.Equipamento.Janela;
import Dados.Equipe.JanelaEquipe;
import Dados.Evento.JanelaEvento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI{
    private APP app;
    Operador op;
    private JanelaEvento janelaEvento;
    private JanelaCadastroAtendimento janelaAtendimento;
    private JanelaEquipe janelaEquipe;
    private CadastroEquipamento cadastroEquipamento;
    private vincularEquipeEquipamento janelaEquipeEquipamento;
    private JanelaAlterarSit janelaAlterarS;
    private JanelaRelatorio janelaRelatorio;
    private JPanel panel1;
    private JButton CadastrarAtendimento;
    private JButton CadastrarEquipe;
    private JButton CadastrarEvento;
    private JButton CadastrarEquipamento;
    private JButton Finalizar;
    private JTextPane textPane1;
    private JButton Atualizar;
    private JComboBox AtendimentoCombo;
    private JTextField CodigoTField;
    private JTextField DataTField;
    private JTextField DuracaoTField;
    private JTextField StatusTField;
    private JTextField CodETField;
    private JTextField CodiETField;
    private JButton CarregarDados;
    private JButton SalvarDados;
    private JButton Detalhe;
    private JLabel Atendimento;
    private JButton Vincularquipamento;
    private JButton Alterar;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel Código;
    private JLabel Data;
    private JLabel Duracao;
    private JLabel Status;
    private JLabel CodEvento;
    private JLabel CodiEquipe;

    public GUI(){

        app = new APP();
        op = new Operador(app);
        Detalhe.setEnabled(false);
        CadastrarEvento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaEventos();
                Detalhe.setEnabled(true);
            }
        });
        CadastrarAtendimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaAtendimentos();
                Detalhe.setEnabled(true);
            }
        });
        CadastrarEquipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaEquipe();
                Detalhe.setEnabled(true);
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
                app.alocarAtendimento();
                ModelSelecionaAtendimento();
                selecionaAtendimento();
            }
        });
        AtendimentoCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selecionaAtendimento();
            }
        });
        CadastrarEquipamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaEquipamentos();
                Detalhe.setEnabled(true);
            }
        });
        SalvarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                op.salvarDados(JOptionPane.showInputDialog("Digite o nome do arquivo: "));
            }
        });
        CarregarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    op.carregarDados(JOptionPane.showInputDialog("Digite o Prefixo dos arquivos: "));
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
        Vincularquipamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaVincularEquip();
            }
        });
        Detalhe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaRelatorio = new JanelaRelatorio(app);
            }
        });
        Alterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(app.getAtendimentos().isEmpty()) JOptionPane.showMessageDialog(null, "Não há atendimentos cadastrados!");
                else JanelaAlterarSituacao();
            }
        });
    }

    private void JanelaAlterarSituacao() {
        janelaAlterarS = new JanelaAlterarSit(this.app);
    }

    public void selecionaAtendimento(){
        String codigo = AtendimentoCombo.getSelectedItem().toString();
        Atendimento atendimento = app.buscaAtendimento(Integer.parseInt(codigo));
        CodigoTField.setText(String.valueOf(atendimento.getCod()));
        DataTField.setText(atendimento.getData());
        DuracaoTField.setText(String.valueOf(atendimento.getDuracao()));
        StatusTField.setText(atendimento.getStatus().getDescricao());
        try {
            CodiETField.setText(String.valueOf(atendimento.getCodEquipe()));
        }catch(NullPointerException exception){
            CodiETField.setText("Equipe Pendente");
        }
        CodETField.setText(String.valueOf(atendimento.getCodEvento()));
    }
    private void ModelSelecionaAtendimento(){
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
        new Janela(eq, this.app);
    }

    public void JanelaEventos(){
        janelaEvento = new JanelaEvento(this.app);
    }
    public void JanelaAtendimentos(){
        janelaAtendimento = new JanelaCadastroAtendimento(this.app);
    }
    public void JanelaVincularEquip(){
        janelaEquipeEquipamento = new vincularEquipeEquipamento(this.app);
    }
    public static void FecharJanela(JFrame janela){
        janela.setVisible(false);
    }
}
