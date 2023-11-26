package Janela_Principal;

import Dados.Equipe.JanelaCadastroAtendimento;
import Dados.Evento.Janela;

import java.util.Scanner;

public class GUI {
    private APP app = new APP();
    private Janela janela;

    public GUI(){
        Janela();
        Scanner sc = new Scanner(System.in);
        sc.next();
        JanelaCadastroAtendimento janelaAtendimento = new JanelaCadastroAtendimento(this.app);
        System.out.println(app.getEventos());
    }

    public void Janela(){
        janela = new Janela(this.app);
    }
}
