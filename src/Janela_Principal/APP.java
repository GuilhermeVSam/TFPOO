package Janela_Principal;

import Dados.Atendimentos.Atendimento;
import Dados.Atendimentos.ListaAtendimentos;
import Dados.Equipe.Cadastro;
import Dados.Equipe.Equipe;
import Dados.Evento.Eventos.*;
import Dados.Evento.JanelaEventos;
import Dados.Equipamento.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class APP {
    private ListaEventos listaEventos;
    private ListaAtendimentos listaAtendimentos;
    private Cadastro listaEquipes;
    private ListaEquipamento listaEquipamentos;

    public APP(){
        listaEventos = new ListaEventos();
        listaAtendimentos = new ListaAtendimentos();
        listaEquipes = new Cadastro();
        listaEquipamentos=new ListaEquipamento();
    }

    public void addEvento(Evento e) throws Exception {
        Exception InvalidCode = new Exception("Código Inválido");
        if(!listaEventos.addEvento(e)) throw InvalidCode;
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
        return listaAtendimentos.consultarAtendimentos();
    }

    public Evento buscaPorCodigo(String codigo){
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
        listaAtendimentos.addAtendimento(atendimento);
    }

    public Atendimento buscaAtendimento(int cod){
        return listaAtendimentos.buscaAtendimento(cod);
    }

    public boolean pesquisaStatus(int codi){
        return listaAtendimentos.pesquisaStatus(codi);
    }

    public boolean pesquisaCodEventoAtendimento(int cod){
        return listaAtendimentos.pesquisaCodEvento(cod) != -1;
    }


    public void alocarAtendimento(){
        listaAtendimentos.AlocarAtendimentos(listaEquipes);
    }

    public boolean addEquipe(Equipe equipe){
        return listaEquipes.addEquipe(equipe);
    }

    public ArrayList<Atendimento> getAtendimentos() {
        return listaAtendimentos.getListaAtendimentos();
    }

    public Equipe buscaPorCodinome(String codinomeEquipe) {
        return listaEquipes.buscaPorCodinome(codinomeEquipe);
    }
}