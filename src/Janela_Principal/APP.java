package Janela_Principal;

import Dados.Atendimentos.AlterarSituacao.FilaAtendimentos;
import Dados.Atendimentos.Atendimento;
import Dados.Equipe.Cadastro;
import Dados.Equipe.Equipe;
import Dados.Evento.Eventos.*;
import Dados.Equipamento.*;

import java.util.ArrayList;
import java.util.Queue;

public class APP {
    private ListaEventos listaEventos;
    private FilaAtendimentos filaAtendimentos;
    private Cadastro listaEquipes;
    private ListaEquipamento listaEquipamentos;
    private ArrayList listaEquipesDisponiveis;

    public APP(){
        listaEventos = new ListaEventos();
        filaAtendimentos = new FilaAtendimentos();
        listaEquipes = new Cadastro();
        listaEquipamentos=new ListaEquipamento();
    }

    public void addEvento(Evento e) throws Exception {
        if(!listaEventos.addEvento(e)) throw new Exception("ERRO (Código): Código Já Utilizado");
    }

    public String listarEventos() {
        if (listaEventos == null) {
            return "Nenhum evento cadastrado!";
        } else {
            return listaEventos.toString();
        }
    }
    public void addEquipamento(Equipamento e){
        listaEquipamentos.addEquipamento(e);
    }
    public String eqDescricao() {
        if (listaEventos == null) {
            return "Nenhuma equipe cadastrada!";
        } else {
            return listaEquipes.toString();
        }
    }

    public String listarEquipamentos() {
        if (listaEventos == null) {
            return "Nenhum evento cadastrado!";
        } else {
            return listaEquipamentos.toString();
        }
    }
    public ArrayList<Equipamento> getEquipamento(){
        return listaEquipamentos.getEquipamentos();
    }
    public String listarAtendimentos(){
        return filaAtendimentos.consultarAtendimentos();
    }

    public String getTodosAtendimentos(){
        return filaAtendimentos.listarTodos();
    }

    public Queue<Atendimento> getTodasFilas(){
        return filaAtendimentos.getTodasFilas();
    }

    public Evento buscaEvento(String codigo){
        return listaEventos.buscaCodigo(codigo);
    }

    public ArrayList<Evento> getEventos(){
        return listaEventos.getEventos();
    }
    public ArrayList<Equipe> getEquipes(){
        return listaEquipes.getEquipes();
    }
    public ArrayList<Equipamento> getEquipamentos(){return listaEquipamentos.getEquipamentos();}

    public void addAtendimento(Atendimento atendimento) throws Exception{
        filaAtendimentos.add(atendimento);
    }

    public Atendimento buscaAtendimento(int cod){
        return filaAtendimentos.buscaAtendimento(cod);
    }

    public boolean pesquisaStatus(int codi){
        return filaAtendimentos.pesquisaStatus(codi);
    }

    public boolean pesquisaCodEventoAtendimento(String cod){
        return filaAtendimentos.pesquisaCodEvento(cod) == null;
    }


    public void alocarAtendimento(){
        listaEquipesDisponiveis = listaEquipes.getEquipesDisponiveis();
        filaAtendimentos.AlocarAtendimentos(listaEquipesDisponiveis);
    }

    public boolean addEquipe(Equipe equipe){
        return listaEquipes.addEquipe(equipe);
    }

    public Queue<Atendimento> getAtendimentos() {
        return filaAtendimentos.getLista();
    }

    public Equipe buscaEquipe(String codinomeEquipe) {
        return listaEquipes.buscaPorCodinome(codinomeEquipe);
    }

    public Equipamento buscaEquipamento(String id){
        return listaEquipamentos.buscaEquipamento(id);
    }
}