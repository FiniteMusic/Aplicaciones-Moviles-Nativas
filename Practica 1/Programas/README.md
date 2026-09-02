# Práctica 1 — Instalación y Funcionamiento de los Entornos Móviles

## Ejercicio 1 — Instalación de Herramientas

### Introducción

En este ejercicio se realizó la instalación y configuración de las herramientas necesarias para establecer un entorno de desarrollo para aplicaciones móviles. El propósito fue preparar el sistema para desarrollar, compilar y ejecutar aplicaciones Android utilizando diferentes tecnologías y enfoques de construcción de interfaces.

Las herramientas utilizadas fueron **Android Studio, Git, GitHub, Node.js, Docker, Java Development Kit (JDK) y Flutter**. Después de realizar su instalación y configuración, se verificó el funcionamiento de cada una mediante los comandos correspondientes.

---

## 1. Android Studio

**Android Studio** es el entorno de desarrollo integrado utilizado para la creación de aplicaciones para dispositivos Android. Proporciona herramientas para escribir y organizar el código, diseñar interfaces, administrar proyectos, compilar aplicaciones y ejecutarlas mediante dispositivos físicos o emuladores.

Para esta práctica, Android Studio constituye una de las herramientas principales, ya que se utiliza para desarrollar las aplicaciones Android mediante **Views con XML** y **Jetpack Compose**. También permite administrar el emulador utilizado para comprobar el funcionamiento de las aplicaciones.

### Instalación y configuración

Se realizó la instalación de Android Studio y su configuración inicial. Posteriormente, se configuró el entorno necesario para ejecutar aplicaciones Android mediante un emulador.

La instalación fue verificada ejecutando correctamente el entorno de desarrollo y comprobando el funcionamiento del emulador.

### Evidencia

> Captura de pantalla de Android Studio con el entorno de desarrollo y el emulador funcionando correctamente.

---

## 2. Git y GitHub

### Git

**Git** es un sistema de control de versiones utilizado para administrar los cambios realizados en los archivos de un proyecto. Permite mantener un historial de modificaciones y facilita la organización del código durante el desarrollo.

En esta práctica, Git se utiliza para llevar el control de versiones de los proyectos desarrollados y realizar los commits correspondientes.

### GitHub

**GitHub** es una plataforma para alojar repositorios de código y trabajar con proyectos mediante sistemas de control de versiones como Git.

Se configuró un repositorio público en GitHub para almacenar los proyectos desarrollados durante la práctica. Cada aplicación será organizada en su propia carpeta dentro del repositorio.

### Instalación y configuración

Se instaló Git y se configuró para trabajar con repositorios remotos. Posteriormente, se estableció la conexión con el repositorio de GitHub utilizado para la práctica.

La instalación de Git se verificó mediante el siguiente comando:

```bash
git --version
```

### Evidencia

> Captura de pantalla de la terminal mostrando la versión de Git instalada y/o del repositorio de GitHub configurado.

---

## 3. Node.js

**Node.js** es un entorno de ejecución de JavaScript que permite ejecutar código fuera de un navegador. Es utilizado por diferentes herramientas y aplicaciones del ecosistema de desarrollo de software.

Dentro del entorno configurado para esta práctica, Node.js forma parte de las herramientas instaladas en el sistema y permite disponer de un entorno de ejecución para aplicaciones y herramientas basadas en JavaScript.

### Instalación y configuración

Se realizó la instalación de Node.js y se verificó que el sistema pudiera reconocer correctamente el entorno de ejecución.

La instalación fue comprobada mediante:

```bash
node -v
```

El comando muestra la versión de Node.js instalada en el sistema.

### Evidencia

> Captura de pantalla de la terminal mostrando la versión de Node.js.

---

## 4. Docker

**Docker** es una plataforma que permite crear, ejecutar y administrar aplicaciones mediante contenedores. Estos permiten aislar aplicaciones y sus dependencias, facilitando la creación de entornos reproducibles.

La herramienta forma parte del entorno de desarrollo instalado y permite ejecutar servicios dentro de contenedores sin depender directamente de la configuración del sistema operativo.

### Instalación y configuración

Se realizó la instalación de Docker y su configuración inicial. Posteriormente, se comprobó que el servicio funcionara correctamente mediante la ejecución de un contenedor de prueba.

La versión instalada se verificó mediante:

```bash
docker --version
```

También se comprobó el funcionamiento de Docker utilizando:

```bash
docker run hello-world
```

El contenedor de prueba permitió verificar que Docker se encontraba correctamente instalado y operativo.

### Evidencia

> Captura de pantalla de la terminal mostrando la versión de Docker y el resultado de la ejecución del contenedor `hello-world`.

---

## 5. Java Development Kit (JDK)

El **Java Development Kit (JDK)** es un conjunto de herramientas utilizado para desarrollar, compilar y ejecutar aplicaciones basadas en Java. Dentro del desarrollo Android, el JDK es necesario para realizar diferentes procesos relacionados con la compilación y ejecución de los proyectos.

Para el desarrollo de aplicaciones móviles, el JDK forma parte de los componentes necesarios para que el entorno de Android funcione correctamente.

### Instalación y configuración

Se realizó la instalación y configuración del JDK en el sistema. Posteriormente, se verificó que Java pudiera ser ejecutado correctamente desde la terminal.

La instalación se comprobó mediante:

```bash
java -version
```

El comando permitió confirmar la versión del JDK instalada y comprobar que se encontraba correctamente configurada.

### Evidencia

> Captura de pantalla de la terminal mostrando la versión del JDK instalada.

---

## 6. Flutter

**Flutter** es un SDK de desarrollo de aplicaciones multiplataforma que permite crear interfaces y aplicaciones utilizando una misma base de código. Flutter utiliza el lenguaje de programación Dart y proporciona diferentes componentes para construir interfaces de usuario.

En esta práctica, Flutter se utilizará para desarrollar la tercera versión de la aplicación **"Hola Mundo"**, complementando las versiones desarrolladas de forma nativa mediante Views/XML y Jetpack Compose.

### Instalación y configuración

Se realizó la instalación del SDK de Flutter y se configuró su acceso desde la variable de entorno `PATH`. Posteriormente, se configuró Flutter para trabajar con el entorno de desarrollo de Android.

La instalación y las dependencias necesarias fueron verificadas mediante:

```bash
flutter doctor
```

Este comando permitió comprobar el estado de la instalación de Flutter, así como identificar que las herramientas necesarias para el desarrollo y ejecución de aplicaciones Android estuvieran disponibles.

### Evidencia

> Captura de pantalla de la terminal mostrando el resultado de `flutter doctor`.

---
