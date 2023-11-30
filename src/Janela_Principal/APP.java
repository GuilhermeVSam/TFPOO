package Janela_Principal;

import Dados.Atendimentos.FilaAtendimentos;
import Dados.Atendimentos.Atendimento;
import Dados.Atendimentos.STATUS;
import Dados.Equipe.ListaEquipes;
import Dados.Equipe.Equipe;
import Dados.Evento.Eventos.*;
import Dados.Equipamento.*;

import java.util.ArrayList;

public class APP {
    private ListaEventos listaEventos;
    private FilaAtendimentos filaAtendimentos;
    private ListaEquipes listaEquipes;
    private ListaEquipamento listaEquipamentos;

    public APP(){
        listaEventos = new ListaEventos();
        filaAtendimentos = new FilaAtendimentos();
        listaEquipes = new ListaEquipes();
        listaEquipamentos=new ListaEquipamento();
    }


    //Evento
    public void addEvento(Evento e) throws Exception {
        if(!listaEventos.addEvento(e)) throw new Exception("ERRO (Código): Código Já Utilizado");
    }
    public String listarEventos() throws Exception {
        if (listaEventos.getEventos().isEmpty()) {
            throw new Exception("Nenhum evento cadastrado!");
        } else {
            return listaEventos.toString();
        }
    }
    public ArrayList<Evento> getEventos(){
        return listaEventos.getEventos();
    }
    public Evento buscaEvento(String codigo){
        return listaEventos.buscaCodigo(codigo);
    }





    //Equipamento
    public void addEquipamento(Equipamento e){
        listaEquipamentos.addEquipamento(e);
    }
    public ArrayList<Equipamento> getEquipamentos(){return listaEquipamentos.getEquipamentos();}
    public Equipamento buscaEquipamento(String id){
        return listaEquipamentos.buscaEquipamento(id);
    }
    public String listarEquipamentos() throws Exception {
        if (listaEquipamentos.getEquipamentos().isEmpty()) {
            throw new Exception("Nenhum equipamento cadastrado!");
        } else {
            return listaEquipamentos.toString();
        }
    }
    public ArrayList<Equipamento> getEquipamento(){
        return listaEquipamentos.getEquipamentos();
    }
    public void vincularEquipamentoEquipe(){
        listaEquipes.vincularEquipamento(listaEquipamentos.getEquipamentos());
    }





    //Equipe
    public boolean addEquipe(Equipe equipe){
        return listaEquipes.addEquipe(equipe);
    }
    public ArrayList<Equipe> getEquipes(){
        return listaEquipes.getEquipes();
    }
    public String listarEquipes() throws Exception {
        if (listaEquipes.getEquipes().isEmpty()) {
            throw new Exception("Nenhuma equipe cadastrada!");
        } else {
            return listaEquipes.toString();
        }
    }
    public Equipe buscaEquipe(String codinomeEquipe) {
        return listaEquipes.buscaPorCodinome(codinomeEquipe);
    }
    public String listarAtendimentos() throws Exception {
        if(filaAtendimentos.getTodasFilas().isEmpty()){
            throw new Exception("Nenhum atendimento cadastrado!");
        } else {
            return filaAtendimentos.listarTodos();
        }
    }
    public ArrayList<Atendimento> getTodasFilas(){
        return filaAtendimentos.getTodasFilas();
    }







    //Atendimento
    public void addAtendimento(Atendimento atendimento) throws Exception{
        filaAtendimentos.add(atendimento);
    }
    public void addAtendimento(Atendimento atendimento, STATUS status){
        String statusAtendimento = status.getDescricao().toUpperCase();
        switch(statusAtendimento){
            case "PENDENTE" -> {
                filaAtendimentos.add(atendimento);
            }
            case "CANCELADO" -> {
                filaAtendimentos.addCancelado(atendimento);
            }
            case "EXECUTANDO" -> {
                filaAtendimentos.addExecutando(atendimento);
            }
            case "FINALIZADO" -> {
                filaAtendimentos.addFinalizado(atendimento);
            }
        }
    }
    public Atendimento buscaAtendimento(int cod){
        return filaAtendimentos.buscaAtendimento(cod);
    }
    public boolean pesquisaStatus(int codi){
        return filaAtendimentos.pesquisaStatus(codi);
    }
    public void alocarAtendimento(){
        filaAtendimentos.AlocarAtendimentos(listaEquipes.getEquipes());
    }
}