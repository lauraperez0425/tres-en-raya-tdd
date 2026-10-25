#!/usr/bin/env bash
set -euo pipefail

MVN_CMD="${MVN_CMD:-mvn}"

echo "Suite manual"
"$MVN_CMD" -q -Dtest='TresEnRayaManualTest' test

echo "Suite generada con IA"
"$MVN_CMD" -q -Dtest='TresEnRayaGeneradoPorIaTest' test

echo "Suite completa y cobertura"
"$MVN_CMD" -q clean test
echo "Reporte: target/site/jacoco/index.html"
