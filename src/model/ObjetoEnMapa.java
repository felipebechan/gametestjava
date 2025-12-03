package model;

public class ObjetoEnMapa {
    private int x;
    private int y;
    private char sprite;

    public ObjetoEnMapa(int x, int y, char sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public char getSprite() {
        return sprite;
    }

    public void mover(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void moverA(int nuevoX, int nuevoY) {
        this.x = nuevoX;
        this.y = nuevoY;
    }
}