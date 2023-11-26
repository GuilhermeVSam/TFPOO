package Dados.Atendimentos;

public enum STATUS {
    PENDENTE("Pendente"), EXECUTANDO("Executando"), FINALIZADO("Finalizado"), CANCELADO("Cancelado");

    private String descricao;

    private STATUS(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

    public static STATUS getSTATUS(String descricao){
        for(STATUS status : STATUS.values()){
            if(status.getDescricao().equals(descricao)){
                return status;
            }
        }
        return null;
    }
}
