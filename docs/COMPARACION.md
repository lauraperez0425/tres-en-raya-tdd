# Comparación de pruebas unitarias

| Criterio | Pruebas manuales | Pruebas generadas con IA |
|---|---|---|
| Intención | Guiar el diseño durante Rojo-Verde-Refactor | Auditar el comportamiento ya definido y buscar omisiones |
| Organización | Un caso por requisito central | Parametrización de las ocho líneas ganadoras y casos defensivos |
| Fortalezas | Lectura directa, trazabilidad con requisitos, fallos fáciles de diagnosticar | Mayor amplitud con menos repetición; detecta fin de partida y copia defensiva |
| Debilidades | Repite preparación y solo ejemplifica algunas líneas | Puede proponer secuencias inválidas o probar detalles no pactados; exige revisión humana |
| Riesgo principal | Sesgo del autor hacia el camino feliz | Confianza excesiva en código plausible pero incorrecto |

## Resultados medidos

| Suite | Casos ejecutados | Líneas | Ramas | Resultado |
|---|---:|---:|---:|---|
| Manual | 8 | 92,3 % | 90,0 % | 8 aprobados |
| IA revisada | 13 | 98,1 % | 95,0 % | 13 aprobados |
| Combinada | 21 | 100,0 % | 100,0 % | 21 aprobados |

Medición realizada con JUnit 5 y JaCoCo 0.8.12 sobre la versión entregada.

La comparación cuantitativa se actualiza con resultados reproducibles mediante
`./scripts/medir-suites.sh`. El número de pruebas no equivale por sí solo a calidad:
la suite manual explica el diseño y la suite de IA amplía combinaciones y condiciones
de borde. Juntas proporcionan mejor confianza que cualquiera por separado.
