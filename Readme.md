# Plataforma de Tarjetas de Regalo

## Estado del Proyecto

El desarrollo de la plataforma ha alcanzado los objetivos principales establecidos. A continuación, se detallan los módulos implementados y las funcionalidades logradas:

### ✅ Microservicio 1: Gestión de Tarjetas (Java + Spring Boot, Reactivo)
- CRUD de tarjetas de regalo (crear, obtener, actualizar, eliminar).
- Implementación de programación reactiva con Reactor.
- Conexión a PostgreSQL para almacenamiento de tarjetas.
- Arquitectura Hexagonal para la separación de capas.

### ✅ Microservicio 2: Autenticación y Usuarios
- CRUD de usuarios (crear, listar, actualizar, eliminar).
- Autenticación basada en JWT.
- Integración con el Microservicio de Tarjetas (solo usuarios autenticados pueden acceder).
- PostgreSQL para almacenamiento de credenciales y perfiles.

### ✅ Infraestructura y Mensajería
- Balanceo de carga y tolerancia a fallos.
- Implementación de RabbitMQ para mensajería asíncrona.

### 🔄 Propuesta para Manejo de Almacenamiento y Envio de Correos
- Se propone incorporar eventos asíncronos en el microservicio de emisión de tarjetas.
- Este servicio enviará un mensaje a RabbitMQ, que será escuchado por un nuevo microservicio.
- El nuevo microservicio se conectará a un sistema de almacenamiento como S3 para guardar datos.
- Posteriormente, emitirá un nuevo mensaje que el microservicio de emisión de tarjetas recibirá para actualizar el registro de la tarjeta emitida.

### 🚧 Pendientes y Mejoras Futuras
- Mejorar el manejo de errores, centralizando la gestión y proporcionando mensajes intuitivos.
- Implementar un microservicio para la gestión de autorizaciones por rol.
- Desarrollar la interfaz frontend en Angular.

---

## Descripción General

Este proyecto es una plataforma de tarjetas de regalo basada en una arquitectura de microservicios utilizando **Spring Boot** y **Angular**. La solución sigue la **Arquitectura Hexagonal** y utiliza **programación reactiva** en el servicio de gestión de tarjetas.

### 📌 Arquitectura
![Arquitectura de Microservicios](https://example.com/arquitectura.png)

---

## 📌 Módulos del Proyecto

### 1⃣ API Gateway
**Tecnología:** Spring Cloud Gateway  
🔹 Actúa como un punto de entrada para los microservicios.  
🔹 Implementa autenticación con JWT.  
🔹 Gestiona el enrutamiento y seguridad.

### 2⃣ Discovery Server (Eureka)
**Tecnología:** Spring Cloud Eureka  
🔹 Permite que los microservicios se registren dinámicamente.  
🔹 Facilita la escalabilidad y balanceo de carga.

### 3⃣ Microservicio de Tarjetas
**Tecnología:** Spring Boot + WebFlux + PostgreSQL  
🔹 CRUD de tarjetas de regalo.  
🔹 Programación reactiva.  
🔹 Procesamiento asíncrono con colas de mensajes (RabbitMQ/Kafka).

### 4⃣ Microservicio de Autenticación y Usuarios
**Tecnología:** Spring Boot + Spring Security + JWT  
🔹 Maneja el registro y autenticación de usuarios.  
🔹 Protege los endpoints de los demás microservicios.

### 5⃣ Frontend Angular
🔹 Registro y autenticación de usuarios.  
🔹 Creación y gestión de tarjetas.  
🔹 Comunicación con API Gateway.

---

## 🚀 Configuración y Ejecución

### 🔧 Prerrequisitos
- **Docker + Docker Compose**
- **Java 17+**
- **Node.js + Angular CLI**

📌 **Esto iniciará automáticamente los siguientes servicios:**  
👉 **PostgreSQL** como base de datos.  
👉 **RabbitMQ** (si se usa para mensajería).  
👉 **Discovery Server (Eureka)**.  
👉 **API Gateway**.  
👉 **Microservicio de Tarjetas** y **Microservicio de Autenticación**.  
👉 **Frontend Angular**.

---

## 🔥 Mejoras Futuras
✅ Agregar documentación con Swagger. [Grupo Exito REST API basics- CRUD.postman_collection.json](..%2F..%2F..%2F..%2FDescargas%2FGrupo%20Exito%20REST%20API%20basics-%20CRUD.postman_collection.json) 
✅ Mejorar la UI con Material UI o Bootstrap.  
✅ Implementar métricas y monitoreo con Prometheus + Grafana.

---

📌 **Autor:** Mauricio Elias Coronado Rodriguez  
📅 **Fecha:** 2025/02/22

