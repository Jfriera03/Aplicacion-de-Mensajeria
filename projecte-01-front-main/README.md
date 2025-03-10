# Grupo 1 - Aplicación de mensajería - Front
`Integrantes`: Luz Salvá, Laura Cavero, Juan Francisco Riera, Constantino Pérez

## Introducción
Este proyecto consiste en una interfaz web desarrollada con **React** que ofrece una experiencia moderna y responsiva. La aplicación incluye funcionalidades como autenticación, navegación entre páginas y manejo de conversaciones en tiempo real.

## Características principales
- **Autenticación**: Permite a los usuarios iniciar sesión y registrarse.
- **Gestor de Conversaciones**: Muestra una lista de conversaciones existentes y permite seleccionar una para interactuar.
- **Panel de Chat**: Incluye la funcionalidad de enviar mensajes y usar emojis en un entorno visual atractivo.

## Estructura del Proyecto

El proyecto se organiza en los siguientes componentes principales:

1. **LoginPage**: Pantalla de autenticación para inicio de sesión y registro.
2. **Homepage**: Contiene la estructura principal de la aplicación dividida en dos paneles:
    - **LeftPanel**: Muestra las conversaciones disponibles.
    - **RightPanel**: Permite la interacción dentro de una conversación seleccionada.
3. **ChatContainer**: Maneja el flujo y visualización de mensajes dentro de una conversación.

## Tecnologías Utilizadas
- **React**: Biblioteca principal para construir la interfaz de usuario.
- **Material-UI**: Framework de diseño para estilos y componentes responsivos.
- **Axios**: Para manejar peticiones HTTP.
- **React Router**: Para navegación entre rutas.

## Configuración y Ejecución

### Prerrequisitos
Asegúrate de tener instalados los siguientes programas:
- **Node.js**: [Descargar Node.js](https://nodejs.org)
- **npm** o **yarn**: Administrador de paquetes.

### Instrucciones de Instalación
1. Clona este repositorio:
    ```bash
    git clone https://github.com/Analisi-i-Arquitectura-de-Software/projecte-01-front.git
    ```
2. Ingresa al directorio del proyecto:
    ```bash
    cd projecte-01-front
    ```
3. Instala las dependencias:
    ```bash
    npm install
    ```

### Ejecución del Proyecto
1. Inicia el servidor de desarrollo:
    ```bash
    npm start
    ```
2. Abre tu navegador y accede a `http://localhost:3000`.

## Componentes Clave

### LoginPage
- Maneja el inicio de sesión y registro de usuarios.
- Hace uso de `axios` para comunicarse con los endpoints de la API.

### Homepage
- Contiene el contenedor principal de la aplicación dividido en:
  - **LeftPanel**: Lista de conversaciones con funcionalidad de búsqueda.
  - **RightPanel**: Incluye el flujo de chat y opciones como emojis y adjuntos.

### AttachmentPopover
- Proporciona un menú para adjuntar documentos, fotos o stickers al chat.

### ChatContainer
- Administra los mensajes enviados y recibidos en tiempo real.

## Seguridad
El sistema de autenticación utiliza un token almacenado en `localStorage` para gestionar las sesiones.

## API Endpoints

### Registro de Usuario
- **POST** `/auth/register`
- Cuerpo:
  ```json
  {
    "username": "nombreUsuario",
    "password": "miContraseña",
    "email": "correo@ejemplo.com",
    "name": "Nombre Completo"
  }

### Inicio de Sesión
- **POST** `/auth/login`
- Cuerpo:
  ```json
  {
    "username": "nombreUsuario",
    "password": "miContraseña"
  }
