package Dados.Evento.Eventos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ListaEventos {

    static class sortName implements Comparator<Evento> {
        public int compare(Evento a, Evento b) {
            return a.getCodigo().compareTo(b.getCodigo());
        }
    }

    private ArrayList<Evento> listaEventos;

    public ListaEventos() {
        listaEventos = new ArrayList<>();
    }

    public boolean addEvento(Evento evento) {
        for (Evento e : listaEventos) {
            if (e.getCodigo().equals(evento.getCodigo())) {
                return false;
            }
        }
        listaEventos.add(evento);
        listaEventos.sort(new sortName());
        return true;
    }

    public ArrayList<Evento> getEventos() {
        return listaEventos;
    }

    @Override
    public String toString() {
        String evento = "";
        for (Evento e : listaEventos) {
            evento += e + "\n";
        }
        return evento;
    }

    public Evento buscaCodigo(String codigo) {
        for (Evento e : listaEventos) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                return e;
            }
        }
        return null;
    }
}
//        this.codigo = codigo.toUpperCase();
//        this.data = data;
//        this.latitude = latitude;
//        this.longitude = longitude;