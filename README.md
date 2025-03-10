# Aplicación de Mensajería - En desarrollo

Este proyecto implementa una aplicación de mensajería web completa, compuesta por un backend desarrollado en **Spring Boot** y un frontend basado en **React**. Está diseñada para permitir que los usuarios se registren, autentiquen, gestionen sus perfiles, y mantengan conversaciones privadas o grupales de forma segura.

---

## Backend
El backend actúa como núcleo del sistema gestionando:

- **Autenticación y autorización** con tokens JWT para sesiones seguras.
- **Gestión de usuarios y perfiles**: Registro, autenticación y edición de perfiles.
- **Conversaciones**: creación y gestión de chats privados y grupales.
- **Mensajes**: envío, recepción y eliminación de mensajes, junto con la gestión del estado de lectura.
- **Arquitectura hexagonal** para desacoplar la lógica de negocio del almacenamiento de datos mediante puertos y adaptadores.

### Tecnologías utilizadas:
- **Spring Boot**
- **MySQL**
- **JWT** para autenticación segura.

---

## Frontend

La interfaz proporciona una experiencia de usuario moderna y receptiva mediante:

- **Interfaz intuitiva** para registro e inicio de sesión.
- **Gestión visual y dinámica de conversaciones**.
- **Chat interactivo** con soporte para mensajes y emojis.
- **Autenticación segura** usando tokens JWT almacenados en `localStorage`.

### Componentes Principales:
- **LoginPage** para registro e inicio de sesión.
- **Homepage** para visualización de conversaciones activas.
- **ChatContainer** para interactuar con mensajes en tiempo real.

### Tecnologías utilizadas:
- **React**
- **Material-UI**
- **Axios**

---

## Aspectos técnicos destacados:
- Uso de la arquitectura hexagonal, facilitando mantenimiento, pruebas y escalabilidad.
- Uso de JWT para gestión segura de sesiones.
- Comunicación eficiente backend-frontend mediante APIs REST.
- Almacenamiento persistente en base de datos MySQL.
