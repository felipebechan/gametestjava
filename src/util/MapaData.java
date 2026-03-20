package util;

import model.Mapa;
import model.ObjetoEnMapa;
import java.util.List;

public class MapaData {
    public final Mapa mapa;
    public final ObjetoEnMapa jugador;
    public final List<ObjetoEnMapa> enemigos;
    public final ObjetoEnMapa meta;

    public MapaData(Mapa mapa, ObjetoEnMapa jugador, List<ObjetoEnMapa> enemigos, ObjetoEnMapa meta) {
        this.mapa     = mapa;
        this.jugador  = jugador;
        this.enemigos = enemigos;
        this.meta     = meta;
    }
}
