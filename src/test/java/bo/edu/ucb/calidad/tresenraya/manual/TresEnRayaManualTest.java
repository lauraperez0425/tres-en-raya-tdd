package bo.edu.ucb.calidad.tresenraya.manual;

import bo.edu.ucb.calidad.tresenraya.EstadoPartida;
import bo.edu.ucb.calidad.tresenraya.Jugador;
import bo.edu.ucb.calidad.tresenraya.ResultadoJugada;
import bo.edu.ucb.calidad.tresenraya.TresEnRaya;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Pruebas diseñadas manualmente siguiendo ciclos Rojo-Verde-Refactor. */
class TresEnRayaManualTest {
    private TresEnRaya juego;

    @BeforeEach
    void crearPartida() {
        juego = new TresEnRaya();
    }

    @Test
    void laPrimeraJugadaPerteneceAX() {
        ResultadoJugada resultado = juego.jugar(2, 2);

        assertEquals(Jugador.X, resultado.jugador());
        assertEquals(Jugador.X, juego.casilla(2, 2).orElseThrow());
        assertEquals(Jugador.O, juego.turnoActual());
    }

    @Test
    void alternaLosTurnosEntreXyO() {
        juego.jugar(1, 1);
        juego.jugar(1, 2);
        juego.jugar(2, 1);

        assertEquals(Jugador.X, juego.casilla(1, 1).orElseThrow());
        assertEquals(Jugador.O, juego.casilla(1, 2).orElseThrow());
        assertEquals(Jugador.X, juego.casilla(2, 1).orElseThrow());
        assertEquals(Jugador.O, juego.turnoActual());
    }

    @Test
    void rechazaUnaPosicionOcupadaSinConsumirElTurno() {
        juego.jugar(1, 1);

        IllegalStateException error = assertThrows(IllegalStateException.class, () -> juego.jugar(1, 1));

        assertEquals("La posición ya está ocupada", error.getMessage());
        assertEquals(Jugador.O, juego.turnoActual());
    }

    @Test
    void rechazaCoordenadasFueraDelTablero() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> juego.jugar(0, 1)),
                () -> assertThrows(IllegalArgumentException.class, () -> juego.jugar(4, 1)),
                () -> assertThrows(IllegalArgumentException.class, () -> juego.jugar(1, 0)),
                () -> assertThrows(IllegalArgumentException.class, () -> juego.jugar(1, 4))
        );
    }

    @Test
    void detectaVictoriaHorizontal() {
        juego.jugar(1, 1); // X
        juego.jugar(2, 1); // O
        juego.jugar(1, 2); // X
        juego.jugar(2, 2); // O

        ResultadoJugada resultado = juego.jugar(1, 3);

        assertEquals(EstadoPartida.GANADA, resultado.estado());
        assertEquals(Jugador.X, resultado.ganador().orElseThrow());
    }

    @Test
    void detectaVictoriaVertical() {
        juego.jugar(1, 1); // X
        juego.jugar(1, 2); // O
        juego.jugar(2, 1); // X
        juego.jugar(2, 2); // O

        assertEquals(EstadoPartida.GANADA, juego.jugar(3, 1).estado());
    }

    @Test
    void detectaVictoriaEnDiagonalPrincipal() {
        juego.jugar(1, 1); // X
        juego.jugar(1, 2); // O
        juego.jugar(2, 2); // X
        juego.jugar(1, 3); // O

        assertEquals(Jugador.X, juego.jugar(3, 3).ganador().orElseThrow());
    }

    @Test
    void detectaEmpateCuandoSeLlenaElTableroSinGanador() {
        int[][] jugadas = {{1,1}, {1,2}, {1,3}, {2,1}, {2,3}, {2,2}, {3,1}, {3,3}, {3,2}};
        ResultadoJugada resultado = null;
        for (int[] jugada : jugadas) {
            resultado = juego.jugar(jugada[0], jugada[1]);
        }

        assertNotNull(resultado);
        assertEquals(EstadoPartida.EMPATE, resultado.estado());
        assertTrue(resultado.ganador().isEmpty());
    }
}
