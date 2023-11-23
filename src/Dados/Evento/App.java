package Dados.Evento;

import Dados.Evento.Eventos.*;

public class App {
    private ListaEventos listaEventos;

    public App(){
        listaEventos = new ListaEventos();
    }

    public void addEvento(Evento e) throws Exception {
        Exception InvalidCode = new Exception("Código Inválido");
        if(!listaEventos.addEvento(e)) throw InvalidCode;
    }

    public String listar(){
        if(listaEventos.toString().isEmpty()){
            return "Não há nenhum evento cadastrado";
        }
        return listaEventos.toString();
    }
}
