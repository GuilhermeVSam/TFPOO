package Dados.Equipamento;

public class Barco extends Equipamento{
    private int capacidade;
    public Barco(int id, String nome, double custoDia, int capacidade){
        super(id,nome,custoDia);
        this.capacidade=capacidade;
    }
    public int getCapacidade(){
        return  capacidade;
    }
    public void setCapacidade(int capacidade){
        this.capacidade=capacidade;
    }
}
