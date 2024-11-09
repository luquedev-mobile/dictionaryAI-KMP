# dictionaryAI-KMP
Dictionary AI es una aplicación multiplataforma que permite a los usuarios conocer el significado de palabras o frases en inglés, proporcionando una explicación detallada en español. La app utiliza la inteligencia artificial de Gemini para ofrecer respuestas precisas y contextualizadas.

## Características

-	Búsqueda de significados de palabras y frases en inglés.
-	Respuestas y explicaciones en español.
-	Integración con Gemini AI para análisis y generación de contenido.
-	Compatible con dispositivos iOS y Android gracias a Kotlin Multiplatform (KMP) y Compose Multiplatform.
-	Basado en Clean Architecture para una separación clara de responsabilidades y fácil mantenimiento.

## Requisitos

Para poder ejecutar el proyecto es necesario obtener una **API Key** para Gemini. Sigue los pasos a continuación:

1. Crea una API Key en [Google AI Studio](https://aistudio.google.com/app/apikey?hl=es-419).
2. Instalar Android Studio
3. Instalar plugin Kotlin multiplatform
4. Crea un archivo **local.properties** a nivel del proyecto y agrega la siguiente variable:

    ```properties
    API_KEY="YOUR_API_KEY"
    ```
## Arquitectura

Este proyecto utiliza **Kotlin Multiplatform** y **Compose Multiplatform**. La arquitectura sigue el patrón **Clean Architecture**, dividiendo el código en capas de **domain**, **usecases**, **data**, y **app** para mejorar la escalabilidad y mantenimiento del código. Además, utiliza **Koin** como inyector de dependencias para gestionar de forma eficiente las dependencias en todo el proyecto.

## Tecnologías y herramientas

- **Kotlin Multiplatform (KMP)** para compartir código entre Android e iOS.
- **Compose Multiplatform** para la interfaz de usuario en ambas plataformas.
- **Gemini AI** para el análisis y generación de significados.
- **Koin** para la inyección de dependencias.
- **Clean Architecture** para organizar el código en capas.
  
## Capturas de Pantalla

<img width="441" alt="image" src="https://github.com/user-attachments/assets/0e0e9daa-61e7-48dc-b764-635d367296e6">
<img width="441" alt="image" src="https://github.com/user-attachments/assets/1809413d-5742-4451-9f81-297d5b94f179">
<img width="441" alt="image" src="https://github.com/user-attachments/assets/b20eeaa2-caca-42fd-b5ff-a8a09fa73259">

## Instalación

1. Clona el repositorio

2. Abre el proyecto en Android Studio.

3. Configura tu API Key en `local.properties` como se mencionó en la sección de requisitos.
   
4. Sincroniza el proyecto
   
5. ejecuta ./gradlew clean
./gradlew build

6. Ejecuta la aplicación:

   
   <img width="441" alt="image" src="https://github.com/user-attachments/assets/6313f3a7-4d2c-41a0-89ac-bf41181aad1b">



