package app;

import model.Mapa;
import model.ObjetoEnMapa;
import control.GameController;
import control.MapView;
import engine.GameLoop;
import input.InputHandler;
import view.GameWindow;

public class App {
    public static void main(String[] args) {
        ObjetoEnMapa jugador = new ObjetoEnMapa(1, 1, '@');
        ObjetoEnMapa enemigo = new ObjetoEnMapa(8, 8, 'E');
        ObjetoEnMapa meta    = new ObjetoEnMapa(9, 9, 'G');

        Mapa mapa = new Mapa(10, 10, new ObjetoEnMapa[]{jugador, enemigo, meta});

        MapView vista         = new MapView(mapa);
        GameController controller = new GameController(mapa, jugador, enemigo, meta, vista);
        InputHandler input    = new InputHandler(controller);

        vista.addKeyListener(input);
        vista.setFocusable(true);

        new GameWindow(vista);

        new GameLoop(controller, 500).iniciar();
    }
}
