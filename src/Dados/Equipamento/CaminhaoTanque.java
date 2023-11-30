package Dados.Equipamento;

import Dados.Equipe.Equipe;

import java.util.ArrayList;
public class CaminhaoTanque extends Equipamento {
    private double capacidade;

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

    @Override
    public String toString() {
        return super.toString() +
                "Capacidade: " + capacidade;
    }

}
