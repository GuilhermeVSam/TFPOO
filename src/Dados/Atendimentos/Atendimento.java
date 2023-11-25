package Dados.Atendimentos;
import Dados.Equipe.Equipe;
import Dados.Evento.Eventos.Evento;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Atendimento {
private int cod;
private String dataInicio;
private int duracao;
private String status;
private Evento evento;

    public Atendimento(int cod, String dataInicio, int duracao, String status,Evento evento) {
        this.cod = cod;
        this.dataInicio = dataInicio;
        this.duracao = duracao;
        this.status = status;
        this.evento = evento;
    }
    public int getCod() {
        return cod;
    }
    public Evento getEvento() {
        return evento;
    }
    public String getStatus() {
        return status;
    }
    public int getDuracao() {
        return duracao;
    }
    public String getDataInicio() {
        return dataInicio;
    }
    @Override
    public String toString() {
        return "Atendimento{" +
                "Codigo: " + cod +
                ",Data de inico: '" + dataInicio + '\'' +
                ", Duração em dias: =" + duracao +
                ", Status: ='" + status + '\'' +
                ", Evento: =" + evento +
                '}';
    }
}
