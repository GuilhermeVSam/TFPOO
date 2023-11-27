package Dados.Evento;

import Dados.Atendimentos.Atendimento;
import Dados.Evento.Eventos.Ciclone;
import Dados.Evento.Eventos.Seca;
import Dados.Evento.Eventos.Terremoto;
import Janela_Principal.APP;
import Janela_Principal.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class JanelaEventos {
    private JPanel Painel;
    private JPanel Botao;
    private JPanel CenterMain;
    private JPanel InN;
    private JPanel InC;
    private JButton Cadastrar;
    private JTextField Codigo;
    private JComboBox tipoDeEventoComboBox;
    private JTextField Data;
    private JTextField Latitude;
    private JTextField Longitude;
    private JTextField Velocidade;
    private JTextField Precipitacao;
    private JTextField Estiagem;
    private JTextField Magnitude;
    private JButton Finalizar;
    private JButton limpar;
    private JButton Listar;
    private APP app;

    public JanelaEventos(APP app) {
        this.app = app;
        selecionaTipo();
        Cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigo = Codigo.getText();
                    String data = Data.getText();
                    String STRlatitude = Latitude.getText();
                    double latitude = Double.parseDouble(STRlatitude);
                    String STRlongitude = Longitude.getText();
                    double longitude = Double.parseDouble(STRlongitude);
                    String STRtipo = tipoDeEventoComboBox.getSelectedItem().toString();
                    try {
                        switch (STRtipo) {
                            case "Tipo de Evento" -> {
                                JOptionPane.showMessageDialog(null, "ERRO: Selecione um tipo de evento.");
                            }
                            case "Ciclone" -> {
                                try {
                                    String STRvelocidade = Velocidade.getText();
                                    double velocidade = Double.parseDouble(STRvelocidade);
                                    String STRprecipitacao = Precipitacao.getText();
                                    double precipitacao = Double.parseDouble(STRprecipitacao);
                                    Ciclone evento = new Ciclone(codigo, data, latitude, longitude, velocidade, precipitacao);
                                    app.addEvento(evento);
                                    JOptionPane.showMessageDialog(null, "Evento Cadastrado");
                                } catch(NumberFormatException exception){
                                    JOptionPane.showMessageDialog(null,"ERRO! (Velocidade / Precipitação): Utilize Somente Números Reais \n");
                                }
                            }
                            case "Terremoto" -> {
                                try {
                                    String STRmagnitude = Magnitude.getText();
                                    int magnitude = Integer.parseInt(STRmagnitude);
                                    Terremoto evento = new Terremoto(codigo, data, latitude, longitude, magnitude);
                                    app.addEvento(evento);
                                    JOptionPane.showMessageDialog(null, "Evento Cadastrado");
                                } catch(Exception exception){
                                    JOptionPane.showMessageDialog(null,"ERRO (Magnitude): Utilize somente números reais de 1 à 10. \n");
                                }
                            }
                            case "Seca" -> {
                                try {
                                    String STRestiagem = Estiagem.getText();
                                    int estiagem = Integer.parseInt(STRestiagem);
                                    Seca evento = new Seca(codigo, data, latitude, longitude, estiagem);
                                    app.addEvento(evento);
                                    JOptionPane.showMessageDialog(null, "Evento Cadastrado");
                                } catch(NumberFormatException exception){
                                    JOptionPane.showMessageDialog(null,"ERRO! (Estiagem): Utilize Somente Números Inteiros. \n");
                                }
                            }
                        }
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null,"ERRO! Código Duplicado. \n");
                    }
                } catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(null,"ERRO! (Latitude / Longitude): Utilize somente números. \n");
                }
            }
        });
        tipoDeEventoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                Magnitude.setEnabled(false);
                Velocidade.setEnabled(false);
                Precipitacao.setEnabled(false);
                Estiagem.setEnabled(false);

                String tipo = comboBox.getSelectedItem().toString();
                switch (tipo) {
                    case "Ciclone" -> {
                        Velocidade.setEnabled(true);
                        Precipitacao.setEnabled(true);
                    }
                    case "Terremoto" -> Magnitude.setEnabled(true);
                    case "Seca" -> Estiagem.setEnabled(true);
                }
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
                JOptionPane.showMessageDialog(null,app.listarEventos() + "\n");}
        });
        Finalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.FecharJanela((JFrame) SwingUtilities.getWindowAncestor(Painel));
            }
        });
    }

    private void selecionaTipo(){
        DefaultComboBoxModel<String> tipoEvento = new DefaultComboBoxModel<>();
        tipoEvento.addElement("Tipo de Evento");
        tipoEvento.addElement("Terremoto");
        tipoEvento.addElement("Ciclone");
        tipoEvento.addElement("Seca");
        tipoDeEventoComboBox.setModel(tipoEvento);
    }

    public void clear(){
        Codigo.setText("");
        Data.setText("");
        Latitude.setText("");
        Longitude.setText("");
        Velocidade.setText("");
        Precipitacao.setText("");
        Magnitude.setText("");
        Estiagem.setText("");
    }

    public JPanel getPainel(){
        return Painel;
    }
}
