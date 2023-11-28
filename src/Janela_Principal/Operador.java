package Janela_Principal;

import Dados.Atendimentos.Atendimento;
import Dados.Equipe.Equipe;
import Dados.Evento.Eventos.Ciclone;
import Dados.Evento.Eventos.Evento;
import Dados.Evento.Eventos.Seca;
import Dados.Evento.Eventos.Terremoto;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Operador {
    private APP app;

    public Operador(APP app){
        this.app = app;
    }

    public void carregarDados(String nomeArquivo){
        carregarEventos(nomeArquivo);
        carregarEquipes(nomeArquivo);
        carregarEquipamentos(nomeArquivo);
        carregarAtendimentos(nomeArquivo);
    }

    public void salvarDados(String nomeArquivo){
        salvarDadosEvento(nomeArquivo);
        salvarDadosEquipe(nomeArquivo);
        salvarDadosAtendimentos(nomeArquivo);
    }

    public void salvarDadosEvento(String nomeArquivo){
        ArrayList<Evento> listaEventos = app.getEventos();
        Path path1 = Paths.get(nomeArquivo + "-Evento.csv");
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path1, Charset.defaultCharset()))) {
            for (Evento e : listaEventos) {
                writer.print(e.getCodigo() + ";");
                writer.print(e.getData() + ";");
                writer.print(e.getLatitude() + ";");
                writer.print(e.getLongitude() + ";");
                if(e instanceof Ciclone){
                    writer.print("Ciclone" + ";");
                    writer.print(((Ciclone) e).getVelocidade() + ";");
                    writer.print(((Ciclone) e).getPrecipitacao() + ";");
                } else if(e instanceof Terremoto){
                    writer.print("Terremoto" + ";");
                    writer.print(((Terremoto) e).getMagnitude() + ";");
                } else{
                    writer.print("Seca" + ";");
                    writer.print(((Seca) e).getEstiagem() + ";");
                }
                writer.print("\n");
            }
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }

    public void salvarDadosEquipe(String nomeArquivo){
        ArrayList<Equipe> equipes = app.getEquipes();
        Path path1 = Paths.get(nomeArquivo + "-Equipe.csv");
        try (PrintWriter writer = new PrintWriter(
                Files.newBufferedWriter(path1, Charset.defaultCharset()))) {
            for (Equipe e : equipes) {
                writer.print(e.getCodinome() + ";");
                writer.print(e.getQuantidade() + ";");
                writer.print(e.getLatitude() + ";");
                writer.print(e.getLongitude() + ";");
                writer.print("\n");
            }
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }

    public void salvarDadosAtendimentos(String nomeArquivo){
        ArrayList<Atendimento> listaAtendimentos = app.getAtendimentos();
        Path path = Paths.get(nomeArquivo + "-Atendimento.csv");
        try (PrintWriter writer = new PrintWriter(
                Files.newBufferedWriter(path, Charset.defaultCharset()))) {
            for (Atendimento a : listaAtendimentos) {
                writer.print(a.getCod() + ";");
                writer.print(a.getData() + ";");
                writer.print(a.getDuracao() + ";");
                writer.print(a.getStatus() + ";");
                writer.print(a.getEvento().getCodigo() + ";");
                writer.print(a.getEquipe().getCodinome() + ";");
                writer.print("\n");
            }
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }

    public void salvarDadosEquipamentos(String nomeArquivo){

    }

    public void carregarEventos(String nomeArquivo){

    }

    public void carregarEquipes(String nomeArquivo){

    }

    public void carregarEquipamentos(String nomeArquivo){

    }

    public void carregarAtendimentos(String nomeArquivo){

    }
}
