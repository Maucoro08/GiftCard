# Plataforma de Tarjetas de Regalo

## Descripción General

Este proyecto es una plataforma de tarjetas de regalo basada en una arquitectura de microservicios utilizando **Spring Boot** y **Angular**. La solución sigue la **Arquitectura Hexagonal** y utiliza **programación reactiva** en el servicio de gestión de tarjetas.

### 📌 Arquitectura
![Arquitectura de Microservicios](https://example.com/arquitectura.png)

---

## 📌 Módulos del Proyecto

### 1️⃣ API Gateway
**Tecnología:** Spring Cloud Gateway  
🔹 Actúa como un punto de entrada para los microservicios.  
🔹 Implementa autenticación con JWT.  
🔹 Gestiona el enrutamiento y seguridad.

### 2️⃣ Discovery Server (Eureka)
**Tecnología:** Spring Cloud Eureka  
🔹 Permite que los microservicios se registren dinámicamente.  
🔹 Facilita la escalabilidad y balanceo de carga.

### 3️⃣ Microservicio de Tarjetas
**Tecnología:** Spring Boot + WebFlux + PostgreSQL  
🔹 CRUD de tarjetas de regalo.  
🔹 Programación reactiva.  
🔹 Procesamiento asíncrono con colas de mensajes (RabbitMQ/Kafka).

### 4️⃣ Microservicio de Autenticación y Usuarios
**Tecnología:** Spring Boot + Spring Security + JWT  
🔹 Maneja el registro y autenticación de usuarios.  
🔹 Protege los endpoints de los demás microservicios.

### 5️⃣ Frontend Angular
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
✅ Agregar documentación con Swagger.  
✅ Mejorar la UI con Material UI o Bootstrap.  
✅ Implementar métricas y monitoreo con Prometheus + Grafana.

---

📌 **Autor:** Mauricio Elias Coronado Rodriguez  
📅 **Fecha:** 2025/02/22  

