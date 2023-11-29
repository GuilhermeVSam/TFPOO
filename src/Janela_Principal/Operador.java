package Janela_Principal;

import Dados.Atendimentos.Atendimento;
import Dados.Equipamento.Barco;
import Dados.Equipamento.CaminhaoTanque;
import Dados.Equipamento.Equipamento;
import Dados.Equipamento.Escavadeira;
import Dados.Equipe.Equipe;
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
import java.util.Scanner;

public class Operador {
    private APP app;

    public Operador(APP app){
        this.app = app;
    }

    public void carregarDados(String nomeArquivo) throws Exception{
        carregarEventos(nomeArquivo);
        carregarEquipes(nomeArquivo);
        carregarEquipamentos(nomeArquivo);
        carregarAtendimentos(nomeArquivo);
    }

    public void salvarDados(String nomeArquivo) throws Exception{
        salvarDadosEvento(nomeArquivo);
        salvarDadosEquipe(nomeArquivo);
        salvarDadosEquipamentos(nomeArquivo);
        salvarDadosAtendimentos(nomeArquivo);
    }

    public void salvarDadosEvento(String nomeArquivo) throws IOException{
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
        }
    }

    public void salvarDadosEquipe(String nomeArquivo) throws IOException{
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
        }
    }

    public void salvarDadosAtendimentos(String nomeArquivo) throws IOException {
        ArrayList<Atendimento> listaAtendimentos = app.getAtendimentos();
        Path path = Paths.get(nomeArquivo + "-Atendimento.csv");
        try (PrintWriter writer = new PrintWriter(
                Files.newBufferedWriter(path, Charset.defaultCharset()))) {
            for (Atendimento a : listaAtendimentos) {
                writer.print(a.getCod() + ";");
                writer.print(a.getData() + ";");
                writer.print(a.getDuracao() + ";");
                writer.print(a.getCodEvento());
                writer.print("\n");
            }
        }
    }

    public void salvarDadosEquipamentos(String nomeArquivo) throws IOException{
        ArrayList<Equipamento> listaEquipamentos = app.getEquipamentos();
        Path path = Paths.get(nomeArquivo + "-Equipamento.csv");
        try(PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.defaultCharset()))){
            for(Equipamento e : listaEquipamentos){
                writer.print(e.getId() + ";");
                writer.print(e.getNome() + ";");
                writer.print(e.getCustoDia() + ";");
                writer.print((e.getCodinomeEquipe()) + ";");
                if(e instanceof Barco){
                    writer.print("Barco" + ";");
                    writer.print(((Barco) e).getCapacidade());
                } else if(e instanceof Escavadeira){
                    writer.print(((Escavadeira) e).getCombustivel());
                    writer.print(((Escavadeira) e).getCarga());
                } else{
                    writer.print(((CaminhaoTanque) e).getCapacidade());
                }
                writer.print("\n");
            }
        }
    }

    public void carregarEventos(String nomeArquivo) throws Exception{
        Path path = Paths.get(nomeArquivo + "-Evento.csv");
        try (BufferedReader br = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))) {
            String linha;
            while((linha = br.readLine()) != null){
                Scanner sc = new Scanner(linha).useDelimiter(";");
                String codigo = sc.next();
                String data = sc.next();
                String latitude = sc.next();
                String longitude = sc.next();
                String tipo = sc.next();
                if(tipo.equals("Ciclone")){
                    String velocidade = sc.next();
                    String precipitacao = sc.next();
                    Ciclone ciclone = new Ciclone(codigo, data, Double.parseDouble(latitude), Double.parseDouble(longitude), Integer.parseInt(velocidade), Integer.parseInt(precipitacao));
                    app.addEvento(ciclone);
                } else if(tipo.equals("Terremoto")){
                    String magnitude = sc.next();
                    Terremoto terremoto = new Terremoto(codigo, data, Double.parseDouble(latitude), Double.parseDouble(longitude), Double.parseDouble(magnitude));
                    app.addEvento(terremoto);
                } else{
                    String estiagem = sc.next();
                    Seca seca = new Seca(codigo, data, Double.parseDouble(latitude), Double.parseDouble(longitude), Integer.parseInt(estiagem));
                    app.addEvento(seca);
                }
            }
        }
    }

    public void carregarEquipes(String nomeArquivo) throws IOException{
        Path path = Paths.get(nomeArquivo + "-Equipe.csv");
        try (BufferedReader br = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))) {
            String linha;
            while((linha = br.readLine()) != null){
                Scanner sc = new Scanner(linha).useDelimiter(";");
                String codinome = sc.next();
                String quantidade = sc.next();
                String latitude = sc.next();
                String longitude = sc.next();
                Equipe equipe = new Equipe(codinome, Integer.parseInt(quantidade), Double.parseDouble(latitude), Double.parseDouble(longitude));
                app.addEquipe(equipe);
            }
        }
    }

    public void carregarEquipamentos(String nomeArquivo) throws IOException{
        Path path = Paths.get(nomeArquivo + "-Equipamento.csv");
        try(BufferedReader br = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))){
            String linha;
            while((linha = br.readLine()) != null){
                Scanner sc = new Scanner(linha).useDelimiter(";");
                String id = sc.next();
                String nome = sc.next();
                String custoDia = sc.next();
                String codinome = sc.next();
                Equipe equipe = app.buscaEquipe(codinome);
                String tipo = sc.next();
                if(tipo.equals("Escavadeira")){
                    String combustivel = sc.next();
                    String carga = sc.next();
                    Escavadeira equipamento = new Escavadeira(Integer.parseInt(id), nome, Double.parseDouble(custoDia), combustivel, Integer.parseInt(carga));
                    equipamento.setEquipe(equipe);
                    app.addEquipamento(equipamento);
                } else if(tipo.equals("Barco")){
                    String capacidade = sc.next();
                    Barco equipamento = new Barco(Integer.parseInt(id), nome, Double.parseDouble(custoDia), Integer.parseInt(capacidade));
                    equipamento.setEquipe(equipe);
                    app.addEquipamento(equipamento);
                } else {
                    String capacidade = sc.next();
                    CaminhaoTanque equipamento = new CaminhaoTanque(Integer.parseInt(id), nome, Double.parseDouble(custoDia), Integer.parseInt(capacidade));
                    equipamento.setEquipe(equipe);
                    app.addEquipamento(equipamento);
                }
            }
        }
    }
    public void carregarAtendimentos(String nomeArquivo) throws IOException{
        Path path = Paths.get(nomeArquivo + "-Atendimento.csv");
        try(BufferedReader br = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))){
            String linha;
            while((linha = br.readLine()) != null){
                Scanner sc = new Scanner(linha).useDelimiter(";");
                String cod = sc.next();
                String data = sc.next();
                String duracao = sc.next();
                String codigoEvento = sc.next();
                Evento evento = app.buscaEvento(codigoEvento);
                Atendimento atendimento = new Atendimento(Integer.parseInt(cod), data, Integer.parseInt(duracao), evento);
                app.addAtendimento(atendimento);
                app.alocarAtendimento();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}