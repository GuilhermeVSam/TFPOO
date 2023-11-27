package Janela_Principal;

import Dados.Atendimentos.Atendimento;
import Dados.Equipe.Equipe;
import Dados.Evento.Eventos.Ciclone;
import Dados.Evento.Eventos.Evento;
import Dados.Evento.Eventos.Seca;
import Dados.Evento.Eventos.Terremoto;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Operador {
    private APP app;

    public Operador(APP app){
        this.app = app;
    }

    public void carregarDadosEvento(String nomeArquivo) {
        Path path = Paths.get(nomeArquivo + "-Evento.csv");
        try (BufferedReader br = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))) {
            String linha = br.readLine();
            while (linha != null) {
                Scanner sc = new Scanner(linha).useDelimiter(";");
                String codigo = sc.next();
                String data = sc.next();
                double latitude = Double.parseDouble(sc.next());
                double longitude = Double.parseDouble(sc.next());
                String tipo = sc.next();
                if (tipo.equals("Ciclone")) {
                    int velocidade = Integer.parseInt(sc.next());
                    double precipitacao = sc.nextDouble();
                    Ciclone c = new Ciclone(codigo, data, latitude, longitude, velocidade, precipitacao);
                    app.addEvento(c);
                } else if (tipo.equals("Terremoto")) {
                    double magnitude = Double.parseDouble(sc.next());
                    Terremoto t = new Terremoto(codigo, data, latitude, longitude, magnitude);
                    System.out.println(t);
                    app.addEvento(t);
                } else {
                    int estiagem = Integer.parseInt(sc.next());
                    Seca s = new Seca(codigo, data, latitude, longitude, estiagem);
                    app.addEvento(s);
                }
                linha = br.readLine();
            }
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        } catch (Exception ignored) {
        }
    }

    public void carregarDadosAtendimentos(String nomeArquivo) {
        Path path = Paths.get(nomeArquivo + "-Evento.csv");
        try (BufferedReader br = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))) {
            String linha = br.readLine();
            while(linha != null) {
                Scanner sc = new Scanner(linha).useDelimiter(";");
                int codigo = Integer.parseInt(sc.next());
                String data = sc.next();
                int duracao = Integer.parseInt(sc.next());
                Evento e = app.buscaPorCodigo(sc.next());
                System.out.println(e);
                Atendimento a = new Atendimento(codigo, data, duracao, e);
                app.addAtendimento(a);
                linha = br.readLine();
            }
            app.alocarAtendimento();
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }

    public void carregarDadosEquipe(String nomeArquivo) {
        Path path = Paths.get(nomeArquivo + "-Equipe.csv");
        try (BufferedReader br = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))) {
            String linha = br.readLine();
            while(linha != null) {
                Scanner sc = new Scanner(linha).useDelimiter(";");
                Equipe e = new Equipe(sc.next(), Integer.parseInt(sc.next()), Double.parseDouble(sc.next()), Double.parseDouble(sc.next()));
                app.addEquipe(e);
                linha = br.readLine();
            }
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e);
        }
    }
}
