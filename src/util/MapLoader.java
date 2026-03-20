package util;

import model.*;
import java.io.*;
import java.util.*;

public class MapLoader {

    /**
     * Carga un mapa desde un archivo de texto.
     *
     * Caracteres reconocidos:
     *   #  = Pared (solida, bloquea jugador y enemigo)
     *   @  = Posicion inicial del jugador
     *   E  = Posicion inicial del enemigo
     *   G  = Meta
     *   .  = celda vacia (cualquier otro caracter tambien se ignora)
     */
    public static MapaData cargar(String ruta) throws IOException {
        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        }

        if (lineas.isEmpty()) throw new IOException("El archivo de mapa esta vacio: " + ruta);

        int alto  = lineas.size();
        int ancho = lineas.stream().mapToInt(String::length).max().orElse(0);

        List<Entidad> entidades     = new ArrayList<>();
        List<ObjetoEnMapa> enemigos = new ArrayList<>();
        ObjetoEnMapa jugador = null, meta = null;

        for (int y = 0; y < alto; y++) {
            String linea = lineas.get(y);
            for (int x = 0; x < linea.length(); x++) {
                char c = linea.charAt(x);
                switch (c) {
                    case '#':
                        entidades.add(new Pared(x, y));
                        break;
                    case '@':
                        jugador = new ObjetoEnMapa(x, y, '@');
                        entidades.add(jugador);
                        break;
                    case 'E':
                        ObjetoEnMapa enemigo = new ObjetoEnMapa(x, y, 'E');
                        enemigos.add(enemigo);
                        entidades.add(enemigo);
                        break;
                    case 'G':
                        meta = new ObjetoEnMapa(x, y, 'G');
                        entidades.add(meta);
                        break;
                    // '.' y cualquier otro caracter = celda vacia, se ignora
                }
            }
        }

        if (jugador == null)    throw new IOException("El mapa no tiene jugador (@): " + ruta);
        if (enemigos.isEmpty()) throw new IOException("El mapa no tiene enemigo (E): "  + ruta);
        if (meta == null)       throw new IOException("El mapa no tiene meta (G): "     + ruta);

        Mapa mapa = new Mapa(alto, ancho, entidades.toArray(new Entidad[0]));
        return new MapaData(mapa, jugador, enemigos, meta);
    }
}
