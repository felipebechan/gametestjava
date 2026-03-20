package model;

public class Pared extends Entidad {

    public Pared(int x, int y) {
        super(x, y, '#');
    }

    @Override
    public boolean esSolido() { return true; }
}
