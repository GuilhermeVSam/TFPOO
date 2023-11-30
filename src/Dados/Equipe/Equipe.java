package Dados.Equipe;

import Dados.Equipamento.Equipamento;

import java.util.ArrayList;

public class Equipe {
private String codinome;
private int quantidade;
private double longitude;
private double latitude;
private boolean disponivel;
private ArrayList<Equipamento> equipamentosDaEquipe;
public Equipe(String codinome, int quantidade, double latitude, double longitude){
    this.codinome = codinome;
    this.quantidade = quantidade;
    this.latitude = latitude;
    this.longitude = longitude;
    this.disponivel = true;
    this.equipamentosDaEquipe = new ArrayList<>();
}
    public void addEquipamento(Equipamento equipamento) {
        equipamentosDaEquipe.add(equipamento);
    }
    public String getCodinome(){
    return codinome;
    }
    public int getQuantidade(){
    return quantidade;
    }
    public double getLongitude(){
    return longitude;
    }
   public double getLatitude() {
       return latitude;
   }
   public boolean getDisponivel(){
    return disponivel;
   }
    public void setDisponivel(boolean disponivel){
    this.disponivel = disponivel;
   }
    public void setLongitude(double longitude){
    this.longitude = longitude;
    }
    public void setLatitude(double latitude){
    this.latitude = latitude;
    }
    public ArrayList<Equipamento> getEquipamentosDaEquipe() {
        return equipamentosDaEquipe;
    }
    public String getNomeEquipamento(){
        String nomeEquipamento = "";
        for (Equipamento e:equipamentosDaEquipe) {
            nomeEquipamento += e.getNome() + ", ";
        }
        return nomeEquipamento;
    }

    private double getSomatorioCustoDiarioEquipamentos() {
        double somatorio = 0.0;
        for (Equipamento equipamento : getEquipamentosDaEquipe()) {
            somatorio += equipamento.getCustoDia();
        }
        return somatorio;
    }

    @Override
    public String toString() {
        return "Codinome: " + codinome + "\n" +
                "Quantidade: " + quantidade + "\n" +
                "Longitude: " + longitude + "\n" +
                "Latitude: " + latitude + "\n" +
                "Equipamento: " + getNomeEquipamento() + "\n" +
                "Custo de Equipamentos: R$" + getSomatorioCustoDiarioEquipamentos() + "\n";
    }
}

