package Dados.Atendimentos;
import Dados.Equipe.Cadastro;
import Dados.Equipe.Equipe;
import Dados.Evento.Eventos.Evento;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class ListaAtendimentos {
    private ArrayList<Atendimento> listaAtendimentos;

    public ListaAtendimentos(){
        listaAtendimentos = new ArrayList<>();
    }

    public void addAtendimento(Atendimento atendimento){
        listaAtendimentos.add(atendimento);
    }
    public ArrayList<Atendimento> getListaAtendimentos() {
        return new ArrayList<>(listaAtendimentos);
    }
    public double calculaDistancia(Equipe a, Evento b){
/*        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("##.###KM", symbols);*/

        double LatEquipe = a.getLatitude();
        double LongEquipe = a.getLongitude();
        double LatEvento = b.getLatitude();
        double LongEvento = b.getLongitude();

        double X1ToRad = Math.toRadians(LatEquipe);
        double X2ToRad = Math.toRadians(LatEvento);

        double deltaLongitude = Math.toRadians(LongEquipe - LongEvento);

        return Math.acos(Math.cos(X1ToRad) * Math.cos(X2ToRad) * Math.cos(deltaLongitude) + Math.sin(X1ToRad) * Math.sin(X2ToRad)) * 6.371;
    }
    public int pesquisaCodEvento(int cod) {
        for (Atendimento a : listaAtendimentos) {
            if (cod == a.getCod()) {
                return a.getCod();
            }
        }
        return -1;
    }

    public boolean pesquisaStatus(int codi) {
        for (Atendimento a : listaAtendimentos) {
            return codi == a.getCod();
        }
        return false;
    }

    //Alocar atendimentos (a partir da fila de atendimentos pendentes; o sistema fará a
    //alocação automática de um atendimento a uma equipe. Verifica se é possível designar
    //alguma equipe disponível para cada atendimento, e atualiza o seu estado. Se há
    //alguma equipe dentro da distância máxima, mas já está alocada para outro
    //atendimento, o atendimento retorna para a fila de atendimentos pendentes. Se não há
    //nenhuma equipe dentro da distância máxima para fazer o atendimento, o atendimento
    //muda para a situação CANCELADO [se não há atendimentos na fila de atendimentos
    //pendentes, mostra uma mensagem de erro]).

    public void AlocarAtendimentos(Cadastro listaEquipes){
        for (Atendimento a : listaAtendimentos) {
            if(a.getStatus() == STATUS.PENDENTE){
                for (Equipe e : listaEquipes.getEquipes()) {
                    if(calculaDistancia(e, a.getEvento()) <= 5000){
                        if(e.getDisponivel()) {
                            a.setStatus(STATUS.EXECUTANDO);
                            a.setEquipe(e);
                            e.setDisponivel(false);
                            break;
                        }
                    } else{
                        a.setStatus(STATUS.CANCELADO);
                    }
                }
            }
        }
    }

}
