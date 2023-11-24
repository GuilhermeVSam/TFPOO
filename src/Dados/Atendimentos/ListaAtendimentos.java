package Dados.Atendimentos;

import Dados.Equipe.Equipe;
import Dados.Evento.Eventos.Evento;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ListaAtendimentos {

    public void calculaDistancia(Equipe a, Evento b){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("##.###KM", symbols);

        double LatEquipe = 52.237049;
        double LongEquipe = 21.017532;
        double LatEvento = -15.7801;
        double LongEvento = -47.9292;

        double X1ToRad = Math.toRadians(LatEquipe);
        double X2ToRad = Math.toRadians(LatEvento);

        double deltaLongitude = Math.toRadians(LongEquipe - LongEvento);

        double distancia = Math.acos(Math.cos(X1ToRad) * Math.cos(X2ToRad) * Math.cos(deltaLongitude) + Math.sin(X1ToRad) * Math.sin(X2ToRad)) * 6.371;

        System.out.println(df.format(distancia));
    }
}
