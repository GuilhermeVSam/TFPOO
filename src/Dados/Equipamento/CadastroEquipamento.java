package Dados.Equipamento;

import Janela_Principal.APP;
import Janela_Principal.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CadastroEquipamento {
    private JTextField id;
    private JButton finalizarButton;
    private JPanel CadastroEqui;
    private JPanel Painel;
    private JPanel Botao;
    private JButton Cadastrar;
    private JButton Listar;
    private JButton limpar;
    private JButton Finalizar;
    private JPanel CenterMain;
    private JPanel InN;
    private JPanel InC;
    private JTextField nome;
    private JTextField cargaHumana;
    private JTextField capacidadeAgua;
    private JComboBox tipoDeEquipamentoComboBox;
    private JTextField CargaDeslocamento;
    private JComboBox Combustivel;
    private JTextField custo;
    private ArrayList<Equipamento> equip;
    private Equipamento e;
    private APP ap;
    private Janela janela = new Janela();
    private ListaEquipamento list;
    private GUI gui;
    public String nomeEquip;
    public CadastroEquipamento(){

    }



    public CadastroEquipamento(Equipamento equipament, Janela janela, APP app) {
        list=new ListaEquipamento();
        equip=new ArrayList<>();
        this.e=equipament;
        this.janela=janela;
        this.ap=app;
        selecionaTipo();
        selecionaTipo2();
        Cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int i = Integer.parseInt(id.getText());
                    String n = nome.getText();
                    double c = Double.parseDouble(custo.getText());
                    String STRtipo = tipoDeEquipamentoComboBox.getSelectedItem().toString();
                    try {
                        switch (STRtipo) {
                            case "Tipo de Equipamento" -> {
                                JOptionPane.showMessageDialog(null, "ERRO: Selecione um tipo de equipamneto.");
                            }
                            case "Caminhão Tanque" -> {
                                try {
                                     double cap = Double.parseDouble(capacidadeAgua.getText());
                                     CaminhaoTanque caminhaoTanque=new CaminhaoTanque(i,n,c,cap);
                                     ap.addEquipamento(caminhaoTanque);
                                    JOptionPane.showMessageDialog(null, "Equipamento Cadastrado");
                                } catch(NumberFormatException exception){
                                    JOptionPane.showMessageDialog(null,"ERRO! (Capacidade): Utilize Somente Números Reais \n");
                                }
                            }
                            case "Escavadeira" -> {
                                String STRCombustivel = Combustivel.getSelectedItem().toString();
                                switch (STRCombustivel) {
                                    case "Tipo de Combústivel" -> {
                                        JOptionPane.showMessageDialog(null, "ERRO: Selecione um tipo de Combústivel.");
                                    }
                                    case "Diesel" -> {
                                        try {
                                            String comb = "Diesel";
                                            double cargaDesloc = Double.parseDouble(CargaDeslocamento.getText());
                                            Escavadeira escavadeira = new Escavadeira(i, n, c, comb, cargaDesloc);
                                            ap.addEquipamento(escavadeira);
                                            JOptionPane.showMessageDialog(null, "Equipamento Cadastrado");
                                        } catch (NumberFormatException e1) {
                                            JOptionPane.showMessageDialog(null, "ERRO! (Carga de Deslocamento): Utilize Somente Números Reais \n");
                                        }
                                    }
                                    case "Gasolina" -> {
                                        try {
                                            String comb = "Gasolina";
                                            double cargaDesloc = Double.parseDouble(CargaDeslocamento.getText());
                                            Escavadeira escavadeira = new Escavadeira(i, n, c, comb, cargaDesloc);
                                            ap.addEquipamento(escavadeira);
                                            JOptionPane.showMessageDialog(null, "Equipamento Cadastrado");
                                        } catch (NumberFormatException e1) {
                                            JOptionPane.showMessageDialog(null, "ERRO! (Carga de Deslocamento): Utilize Somente Números Reais \n");
                                        }
                                    }
                                    case "Alcool" -> {
                                        try {
                                            String comb = "Alcool";
                                            double cargaDesloc = Double.parseDouble(CargaDeslocamento.getText());
                                            Escavadeira escavadeira = new Escavadeira(i, n, c, comb, cargaDesloc);
                                            ap.addEquipamento(escavadeira);
                                            JOptionPane.showMessageDialog(null, "Equipamento Cadastrado");
                                        } catch (NumberFormatException e1) {
                                            JOptionPane.showMessageDialog(null, "ERRO! (Carga de Deslocamento): Utilize Somente Números Reais \n");
                                        }
                                    }
                                }
                            }
                            case "Barco" -> {
                                try {
                                    int carg = Integer.parseInt(cargaHumana.getText());
                                    Barco barco = new Barco(i,n,c,carg);
                                    ap.addEquipamento(barco);
                                    JOptionPane.showMessageDialog(null, "Equipamento Cadastrado");
                                } catch(Exception exception){
                                    JOptionPane.showMessageDialog(null,"ERRO (Carga): Utilize somente números inteiros. \n");
                                }
                            }
                        }
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null,"ERRO! Id Duplicado. \n");
                    }
                } catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(null,"ERRO! (Custo por Dia): Utilize somente números reais. \n");
                }
            }
        });
        tipoDeEquipamentoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                capacidadeAgua.setEnabled(false);
                cargaHumana.setEnabled(false);
                Combustivel.setEnabled(false);
                CargaDeslocamento.setEnabled(false);

                String tipo = comboBox.getSelectedItem().toString();
                switch (tipo) {
                    case "Escavadeira" -> {
                        Combustivel.setEnabled(true);
                        CargaDeslocamento.setEnabled(true);
                    }
                    case "Barco" -> cargaHumana.setEnabled(true);
                    case "Caminhão Tanque" -> capacidadeAgua.setEnabled(true);
                }
                Cadastrar.setEnabled(true);
            }
        });
        limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        Listar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, app.listarEquipamentos());}


        });
        Finalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.FecharJanela((JFrame) SwingUtilities.getWindowAncestor(Painel));
            }
        });
    }

    private void selecionaTipo(){
        DefaultComboBoxModel<String> tipoEquipamento = new DefaultComboBoxModel<>();
        tipoEquipamento.addElement("Tipo de Equipamento");
        tipoEquipamento.addElement("Barco");
        tipoEquipamento.addElement("Caminhão Tanque");
        tipoEquipamento.addElement("Escavadeira");
        tipoDeEquipamentoComboBox.setModel(tipoEquipamento);
    }
    private void selecionaTipo2(){
        DefaultComboBoxModel<String> tipoCombustivel = new DefaultComboBoxModel<>();
        tipoCombustivel.addElement("Tipo de Combústivel");
        tipoCombustivel.addElement("Diesel");
        tipoCombustivel.addElement("Gasolina");
        tipoCombustivel.addElement("Alcool");
        Combustivel.setModel(tipoCombustivel);
    }

    public void clear(){
        id.setText("");
        nome.setText("");
        custo.setText("");
        capacidadeAgua.setText("");
        cargaHumana.setText("");
        CargaDeslocamento.setText("");
    }

    public JPanel getPainel(){
        return Painel;
    }

}

