package Dados.Equipamento;

import Dados.Equipe.Equipe;

public class Equipamento {
    private int id;
    private String nome;
    private double custoDia;
    private Equipe equipe;

    public Equipamento(int id, String nome, double custoDia){
        this.id = id;
        this.nome = nome;
        this.custoDia = custoDia;
        this.equipe = null;
    }

    public Equipamento(){}
    public int getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public double getCustoDia(){
        return custoDia;
    }
    public Equipe getEquipe() {
        return equipe;
    }

    public String getCodinomeEquipe(){
        if(equipe == null) return "null";
        return equipe.getCodinome();
    }
    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    @Override
    public String toString() {
        return "Id: " + id +'\n'+
                "Nome: " + nome + '\n' +
                "Custo diário: " + custoDia +'\n';
    }
}

