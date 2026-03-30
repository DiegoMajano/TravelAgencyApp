# MundeApp - Agency Travel

**MundeApp** es una aplicación de Android desarrollada en **Kotlin** diseñada para gestionar destinos turísticos de una agencia de viajes. La aplicación permite a los usuarios autenticarse y realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) completas, integrando almacenamiento de datos e imágenes en la nube. El apk se ha subido al Drive anexado a la entrega, pesa mas de 25MB y Github no lo sube.

## Características

* **Autenticación de Usuarios:** Registro e inicio de sesión mediante Firebase Auth.
* **Gestión de Destinos:** Listado de destinos con nombre, país, precio y descripción.
* **Subida de Imágenes:** Integración con Firebase Storage para almacenar fotos de los destinos.
* **Base de Datos en Tiempo Real:** Uso de Cloud Firestore para la persistencia de datos.
* **Interfaz Moderna:** Diseño basado en Material Components, utilizando `RecyclerView` para listas y `Glide` para la carga eficiente de imágenes.
* **Validaciones:** Control de errores en formularios (campos vacíos, longitud de descripción, formato de correo, etc.).

## Tecnologías Utilizadas

* **Lenguaje:** [Kotlin](https://kotlinlang.org/)
* **Base de Datos:** [Firebase Firestore](https://firebase.google.com/docs/firestore)
* **Autenticación:** [Firebase Authentication](https://firebase.google.com/docs/auth)
* **Almacenamiento de Archivos:** [Firebase Storage](https://firebase.google.com/docs/storage)
* **Carga de Imágenes:** [Glide](https://github.com/bumptech/glide)
* **UI/UX:** Material Design Components & XML layouts.

## Estructura del Proyecto

* `model/`: Contiene la data class `Destination`.
* `firebase/`: `FirebaseService.kt` centraliza toda la lógica de conexión con Firebase.
* `ui/`: Actividades para Login, Registro, Listado principal y Formulario de gestión.
* `res/`: Recursos visuales, strings localizados, colores y temas personalizados.

## Configuración del Proyecto

Para ejecutar este proyecto localmente, sigue estos pasos:

1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/DiegoMajano/TravelAgencyApp.git
    ```
2.  **Configurar Firebase:**
    * Crea un nuevo proyecto en [Firebase Console](https://console.firebase.google.com/).
    * Añade una aplicación Android con el nombre de paquete `com.dsm.agencytravel`.
    * Descarga el archivo `google-services.json` y colócalo en la carpeta `app/` de tu proyecto.
    * Habilita los siguientes servicios en Firebase:
        * **Authentication:** Activa el método "Correo electrónico/contraseña".
        * **Cloud Firestore:** Crea la base de datos en modo de prueba.
        * **Firebase Storage:** Configura el bucket de almacenamiento.
3.  **Compilar y Ejecutar:** Abre el proyecto en **Android Studio** y ejecútalo en un emulador o dispositivo físico, recomiendo utilizar dispositivo físico para evitar problemas de acceso a internet en la app.

## Requisitos de la Aplicación

* Android SDK 21 (Lollipop) o superior.
* Conexión a Internet (para sincronización con Firebase).

## Información del desarrollo
### **Desarrollado por:** > **DIEGO JOSÉ RODRÍGUEZ MAJANO**
>
> **Carnet:** RM220481  
> **Materia:** Desarrollo de Software para Móviles (DSM)
> Documentación por Mintlify</b> - [Documentación Mintlify](https://www.mintlify.com/DiegoMajano/TravelAgencyApp/introduction)
---
<p align="center">
  <b>MundeApp 2026</b> - Desafío Práctico #2
</p>
