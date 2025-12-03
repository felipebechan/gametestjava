package app;

import model.Mapa;
import model.ObjetoEnMapa;
import control.Controlador;
import control.MapView;


public class App {
    public static void main(String[] args) {
        // crea los objetos del juego y los pasa al controlador y la vista
        ObjetoEnMapa jugador = new ObjetoEnMapa(1, 1, '@');
        ObjetoEnMapa enemigo = new ObjetoEnMapa(8, 8, 'E'); // pos inicial
        ObjetoEnMapa meta    = new ObjetoEnMapa(9, 9, 'G'); // La meta
        
        // mapa dibuja
        ObjetoEnMapa[] objetos = {jugador, enemigo, meta};
        
        // El es mapa de 10x10
        Mapa mapa = new Mapa(10, 10, objetos);

        // crea la vista del mapa
        MapView vista = new MapView(mapa);
        
        // crea el controlador
        Controlador controlador = new Controlador(mapa, jugador, enemigo, meta, vista);
        
        // inicia el juego y controlador
        controlador.iniciar();
    }
}