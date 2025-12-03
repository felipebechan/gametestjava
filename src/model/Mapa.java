package model;

public class Mapa {
    private int alto;
    private int ancho;
    private ObjetoEnMapa[] objetos;

    public Mapa(int alto, int ancho, ObjetoEnMapa[] objetos) {
        this.alto = alto;
        this.ancho = ancho;
        this.objetos = objetos;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
    
    
    public ObjetoEnMapa[] getObjetos() {
        return objetos;
    }
}