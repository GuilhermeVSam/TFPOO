package Janela_Principal;

import Dados.Atendimentos.Atendimento;
import Dados.Atendimentos.STATUS;
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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Queue;
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
        if(nomeArquivo.equalsIgnoreCase("exemplo")) throw new Exception("Nome de Arquivo Inválido");
        salvarDadosEvento(nomeArquivo);
        salvarDadosEquipe(nomeArquivo);
        salvarDadosEquipamentos(nomeArquivo);
        salvarDadosAtendimentos(nomeArquivo);
    }

    public void salvarDadosEvento(String nomeArquivo) throws IOException{
        //codigo;data;latitude;longitude;tipo;velocidade_magnitude_estiagem;precipitacao
        ArrayList<Evento> listaEventos = app.getEventos();
        Path path1 = Paths.get(nomeArquivo + "-EVENTOS.csv");
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path1, Charset.defaultCharset()))) {
            for (Evento e : listaEventos) {
                writer.print(e.getCodigo() + ";");
                writer.print(e.getData() + ";");
                writer.print(e.getLatitude() + ";");
                writer.print(e.getLongitude() + ";");
                if(e instanceof Ciclone){
                    writer.print("1;");
                    writer.print(((Ciclone) e).getVelocidade() + ";");
                    writer.print(((Ciclone) e).getPrecipitacao() + ";");
                } else if(e instanceof Terremoto){
                    writer.print("2;");
                    writer.print(((Terremoto) e).getMagnitude() + ";");
                } else{
                    writer.print("3;");
                    writer.print(((Seca) e).getEstiagem() + ";");
                }
                writer.print("\n");
            }
        }
    }

    public void salvarDadosEquipe(String nomeArquivo) throws IOException{
        ArrayList<Equipe> equipes = app.getEquipes();
        Path path1 = Paths.get(nomeArquivo + "-EQUIPES.csv");
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
        Queue<Atendimento> listaAtendimentos = app.getAtendimentos();
        Path path = Paths.get(nomeArquivo + "-ATENDIMENTOS.csv");
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
        Path path = Paths.get(nomeArquivo + "-EQUIPAMENTOS.csv");
        try(PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, Charset.defaultCharset()))){
            for(Equipamento e : listaEquipamentos){
                writer.print(e.getId() + ";");
                writer.print(e.getNome() + ";");
                writer.print(e.getCustoDia() + ";");
                writer.print((e.getCodinomeEquipe()) + ";");
                if(e instanceof Barco){
                    writer.print("Barco;");
                    writer.print(((Barco) e).getCapacidade() + ";");
                } else if(e instanceof Escavadeira){
                    writer.print("Escavadeira;");
                    writer.print(((Escavadeira) e).getCombustivel() + ";");
                    writer.print(((Escavadeira) e).getCarga() + ";");
                } else{
                    writer.print("CaminhaoTanque;");
                    writer.print(((CaminhaoTanque) e).getCapacidade() + ";");
                }
                writer.print("\n");
            }
        }
    }

    public void carregarEventos(String nomeArquivo) throws Exception{
        Path path = Paths.get(nomeArquivo + "-EVENTOS.csv");
        try (BufferedReader br = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))) {
            String linha;
            br.readLine();
            while((linha = br.readLine()) != null){
                Scanner sc = new Scanner(linha).useDelimiter(";");
                String codigo = sc.next();
                String data = sc.next();
                String latitude = sc.next();
                String longitude = sc.next();
                String tipo = sc.next();
                switch (tipo) {
                    case "1" -> {
                        String velocidade = sc.next();
                        String precipitacao = sc.next();
                        Ciclone ciclone = new Ciclone(codigo, data, Double.parseDouble(latitude), Double.parseDouble(longitude), Double.parseDouble(velocidade), Double.parseDouble(precipitacao));
                        app.addEvento(ciclone);
                    }
                    case "2" -> {
                        String magnitude = sc.next();
                        Terremoto terremoto = new Terremoto(codigo, data, Double.parseDouble(latitude), Double.parseDouble(longitude), Double.parseDouble(magnitude));
                        app.addEvento(terremoto);
                    }
                    case "3" -> {
                        String estiagem = sc.next();
                        Seca seca = new Seca(codigo, data, Double.parseDouble(latitude), Double.parseDouble(longitude), Integer.parseInt(estiagem));
                        app.addEvento(seca);
                    }
                    default -> {

                    }
                }
            }
        }
    }

    public void carregarEquipes(String nomeArquivo) throws IOException{
        Path path = Paths.get(nomeArquivo + "-EQUIPES.csv");
        try (BufferedReader br = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))) {
            String linha;
            br.readLine();
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
        Path path = Paths.get(nomeArquivo + "-EQUIPAMENTOS.csv");
        try(BufferedReader br = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))){
            String linha;
            br.readLine();
            while((linha = br.readLine()) != null){
                Scanner sc = new Scanner(linha).useDelimiter("\\s*;\\s*");
                String id = sc.next();
                String nome = sc.next();
                String custoDia = sc.next();
                String codinome = sc.next();
                Equipe equipe = app.buscaEquipe(codinome);
                String tipo = sc.next();
                switch (tipo) {
                    case "3" -> {
                        String combustivel = sc.next();
                        String carga = sc.next();
                        Escavadeira equipamento = new Escavadeira(Integer.parseInt(id), nome, Double.parseDouble(custoDia), combustivel, Double.parseDouble(carga));
                        equipamento.setEquipe(equipe);
                        app.addEquipamento(equipamento);
                    }
                    case "1" -> {
                        String capacidade = sc.next();
                        Barco equipamento = new Barco(Integer.parseInt(id), nome, Double.parseDouble(custoDia), Integer.parseInt(capacidade));
                        equipamento.setEquipe(equipe);
                        app.addEquipamento(equipamento);
                    }
                    case "2" -> {
                        String capacidade = sc.next();
                        CaminhaoTanque equipamento = new CaminhaoTanque(Integer.parseInt(id), nome, Double.parseDouble(custoDia), Double.parseDouble(capacidade));
                        equipamento.setEquipe(equipe);
                        app.addEquipamento(equipamento);
                    }
                }
            }
        }
    }
    public void carregarAtendimentos(String nomeArquivo) throws IOException{
        Path path = Paths.get(nomeArquivo + "-ATENDIMENTOS.csv");
        try(BufferedReader br = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))){
            String linha;
            br.readLine();
            while((linha = br.readLine()) != null){
                //3333;04/11/2023;12;PENDENTE;3
                Scanner sc = new Scanner(linha).useDelimiter(";");
                String cod = sc.next();
                String data = sc.next();
                String duracao = sc.next();
                String status = sc.next();
                status = status.toUpperCase();
                String codigoEvento = sc.next();
                Evento evento = app.buscaEvento(codigoEvento);
                Atendimento atendimento = new Atendimento(Integer.parseInt(cod), data, Integer.parseInt(duracao), evento);
                switch(status){
                    case "PENDENTE" ->{

                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}