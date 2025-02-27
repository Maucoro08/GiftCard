#!/bin/bash
# Obtener la ruta del directorio donde está el script
BASE_DIR=$(dirname "$(realpath "$0")")
# Recorrer todas las subcarpetas dentro del directorio del script
for dir in "$BASE_DIR"/*/; do
    # Verificar si la carpeta contiene un Dockerfile
    if [[ -f "$dir/Dockerfile" ]]; then
        # Obtener el nombre de la carpeta y convertirlo a minúsculas
        IMAGE_NAME=$(basename "$dir" | tr '[:upper:]' '[:lower:]')
        echo "🚀 Procesando '$IMAGE_NAME'..."
        # Si hay un pom.xml, ejecutar Maven Wrapper para compilar el proyecto
        if [[ -f "$dir/pom.xml" ]]; then
            echo "🔨 Compilando proyecto Java en '$IMAGE_NAME'..."
            cd "$dir" || exit 1
            ./mvnw clean package -DskipTests
            # Verificar si la compilación fue exitosa
            if [[ $? -ne 0 ]]; then
                echo "❌ Error en la compilación de Maven en '$IMAGE_NAME'. Saltando imagen..."
                cd "$BASE_DIR"
                continue
            fi
            cd "$BASE_DIR"
        fi
        # Construir la imagen de Docker
        echo "🐳 Construyendo imagen Docker '$IMAGE_NAME'..."
        docker build -t "$IMAGE_NAME" "$dir"
        # Verificar si la construcción fue exitosa
        if [[ $? -eq 0 ]]; then
            echo "✅ Imagen '$IMAGE_NAME' creada exitosamente."
        else
            echo "❌ Error al crear la imagen '$IMAGE_NAME'."
        fi
    else
        echo "⚠️ No se encontró un Dockerfile en '$dir', saltando..."
    fi
done
