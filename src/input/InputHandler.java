package input;

import control.GameController;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    private GameController controller;

    public InputHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int dx = 0, dy = 0;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: case KeyEvent.VK_UP:dy =-1; break;
            case KeyEvent.VK_S: case KeyEvent.VK_DOWN:dy =1; break;
            case KeyEvent.VK_A: case KeyEvent.VK_LEFT:dx =-1; break;
            case KeyEvent.VK_D: case KeyEvent.VK_RIGHT:dx =1; break;
            case KeyEvent.VK_Q: System.exit(0); return;
        }

        if (dx != 0 || dy != 0) {
            controller.moverJugador(dx, dy);
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
