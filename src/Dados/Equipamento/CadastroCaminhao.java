package Dados.Equipamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class CadastroCaminhao {
    private JTextField capacidade;
    private JButton limpar;
    private JButton confirma;
    private JPanel painel;
    private JTextArea textArea1;
    private JanelaDois janelaDoiss;
    private CaminhaoTanque caminhaoTanque;
    private Cadastro cadastro;
    private String nomeEq;
    public double cap;


    public JPanel getPainel(){
        return painel;
    }
    public CadastroCaminhao(Equipamento equipamento, JanelaDois janelaDois) {

        cadastro = new Cadastro();
        this.janelaDoiss = janelaDois;
        if (equipamento instanceof CaminhaoTanque) {
            this.caminhaoTanque = (CaminhaoTanque) equipamento;
            confirma.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        cap = Double.parseDouble(capacidade.getText());
                        caminhaoTanque = new CaminhaoTanque(cadastro.getId(), cadastro.getNome(), cadastro.getCustoDia(), Double.parseDouble(capacidade.getText()));

                    } catch (NumberFormatException e2) {
                        confirma.setEnabled(false);
                        textArea1.setText("Erro! Digite a capacidade no formato numérico válido!");

                    }
                }
            });

            limpar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    capacidade.setText("");
                    textArea1.setText("");
                }
            });
        }
        confirma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaDoiss.setVisible(false);
            }
        });
    }
        public CaminhaoTanque getCaminhaoTanque(){
        return caminhaoTanque;
        }

}
