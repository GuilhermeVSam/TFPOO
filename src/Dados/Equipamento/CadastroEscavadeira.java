package Dados.Equipamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroEscavadeira {
    private JPanel painel2;
    private JTextField combustivel;
    private JTextField carga;
    private JButton confirma;
    private JButton limparButton;

    private Escavadeira escavadeira;
    private JanelaQuatro janelaQuatroo;
    private     String comb;
    private double carg;
    private CadastroEquipamento cadastroEquipamento;
    public JPanel getPainel2(){
        return painel2;
    }
    public CadastroEscavadeira(Equipamento equipamento, JanelaQuatro janelaQuatro){
        cadastroEquipamento =new CadastroEquipamento();
        this.janelaQuatroo = janelaQuatro;
        if(equipamento instanceof Escavadeira) {
            this.escavadeira = (Escavadeira) equipamento;
            confirma.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        comb=combustivel.getText();
                        carg=Double.parseDouble(carga.getText());
                        escavadeira =new Escavadeira(cadastroEquipamento.getId(), cadastroEquipamento.getNome(), cadastroEquipamento.getCustoDia(),comb,carg);
                        if (comb== null || comb.equals("")) throw new NullPointerException();
                    }catch (NumberFormatException e2){
                        JOptionPane.showMessageDialog(null,"Erro! Digite a carga no formato numérico válido!");
                    }catch (NullPointerException e2){
                        JOptionPane.showMessageDialog(null,"Erro! Digite o nome do combustivel!\n");
                    }
                }
            });
        }
        confirma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaQuatroo.setVisible(false);
            }
        });
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                combustivel.setText("");
                carga.setText("");
            }
        });

    }
    public Escavadeira getEscavadeira(){
        return escavadeira;
    }
}

