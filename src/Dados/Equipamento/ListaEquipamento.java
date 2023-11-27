package Dados.Equipamento;

import Dados.Evento.Eventos.Evento;
import Dados.Evento.Eventos.ListaEventos;

import java.util.ArrayList;

public class ListaEquipamento {
    private ArrayList<Equipamento> listaEquipamentos;

    public ListaEquipamento() {

        listaEquipamentos = new ArrayList<>();
    }

    public boolean addEquipamento(Equipamento equipamento) {
        for (Equipamento e : listaEquipamentos) {
            if (e.getId()==(equipamento.getId())) {
                return false;
            }
        }
        listaEquipamentos.add(equipamento);
        return true;
    }
    public ArrayList<Equipamento> getEquipamentos() {

        return listaEquipamentos;
    }

    @Override
    public String toString() {
        String equipt = "";
        for (Equipamento e : listaEquipamentos) {
            equipt += e + "\n";
        }
        return equipt;
    }

    public Equipamento buscaCodigo(int id) {
        for (Equipamento e : listaEquipamentos) {
            if (e.getId()==(id)) {
                return e;
            }
        }
        return null;
    }
}

