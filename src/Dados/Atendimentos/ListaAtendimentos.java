package Dados.Atendimentos;
import Dados.Equipe.Cadastro;
import Dados.Equipe.Equipe;
import Dados.Evento.Eventos.Ciclone;
import Dados.Evento.Eventos.Evento;
import Dados.Evento.Eventos.Seca;
import Dados.Evento.Eventos.Terremoto;
import Janela_Principal.APP;

import javax.management.MBeanServerDelegateMBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Scanner;

public class ListaAtendimentos {
    private ArrayList<Atendimento> listaAtendimentos;
    private APP app;

    public ListaAtendimentos() {
        listaAtendimentos = new ArrayList<>();
        this.app = app;
    }

    public void addAtendimento(Atendimento atendimento) {
        listaAtendimentos.add(atendimento);
    }

    public ArrayList<Atendimento> getListaAtendimentos() {
        return new ArrayList<>(listaAtendimentos);
    }

    public double calculaDistancia(Equipe a, Evento b) {
/*        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("##.###KM", symbols);*/

        double LatEquipe = a.getLatitude();
        double LongEquipe = a.getLongitude();
        double LatEvento = b.getLatitude();
        double LongEvento = b.getLongitude();

        double X1ToRad = Math.toRadians(LatEquipe);
        double X2ToRad = Math.toRadians(LatEvento);

        double deltaLongitude = Math.toRadians(LongEquipe - LongEvento);

        return Math.acos(Math.cos(X1ToRad) * Math.cos(X2ToRad) * Math.cos(deltaLongitude) + Math.sin(X1ToRad) * Math.sin(X2ToRad)) * 6.371;
    }

    public int pesquisaCodEvento(int cod) {
        for (Atendimento a : listaAtendimentos) {
            if (cod == a.getCod()) {
                return -1;
            }
        }
        return cod;
    }

    public boolean pesquisaStatus(int codi) {
        for (Atendimento a : listaAtendimentos) {
            return codi == a.getCod();
        }
        return false;
    }

    public ArrayList<Atendimento> atendimentosPendentes() {
        ArrayList<Atendimento> pendentes = new ArrayList<>();
        for (Atendimento a : listaAtendimentos) {
            if (a.getStatus() == STATUS.PENDENTE) {
                pendentes.add(a);
            }
        }
        return pendentes;
    }

    public void AlocarAtendimentos(Cadastro listaEquipes){
        for (Atendimento a : atendimentosPendentes()) {
            for (Equipe e : listaEquipes.getEquipes()) {
                if (calculaDistancia(e, a.getEvento()) <= 5000) {
                    if (e.getDisponivel()) {
                        a.setStatus(STATUS.EXECUTANDO);
                        a.setEquipe(e);
                        e.setDisponivel(false);
                        break;
                    }
                } else {
                    a.setStatus(STATUS.CANCELADO);
                }
            }
        }
    }

    public void AlterarSituacao(int cod, STATUS status) throws Exception {
        for (Atendimento a : listaAtendimentos) {
            if (a.getCod() == cod) {
                if (a.getStatus() == STATUS.FINALIZADO) {
                    throw new Exception("Atendimento já finalizado!");
                }
                a.setStatus(status);
            }
        }
        throw new Exception("Atendimento não encontrado!");
    }

    public String consultarAtendimentos() {
        String texto = "";
        for (Atendimento a : listaAtendimentos) {
            texto += a.toString();
        }
        return texto;
    }
    public double custoEquipe(Equipe equipe) {
        double custo = 0;
        for (Atendimento a : listaAtendimentos) {
            if(a.getEquipe().equals(equipe)) {
                custo = (a.getDuracao() * 250 * a.getEquipe().getQuantidade());
            }
    }
        return custo;
    }

    public double custoEquipamentos(Equipe equipe){
        double custodiario = 0.0;
        return custodiario;
     }

    public Atendimento buscaAtendimento(int cod) {
        for (Atendimento a : listaAtendimentos) {
            if (a.getCod() == cod) {
                return a;
            }
        }
        return null;
    }

    public void salvarDados(String nomeArquivo) {
        Path path = Paths.get(nomeArquivo + "-Evento.csv");
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
}