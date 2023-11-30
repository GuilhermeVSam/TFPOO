package Dados.Equipamento;

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
        String eq = "";
        for (Equipamento e:listaEquipamentos) {
            eq += "\n" + e + "\n";
        }
        return eq;
    }

    public Equipamento buscaCodigo(int id) {
        for (Equipamento e : listaEquipamentos) {
            if (e.getId()==(id)) {
                return e;
            }
        }
        return null;
    }

    public Equipamento buscaEquipamento(String id) {
        int idInt = Integer.parseInt(id);
        for (Equipamento e : listaEquipamentos) {
            if (e.getId() == idInt) {
                return e;
            }
        }
        return null;
    }
}

