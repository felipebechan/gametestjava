package engine;

import model.Mapa;
import model.ObjetoEnMapa;
import java.util.Random;

public class EnemyAI {
    private final Random random = new Random();

    public void mover(ObjetoEnMapa enemigo, ObjetoEnMapa jugador, Mapa mapa) {
        int dx = 0, dy = 0;

        if (random.nextBoolean()) {
            if      (jugador.getX() > enemigo.getX()) dx =  1;
            else if (jugador.getX() < enemigo.getX()) dx = -1;
        } else {
            if      (jugador.getY() > enemigo.getY()) dy =  1;
            else if (jugador.getY() < enemigo.getY()) dy = -1;
        }

        int nuevoX = enemigo.getX() + dx;
        int nuevoY = enemigo.getY() + dy;

        if (nuevoX >= 0 && nuevoX < mapa.getAncho() &&
            nuevoY >= 0 && nuevoY < mapa.getAlto()) {
            enemigo.mover(dx, dy);
        }
    }
}
