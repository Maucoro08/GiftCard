#!/bin/bash
# Obtener el nombre de la carpeta actual
FOLDER_NAME=$(basename "$PWD" | tr '[:upper:]' '[:lower:]')
# Construir la imagen de Docker con el nombre de la carpeta
docker build -t "$FOLDER_NAME" .
# Verificar si la imagen se creó correctamente
if [[ $? -eq 0 ]]; then
    echo "✅ Imagen '$FOLDER_NAME' creada exitosamente."
else
    echo "❌ Error al crear la imagen."
fi