package control;

import engine.CollisionSystem;
import engine.EnemyAI;
import javax.swing.JOptionPane;
import model.Mapa;
import model.ObjetoEnMapa;
import java.util.List;

public class GameController {
    private final Mapa mapa;
    private final ObjetoEnMapa jugador;
    private final List<ObjetoEnMapa> enemigos;
    private final ObjetoEnMapa meta;
    private final MapView vista;
    private final EnemyAI enemigoAI = new EnemyAI();
    private final CollisionSystem colisiones = new CollisionSystem();

    public GameController(Mapa mapa, ObjetoEnMapa jugador, List<ObjetoEnMapa> enemigos,
                          ObjetoEnMapa meta, MapView vista) {
        this.mapa     = mapa;
        this.jugador  = jugador;
        this.enemigos = enemigos;
        this.meta     = meta;
        this.vista    = vista;
    }

    public void tick() {
        for (ObjetoEnMapa enemigo : enemigos) {
            enemigoAI.mover(enemigo, jugador, mapa);
        }
        comprobarCondiciones();
        vista.repaint();
    }

    public void moverJugador(int dx, int dy) {
        int nuevoX = jugador.getX() + dx;
        int nuevoY = jugador.getY() + dy;

        boolean dentroDelMapa = nuevoX >= 0 && nuevoX < mapa.getAncho()
                             && nuevoY >= 0 && nuevoY < mapa.getAlto();

        if (dentroDelMapa && !mapa.esSolido(nuevoX, nuevoY)) {
            jugador.mover(dx, dy);
            comprobarCondiciones();
            vista.repaint();
        }
    }

    private void comprobarCondiciones() {
        if (colisiones.hayVictoria(jugador, meta)) {
            JOptionPane.showMessageDialog(null, "¡VICTORIA!");
            System.exit(0);
        }
        for (ObjetoEnMapa enemigo : enemigos) {
            if (colisiones.hayDerrota(jugador, enemigo)) {
                JOptionPane.showMessageDialog(null, "¡DERROTA!");
                System.exit(0);
            }
        }
    }
}
