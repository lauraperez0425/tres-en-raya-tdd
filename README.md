# Tres en Raya con TDD

Solución del Taller #6. El repositorio separa las pruebas creadas manualmente durante
el desarrollo de las pruebas propuestas por IA y revisadas por una persona.

## Requisitos

- Java 17 o superior
- Maven 3.9 o superior

## Ejecutar

```bash
mvn clean test
```

El reporte de cobertura queda en `target/site/jacoco/index.html`.

Para ejecutar cada conjunto por separado:

```bash
mvn -Dtest='TresEnRayaManualTest' test
mvn -Dtest='TresEnRayaGeneradoPorIaTest' test
```

## Estructura

- `src/main`: implementación del dominio.
- `src/test/.../manual`: pruebas diseñadas por la autora o el autor.
- `src/test/.../ia`: pruebas creadas a partir del prompt y revisadas.
- `docs/PROMPTS_IA.md`: prompts entregables y criterio de aceptación.
- `docs/COMPARACION.md`: análisis comparativo.
- `presentacion`: presentación editable y PDF final.

## Flujo TDD

El desarrollo sigue la secuencia prueba que falla, implementación mínima y
refactorización. Las suites separadas permiten revisar qué casos nacieron del
diseño manual y cuáles surgieron del prompt de IA.

## Repositorio público

El proyecto está publicado en <https://github.com/lauraperez0425/tres-en-raya-tdd>.

Referencia consultada: <https://github.com/gpalabral/TDD>.
