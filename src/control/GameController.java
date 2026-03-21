package control;

import engine.CollisionSystem;
import engine.EnemyAI;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.Mapa;
import model.ObjetoEnMapa;
import util.MapLoader;
import util.MapaData;
import java.io.IOException;
import java.util.List;

public class GameController {
    private Mapa mapa;
    private ObjetoEnMapa jugador;
    private List<ObjetoEnMapa> enemigos;
    private ObjetoEnMapa meta;
    private final MapView vista;
    private final EnemyAI enemigoAI = new EnemyAI();
    private final CollisionSystem colisiones = new CollisionSystem();
    private int nivelActual;
    private final String rutaBase; // e.g. "maps/nivel%d.map"

    public GameController(Mapa mapa, ObjetoEnMapa jugador, List<ObjetoEnMapa> enemigos,
                          ObjetoEnMapa meta, MapView vista, int nivelActual, String rutaBase) {
        this.mapa        = mapa;
        this.jugador     = jugador;
        this.enemigos    = enemigos;
        this.meta        = meta;
        this.vista       = vista;
        this.nivelActual = nivelActual;
        this.rutaBase    = rutaBase;
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
            cargarSiguienteNivel();
            return;
        }
        for (ObjetoEnMapa enemigo : enemigos) {
            if (colisiones.hayDerrota(jugador, enemigo)) {
                JOptionPane.showMessageDialog(null, "¡DERROTA!");
                System.exit(0);
            }
        }
    }

    private void cargarSiguienteNivel() {
        int siguiente = nivelActual + 1;
        String ruta = String.format(rutaBase, siguiente);

        try {
            MapaData datos = MapLoader.cargar(ruta);
            this.mapa     = datos.mapa;
            this.jugador  = datos.jugador;
            this.enemigos = datos.enemigos;
            this.meta     = datos.meta;
            this.nivelActual = siguiente;

            vista.setMapa(datos.mapa);
            SwingUtilities.getWindowAncestor(vista).pack();
            vista.repaint();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "¡COMPLETASTE EL JUEGO!");
            System.exit(0);
        }
    }
}
