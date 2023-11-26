package Janela_Principal;

import Dados.Atendimentos.JanelaCadastroAtendimento;
import Dados.Evento.Janela;

import java.util.Scanner;

public class GUI {
    private APP app = new APP();
    private Janela janela;
    private JanelaCadastroAtendimento janelaAtendimento;

    public GUI(){
        JanelaEventos();
        Scanner sc = new Scanner(System.in);
        sc.next();
        JanelaAtendimentos();
    }

    public void JanelaEventos(){
        janela = new Janela(this.app);
    }

    public void JanelaAtendimentos(){
        janelaAtendimento = new JanelaCadastroAtendimento(this.app);
    }
}