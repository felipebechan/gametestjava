package model;

public class ObjetoEnMapa extends Entidad {

    public ObjetoEnMapa(int x, int y, char sprite) {
        super(x, y, sprite);
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
