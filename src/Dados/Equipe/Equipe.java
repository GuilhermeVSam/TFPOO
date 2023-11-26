package Dados.Equipe;
public class Equipe {
private String codinome;
private int quantidade;
private double longitude;
private double latitude;
private boolean disponivel;
public Equipe(String codinome, int quantidade, double latitude, double longitude){
    this.codinome = codinome;
    this.quantidade = quantidade;
    this.latitude = latitude;
    this.longitude = longitude;
    this.disponivel = true;
}
    public String getCodinome(){
    return codinome;
    }
    public int getQuantidade(){
    return quantidade;
    }
    public double getLongitude(){
    return longitude;
    }
   public double getLatitude() {
       return latitude;
   }
   public boolean getDisponivel(){
    return disponivel;
   }
    public void setDisponivel(boolean disponivel){
    this.disponivel = disponivel;
   }
    public void setCodinome(String codinome) {
        this.codinome = codinome;
    }
    public void setQuantidade(int quantidade){
    this.quantidade = quantidade;
    }
    public void setLongitude(double longitude){
    this.longitude = longitude;
    }
    public void setLatitude(double latitude){
    this.latitude = latitude;
    }
}
