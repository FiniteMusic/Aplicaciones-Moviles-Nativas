# Práctica 1 — Instalación y Funcionamiento de los Entornos Móviles

## Información del alumno

- **Nombre:** Marco Uriel De la Cruz Velázquez
- **Grupo:** 7CV4
- **Unidad de aprendizaje:** Desarrollo de Aplicaciones Móviles Nativas
- **Institución:** Instituto Politécnico Nacional — Escuela Superior de Cómputo

## Seccion 2

En esta parte de la práctica se desarrollaron tres versiones de una aplicación básica de tipo **"Hola Mundo"**, utilizando diferentes enfoques para la construcción de interfaces móviles:

1. Android nativo con Views/XML.
2. Android nativo con Jetpack Compose.
3. Flutter.

---

## 1. Hola Mundo — Android Views/XML

## Descripción

La primera versión fue desarrollada utilizando el enfoque tradicional de interfaces de Android mediante Views y archivos XML.

El proyecto fue creado desde Android Studio utilizando la plantilla Empty Views Activity y el lenguaje Kotlin.

La interfaz se construyó mediante un archivo de layout XML, utilizando componentes de tipo "TextView" organizados dentro de un contenedor de diseño.

## Estructura general

La interfaz se encuentra definida en el archivo de layout XML, mientras que la lógica principal de la actividad se encuentra implementada en Kotlin.

Una estructura simplificada del proyecto es:
```text
hola_mundo_xml/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           ├── res/
│           │   └── layout/
│           └── AndroidManifest.xml
└── build.gradle
```

## Ejecución

### Para ejecutar esta versión:

1. Abrir el proyecto "hola_mundo_xml" en Android Studio.
2. Esperar a que Gradle termine de sincronizar las dependencias.
3. Iniciar un emulador Android o conectar un dispositivo físico.
4. Seleccionar el dispositivo desde Android Studio.
5. Ejecutar el proyecto mediante Run .

La aplicación mostrará la información solicitada directamente en la interfaz.

### Evidencia

> <img width="1912" height="1025" alt="image" src="https://github.com/user-attachments/assets/7d2c1034-462b-43e2-916f-3f796a8f989d" />


---

## 2. Hola Mundo — Jetpack Compose

## Descripción

La segunda versión fue desarrollada utilizando Jetpack Compose, el enfoque declarativo de Android para construir interfaces de usuario.

El proyecto fue creado en Android Studio utilizando la plantilla Empty Activity (Jetpack Compose).

A diferencia de la versión anterior, la interfaz no se define mediante un archivo XML separado. Los elementos visuales se describen directamente mediante funciones "@Composable".

Para organizar la información se utilizó un "Column" junto con componentes "Text" y modificadores de estilo.

## Características principales

- Uso de funciones "@Composable".
- Componentes "Text" para mostrar la información.
- Organización de elementos mediante "Column".
- Uso de modificadores para aplicar estilos y espaciado.
- Uso de "@Preview" para visualizar la interfaz directamente desde Android Studio.

## Estructura general
```text
hola_mundo_compose/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           ├── res/
│           └── AndroidManifest.xml
└── build.gradle
```

### Ejecución

Para ejecutar esta versión:

1. Abrir el proyecto `hola_mundo_compose` en Android Studio.
2. Esperar a que finalice la sincronización del proyecto.
3. Iniciar un emulador Android o conectar un dispositivo físico.
4. Seleccionar el dispositivo.
5. Ejecutar el proyecto mediante **Run**.



### Evidencia

> <img width="1917" height="978" alt="image" src="https://github.com/user-attachments/assets/f1ba34fe-2d1c-46b6-9f48-7a51064dfaa1" />


---

## 3. Hola Mundo — Flutter

## Descripción

La tercera versión fue desarrollada utilizando Flutter, un SDK que permite construir aplicaciones multiplataforma mediante una misma base de código.

El proyecto fue generado mediante el comando:

```bash
flutter create hola_mundo_flutter
```

La interfaz se implementó en el archivo:

```bash
lib/main.dart
```

Para construir la aplicación se utilizaron componentes de Flutter como "MaterialApp", "Scaffold", "Column" y "Text".

## Estructura general

```text
hola_mundo_flutter/
├── android/
├── ios/
├── lib/
│   └── main.dart
├── test/
├── pubspec.yaml
└── README.md
```

### Ejecución

Para ejecutar esta versión:

1. Abrir una terminal dentro de la carpeta `hola_mundo_flutter`.
2. Comprobar que el emulador Android se encuentre disponible.
3. Ejecutar:

```bash
flutter devices

flutter emulators

flutter emulators --launch Medium_Phone
```

4. Ejecutar la aplicación mediante:

```bash
flutter run
```

Flutter compilará la aplicación y la instalará en el dispositivo o emulador seleccionado.

### Evidencia

> <img width="1917" height="1013" alt="image" src="https://github.com/user-attachments/assets/5066e288-2d93-4f40-b11e-00d80add68a5" />


---

## 4. Comparación de los enfoques

Característica| Views/XML| Jetpack Compose| Flutter
Tecnología| Android nativo| Android nativo| Multiplataforma
Interfaz| XML + Kotlin| Kotlin + Composables| Dart + Widgets
Diseño| Basado en layouts| Declarativo| Declarativo
IDE utilizado| Android Studio| Android Studio| Android Studio
Archivo principal de interfaz| XML| Kotlin| "main.dart"
Ejecución| Android| Android| Android y otras plataformas

### Observaciones

El desarrollo mediante Views/XML separa la definición de la interfaz de la lógica de la aplicación, utilizando archivos XML para establecer los elementos visuales.

Jetpack Compose permite definir la interfaz directamente mediante código Kotlin y utiliza un enfoque declarativo, por lo que la construcción de interfaces puede realizarse sin depender de archivos XML.

Por otro lado, Flutter utiliza Dart y widgets para construir la interfaz, permitiendo desarrollar aplicaciones para diferentes plataformas utilizando una misma base de código.

---

## Conclusión

El desarrollo de las tres versiones de la aplicación "Hola Mundo" permitió observar diferentes alternativas para la construcción de interfaces móviles.

Views/XML utiliza un enfoque tradicional basado en archivos de diseño XML, mientras que Jetpack Compose permite construir las interfaces mediante un modelo declarativo utilizando Kotlin. Flutter, por su parte, utiliza Dart y widgets para proporcionar una alternativa multiplataforma.

