package Dados.Atendimentos;
import Dados.Equipe.Equipe;
import Dados.Evento.Eventos.Evento;
import jdk.jshell.Snippet;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Atendimento {
    private int cod;
    private String data;
    private int duracao;
    private STATUS status;
    private Evento evento;
    private Equipe equipe;

    public Atendimento(int cod, String data, int duracao, Evento evento) {
        this.cod = cod;
        this.data = data;
        this.duracao = duracao;
        this.status = STATUS.PENDENTE;
        this.evento = evento;
        this.equipe = null;
    }

    public int getCod() {
        return cod;
    }
    public String getData() {
        return data;
    }

    public int getDuracao() {
        return duracao;
    }

    public STATUS getStatus() {
        return status;
    }

    public Evento getEvento() {
        return evento;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    @Override
    public String toString() {
        try {
            return "Atendimento:" + "\n" +
                    "CÓDIGO: " + cod + "\n" +
                    "DATA: " + data + "\n" +
                    "DURAÇÃO: " + duracao + "\n" +
                    "STATUS: " + status.getDescricao() + "\n" +
                    "EVENTO: " + "\n" + evento.toString() + "\n" +
                    "EQUIPE: " + "\n" + equipe.toString();
        } catch (NullPointerException exception){
            return "Atendimento:" + "\n" +
                    "CÓDIGO: " + cod + "\n" +
                    "DATA: " + data + "\n" +
                    "DURAÇÃO: " + duracao + "\n" +
                    "STATUS: " + status.getDescricao() + "\n" +
                    "EVENTO: " + "\n" + evento.toString() + "\n" +
                    "EQUIPE: " + "Pendente";
        }
    }

    public String getCodEquipe() {
        return equipe.getCodinome();
    }
    public String getCodEvento() {
        return evento.getCodigo();
    }
}
