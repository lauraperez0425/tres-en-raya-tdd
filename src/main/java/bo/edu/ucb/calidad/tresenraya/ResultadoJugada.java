package bo.edu.ucb.calidad.tresenraya;

import java.util.Objects;
import java.util.Optional;

public record ResultadoJugada(EstadoPartida estado, Jugador jugador, Optional<Jugador> ganador) {
    public ResultadoJugada {
        Objects.requireNonNull(estado, "El estado es obligatorio");
        Objects.requireNonNull(jugador, "El jugador es obligatorio");
        Objects.requireNonNull(ganador, "El ganador es obligatorio");
    }
}
