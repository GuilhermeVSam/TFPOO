package Dados.Equipamento;

import Dados.Equipe.Equipe;

import java.util.ArrayList;
public class CaminhaoTanque extends Equipamento {
    private double capacidade;
    private ArrayList<Equipamento> equipamentos;

    public CaminhaoTanque(int id, String nome, double custoDia, double capacidade) {
        super(id, nome, custoDia);
        this.capacidade = capacidade;
    }

    public void setCapacidade(double capacidade) {
        this.capacidade = capacidade;
    }
    public double getCapacidade(){
        return capacidade;
    }

    public boolean add(Equipamento equipamento) {
       return equipamentos.add(equipamento);
    }
    public ArrayList<Equipamento> clonarEquipamentos(){
        ArrayList<Equipamento> cloneEquipamento = new ArrayList<>(equipamentos);
        return cloneEquipamento;
    }

}
