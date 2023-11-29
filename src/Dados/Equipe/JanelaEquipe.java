package Dados.Equipe;

import Dados.Equipamento.Equipamento;
import Janela_Principal.APP;
import Janela_Principal.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class JanelaEquipe extends JFrame implements ActionListener {
    private APP app;
    private JTextField campoCodinome;
    private JTextField campoQuantidade;
    private JTextField campoLatitude;
    private JTextField campoLongitude;
    private JButton cadastrar;
    private JButton listar;
    private JTextArea area;
    private JButton fechar;
    private JButton limpar;
    private Cadastro cadastro;
    private JScrollPane barra;
    public JanelaEquipe(APP app) {
        super();
        this.app = app;
        this.setTitle("Cadastrar equipe");
        this.setSize(1800, 1800);
        cadastro = new Cadastro();
        JPanel textop = new JPanel(new GridLayout(4, 2));
        textop.add(new JLabel("Codinome:"));
        campoCodinome = new JTextField(30);
        textop.add(campoCodinome);
        textop.add(new JLabel("Quantidade:"));
        campoQuantidade = new JTextField(30);
        textop.add(campoQuantidade);
        textop.add(new JLabel("Latitude:"));
        campoLatitude = new JTextField(30);
        textop.add(campoLatitude);
        textop.add(new JLabel("Longitude:"));
        campoLongitude = new JTextField(30);
        textop.add(campoLongitude);
        cadastrar = new JButton("Cadastrar equipe");
        limpar = new JButton("Limpar");
        listar = new JButton("Listar equipes cadastradas");
        fechar = new JButton("Fechar");
        JPanel botoesPanel = new JPanel();
        botoesPanel.add(cadastrar);
        botoesPanel.add(limpar);
        botoesPanel.add(listar);
        botoesPanel.add(fechar);
        barra = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar vertical = barra.getVerticalScrollBar();
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(textop);
        container.add(botoesPanel);
        container.add(barra);
        cadastrar.addActionListener(this);
        limpar.addActionListener(this);
        listar.addActionListener(this);
        fechar.addActionListener(this);
        this.add(container);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cadastrar){
            CadastrarEquipe();
        }
        else if(e.getSource()==fechar){
            GUI.FecharJanela(this);
        }
        else if(e.getSource()==listar){
            ListarEquipes();
        }
        else if(e.getSource()==limpar){
            campoCodinome.setText("");
            campoQuantidade.setText("");
            campoLatitude.setText("");
            campoLongitude.setText("");
        }
    }
    public void CadastrarEquipe() {
      try {
            String codinome = campoCodinome.getText();
            if(codinome.isEmpty()){
                JOptionPane.showMessageDialog(null, "O codinome não pode estar vazio");
            }
            else{
                 int quantidade = Integer.parseInt(campoQuantidade.getText());
                 double longitude = Double.parseDouble(campoLongitude.getText());
                 double latitude = Double.parseDouble(campoLatitude.getText());
                Equipamento equipamento = null;
                Equipe eq = new Equipe(codinome, quantidade, longitude, latitude);
                 if (app.addEquipe(eq)) {
                     JOptionPane.showMessageDialog(null, "Equipe cadastrada com sucesso!");
                 }
                 else {
                     JOptionPane.showMessageDialog(null, "Equipe com esse codinome já existe.");
                 }
               }
       }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar: Os dados são inválidos!");
      }
    }
    public void limparCampos(){
        campoCodinome.setText("");
        campoQuantidade.setText("");
        campoLatitude.setText("");
        campoLongitude.setText("");
    }
    public void ListarEquipes() {
        ArrayList<Equipe> equipes = app.getEquipes();
        if (equipes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma equipe foi cadastrada!");
        } else{
            JOptionPane.showMessageDialog(null, app.eqDescricao());
        }
    }
}
