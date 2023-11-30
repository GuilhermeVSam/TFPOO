package Dados.Atendimentos.AlterarSituacao;

import Dados.Atendimentos.Atendimento;
import Dados.Atendimentos.STATUS;
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

    public FilaAtendimentos(){
        filaAtendimentosPendentes = new LinkedList<>();
        filaAtendimentos = new LinkedList<>();
        filaAtendimentosCancelados = new LinkedList<>();
        filaAtendimentosExecutando = new LinkedList<>();
        filaAtendimentosFinalizados = new LinkedList<>();
    }

    public Queue<Atendimento> getLista(){
        Queue<Atendimento> aux = filaAtendimentos;
        return aux;
    }

    public Queue<Atendimento> getTodasFilas(){
        Queue<Atendimento> aux = new LinkedList<>();
        for (Atendimento a:filaAtendimentosPendentes) {
            if(a != null) aux.add(a);
        }
        for (Atendimento a:filaAtendimentosCancelados) {
            if(a != null) aux.add(a);
        }
        for (Atendimento a:filaAtendimentosExecutando){
            if(a != null) aux.add(a);
        }
        for (Atendimento a:filaAtendimentosExecutando){
            if(a != null) aux.add(a);
        }
        return aux;
    }

    public boolean add(Atendimento atendimento){
        for (Atendimento a : filaAtendimentos) {
            if(a.getCod() == atendimento.getCod()) return false;
        }
        return filaAtendimentos.add(atendimento);
    }

    public Atendimento buscaAtendimento(int id){
        for (Atendimento a:filaAtendimentos) {
            if(a.getCod() == id) return a;
        }
        return null;
    }

    public String consultarAtendimentos(){
        String str = "";
        for (Atendimento a: filaAtendimentos) {
            str += a;
        }
        return str;
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
        for (Atendimento a : filaAtendimentos) {
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

    public void AlocarAtendimentos(ArrayList<Equipe> listaEquipes) {
        while(!filaAtendimentos.isEmpty()) {
            Atendimento a = filaAtendimentos.peek();
            for (Equipe e : listaEquipes) {
                if(calculaDistancia(e, a.getEvento()) <= 5) {
                    Atendimento atend = filaAtendimentos.poll();
                    if(e.getDisponivel() == true){
                        atend.setEquipe(e);
                        e.setDisponivel(false);
                        atend.setStatus(STATUS.EXECUTANDO);
                        filaAtendimentosExecutando.add(atend);
                    } else filaAtendimentosPendentes.add(atend);
                }
            }
            Atendimento cancelado = filaAtendimentos.poll();
            cancelado.setStatus(STATUS.CANCELADO);
            filaAtendimentosCancelados.add(filaAtendimentos.poll());
        }
    }

    public String listarTodos() {
        String str = "";
        if(!filaAtendimentos.isEmpty()){
            str += "Atendidos: ";
            for (Atendimento a:filaAtendimentos) {
                str += a;
            }
        }

        if(!filaAtendimentosPendentes.isEmpty()){
            str += "Pendentes: ";
            for (Atendimento a:filaAtendimentosPendentes) {
                str += a;
            }
        }

        if(!filaAtendimentosCancelados.isEmpty()){
            str += "Cancelados: ";
            for (Atendimento a:filaAtendimentosCancelados) {
                str += a;
            }
        }
        return str;
    }
}
