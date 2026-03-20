package engine;

import model.ObjetoEnMapa;

public class CollisionSystem {

    public boolean hayVictoria(ObjetoEnMapa jugador, ObjetoEnMapa meta) {
        return jugador.getX() == meta.getX() && jugador.getY() == meta.getY();
    }

    public boolean hayDerrota(ObjetoEnMapa jugador, ObjetoEnMapa enemigo) {
        return jugador.getX() == enemigo.getX() && jugador.getY() == enemigo.getY();
    }
}
