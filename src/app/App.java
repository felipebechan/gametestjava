package app;

import util.MapLoader;
import util.MapaData;
import control.GameController;
import control.MapView;
import engine.GameLoop;
import input.InputHandler;
import view.GameWindow;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String ruta = args.length > 0 ? args[0] : "maps/nivel1.map";

        try {
            MapaData datos = MapLoader.cargar(ruta);

            MapView vista         = new MapView(datos.mapa);
            GameController controller = new GameController(datos.mapa, datos.jugador, datos.enemigos, datos.meta, vista);
            InputHandler input    = new InputHandler(controller);

            vista.addKeyListener(input);
            vista.setFocusable(true);

            new GameWindow(vista);
            new GameLoop(controller, 500).iniciar();

        } catch (IOException e) {
            System.err.println("Error al cargar el mapa: " + e.getMessage());
        }
    }
}
