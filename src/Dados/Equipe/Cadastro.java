package Dados.Equipe;
import Dados.Equipamento.Equipamento;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Cadastro {
    private ArrayList<Equipe> equipes;

    public Cadastro() {
        equipes = new ArrayList<>();
    }

    public boolean addEquipe(Equipe equipe) {
        if (buscarNome(equipe.getCodinome()) != null) {
            return false;
        }
        equipes.add(equipe);
        Comparator<Equipe> comparador = Comparator.comparing(Equipe::getCodinome);
        Collections.sort(equipes, comparador);
        return true;
    }

    public String buscarNome(String codinome) {
        int i;
        for (i = 0; i < equipes.size(); i++) {
            Equipe eq = equipes.get(i);
            if (eq.getCodinome().equalsIgnoreCase(codinome)) {
                return codinome;
            }
        }
        return null;
    }

    public ArrayList<Equipe> getEquipes() {
        return equipes;
    }
    @Override
    public String toString() {
        String eq = "";
        for (Equipe e : equipes) {
            eq += e + "\n";
        }
        return eq;
    }
    public double getSomatorioCustoDiarioEquipamentos(Equipe equipe) {
        double somatorio = 0.0;
        for (Equipamento equipamento :equipe.getEquipamentosDaEquipe()) {
            somatorio += equipamento.getCustoDia();
        }
        return somatorio;
    }
    public Equipe buscaPorCodinome(String codinomeEquipe) {
        if(codinomeEquipe.equals("null")) return null;
        for (Equipe equipe : equipes) {
            if (equipe.getCodinome().equalsIgnoreCase(codinomeEquipe)) {
                return equipe;
            }
        }
        return null;
    }
}
