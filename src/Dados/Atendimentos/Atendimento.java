package Dados.Atendimentos;
import Dados.Equipe.Equipe;
import Dados.Evento.Eventos.Evento;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Atendimento {
    private int cod;
    private String data;
    private int duracao;
    private String status;
    private Evento evento;

    public Atendimento(int cod, String data, int duracao, String status, Evento evento) {
        this.cod = cod;
        this.data = data;
        this.duracao = duracao;
        this.status = "PENDENTE";
        this.evento = evento;
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

    public String getStatus() {
        return status;
    }

    public Evento getEvento() {
        return evento;
    }

}
