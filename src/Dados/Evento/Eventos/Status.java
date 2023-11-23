package Dados.Evento.Eventos;

public enum Status {
    PENDENTE("PENDENTE"), EXECUTANDO("EXECUTANDO"), FINALIZADO("FINALIZADO"), CANCELADO("CANCELADO");

    private String nome;

    Status(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public String buscaNome(String nome){
        for (Status e : Status.values()) {
            if(e.getNome().equals(nome)){
                return e.getNome();
            }
        }
        return null;
    }
}
