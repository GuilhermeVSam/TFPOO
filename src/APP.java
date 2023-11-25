import Dados.Atendimentos.Atendimento;
import Dados.Atendimentos.ListaAtendimentos;
import Dados.Equipe.Cadastro;
import Dados.Equipe.Equipe;
import Dados.Evento.Eventos.*;

import java.util.ArrayList;

public class APP {
    private ListaEventos listaEventos;
    private ListaAtendimentos listaAtendimentos;
    private Cadastro listaEquipes;

    public APP(){
        listaEventos = new ListaEventos();
        listaAtendimentos = new ListaAtendimentos();
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

    public ArrayList<Equipe> listarDisponiveis(Evento evento){
        ArrayList<Equipe> disponiveis = new ArrayList<>();
        for (Equipe e : listaEquipes.getEquipes()) {
            if(listaAtendimentos.calculaDistancia(e, evento) < 5000){
                disponiveis.add(e);
            }
        }
        return disponiveis;
    }

/*    public void alocarAtendimento(Equipe equipe, Evento evento){
        Atendimento atendimento = new Atendimento(equipe, evento);
        listaAtendimentos.addAtendimento(atendimento);
    }*/
}
