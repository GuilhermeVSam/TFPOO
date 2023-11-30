package Dados.Atendimentos;

import Dados.Equipe.Equipe;
import Dados.Evento.Eventos.Evento;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FilaAtendimentos {
    private Queue<Atendimento> filaAtendimentosPendentes;
    private Queue<Atendimento> filaAtendimentosCancelados;
    private Queue<Atendimento> filaAtendimentos;
    private Queue<Atendimento> filaAtendimentosExecutando;
    private Queue<Atendimento> filaAtendimentosFinalizados;

    public FilaAtendimentos() {
        filaAtendimentos = new LinkedList<>();
        filaAtendimentosPendentes = new LinkedList<>();
        filaAtendimentosCancelados = new LinkedList<>();
        filaAtendimentosExecutando = new LinkedList<>();
        filaAtendimentosFinalizados = new LinkedList<>();
    }

    public ArrayList<Atendimento> getTodasFilas() {
        ArrayList<Atendimento> aux = new ArrayList<>();
        for (Atendimento a : filaAtendimentosPendentes) {
            if (a != null) aux.add(a);
        }
        for (Atendimento a : filaAtendimentosExecutando) {
            if (a != null) aux.add(a);
        }
        for (Atendimento a : filaAtendimentosCancelados) {
            if (a != null) aux.add(a);
        }
        for (Atendimento a : filaAtendimentosFinalizados) {
            if (a != null) aux.add(a);
        }
        return aux;
    }

    public void add(Atendimento atendimento) {
        for (Atendimento a : filaAtendimentos) {
            if (a.getCod() == atendimento.getCod());
        }
        filaAtendimentos.add(atendimento);
    }

    public void addPendente(Atendimento atendimento) {
        for (Atendimento a : filaAtendimentosPendentes) {
            if (a.getCod() == atendimento.getCod());
        }
        atendimento.setStatus(STATUS.PENDENTE);
        filaAtendimentosPendentes.add(atendimento);
    }

    public void addCancelado(Atendimento atendimento) {
        for (Atendimento a : filaAtendimentosCancelados) {
            if (a.getCod() == atendimento.getCod());
        }
        atendimento.setStatus(STATUS.CANCELADO);
        filaAtendimentosCancelados.add(atendimento);
    }

    public void addExecutando(Atendimento atendimento) {
        for (Atendimento a : filaAtendimentosExecutando) {
            if (a.getCod() == atendimento.getCod());
        }
        atendimento.setStatus(STATUS.EXECUTANDO);
        filaAtendimentosExecutando.add(atendimento);
    }

    public void addFinalizado(Atendimento atendimento) {
        for (Atendimento a : filaAtendimentosFinalizados) {
            if (a.getCod() == atendimento.getCod());
        }
        atendimento.setStatus(STATUS.FINALIZADO);
        filaAtendimentosFinalizados.add(atendimento);
    }

    public Atendimento buscaAtendimento(int id) {
        for (Atendimento a : getTodasFilas()) {
            if (a.getCod() == id) return a;
        }
        return null;
    }

    public double calculaDistancia(Equipe a, Evento b) {
        double LatEquipe = a.getLatitude();
        double LongEquipe = a.getLongitude();
        double LatEvento = b.getLatitude();
        double LongEvento = b.getLongitude();

        double X1ToRad = Math.toRadians(LatEquipe);
        double X2ToRad = Math.toRadians(LatEvento);

        double deltaLongitude = Math.toRadians(LongEquipe - LongEvento);

        return Math.acos(Math.cos(X1ToRad) * Math.cos(X2ToRad) * Math.cos(deltaLongitude) + Math.sin(X1ToRad) * Math.sin(X2ToRad)) * 6.371;
    }

    public String pesquisaCodEvento(String cod) {
        for (Atendimento a : getTodasFilas()) {
            if (cod.equals(a.getEvento().getCodigo())) {
                return cod;
            }
        }
        return null;
    }

    public boolean pesquisaStatus(int codi) {
        for (Atendimento a : filaAtendimentos) {
            if (a.getCod() == codi && a.getStatus() == STATUS.EXECUTANDO) {
                return true;
            }
        }
        return false;
    }

    public void AlocarAtendimentos() {
        while(!filaAtendimentos.isEmpty()){
            Atendimento a = filaAtendimentos.poll();
            switch (a.getStatus()) {
                case PENDENTE -> {
                    addPendente(a);
                }
                case CANCELADO -> {
                    addCancelado(a);
                }
                case EXECUTANDO -> {
                    addExecutando(a);
                }
            }
        }
    }

    public void AlocarAtendimentos(ArrayList<Equipe> listaEquipes){
        for (Atendimento a:filaAtendimentos) {
            for (Equipe e:listaEquipes) {
                if(a.getStatus()==STATUS.EXECUTANDO) break;
                if(calculaDistancia(e,a.getEvento()) <= 5){
                    if(e.getDisponivel()){
                        a.setEquipe(e);
                        e.setDisponivel(false);
                        a.setStatus(STATUS.EXECUTANDO);
                    } else{
                        a.setEquipe(e);
                        a.setStatus(STATUS.PENDENTE);
                    }
                }
                if(a.getEquipe() != null) break;
                a.setStatus(STATUS.CANCELADO);
            }
        }
        AlocarAtendimentos();
    }

    public String listarTodos() {
        String str = "";
        for (Atendimento a: getTodasFilas()) {
            str += "\n" + a + "\n";
        }
        return str;
    }
}
