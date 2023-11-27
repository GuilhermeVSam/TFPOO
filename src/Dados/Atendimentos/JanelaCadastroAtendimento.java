package Dados.Atendimentos;
import Dados.Evento.Eventos.Evento;
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
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class JanelaCadastroAtendimento extends JFrame implements ActionListener {
    private JTextField campoCod;
    private JTextField campoData;
    private JTextField campoDuracao;
    private JComboBox campoEvento;
    private JTextField campoEquipe;
    private JButton cadastrar;
    private JTextArea area;
    private JButton fechar;
    private JButton limpar;
    private JScrollPane barra;
    private ArrayList<Atendimento> listaAtendimentos;
    private APP app;

    public JanelaCadastroAtendimento(APP app) {
        super();
        this.app = app;
        this.setTitle("Cadastrar atendimento");
        JPanel textop = new JPanel(new GridLayout(6, 2));
        textop.add(new JLabel("Código do atendimento:"));
        campoCod = new JTextField(30);
        textop.add(campoCod);
        textop.add(new JLabel("Data:"));
        campoData = new JTextField(30);
        textop.add(campoData);
        textop.add(new JLabel("Duração:"));
        campoDuracao = new JTextField(30);
        textop.add(campoDuracao);
        textop.add(new JLabel("Evento:"));
        campoEvento = new JComboBox<>();
        for (Evento evento : app.getEventos()) {
            campoEvento.addItem(evento.getCodigo());
        }
        textop.add(campoEvento);

        cadastrar = new JButton("Cadastrar Atendimento");
        limpar = new JButton("Limpar");
        fechar = new JButton("Fechar");

        JPanel botoesPanel = new JPanel();
        botoesPanel.add(cadastrar);
        botoesPanel.add(limpar);
        botoesPanel.add(fechar);

        area = new JTextArea(30, 40);
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(textop);
        container.add(botoesPanel);

        cadastrar.addActionListener(this);
        limpar.addActionListener(this);
        fechar.addActionListener(this);

        this.add(container);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fechar) {
            GUI.FecharJanela(this);
        }
     else if (e.getSource() == cadastrar){
         try {
             CadastrarAtendimento();
         } catch(Exception exception){
             JOptionPane.showMessageDialog(null, exception.getMessage());
         }
    }
    else if (e.getSource() == limpar) {
            area.setText("");
            campoCod.setText("");
            campoData.setText("");
            campoDuracao.setText("");
        }
    }
    public void limparCampos() {
        campoCod.setText("");
        campoData.setText("");
        campoDuracao.setText("");
    }
    public void CadastrarAtendimento() throws Exception{
        try {
            int cod = Integer.parseInt(campoCod.getText());
            if (!app.pesquisaCodEventoAtendimento(cod)) {
                System.out.println("Erro: O código já está cadastrado!");
                return;
            }
            String data = campoData.getText();
            if(app.pesquisaStatus(cod)){
                System.out.println("Erro: O evento já possui um atendimento cadastrado!!");
                return;
            }
            int duracao = Integer.parseInt(campoDuracao.getText());
            Evento evento = app.buscaPorCodigo(Objects.requireNonNull(campoEvento.getSelectedItem()).toString());
            Atendimento atendimento = new Atendimento(cod, data, duracao, evento);
            app.addAtendimento(atendimento);
            app.alocarAtendimento();
        } catch (NumberFormatException ex) {
            area.setText("Erro ao cadastrar: Os dados são inválidos!");
        }
    }
}