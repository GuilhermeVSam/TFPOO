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
    private  Cadastro cadastroEquipes;

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
            for (Equipe e : listaEquipes.clonarEquipes()) {
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
    public double custoAtendimento(Atendimento atendimento) {
        double custoGeral = 0.0;
        double custoEquipe = custoEquipe(atendimento.getEquipe());
        double custoDiarioEquipamentos = custoDiarioEquipamentos(atendimento.getEquipe());
        double custoDeslocamento = custoDeslocamento(atendimento.getEquipe());
        custoGeral = custoEquipe + custoDiarioEquipamentos + custoDeslocamento;
        return custoGeral;
    }

    public double custoEquipe(Equipe equipe) {
        double custo = 0.0;
        for (Atendimento a : listaAtendimentos) {
            if(a.getEquipe().equals(equipe)) {
                custo = (a.getDuracao() * 250 * a.getEquipe().getQuantidade());
            }
    }
        return custo;
    }

    public double custoDiarioEquipamentos(Equipe equipe) {
        double custoDiarioEq = 0.0;

        for (Atendimento atendimento : listaAtendimentos) {
            for (Equipe e : cadastroEquipes.clonarEquipes()) {
                if (equipe.getCodinome().equals(e.getCodinome())) {
                    double duracaoAtendimento = atendimento.getDuracao();
                    double somatorioCustoEquipamentos = cadastroEquipes.getSomatorioCustoDiarioEquipamentos(equipe);
                    custoDiarioEq += duracaoAtendimento * somatorioCustoEquipamentos;
                }
            }
        }

        return custoDiarioEq;
    }

    public double custoDeslocamento(Equipe equipe) {
        double custoDesloc = 0.0;

        for (Atendimento atendimento : listaAtendimentos) {
            for (Equipe e : cadastroEquipes.clonarEquipes()) {
                if (equipe.getCodinome().equals(e.getCodinome())) {
                    double custoDiarioEquipamentos = cadastroEquipes.getSomatorioCustoDiarioEquipamentos(equipe);
                    int quantidadeEquipamentos = equipe.getQuantidade();
                    custoDesloc = 100 * quantidadeEquipamentos * custoDiarioEquipamentos;
                }
            }
        }
        return custoDesloc;
    }



    public Atendimento buscaAtendimento(int cod) {
        for (Atendimento a : listaAtendimentos) {
            if (a.getCod() == cod) {
                return a;
            }
        }
        return null;
    }
}