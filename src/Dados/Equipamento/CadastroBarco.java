package Dados.Equipamento;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class CadastroBarco extends PlainDocument {
    private JPanel painel2;
    private JTextField quant;
    private JTextArea textArea1;
    private JButton confirma;
    private JButton limparButton;
    private JPanel painel;
    private Barco barco;
    private JanelaTres janelaTres;
    private     int pessoas;
  private Cadastro cadastro;
  private String nomeEq;
    public JPanel getPainel2(){
        return painel2;
    }
    public CadastroBarco(Equipamento equipamento, JanelaTres janelaTres){
        cadastro=new Cadastro();
        this.janelaTres = janelaTres;
        if(equipamento instanceof CaminhaoTanque) {
            this.barco = (Barco) equipamento;
            confirma.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {

                       pessoas=Integer.parseInt(quant.getText());
                       barco=new Barco(cadastro.getId(),cadastro.getNome(),cadastro.getCustoDia(),pessoas);

                    }catch (NumberFormatException e2){
                        textArea1.setText("Erro! Digite a capacidade no formato numérico válido!");
                    }
                }
            });
        }
        confirma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaTres.setVisible(false);
            }
        });
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quant.setText("");
                textArea1.setText("");
            }
        });
    }
    public Barco getBarco(){
        return barco;
    }
}

