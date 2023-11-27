package Dados.Equipamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private Janela janela=new Janela();

    public int i;
    public double c;
    public String n;
    public String nomeEquip;
    public CadastroEquipamento(){}



    public CadastroEquipamento(Equipamento equipament, Janela janela) {

        equip=new ArrayList<>();
            this.janela=janela;
        this.equipamento=equipament;

        confirma.setEnabled(false);
        textArea1.setFont(new Font("Serif", Font.ITALIC, 12));
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);

        confirma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    textArea1.setText("");
                  i=Integer.parseInt(id.getText());
                    n=nome.getText();
                    c=Double.parseDouble(custododia.getText());
                    if (n== null || n.equals("")) throw new NullPointerException();
                    equipamento=new Equipamento(i,n,c);
                    equip.add(equipamento);
                    textArea1.setText("Equipamento Cadastrado!");
                }catch(NullPointerException e2 ){
                   textArea1.append("Erro! Digite o nome do equipameto!\n");
               } catch (NumberFormatException e2) {
                    textArea1.append("Erro! Digite no formato numérico válido!\n");
                }catch (Exception e2){
                    textArea1.append("Erro! Insira os dados corretamente!\n");
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
                if(equip.isEmpty()){
                    textArea1.setText("Nenhum equipamento cadastrado!");
                }
                else {
                    textArea1.setText("");
                    textArea1.append("Id: ");
                    textArea1.append(String.valueOf(equip.get(0).getId()));
                    textArea1.append("\nNome: ");
                    textArea1.append(equip.get(0).getNome());
                    textArea1.append("\nCusto do dia: ");
                    textArea1.append(String.valueOf(equip.get(0).getCustoDia()));
                    for (int it =1; it<equip.size();it++){
                        textArea1.append("\nId: ");
                        textArea1.append(String.valueOf(equip.get(it).getId()));
                        textArea1.append("\nNome: ");
                        textArea1.append(equip.get(it).getNome());
                        textArea1.append("\nCusto do dia: ");
                        textArea1.append(String.valueOf(equip.get(it).getCustoDia()));
                    }
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
