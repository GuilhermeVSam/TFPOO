package Dados.Equipe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
    public class JanelaCadastroAtendimento extends JFrame implements ActionListener {
        private JTextField campoCod;
        private JTextField campoData;
        private JTextField campoDuracao;
        private JComboBox campoEvento;
        private JTextField campoStatus;
        private JTextField campoEquipe;
        private JButton cadastrar;
        private JTextArea area;
        private JButton fechar;
        private JButton limpar;
        private JScrollPane barra;
        public JanelaCadastroAtendimento() {
            super();
            this.setTitle("Cadastrar equipe");
            this.setSize(1800, 1800);
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
            textop.add(campoEvento);
            textop.add(new JLabel("Status do atendimento:"));
            campoStatus = new JTextField(30);
            textop.add(campoStatus);
            textop.add(new JLabel("Equipe:"));
            campoEquipe = new JTextField(30);
            textop.add(campoEquipe);

            cadastrar = new JButton("Cadastrar Atendimento");
            limpar = new JButton("Limpar");
            fechar = new JButton("Fechar");

            JPanel botoesPanel = new JPanel();
            botoesPanel.add(cadastrar);
            botoesPanel.add(limpar);
            botoesPanel.add(fechar);

            area = new JTextArea(30, 40);
            barra = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            JScrollBar vertical = barra.getVerticalScrollBar();
            JPanel container = new JPanel();
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
            container.add(textop);
            container.add(botoesPanel);
            container.add(barra);

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
            if(e.getSource()==fechar){
                System.exit(0);
            }
            else if(e.getSource()==limpar){
                area.setText("");
                campoCod.setText("");
                campoData.setText("");
                campoDuracao.setText("");
            }
        }
        public void limparCampos(){
            campoCod.setText("");
            campoData.setText("");
            campoDuracao.setText("");
        }}

