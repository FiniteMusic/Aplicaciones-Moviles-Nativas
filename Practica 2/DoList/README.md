# DoList — Aplicación móvil

Aplicación móvil de gestión de tareas desarrollada para la práctica de Aplicaciones Móviles Nativas

Este directorio contiene la aplicación Android desarrollada con **Kotlin y Jetpack Compose**. El backend de la aplicación se encuentra separado en la carpeta `backend/`, donde cuenta con su propio archivo `README.md` con la documentación correspondiente.

> **Estado actual:** versión en desarrollo. Para la entrega de la práctica, los módulos prioritarios de **registro e inicio de sesión** se encuentran funcionales. El desarrollo de las demás funcionalidades continuará posteriormente.

---

## Tecnologías utilizadas

- **Kotlin**
- **Android Studio**
- **Jetpack Compose**
- **Material 3**
- **Navigation Compose**
- **Retrofit**
- **DataStore**
- **Node.js + Express** para el backend
- **Prisma + SQLite** para persistencia
- **JWT** para autenticación
- **Docker** para el entorno del backend

---

## Interfaz gráfica

La interfaz de DoList fue desarrollada completamente en **Jetpack Compose**, utilizando una propuesta visual basada en el concepto de **Liquid Glass**.

Entre las características de diseño se encuentran:

- Superficies translúcidas y elementos con efecto de vidrio.
- Componentes reutilizables para mantener consistencia visual.
- Navegación inferior entre las secciones principales.
- Tarjetas de tareas con prioridad, fecha y estado.
- Formularios para creación y edición.
- Selector de fecha mediante componentes de Material 3.
- Pantallas independientes para autenticación, perfil y configuración.
- Soporte para tema claro y oscuro.

La aplicación busca mantener una interfaz limpia y moderna, evitando elementos visuales que no correspondan con las funcionalidades disponibles en el backend.

---

## Módulos funcionales actualmente

### 1. Autenticación

Los módulos de autenticación se encuentran funcionando correctamente.

#### Registro

Permite:

- Registrar un nuevo usuario.
- Validar los datos requeridos.
- Registrar correo electrónico y contraseña.
- Crear la cuenta mediante el backend.
- Iniciar sesión automáticamente después de un registro exitoso.

#### Inicio de sesión

Permite:

- Ingresar mediante correo electrónico y contraseña.
- Autenticarse mediante JWT.
- Almacenar el token de sesión mediante DataStore.
- Consultar los datos del usuario autenticado.
- Mantener la sesión disponible para las peticiones posteriores.

---

### 2. Home

La pantalla principal permite:

- Mostrar el nombre del usuario que inició sesión.
- Consultar y visualizar las tareas registradas en el backend.
- Separar las tareas entre **Hoy** y **Próximas**.
- Mostrar prioridad y fecha de vencimiento.
- Crear nuevas tareas.
- Acceder al formulario de edición de tareas.
- Mostrar el progreso correspondiente a las tareas del día.

La información del usuario y las tareas se obtiene mediante el backend utilizando Retrofit.

---

### 3. Gestión de tareas

Actualmente se encuentra implementado el flujo para:

- Crear tareas.
- Consultar tareas.
- Actualizar el estado de una tarea.
- Acceder a la edición de una tarea.
- Definir prioridad: Alta, Media o Baja.
- Definir fecha de vencimiento.
- Manejar los estados Pendiente y Completa.

La comunicación entre la aplicación Android y el backend se realiza mediante la API REST.

---

### 4. Perfil

La pantalla de perfil se encuentra integrada a la navegación de la aplicación y permite:

- Visualizar la información básica del usuario.
- Acceder a configuración.
- Acceder al cambio de contraseña.
- Acceder al cambio de imagen de perfil.
- Cambiar la apariencia de la aplicación mediante el selector de tema.
- Cerrar sesión.

Algunas de estas funcionalidades continúan en proceso de integración completa con el backend.

---

### 5. Configuración y preferencias

Se encuentra implementado el manejo local de preferencias relacionadas con la interfaz:

- Tema del sistema.
- Tema claro.
- Tema oscuro.

Estas preferencias se almacenan mediante **DataStore**.

---

## Problemas y bugs conocidos

La aplicación continúa en desarrollo y actualmente presenta algunos problemas conocidos:

### Progreso de tareas

El componente **"Progreso de hoy"** todavía no refleja correctamente el cambio de estado de una tarea después de marcarla como completada.

El comportamiento esperado es:

