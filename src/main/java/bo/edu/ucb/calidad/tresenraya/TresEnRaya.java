package bo.edu.ucb.calidad.tresenraya;

import java.util.Arrays;
import java.util.Optional;

/**
 * Dominio de una partida de Tres en Raya. Las coordenadas públicas usan filas y
 * columnas de 1 a 3 para mantener el lenguaje de la kata.
 */
public final class TresEnRaya {
    private static final int TAMANO = 3;
    private final Jugador[][] tablero = new Jugador[TAMANO][TAMANO];
    private Jugador turno = Jugador.X;
    private EstadoPartida estado = EstadoPartida.EN_CURSO;
    private Jugador ganador;
    private int jugadas;

    public ResultadoJugada jugar(int fila, int columna) {
        validarPartidaEnCurso();
        validarCoordenada(fila, "fila");
        validarCoordenada(columna, "columna");

        int f = fila - 1;
        int c = columna - 1;
        if (tablero[f][c] != null) {
            throw new IllegalStateException("La posición ya está ocupada");
        }

        Jugador jugadorActual = turno;
        tablero[f][c] = jugadorActual;
        jugadas++;

        if (formaLinea(jugadorActual)) {
            estado = EstadoPartida.GANADA;
            ganador = jugadorActual;
        } else if (jugadas == TAMANO * TAMANO) {
            estado = EstadoPartida.EMPATE;
        } else {
            turno = alternar(jugadorActual);
        }

        return new ResultadoJugada(estado, jugadorActual, Optional.ofNullable(ganador));
    }

    public Jugador turnoActual() {
        return turno;
    }

    public EstadoPartida estado() {
        return estado;
    }

    public Optional<Jugador> ganador() {
        return Optional.ofNullable(ganador);
    }

    public Optional<Jugador> casilla(int fila, int columna) {
        validarCoordenada(fila, "fila");
        validarCoordenada(columna, "columna");
        return Optional.ofNullable(tablero[fila - 1][columna - 1]);
    }

    public Jugador[][] tablero() {
        return Arrays.stream(tablero).map(Jugador[]::clone).toArray(Jugador[][]::new);
    }

    private void validarPartidaEnCurso() {
        if (estado != EstadoPartida.EN_CURSO) {
            throw new IllegalStateException("La partida ya terminó");
        }
    }

    private static void validarCoordenada(int valor, String eje) {
        if (valor < 1 || valor > TAMANO) {
            throw new IllegalArgumentException("La " + eje + " debe estar entre 1 y 3");
        }
    }

    private boolean formaLinea(Jugador jugador) {
        for (int i = 0; i < TAMANO; i++) {
            if (tablero[i][0] == jugador && tablero[i][1] == jugador && tablero[i][2] == jugador) {
                return true;
            }
            if (tablero[0][i] == jugador && tablero[1][i] == jugador && tablero[2][i] == jugador) {
                return true;
            }
        }
        return tablero[0][0] == jugador && tablero[1][1] == jugador && tablero[2][2] == jugador
                || tablero[0][2] == jugador && tablero[1][1] == jugador && tablero[2][0] == jugador;
    }

    private static Jugador alternar(Jugador jugador) {
        return jugador == Jugador.X ? Jugador.O : Jugador.X;
    }
}
