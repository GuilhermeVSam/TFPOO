package Dados.Equipamento;

import Janela_Principal.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;

public class CadastroEquipamento {
    private JButton confirma;
    private JTextField id;
    private JTextField nome;
    private JTextField custododia;
    private JTextArea textArea1;
    private JButton limparButton;
    private JButton mostrarDadosButton;
    private JButton finalizarButton;
    private JPanel CadastroEqui;
    private JButton caminhãoTanqueButton;
    private JButton barcoButton;
    private JButton escavadeiraButton;
    private Equipamento equipamento;
    private ArrayList<Equipamento> equip;
    private CadastroCaminhao cadastroCaminhao;
    private Equipamento e;
    private Janela janela = new Janela();

    public int i;
    public double c;
    public String n;
    public String nomeEquip;
    public CadastroEquipamento(){
        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI.FecharJanela((JFrame) SwingUtilities.getWindowAncestor(CadastroEqui));
            }
        });
    }



    public CadastroEquipamento(Equipamento equipament, Janela janela) {

        equip=new ArrayList<>();
            this.janela=janela;
        this.equipamento=equipament;

        confirma.setEnabled(false);

        confirma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    i=Integer.parseInt(id.getText());
                    n=nome.getText();
                    c=Double.parseDouble(custododia.getText());
                    if (n== null || n.equals("")) throw new NullPointerException();
                    equipamento=new Equipamento(i,n,c);
                    equip.add(equipamento);
                    JOptionPane.showMessageDialog(null, "Equipamento Cadastrado!");
                } catch (NullPointerException e2) {
                    JOptionPane.showMessageDialog(null, "Erro! Digite o nome do equipamento!");
                } catch (NumberFormatException e2) {
                    JOptionPane.showMessageDialog(null, "Erro! Digite no formato numérico válido!");
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Erro! Insira os dados corretamente!");
                }
                confirma.setEnabled(false);
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id.setText("");
                nome.setText("");
                custododia.setText("");
                textArea1.setText("");
            }
        });
        caminhãoTanqueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JanelaDois janelaDois=new JanelaDois(equipamento);
                cadastroCaminhao=new CadastroCaminhao(equipamento, janelaDois);
                confirma.setEnabled(true);
            }
        });
        barcoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JanelaTres janelaTres=new JanelaTres(equipamento);
                confirma.setEnabled(true);

            }
        });
        escavadeiraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JanelaQuatro janelaQuatro=new JanelaQuatro(equipamento);
                confirma.setEnabled(true);
            }
        });

        mostrarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (equip.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum equipamento cadastrado!");
                } else {
                    StringBuilder dados = new StringBuilder();
                    for (Equipamento equipamento : equip) {
                        dados.append("Id: ").append(equipamento.getId()).append("\n");
                        dados.append("Nome: ").append(equipamento.getNome()).append("\n");
                        dados.append("Custo do dia: ").append(equipamento.getCustoDia()).append("\n\n");
                    }
                    JOptionPane.showMessageDialog(null, dados.toString());
                }
            }
        });
    }
    public int getId(){
      return i;
    }
    public String getNome(){

        return n;
    }
    public  double getCustoDia(){
        return c;
    }
    public String getNomeEquip(){
        return nomeEquip;
    }
    public JPanel getCadastroEquil(){
        return CadastroEqui;
    }
    public JTextArea textArea1(){
        return textArea1;
    }

}
