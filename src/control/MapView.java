package control;

import java.awt.*;
import javax.swing.*;
import model.Mapa;
import model.ObjetoEnMapa;

public class MapView extends JPanel {
    private Mapa mapa; 
    private final int tamañoCelda = 30;

    public MapView(Mapa mapa) {
        this.mapa = mapa;
        int anchoTotal = mapa.getAncho() * tamañoCelda;
        int altoTotal = mapa.getAlto() * tamañoCelda;
        setPreferredSize(new Dimension(anchoTotal, altoTotal)); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int altoMapa = mapa.getAlto();
        int anchoMapa = mapa.getAncho();
        
        // 1. dibujar matriz fondo
        for (int y = 0; y < altoMapa; y++) {
            for (int x = 0; x < anchoMapa; x++) {
                int px = x * tamañoCelda;
                int py = y * tamañoCelda;
                
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(px, py, tamañoCelda, tamañoCelda);
                g.setColor(Color.DARK_GRAY);
                g.drawRect(px, py, tamañoCelda, tamañoCelda);
            }
        }
        
        // 2. dibujar chars
        g.setFont(new Font("Monospaced", Font.BOLD, 24));
        
        for (ObjetoEnMapa obj : mapa.getObjetos()) {
            int x = obj.getX() * tamañoCelda;
            int y = obj.getY() * tamañoCelda;
            char spriteChar = obj.getSprite();

            if (spriteChar == '@') {
                g.setColor(Color.BLUE.darker());
            } else if (spriteChar == 'E') {
                g.setColor(Color.RED.darker());
            } else if (spriteChar == 'G') { // Meta
                g.setColor(Color.GREEN.darker());
            } else {
                g.setColor(Color.BLACK);
            }
            
            g.drawString(String.valueOf(spriteChar), x + 8, y + 24);
        }
    }
}