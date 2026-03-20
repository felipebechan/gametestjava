package view;

import control.MapView;
import javax.swing.JFrame;

public class GameWindow {

    public GameWindow(MapView vista) {
        JFrame frame = new JFrame("juego test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vista);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
