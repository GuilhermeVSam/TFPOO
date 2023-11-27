package Dados.Equipe;
import Dados.Atendimentos.Atendimento;
import Dados.Evento.Eventos.Ciclone;
import Dados.Evento.Eventos.Evento;
import Dados.Evento.Eventos.Seca;
import Dados.Evento.Eventos.Terremoto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Cadastro {
    private ArrayList<Equipe> equipes;
    public Cadastro() {
        equipes = new ArrayList<>();
    }
    public boolean addEquipe(Equipe equipe) {
        if (buscarNome(equipe.getCodinome()) != null) {
            return false;
        }
        equipes.add(equipe);
        Comparator<Equipe> comparador = Comparator.comparing(Equipe::getCodinome);
        Collections.sort(equipes, comparador);
        return true;
    }
    public String buscarNome(String codinome) {
        int i;
        for (i = 0; i < equipes.size(); i++) {
            Equipe eq = equipes.get(i);
            if (eq.getCodinome().equalsIgnoreCase(codinome)) {
                return codinome;
            }
        }
        return null;
    }

    public ArrayList<Equipe> getEquipes() {
        return equipes;
    }

    public String descricao() {
        String descricao = "";
        for (int i = 0; i < equipes.size(); i++)  {
            Equipe eq = equipes.get(i);
            descricao += eq.getLatitude() + eq.getCodinome();
        }
        return descricao;
    }
    public ArrayList<Equipe> clonarEquipes() {
        ArrayList<Equipe> cloneEquipes = new ArrayList<>(equipes);
        return cloneEquipes;
    }
}