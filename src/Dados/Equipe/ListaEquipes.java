package Dados.Equipe;
import Dados.Equipamento.Equipamento;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ListaEquipes {
    private ArrayList<Equipe> equipes;

    public ListaEquipes() {
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
            eq += "\n" + e + "\n";
        }
        return eq;
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

    public void vincularEquipamento(ArrayList<Equipamento> equipamentos){
        for (Equipe equipe : equipes){
            for (Equipamento equipamento:equipamentos) {
                if(equipamento.getEquipe() == equipe){
                    equipe.addEquipamento(equipamento);
                }
            }
        }
    }
}
