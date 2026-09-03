# Práctica 1 — Instalación y Funcionamiento de los Entornos Móviles

## Aplicación "Hola Mundo" en tres enfoques

## 1. Hola Mundo — Android Views/XML

### Ejecución

Para ejecutar esta versión:

1. Abrir el proyecto `hola_mundo_xml` en Android Studio.
2. Esperar a que Gradle termine de sincronizar las dependencias.
3. Iniciar un emulador Android o conectar un dispositivo físico.
4. Seleccionar el dispositivo desde Android Studio.
5. Ejecutar el proyecto mediante **Run**.

La aplicación mostrará la información solicitada directamente en la interfaz.

### Evidencia

> <img width="1912" height="1025" alt="image" src="https://github.com/user-attachments/assets/7d2c1034-462b-43e2-916f-3f796a8f989d" />


---

## 2. Hola Mundo — Jetpack Compose


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

## 3. Hola Mundo — Flutter


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

