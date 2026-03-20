package control;

import java.awt.*;
import javax.swing.*;
import model.Entidad;
import model.Mapa;

public class MapView extends JPanel {
    private final Mapa mapa;
    private final int tamañoCelda = 30;

    public MapView(Mapa mapa) {
        this.mapa = mapa;
        setPreferredSize(new Dimension(mapa.getAncho() * tamañoCelda, mapa.getAlto() * tamañoCelda));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < mapa.getAlto(); y++) {
            for (int x = 0; x < mapa.getAncho(); x++) {
                int px = x * tamañoCelda;
                int py = y * tamañoCelda;
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(px, py, tamañoCelda, tamañoCelda);
                g.setColor(Color.DARK_GRAY);
                g.drawRect(px, py, tamañoCelda, tamañoCelda);
            }
        }

// Dibuja entidades .-.-.-.-.-.-.-.--.-.-.-.-.-.
        g.setFont(new Font("Monospaced", Font.BOLD, 24));

        for (Entidad ent : mapa.getEntidades()) {
            int px = ent.getX() * tamañoCelda;
            int py = ent.getY() * tamañoCelda;

            if (ent.esSolido()) {
                g.setColor(new Color(50, 50, 50));
                g.fillRect(px, py, tamañoCelda, tamañoCelda);
                g.setColor(Color.BLACK);
                g.drawRect(px, py, tamañoCelda, tamañoCelda);
            } else {
                g.setColor(colorParaSprite(ent.getSprite()));
                g.drawString(String.valueOf(ent.getSprite()), px + 8, py + 24);
            }
        }
    }

    private Color colorParaSprite(char sprite) {
        switch (sprite) {
            case '@': return Color.BLUE.darker();
            case 'E': return Color.RED.darker();
            case 'G': return Color.GREEN.darker();
            default:  return Color.BLACK;
        }
    }
}
