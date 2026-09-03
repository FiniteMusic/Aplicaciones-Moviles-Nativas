# Práctica 1 — Instalación y Funcionamiento de los Entornos Móviles

# Aplicación "Hola Mundo" en tres enfoques

# 1. Hola Mundo — Android Views/XML


### Ejecución

Para ejecutar esta versión:

1. Abrir el proyecto `hola_mundo_xml` en Android Studio.
2. Esperar a que Gradle termine de sincronizar las dependencias.
3. Iniciar un emulador Android o conectar un dispositivo físico.
4. Seleccionar el dispositivo desde Android Studio.
5. Ejecutar el proyecto mediante **Run ▶**.

La aplicación mostrará la información solicitada directamente en la interfaz.

### Evidencia

> <img width="1912" height="1025" alt="image" src="https://github.com/user-attachments/assets/7d2c1034-462b-43e2-916f-3f796a8f989d" />


---

# 2. Hola Mundo — Jetpack Compose

### Descripción

La segunda versión fue desarrollada utilizando **Jetpack Compose**, el enfoque declarativo de Android para construir interfaces de usuario.

El proyecto fue creado en Android Studio utilizando la plantilla **Empty Activity (Jetpack Compose)**.

A diferencia de la versión anterior, la interfaz no se define mediante un archivo XML separado. Los elementos visuales se describen directamente mediante funciones `@Composable`.

Para organizar la información se utilizó un `Column` junto con componentes `Text` y modificadores de estilo.

### Características principales

* Uso de funciones `@Composable`.
* Componentes `Text` para mostrar la información.
* Organización de elementos mediante `Column`.
* Uso de modificadores para aplicar estilos y espaciado.
* Uso de `@Preview` para visualizar la interfaz directamente desde Android Studio.

### Estructura general

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
5. Ejecutar el proyecto mediante **Run ▶**.

También es posible utilizar la anotación `@Preview` para visualizar el diseño directamente dentro del IDE.

### Evidencia

> Captura de pantalla de la aplicación "Hola Mundo" desarrollada con Jetpack Compose ejecutándose en el emulador.

---

# 3. Hola Mundo — Flutter

### Descripción

La tercera versión fue desarrollada utilizando **Flutter**, un SDK que permite construir aplicaciones multiplataforma mediante una misma base de código.

El proyecto fue generado mediante el comando:

```bash
flutter create hola_mundo_flutter
```

La interfaz se implementó en el archivo:

```text
lib/main.dart
```

Para construir la aplicación se utilizaron componentes de Flutter como `MaterialApp`, `Scaffold`, `Column` y `Text`.

### Estructura general

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

