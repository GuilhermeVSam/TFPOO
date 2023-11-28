package Dados.Evento.Eventos;

public abstract class Evento {
    private String codigo;
    private String data;
    private double latitude;
    private double longitude;
    public boolean atendido;

    public Evento(String codigo, String data, double latitude, double longitude) {
        this.codigo = codigo.toUpperCase();
        this.data = data;
        this.latitude = latitude;
        this.longitude = longitude;
        this.atendido = false;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getData() {
        return data;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public boolean getAtendido() {
        return atendido;
    }

    @Override
    public String toString() {
        return  "Codigo: " + codigo + "\n" +
                "Data: " + data + "\n" +
                "Latitude: " + latitude + "\n" +
                "Longitude: " + longitude;
    }

    public void setAtendido(boolean b) {
        this.atendido = b;
    }
}