package engine;

import javax.swing.Timer;
import control.GameController;

public class GameLoop {
    private final Timer timer;

    public GameLoop(GameController controller, int msPorTick) {
        timer = new Timer(msPorTick, e -> controller.tick());
    }

    public void iniciar() {
        timer.start();
    }

    public void detener() {
        timer.stop();
    }
}