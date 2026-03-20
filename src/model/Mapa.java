package model;

public class Mapa {
    private final int alto;
    private final int ancho;
    private final Entidad[] entidades;

    public Mapa(int alto, int ancho, Entidad[] entidades) {
        this.alto      = alto;
        this.ancho     = ancho;
        this.entidades = entidades;
    }

    public int getAncho()          { return ancho; }
    public int getAlto()           { return alto; }
    public Entidad[] getEntidades(){ return entidades; }

    public boolean esSolido(int x, int y) {
        for (Entidad e : entidades) {
            if (e.getX() == x && e.getY() == y && e.esSolido()) return true;
        }
        return false;
    }
}
