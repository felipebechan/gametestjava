package model;

public abstract class Entidad {
    protected int x;
    protected int y;
    protected char sprite;

    public Entidad(int x, int y, char sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public int getX()       { return x; }
    public int getY()       { return y; }
    public char getSprite() { return sprite; }

    public boolean esSolido() { return false; }
}
