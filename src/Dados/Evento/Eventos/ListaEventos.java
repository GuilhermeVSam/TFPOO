package Dados.Evento.Eventos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListaEventos {

    static class sortName implements Comparator<Evento> {
        public int compare(Evento a, Evento b) {
            return a.getCodigo().compareTo(b.getCodigo());
        }
    }
    private ArrayList<Evento> eventos;

    public ListaEventos(){
        eventos = new ArrayList<>();
    }

    public boolean addEvento(Evento evento) {
        for (Evento e : eventos) {
            if (e.getCodigo().equals(evento.getCodigo())) {
                return false;
            }
        }
        eventos.add(evento);
        eventos.sort(new sortName());
        return true;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    @Override
    public String toString() {
        String evento = "";
        for (Evento e: eventos) {
            evento += e + "\n";
        }
        return evento;
    }

    public Evento buscaCodigo(String codigo){
        for (Evento e : eventos) {
            if(e.getCodigo().equalsIgnoreCase(codigo)){
                return e;
            }
        }
        return null;
    }
}
