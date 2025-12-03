package control;

import model.Mapa;
import model.ObjetoEnMapa;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame; 
import javax.swing.JOptionPane;
import java.util.Random;

public class Controlador implements KeyListener {
    private Mapa mapa;
    private ObjetoEnMapa jugador;
    private ObjetoEnMapa enemigo; 
    private ObjetoEnMapa meta;    
    private MapView vista; 
    private Random random = new Random();

    public Controlador(Mapa mapa, ObjetoEnMapa jugador, ObjetoEnMapa enemigo, ObjetoEnMapa meta, MapView vista) {
        this.mapa = mapa;
        this.jugador = jugador;
        this.enemigo = enemigo;
        this.meta = meta;
        this.vista = vista;
        
        vista.addKeyListener(this);
        vista.setFocusable(true); 
    }

    public void iniciar() {
        JFrame frame = new JFrame("Movement Challenge (Esquiva al Enemigo)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vista);
        frame.pack();
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
    
    
    private void actualizarJuego() {
        // 1. mover al enemigo
        moverEnemigo();

        // 2. comprobar condiciones de fin
        comprobarVictoria();
        comprobarDerrota();

        // 3. redibujar la vista
        vista.repaint(); 
    }

    private void moverEnemigo() {
        int dx = 0;
        int dy = 0;

        // movimiento simple: El enemigo intenta reducir la distancia en un eje (X o Y) al azar.
        if (random.nextBoolean()) { 
            // intentar moverse en X
            if (jugador.getX() > enemigo.getX()) dx = 1;
            else if (jugador.getX() < enemigo.getX()) dx = -1;
        } else {
            // intentar moverse en Y
            if (jugador.getY() > enemigo.getY()) dy = 1;
            else if (jugador.getY() < enemigo.getY()) dy = -1;
        }
        
        // evitar salir del mapa
        int nuevoX = enemigo.getX() + dx;
        int nuevoY = enemigo.getY() + dy;

        if (nuevoX >= 0 && nuevoX < mapa.getAncho() &&
            nuevoY >= 0 && nuevoY < mapa.getAlto()) {
            enemigo.mover(dx, dy);
        }
    }

    private void moverJugador(int dx, int dy) {
        int nuevoX = jugador.getX() + dx;
        int nuevoY = jugador.getY() + dy;

        if (nuevoX >= 0 && nuevoX < mapa.getAncho() &&
            nuevoY >= 0 && nuevoY < mapa.getAlto()) {
            jugador.mover(dx, dy);
            // solo si se mueve el jugador, se actualiza el juego
            actualizarJuego(); 
        }
    }

    private void comprobarVictoria() {
        if (jugador.getX() == meta.getX() && jugador.getY() == meta.getY()) {
            JOptionPane.showMessageDialog(null, "¡VICTORIA! ");
            System.exit(0);
        }
    }

    private void comprobarDerrota() {
        if (jugador.getX() == enemigo.getX() && jugador.getY() == enemigo.getY()) {
            JOptionPane.showMessageDialog(null, "¡DERROTA!");
            System.exit(0);
        }
    }
    
    // --- KeyListener ---

    @Override
    public void keyPressed(KeyEvent e) {
        int dx = 0, dy = 0;
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:    dy = -1; break; 
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:  dy = 1; break;  
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:  dx = -1; break; 
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT: dx = 1; break;  
            case KeyEvent.VK_Q: System.exit(0); return;
        }

        if (dx != 0 || dy != 0) {
            moverJugador(dx, dy);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {} 
    @Override
    public void keyReleased(KeyEvent e) {} 
}