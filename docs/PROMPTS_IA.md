# Prompts utilizados para generar pruebas con IA

## Prompt principal

> Actúa como especialista en pruebas unitarias y TDD. Genera una suite de pruebas
> JUnit 5 para una clase Java `TresEnRaya` con esta API pública:
>
> - `ResultadoJugada jugar(int fila, int columna)`; las coordenadas válidas van de 1 a 3.
> - `Jugador turnoActual()`; el primer turno corresponde a X y luego alterna con O.
> - `EstadoPartida estado()`; puede ser EN_CURSO, GANADA o EMPATE.
> - `Optional<Jugador> ganador()` y `Optional<Jugador> casilla(int fila, int columna)`.
> - `Jugador[][] tablero()` devuelve una copia del tablero.
>
> Reglas: una casilla no puede ocuparse dos veces; gana quien complete una fila,
> columna o diagonal; hay empate con nueve jugadas sin ganador; después de una
> victoria o empate no se permiten más jugadas. Usa nombres descriptivos en español,
> patrón Arrange-Act-Assert, pruebas parametrizadas cuando reduzcan duplicación y
> `assertAll` solo si varias afirmaciones describen un mismo resultado. Incluye casos
> límite y comprueba que los errores no muten el estado. No implementes la clase de
> producción. Devuelve únicamente el código de prueba y explica después, en una lista
> separada, qué riesgo cubre cada prueba.

## Prompt de revisión

> Revisa la suite anterior como si fueras revisor de código. Identifica pruebas
> redundantes, expectativas que dependan de detalles internos, casos faltantes y
> falsos positivos posibles. Propón cambios mínimos y conserva la API indicada.

## Criterio humano de aceptación

La respuesta de la IA no se incorporó de forma automática. Se verificó que cada
secuencia de jugadas fuera legal, que ninguna victoria ocurriera antes del último
movimiento esperado y que los nombres expresaran comportamiento. La suite aceptada
se encuentra en `src/test/java/.../ia/TresEnRayaGeneradoPorIaTest.java`.
