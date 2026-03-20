package engine;

import model.Mapa;
import model.ObjetoEnMapa;
import java.util.*;

public class EnemyAI {

    public void mover(ObjetoEnMapa enemigo, ObjetoEnMapa jugador, Mapa mapa) {
        int[] siguientePaso = bfs(
            enemigo.getX(), enemigo.getY(),
            jugador.getX(), jugador.getY(),
            mapa
        );

        if (siguientePaso != null) {
            int dx = siguientePaso[0] - enemigo.getX();
            int dy = siguientePaso[1] - enemigo.getY();
            enemigo.mover(dx, dy);
        }
    }

    private int[] bfs(int origenX, int origenY, int metaX, int metaY, Mapa mapa) {
        if (origenX == metaX && origenY == metaY) return null;

        int ancho = mapa.getAncho();
        int alto  = mapa.getAlto();
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        boolean[][] visitado = new boolean[ancho][alto];
        int[][][] padre     = new int[ancho][alto][2];
        for (int[][] fila : padre)
            for (int[] celda : fila) { celda[0] = -1; celda[1] = -1; }

        Queue<int[]> cola = new LinkedList<>();
        cola.add(new int[]{origenX, origenY});
        visitado[origenX][origenY] = true;

        boolean encontrado = false;

        while (!cola.isEmpty()) {
            int[] actual = cola.poll();
            int cx = actual[0], cy = actual[1];

            if (cx == metaX && cy == metaY) {
                encontrado = true;
                break;
            }

            for (int[] dir : dirs) {
                int nx = cx + dir[0];
                int ny = cy + dir[1];

                boolean transitable = nx >= 0 && nx < ancho
                                   && ny >= 0 && ny < alto
                                   && !visitado[nx][ny]
                                   && !mapa.esSolido(nx, ny); // respeta paredes

                if (transitable) {
                    visitado[nx][ny]  = true;
                    padre[nx][ny][0]  = cx;
                    padre[nx][ny][1]  = cy;
                    cola.add(new int[]{nx, ny});
                }
            }
        }

        if (!encontrado) return null;

        int cx = metaX, cy = metaY;
        while (true) {
            int px = padre[cx][cy][0];
            int py = padre[cx][cy][1];
            if (px == origenX && py == origenY) return new int[]{cx, cy};
            cx = px;
            cy = py;
        }
    }
}
