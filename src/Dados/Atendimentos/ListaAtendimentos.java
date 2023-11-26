package Dados.Atendimentos;
import Dados.Equipe.Equipe;
import Dados.Evento.Eventos.Evento;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class ListaAtendimentos {
    private ArrayList<Atendimento> listaAtendimentos = new ArrayList<>();

    public void addAtendimento(Atendimento atendimento){
        listaAtendimentos.add(atendimento);
    }
    public ArrayList<Atendimento> getListaAtendimentos() {
        return new ArrayList<>(listaAtendimentos);
    }
    public double calculaDistancia(Equipe a, Evento b){
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
    public Integer pesquisaCodEvento(int cod) {
        for (Atendimento a : listaAtendimentos) {
            if (cod == a.getCod()) {
                return a.getCod();
            }
        }
        return null;
    }
    public boolean pesquisaStatus(int codi) {
        for (Atendimento a : listaAtendimentos) {
            if (a.getCod() == codi && a.getStatus().equals("PENDENTE")) {
                return true;
            }
        }
        return false;
    }
}
