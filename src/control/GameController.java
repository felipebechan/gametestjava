package control;

import engine.CollisionSystem;
import engine.EnemyAI;
import model.Mapa;
import model.ObjetoEnMapa;
import javax.swing.JOptionPane;

public class GameController {
    private final Mapa mapa;
    private final ObjetoEnMapa jugador;
    private final ObjetoEnMapa enemigo;
    private final ObjetoEnMapa meta;
    private final MapView vista;
    private final EnemyAI enemigoAI = new EnemyAI();
    private final CollisionSystem colisiones = new CollisionSystem();

    public GameController(Mapa mapa, ObjetoEnMapa jugador, ObjetoEnMapa enemigo,
                          ObjetoEnMapa meta, MapView vista) {
        this.mapa     = mapa;
        this.jugador  = jugador;
        this.enemigo  = enemigo;
        this.meta     = meta;
        this.vista    = vista;
    }

    public void moverJugador(int dx, int dy) {
        int nuevoX = jugador.getX() + dx;
        int nuevoY = jugador.getY() + dy;

        if (nuevoX >= 0 && nuevoX < mapa.getAncho() &&
            nuevoY >= 0 && nuevoY < mapa.getAlto()) {
            jugador.mover(dx, dy);
            actualizarJuego();
        }
    }

    private void actualizarJuego() {
        enemigoAI.mover(enemigo, jugador, mapa);

        if (colisiones.hayVictoria(jugador, meta)) {
            JOptionPane.showMessageDialog(null, "¡VICTORIA!");
            System.exit(0);
        }
        if (colisiones.hayDerrota(jugador, enemigo)) {
            JOptionPane.showMessageDialog(null, "¡DERROTA!");
            System.exit(0);
        }

        vista.repaint();
    }
}
