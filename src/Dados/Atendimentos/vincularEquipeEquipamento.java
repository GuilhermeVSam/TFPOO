//package Dados.Atendimentos;
//
//import Dados.Equipamento.CadastroEquipamento;
//import Dados.Equipamento.Equipamento;
//import Dados.Equipe.Cadastro;
//import Dados.Equipe.Equipe;
//import Dados.Evento.Eventos.Evento;
//import Janela_Principal.APP;
//import Janela_Principal.GUI;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.Objects;
//
//public class vincularEquipeEquipamento extends JFrame implements ActionListener {
//
//        private JComboBox campoEquipe;
//    private JComboBox campoEquipamento;
//        private JButton Vincular;
//        private JButton voltar;
//        private APP app;
//        private Cadastro cadastro;
//        private CadastroEquipamento cadastroEq;
//        public vincularEquipeEquipamento(APP app) {
//            super();
//            this.app = app;
//            this.setTitle("Vincular Equipamentos a Equipe");
//            JPanel textop = new JPanel(new GridLayout(6, 2));
//            textop.add(new JLabel("Equipes cadastradas:"));
//            campoEquipe = new JComboBox();
//            for (Equipe e : cadastro.getEquipes()) {
//                campoEquipe.addItem(e.getCodinome());
//            }
//            textop.add(campoEquipe);
//            textop.add(new JLabel("Equipamentos cadastrados:"));
//            campoEquipamento = new JComboBox();
//            for (Equipamento eq :cadastroEq.g) {
//                campoEquipe.addItem(e.getCodinome());
//            }
//            textop.add(campoData);
//            textop.add(new JLabel("Duração:"));
//            campoDuracao = new JTextField(30);
//            textop.add(campoDuracao);
//            textop.add(new JLabel("Evento:"));
//            campoEvento = new JComboBox<>();
//            for (Evento evento : app.getEventos()) {
//                campoEvento.addItem(evento.getCodigo());
//            }
//            textop.add(campoEvento);
//
//            cadastrar = new JButton("Cadastrar Atendimento");
//            limpar = new JButton("Limpar");
//            fechar = new JButton("Fechar");
//
//            JPanel botoesPanel = new JPanel();
//            botoesPanel.add(cadastrar);
//            botoesPanel.add(limpar);
//            botoesPanel.add(fechar);
//
//            area = new JTextArea(30, 40);
//            JPanel container = new JPanel();
//            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
//            container.add(textop);
//            container.add(botoesPanel);
//
//            cadastrar.addActionListener(this);
//            limpar.addActionListener(this);
//            fechar.addActionListener(this);
//
//            this.add(container);
//            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
//            this.setResizable(false);
//            this.pack();
//            this.setVisible(true);
//        }
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == fechar) {
//                GUI.FecharJanela(this);
//            }
//            else if (e.getSource() == cadastrar){
//                CadastrarAtendimento();
//            }
//            else if (e.getSource() == limpar) {
//                area.setText("");
//                campoCod.setText("");
//                campoData.setText("");
//                campoDuracao.setText("");
//            }
//        }
//        public void limparCampos() {
//            campoCod.setText("");
//            campoData.setText("");
//            campoDuracao.setText("");
//        }
//        public void CadastrarAtendimento() {
//            try {
//                int cod = Integer.parseInt(campoCod.getText());
//                if (!app.pesquisaCodEventoAtendimento(cod)) {
//                    System.out.println("Erro: O código já está cadastrado!");
//                    return;
//                }
//                String data = campoData.getText();
//                if(app.pesquisaStatus(cod)){
//                    System.out.println("Erro: O evento já possui um atendimento cadastrado!!");
//                    return;
//                }
//                int duracao = Integer.parseInt(campoDuracao.getText());
//                Evento evento = app.buscaPorCodigo(Objects.requireNonNull(campoEvento.getSelectedItem()).toString());
//                Atendimento atendimento = new Atendimento(cod, data, duracao, evento);
//                app.addAtendimento(atendimento);
//            } catch (NumberFormatException ex) {
//                area.setText("Erro ao cadastrar: Os dados são inválidos!");
//            }
//        }
//    }
//}
