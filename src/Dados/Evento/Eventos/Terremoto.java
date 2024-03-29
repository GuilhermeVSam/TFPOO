package Dados.Evento.Eventos;

public class Terremoto extends Evento{
    private double magnitude;

    public Terremoto(String codigo, String data, double latitude, double longitude, double magnitude) throws Exception {
        super(codigo, data, latitude, longitude);
        if(magnitude >= 1 && magnitude <= 10) {
            this.magnitude = magnitude;
        } else throw new Exception("ERRO (Magnitude): Utilize números inteiros entre 1 e 10");
    }

    public double getMagnitude() {
        return magnitude;
    }

    @Override
    public String toString() {
        return "Terremoto: \n" + super.toString() + "\n" +
        "Magnitude: " + magnitude;
    }
}
