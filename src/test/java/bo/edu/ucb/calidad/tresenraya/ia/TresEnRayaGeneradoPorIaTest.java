package bo.edu.ucb.calidad.tresenraya.ia;

import bo.edu.ucb.calidad.tresenraya.EstadoPartida;
import bo.edu.ucb.calidad.tresenraya.Jugador;
import bo.edu.ucb.calidad.tresenraya.TresEnRaya;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Suite producida a partir del prompt documentado en docs/PROMPTS_IA.md.
 * Fue revisada antes de incorporarla: la IA propuso casos; la persona conserva
 * la responsabilidad de validar las expectativas y el código.
 */
class TresEnRayaGeneradoPorIaTest {

    @ParameterizedTest(name = "X gana con la línea {index}")
    @MethodSource("victoriasDeX")
    void reconoceTodasLasLineasGanadorasDeX(int[][] jugadas) {
        TresEnRaya juego = new TresEnRaya();

        for (int[] jugada : jugadas) {
            juego.jugar(jugada[0], jugada[1]);
        }

        assertAll(
                () -> assertEquals(EstadoPartida.GANADA, juego.estado()),
                () -> assertEquals(Optional.of(Jugador.X), juego.ganador())
        );
    }

    static Stream<Arguments> victoriasDeX() {
        return Stream.of(
                Arguments.of((Object) new int[][]{{1,1},{2,1},{1,2},{2,2},{1,3}}),
                Arguments.of((Object) new int[][]{{2,1},{1,1},{2,2},{1,2},{2,3}}),
                Arguments.of((Object) new int[][]{{3,1},{1,1},{3,2},{1,2},{3,3}}),
                Arguments.of((Object) new int[][]{{1,1},{1,2},{2,1},{2,2},{3,1}}),
                Arguments.of((Object) new int[][]{{1,2},{1,1},{2,2},{2,1},{3,2}}),
                Arguments.of((Object) new int[][]{{1,3},{1,1},{2,3},{2,1},{3,3}}),
                Arguments.of((Object) new int[][]{{1,1},{1,2},{2,2},{1,3},{3,3}}),
                Arguments.of((Object) new int[][]{{1,3},{1,1},{2,2},{1,2},{3,1}})
        );
    }

    @Test
    void reconoceVictoriaDeO() {
        TresEnRaya juego = new TresEnRaya();
        juego.jugar(1, 1); // X
        juego.jugar(1, 2); // O
        juego.jugar(2, 1); // X
        juego.jugar(2, 2); // O
        juego.jugar(3, 3); // X
        juego.jugar(3, 2); // O

        assertEquals(Optional.of(Jugador.O), juego.ganador());
    }

    @Test
    void impideJugarDespuesDeUnaVictoria() {
        TresEnRaya juego = juegoGanadoPorX();

        IllegalStateException error = assertThrows(IllegalStateException.class, () -> juego.jugar(3, 3));

        assertEquals("La partida ya terminó", error.getMessage());
    }

    @Test
    void impideJugarDespuesDeUnEmpate() {
        TresEnRaya juego = new TresEnRaya();
        int[][] jugadas = {{1,1},{1,2},{1,3},{2,1},{2,3},{2,2},{3,1},{3,3},{3,2}};
        for (int[] jugada : jugadas) juego.jugar(jugada[0], jugada[1]);

        assertThrows(IllegalStateException.class, () -> juego.jugar(2, 2));
    }

    @Test
    void unaConsultaDeCasillaInvalidaNoModificaLaPartida() {
        TresEnRaya juego = new TresEnRaya();

        assertThrows(IllegalArgumentException.class, () -> juego.casilla(-1, 2));
        assertAll(
                () -> assertEquals(Jugador.X, juego.turnoActual()),
                () -> assertEquals(EstadoPartida.EN_CURSO, juego.estado())
        );
    }

    @Test
    void entregaUnaCopiaDefensivaDelTablero() {
        TresEnRaya juego = new TresEnRaya();
        juego.jugar(1, 1);
        Jugador[][] copia = juego.tablero();

        copia[0][0] = Jugador.O;

        assertEquals(Optional.of(Jugador.X), juego.casilla(1, 1));
    }

    private static TresEnRaya juegoGanadoPorX() {
        TresEnRaya juego = new TresEnRaya();
        juego.jugar(1, 1);
        juego.jugar(2, 1);
        juego.jugar(1, 2);
        juego.jugar(2, 2);
        juego.jugar(1, 3);
        return juego;
    }
}
