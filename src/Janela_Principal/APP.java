package Janela_Principal;

import Dados.Atendimentos.Atendimento;
import Dados.Atendimentos.ListaAtendimentos;
import Dados.Equipe.Cadastro;
import Dados.Equipe.Equipe;
import Dados.Evento.Eventos.*;
import Dados.Evento.JanelaEventos;

import java.util.ArrayList;

public class APP {
    private ListaEventos listaEventos;
    private ListaAtendimentos listaAtendimentos;
    private Cadastro listaEquipes;
    private JanelaEventos form;

    public APP(){
        listaEventos = new ListaEventos();
        listaAtendimentos = new ListaAtendimentos();
        listaEquipes = new Cadastro();
    }

    public void addEvento(Evento e) throws Exception {
        Exception InvalidCode = new Exception("Código Inválido");
        if(!listaEventos.addEvento(e)) throw InvalidCode;
    }

    public String listar(){
        if(listaEventos.toString().isEmpty()){
            return "Não há nenhum evento cadastrado";
        }
        return listaEventos.toString();
    }

    public ArrayList<Evento> getEventos(){
        return listaEventos.getEventos();
    }

    public ArrayList<Equipe> listarDisponiveis(Evento evento) {
        ArrayList<Equipe> disponiveis = new ArrayList<>();
        for (Equipe e : listaEquipes.getEquipes()) {
            if (listaAtendimentos.calculaDistancia(e, evento) < 5000) {
                disponiveis.add(e);
            }
        }
        return disponiveis;
    }

    public void addAtendimento(Atendimento atendimento){
        listaAtendimentos.addAtendimento(atendimento);
    }

    public boolean pesquisaStatus(int codi){
        return listaAtendimentos.pesquisaStatus(codi);
    }

    public boolean pesquisaCodEvento(int cod){
        return listaAtendimentos.pesquisaCodEvento(cod) != -1;
    }

    public void alocarAtendimento(){
        listaAtendimentos.AlocarAtendimentos(listaEquipes);
    }
}
