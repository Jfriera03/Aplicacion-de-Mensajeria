# Proyecto Aplicación de Mensajería - En desarrollo

## Introducción
Este proyecto es una aplicación completa de mensajería, compuesta por un backend desarrollado en **Spring Boot** y un frontend desarrollado en **React**. La aplicación permite a los usuarios registrarse, autenticarse, mantener conversaciones privadas o grupales, y gestionar perfiles de manera segura y sencilla.

---

## Backend

El backend es el núcleo de la aplicación y gestiona:

- **Autenticación y Autorización** con JWT para asegurar sesiones seguras.
- **Gestión de conversaciones**, tanto privadas como grupales.
- **Manejo de mensajes**, incluyendo envío, recepción, y eliminación de mensajes.
- **Lecturas de mensajes** mediante registro de visualizaciones.
- **Persistencia de datos** con base de datos MySQL.
- Uso de la **arquitectura hexagonal**, separando claramente lógica de negocio, persistencia y controladores, utilizando puertos y adaptadores.

### Tecnologías principales:
- **Spring Boot**
- **MySQL**
- **Xampp**

---

## Frontend

El frontend proporciona una interfaz de usuario amigable y funcional, con características destacadas:

- **Login y Registro** con formularios integrados con la API backend.
- **Gestión visual de conversaciones**, con posibilidad de enviar mensajes y emojis.
- **Chat en tiempo real**, con soporte para emojis y envío de archivos adjuntos.
- **Interfaz moderna y responsiva**, construida con componentes reutilizables en React y Material-UI.
- **Seguridad de sesión** mediante tokens JWT almacenados en `localStorage`.

### Componentes clave:
- **LoginPage**: Registro e inicio de sesión.
- **Homepage**: Visualización y gestión de conversaciones.
- **ChatContainer**: Gestión interactiva de mensajes en tiempo real.

### Tecnologías principales:
- **React**
- **Material-UI**
- **Axios**

---

## Arquitectura del Proyecto
Se ha utilizado una **arquitectura hexagonal** que asegura una separación clara entre la lógica de negocio, interfaces de usuario, y persistencia de datos. Esto favorece la flexibilidad, la mantenibilidad, y facilita las pruebas del software.

---

## Seguridad
La seguridad se basa en un sistema robusto de autenticación mediante **tokens JWT**, garantizando que sólo usuarios autenticados y autorizados puedan acceder a las funcionalidades del sistema.