```text
0/1 → 1/1
0%  → 100%
```

### Tareas completadas de hoy

Actualmente, al completar una tarea cuya fecha corresponde al día actual, la tarea puede dejar de aparecer en la sección **Hoy**.

El comportamiento esperado es que la tarea permanezca dentro de **Hoy**, pero cambie visualmente a estado **Completa**.

### Fecha de vencimiento

Existe un problema relacionado con el manejo de fechas entre Android y el backend.

Después de reiniciar la aplicación, algunas tareas configuradas para el día actual pueden aparecer con una fecha del día siguiente y, por consecuencia, pasar a la sección **Próximas**.

Este comportamiento se encuentra pendiente de corrección.

### Sincronización de operaciones

La integración completa de determinadas operaciones de tareas todavía está en desarrollo. Se continuará trabajando en la sincronización entre el estado de la interfaz y la información persistida en el backend.

---

## Próximos pasos de desarrollo

El desarrollo continuará principalmente en el siguiente orden:

1. Corregir el manejo de fechas para evitar desplazamientos de un día.
2. Corregir la permanencia de tareas completadas dentro de la sección **Hoy**.
3. Corregir la actualización del componente de progreso.
4. Completar la integración de edición de tareas con el backend.
5. Completar la eliminación de tareas.
6. Integrar completamente la sección **Todas mis tareas** con la API.
7. Completar la integración del cambio de avatar.
8. Completar la integración del cambio de contraseña.
9. Mejorar el manejo de errores y mensajes mostrados al usuario.
10. Realizar pruebas de integración y estabilidad.
11. Continuar refinando la interfaz Liquid Glass.

---

## Arquitectura general

DoList está dividido en dos componentes principales:

```text
DoList
│
├── Aplicación Android
│   ├── Kotlin
│   ├── Jetpack Compose
│   ├── Navigation Compose
│   ├── Retrofit
│   └── DataStore
│
└── Backend
    ├── Node.js
    ├── Express
    ├── Prisma
    ├── SQLite
    ├── JWT
    └── Docker
```

La aplicación Android consume la API REST proporcionada por el backend.

La documentación específica del backend se encuentra en:

```text
backend/README.md
```

De esta manera, la documentación de la aplicación móvil y la del servidor permanecen separadas.

---

## Ejecución del proyecto

### Requisitos

Para ejecutar la aplicación Android se requiere:

- Android Studio.
- Android SDK.
- Un emulador Android o dispositivo físico.
- Backend de DoList ejecutándose.

Para el backend, consultar:

```text
backend/README.md
```

### Dirección del backend

Durante el desarrollo con el emulador de Android, la aplicación utiliza:

```text
http://10.0.2.2:5000/
```

`10.0.2.2` permite que el emulador Android acceda al `localhost` de la computadora donde se ejecuta el backend.

Para utilizar un dispositivo físico, debe configurarse la dirección IP local de la computadora que ejecuta el backend.

---

## Estado del proyecto

| Módulo | Estado |
|---|---|
| Registro | ✅ Funcional |
| Inicio de sesión | ✅ Funcional |
| Persistencia de sesión | ✅ Funcional |
| Consulta del usuario autenticado | ✅ Funcional |
| Visualización de tareas en Home | ✅ Funcional |
| Creación de tareas | ✅ Funcional |
| Actualización de estado | ⚠️ En desarrollo |
| Edición de tareas | ⚠️ En desarrollo |
| Eliminación de tareas | ⚠️ En desarrollo |
| Progreso de hoy | ⚠️ Presenta bug |
| Manejo de fechas | ⚠️ Presenta bug |
| Perfil | ✅ Interfaz funcional |
| Cambio de tema | ✅ Funcional |
| Cambio de avatar | ⚠️ En desarrollo |
| Cambio de contraseña | ⚠️ En desarrollo |
| Todas mis tareas | ⚠️ En desarrollo |

---

## Objetivo de la versión actual

La versión actual tiene como objetivo establecer una base funcional para la aplicación móvil, principalmente mediante la implementación de:

- Interfaz gráfica en Jetpack Compose.
- Navegación entre pantallas.
- Registro de usuarios.
- Inicio de sesión.
- Manejo de sesión mediante JWT.
- Comunicación con el backend mediante Retrofit.
- Consulta y creación de tareas.

A partir de esta base se continuará con la integración y corrección del resto de funcionalidades.

---

## Autor

Proyecto desarrollado por Marco Uriel De la Cruz Velazquez.
