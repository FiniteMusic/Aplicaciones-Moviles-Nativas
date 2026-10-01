# Aplicaciones Móviles Nativas

**Instituto Politécnico Nacional**  
**Escuela Superior de Cómputo (ESCOM)**

**Unidad de Aprendizaje:** Desarrollo de Aplicaciones Móviles Nativas  
**Programa Académico:** Ingeniería en Sistemas Computacionales  
**Plan de Estudios:** 2020  
**Periodo Escolar:** 2027-1  
**Grupo:** 7CV4  

---

# Información del alumno

- **Nombre:** Marco Uriel De la Cruz Velázquez
- **Grupo:** 7CV4
- **Unidad de aprendizaje:** Desarrollo de Aplicaciones Móviles Nativas
- **Institución:** Instituto Politécnico Nacional — Escuela Superior de Cómputo

---

# Examen — Pull Request con aseguramiento de calidad

## 1. Descripción general

El objetivo de esta actividad fue preparar, implementar, verificar y documentar una modificación pequeña y comprobable sobre el proyecto:

**PolitecnicoOpenWorld**

Repositorio original:

https://github.com/gabrielhuav/PolitecnicoOpenWorld

La modificación se desarrolló sobre un fork personal del proyecto y posteriormente se envió mediante un Pull Request dirigido a la rama `main` del repositorio original.

El trabajo se enfocó principalmente en el aseguramiento de calidad de la modificación, incluyendo:

- Reproducción del comportamiento original.
- Definición de criterios de aceptación.
- Identificación de riesgos.
- Implementación del cambio.
- Ejecución de pruebas manuales.
- Validación de accesibilidad.
- Pruebas de regresión.
- Pruebas de compatibilidad.
- Validación local mediante Gradle.
- Registro de evidencias.
- Preparación del Pull Request.
- Revisión por pares.

---

# 2. Enlaces principales

## Repositorio original

https://github.com/gabrielhuav/PolitecnicoOpenWorld

## Fork personal

https://github.com/FiniteMusic/PolitecnicoOpenWorld

## Issue

**Improve Settings switch row interaction and accessibility**

https://github.com/FiniteMusic/PolitecnicoOpenWorld/issues/1

## Pull Request

**PR #168 — Improve Settings switch interaction and accessibility**

https://github.com/gabrielhuav/PolitecnicoOpenWorld/pull/168

---

# 3. Rama de trabajo

La modificación se desarrolló en una rama independiente creada a partir de la versión actualizada de `main`.

```text
fix/settings-switch-accessibility
```

La rama fue publicada en el fork personal y utilizada como rama de origen del Pull Request.

---

# 4. Versiones utilizadas

## SHA base

La reproducción inicial del comportamiento y la creación de la rama de trabajo se realizaron a partir del siguiente commit:

```text
7ed325393f82872c2be94ff2ada46948efa19152
```

## SHA de implementación probado

La versión utilizada para las pruebas manuales y la validación local corresponde al commit:

```text
57cfa8c24129ce8e459fd6a925a38f5bb19c1fb4
```

---

# 5. Problema identificado

El componente reutilizable `SettingsSwitch` mostraba una preferencia compuesta por:

- Título.
- Descripción.
- Switch visual.

Sin embargo, únicamente el `Switch` respondía a las interacciones del usuario.

Al tocar:

- El título.
- La descripción.
- El espacio disponible dentro de la fila.

el estado de la preferencia no cambiaba.

Esto reducía el área interactiva del control y producía una diferencia entre la presentación visual de la preferencia y el área que realmente podía recibir la interacción.

---

# 6. Reproducción del comportamiento original

El comportamiento fue comprobado antes de realizar cualquier modificación sobre el siguiente SHA:

```text
7ed325393f82872c2be94ff2ada46948efa19152
```

Procedimiento utilizado:

1. Ejecutar la aplicación.
2. Acceder a la pantalla de `Settings`.
3. Localizar una preferencia implementada mediante `SettingsSwitch`.
4. Tocar el título de la preferencia.
5. Verificar que el estado no cambia.
6. Tocar la descripción.
7. Verificar nuevamente que el estado no cambia.
8. Tocar directamente el switch.
9. Verificar que el estado sí cambia.

## Evidencia del comportamiento original

https://github.com/user-attachments/assets/e430d868-acda-477c-aea1-96c926b7e063

Esta evidencia fue obtenida antes de implementar la modificación.

---

# 7. Comportamiento esperado

Se definió como comportamiento esperado que toda la fila correspondiente a una preferencia de configuración funcione como un único control lógico de tipo switch.

Esto incluye la interacción sobre:

- El título.
- La descripción.
- El espacio disponible de la fila.
- El propio switch visual.

Cada interacción debe producir exactamente un cambio de estado.

---

# 8. Alcance

La modificación se limitó al componente reutilizable:

```text
PolitecnicoOpenWorld/shared/src/commonMain/kotlin/ovh/gabrielhuav/pow/features/settings/ui/SettingsSectionCommon.kt
```

Específicamente al componente:

```kotlin
SettingsSwitch
```

No se modificaron:

- La arquitectura de navegación.
- Las dependencias del proyecto.
- La estructura general de Settings.
- Otros componentes de configuración.
- Bases de datos.
- Servicios externos.
- El diseño visual general de la aplicación.
- La persistencia de información.
- La arquitectura del proyecto.

---

# 9. Implementación

La solución consistió en trasladar la responsabilidad de interacción desde el switch visual hacia toda la fila de la preferencia.

Se incorporó:

```kotlin
Modifier.toggleable(
    value = checked,
    role = Role.Switch,
    onValueChange = onCheckedChange,
)
```

sobre el `Row` principal.

Además, el switch visual fue configurado mediante:

```kotlin
onCheckedChange = null
```

De esta manera, el `Row` funciona como la única fuente lógica de interacción, mientras que el componente `Switch` continúa representando visualmente el estado actual.

La propiedad:

```kotlin
role = Role.Switch
```

también permite que los servicios de accesibilidad interpreten la fila como un control de tipo switch.

---

# 10. Justificación de la solución

La implementación busca evitar dos controles interactivos independientes dentro de una misma preferencia.

Si tanto el `Row` como el `Switch` administraran de manera independiente el evento de cambio, existiría riesgo de producir:

- Cambios de estado duplicados.
- Estados inconsistentes.
- Controles duplicados para lectores de pantalla.
- Comportamientos diferentes dependiendo del área tocada.

Al utilizar el `Row` como único origen de interacción, se conserva un modelo más consistente.

---

# 11. Criterios de aceptación

Se establecieron los siguientes criterios:

- [x] Tocar el título cambia el estado exactamente una vez.
- [x] Tocar la descripción cambia el estado exactamente una vez.
- [x] Tocar el espacio disponible de la fila cambia el estado exactamente una vez.
- [x] Tocar directamente el switch continúa cambiando el estado exactamente una vez.
- [x] Las interacciones repetidas no producen cambios dobles.
- [x] No se presentan estados inconsistentes entre la fila y el switch visual.
- [x] TalkBack identifica la preferencia como un switch.
- [x] TalkBack comunica correctamente su estado.
- [x] Regresar a Settings no afecta la funcionalidad del control.
- [x] El aumento del tamaño de texto no genera una regresión crítica en la interfaz.

---

# 12. Riesgos identificados

Antes de ejecutar las pruebas se consideraron principalmente los siguientes riesgos.

### R1 — Doble cambio de estado

Al permitir que toda la fila sea interactiva, existía el riesgo de que el `Row` y el `Switch` procesaran el mismo evento y produjeran dos cambios consecutivos.

### R2 — Regresión del switch original

Al trasladar el comportamiento al elemento padre, podía dejar de funcionar correctamente la interacción directa sobre el switch.

### R3 — Duplicación semántica

TalkBack podía detectar la fila y el switch interno como dos controles diferentes, dificultando la navegación mediante tecnologías de asistencia.

### R4 — Regresión de navegación

La modificación podía producir un comportamiento inesperado al abandonar y regresar a Settings.

### R5 — Problemas con escalado de texto

El aumento del área interactiva o el comportamiento del componente podía generar problemas cuando el sistema utilizara texto ampliado.

---

# 13. Estrategia de pruebas

Para validar la modificación se definieron seis casos de prueba manuales.

| ID | Categoría | Objetivo | Resultado |
|---|---|---|---|
| TC-01 | Happy path | Validar interacción con toda la fila | Passed |
| TC-02 | Límite / alternativo | Validar interacciones repetidas | Passed |
| TC-03 | Regresión | Validar interacción directa con el switch | Passed |
| TC-04 | Navegación / estado | Validar comportamiento después de navegar | Passed |
| TC-05 | Accesibilidad | Validar comportamiento mediante TalkBack | Passed |
| TC-06 | Compatibilidad | Validar funcionamiento con texto ampliado | Passed |

---

# 14. Casos de prueba

## TC-01 — Interacción con toda la fila

**Categoría:** Happy path  
**Resultado:** Passed  
**SHA probado:**

```text
57cfa8c24129ce8e459fd6a925a38f5bb19c1fb4
```

### Objetivo

Comprobar que las diferentes áreas de la preferencia pueden utilizarse para modificar su estado.

### Procedimiento

1. Abrir Settings.
2. Localizar una preferencia implementada mediante `SettingsSwitch`.
3. Tocar el título.
4. Comprobar el cambio de estado.
5. Tocar la descripción.
6. Comprobar el cambio de estado.
7. Tocar un espacio disponible de la fila.
8. Comprobar nuevamente el cambio.

### Resultado observado

Las distintas áreas de la fila modificaron el estado exactamente una vez por interacción.

### Evidencia

https://github.com/user-attachments/assets/4e4dc0f7-3f47-4424-a6e3-000f1fdbc802

---

## TC-02 — Interacciones repetidas

**Categoría:** Alternativo / límite  
**Resultado:** Passed

### Objetivo

Verificar que las interacciones repetidas no generen eventos duplicados ni pérdida de sincronización.

### Procedimiento

Se realizaron múltiples interacciones consecutivas utilizando:

- Título.
- Descripción.
- Área libre.
- Switch.

### Resultado observado

Cada interacción generó exactamente un cambio de estado.

No se observaron:

- Cambios dobles.
- Bloqueos.
- Estados inconsistentes.
- Pérdida de respuesta.

### Evidencia

https://github.com/user-attachments/assets/4eac665a-193e-492d-93db-5819c1590c43

---

## TC-03 — Regresión del switch

**Categoría:** Regresión  
**Resultado:** Passed

### Objetivo

Comprobar que el cambio realizado al elemento padre no elimine el funcionamiento esperado al tocar directamente el switch.

### Procedimiento

1. Abrir Settings.
2. Localizar una preferencia.
3. Tocar directamente el switch.
4. Repetir la interacción varias veces.

### Resultado observado

El switch continuó funcionando correctamente y cada interacción generó exactamente un cambio de estado.

### Evidencia

https://github.com/user-attachments/assets/e569f1b7-4a76-4435-a2ac-3dc8a8971e7d

---

## TC-04 — Navegación y regreso a Settings

**Categoría:** Navegación / estado  
**Resultado:** Passed

### Objetivo

Comprobar que abandonar y volver a la pantalla de Settings no afecte el funcionamiento del componente.

### Procedimiento

1. Abrir Settings.
2. Modificar una preferencia.
3. Salir de la pantalla.
4. Regresar a Settings.
5. Localizar nuevamente la preferencia.
6. Interactuar con ella.

### Resultado observado

El componente continuó respondiendo correctamente después de regresar a Settings.

No se observó una regresión relacionada con la navegación.

### Evidencia

https://github.com/user-attachments/assets/5fa22784-bb36-4474-9d1a-948df9b1fbaf

---

## TC-05 — Accesibilidad con TalkBack

**Categoría:** Accesibilidad  
**Resultado:** Passed

### Objetivo

Validar la semántica del componente mediante un servicio de accesibilidad.

### Procedimiento

1. Activar TalkBack.
2. Abrir la aplicación.
3. Acceder a Settings.
4. Navegar hasta una preferencia.
5. Escuchar la información anunciada.
6. Ejecutar una doble pulsación.
7. Comprobar el nuevo estado anunciado.

### Resultado observado

TalkBack:

- Detectó la preferencia como un único control.
- Identificó correctamente su función de switch.
- Comunicó el estado activo/inactivo.
- Permitió modificar el estado mediante doble pulsación.
- Comunicó posteriormente el nuevo estado.

### Evidencia

https://github.com/user-attachments/assets/bec2c711-e1a9-4424-9a41-f6d5ef12ed15

---

## TC-06 — Texto ampliado

**Categoría:** Compatibilidad / entorno  
**Resultado:** Passed

### Objetivo

Comprobar el funcionamiento de la preferencia cuando el sistema utiliza un tamaño de texto mayor.

### Procedimiento

1. Incrementar el tamaño de texto del sistema.
2. Ejecutar nuevamente la aplicación.
3. Abrir Settings.
4. Localizar la preferencia.
5. Revisar título y descripción.
6. Interactuar con la fila.

### Resultado observado

Se comprobó que:

- El título continúa siendo legible.
- La descripción permanece visible.
- No existe superposición crítica entre el texto y el switch.
- El área interactiva continúa funcionando.

### Evidencia

https://github.com/user-attachments/assets/6d205c78-7f6f-40e6-8843-de16e275f839

---

# 15. Matriz de riesgos y cobertura

| Riesgo | Caso utilizado para cubrirlo |
|---|---|
| Doble cambio de estado | TC-02 |
| Regresión del switch | TC-03 |
| Duplicación semántica | TC-05 |
| Regresión de navegación | TC-04 |
| Problemas por escalado de texto | TC-06 |
| Funcionamiento principal de la modificación | TC-01 |

---

# 16. Validación automatizada local

Además de las pruebas manuales se ejecutó el conjunto de tareas Gradle indicado para validar el proyecto.

Desde la carpeta interna de `PolitecnicoOpenWorld` se ejecutó:

```powershell
.\gradlew.bat :app:assembleDebug :app:testDebugUnitTest :shared:testAndroidHostTest --stacktrace
```

Resultado:

```text
BUILD SUCCESSFUL in 41s
79 actionable tasks: 19 executed, 60 up-to-date
```

Esta ejecución incluyó:

- Compilación de la aplicación Android en modo Debug.
- Pruebas unitarias del módulo `app`.
- Pruebas Android Host del módulo `shared`.

---

# 17. Limitación del entorno local

Durante la compilación se informó que:

```text
google-services.json
```

no estaba disponible dentro del clon del proyecto.

Como consecuencia, Firebase Auth permaneció deshabilitado durante este build.

Esta condición no se encuentra relacionada con la modificación realizada sobre `SettingsSwitch` y no impidió la ejecución de las pruebas asociadas a esta contribución.

---

# 18. Estado de Integración Continua

El Pull Request activó el workflow:

```text
PR Quality Gate
```

sobre el commit:

```text
57cfa8c24129ce8e459fd6a925a38f5bb19c1fb4
```

GitHub mostró el siguiente resultado:

```text
Workflow runs completed with no jobs
```

El workflow fue detectado y finalizó sin ejecutar jobs.

Por esta razón, la validación necesaria también fue ejecutada localmente mediante Gradle.

No se registró una falla de implementación por parte del workflow; simplemente no se ejecutaron jobs de CI para este Pull Request.

---

# 19. Archivo modificado

La contribución funcional se encuentra limitada a un solo archivo:

```text
PolitecnicoOpenWorld/shared/src/commonMain/kotlin/ovh/gabrielhuav/pow/features/settings/ui/SettingsSectionCommon.kt
```

Estadísticas del Pull Request:

```text
Archivos modificados: 1
Adiciones: 17
Eliminaciones: 3
Commits: 1
```

---

# 20. Commit

La implementación se registró mediante el commit:

```text
57cfa8c24129ce8e459fd6a925a38f5bb19c1fb4
```

Mensaje:

```text
fix: make settings switch rows fully interactive
```

---

# 21. Rollback

Debido a que la modificación está contenida en un único archivo y un único commit, puede revertirse utilizando:

```bash
git revert 57cfa8c24129ce8e459fd6a925a38f5bb19c1fb4
```

No se realizaron:

- Migraciones.
- Cambios de base de datos.
- Cambios de formatos persistentes.
- Cambios de dependencias.
- Modificaciones arquitectónicas.

Por lo tanto, la estrategia de rollback tiene un impacto reducido sobre el resto del proyecto.

---

# 22. Revisión por pares

El Pull Request fue marcado como:

```text
Ready for review
```

después de completar la validación manual y local.

Pull Request:

https://github.com/gabrielhuav/PolitecnicoOpenWorld/pull/168

La revisión por pares contempla que otro integrante:

1. Revise los cambios realizados.
2. Obtenga la rama del Pull Request.
3. Ejecute la aplicación.
4. Reproduzca al menos un caso de prueba.
5. Registre su resultado mediante una revisión o comentario en GitHub.


# 23. Entorno de pruebas

La aplicación fue desarrollada y validada sobre un entorno Windows utilizando Android Studio y un emulador Android.

| Elemento | Configuración |
|---|---|
| Sistema operativo | Windows 11 64 bits |
| Android Studio | Android Studio Quail 4 \| 2026.1.4 Patch 1 |
| Build de Android Studio | `AI-261.26222.65.2614.16379836` |
| Runtime de Android Studio | OpenJDK 25.0.3 |
| Java instalado | Oracle Java 26.0.2.1 |
| Gradle | 9.5.0 |
| Kotlin | 2.3.20 |
| JVM utilizada por Gradle Launcher | 26.0.2.1 |
| JVM del Gradle Daemon | Compatible con Java 21 |
| Dispositivo / AVD | `sdk_gphone16k_x86_64` |
| Android API | 37 |
| Rama | `fix/settings-switch-accessibility` |
| SHA probado | `57cfa8c24129ce8e459fd6a925a38f5bb19c1fb4` |

---

# 24. Registro individual de actividades

| Actividad | Participación |
|---|---|
| Revisión del repositorio | Realizada |
| Identificación del problema | Realizada |
| Reproducción del comportamiento original | Realizada |
| Creación del Issue | Realizada |
| Definición de criterios de aceptación | Realizada |
| Identificación de riesgos | Realizada |
| Creación de rama de trabajo | Realizada |
| Implementación de la modificación | Realizada |
| TC-01 | Ejecutado |
| TC-02 | Ejecutado |
| TC-03 | Ejecutado |
| TC-04 | Ejecutado |
| TC-05 | Ejecutado |
| TC-06 | Ejecutado |
| Validación Gradle | Ejecutada |
| Documentación de evidencias | Realizada |
| Creación del Pull Request | Realizada |
| Documentación del Pull Request | Realizada |
| Revisión por pares | En proceso |

---

# 25. Hallazgos de QA

Durante la ejecución de los casos definidos no se identificaron defectos bloqueantes relacionados con la implementación.

Los principales riesgos considerados fueron comprobados mediante los casos correspondientes.

La validación permitió comprobar que:

- Toda la fila es interactiva.
- Cada interacción genera un único cambio.
- La interacción directa con el switch continúa funcionando.
- No se detectó duplicación visible de estados.
- TalkBack identifica correctamente el control.
- La navegación no elimina la funcionalidad.
- El aumento de texto no genera una regresión crítica.

---

# 26. Estado de la contribución

Con base en las pruebas realizadas:

```text
TC-01: Passed
TC-02: Passed
TC-03: Passed
TC-04: Passed
TC-05: Passed
TC-06: Passed
```

La validación local también concluyó correctamente:

```text
BUILD SUCCESSFUL
```

No se encontraron defectos bloqueantes atribuibles al cambio probado en:

```text
57cfa8c24129ce8e459fd6a925a38f5bb19c1fb4
```

La revisión por pares permanece pendiente al momento de elaborar este documento.

---

# 27. Uso de herramientas de inteligencia artificial

Durante el desarrollo de esta actividad se utilizó ChatGPT como herramienta de apoyo.

Su uso se limitó principalmente a:

- Organización del proceso de trabajo.
- Explicación de conceptos relacionados con Jetpack Compose.
- Planeación de casos de prueba.
- Organización de la matriz de riesgos.
- Apoyo para estructurar la documentación.
- Revisión del formato Markdown del Pull Request.

Las pruebas, interacciones con la aplicación y evidencias documentadas corresponden a ejecuciones reales realizadas sobre el proyecto.

---

# 28. Conclusiones

La modificación realizada permitió ampliar el área interactiva de las preferencias implementadas mediante `SettingsSwitch`, haciendo que la fila completa pueda utilizarse para cambiar el estado de la opción.

La implementación utilizó `Modifier.toggleable` en conjunto con `Role.Switch`, permitiendo que la preferencia se comporte como un único control lógico y mantenga una semántica adecuada para tecnologías de asistencia.

El diseño también evita que el switch visual procese de manera independiente el evento mediante:

```kotlin
onCheckedChange = null
```

lo cual reduce el riesgo de generar cambios duplicados.

Los seis casos de prueba definidos fueron ejecutados correctamente y cubrieron:

- Flujo principal.
- Condiciones alternativas.
- Regresión.
- Navegación.
- Accesibilidad.
- Compatibilidad.

Adicionalmente, la compilación y las pruebas automatizadas ejecutadas localmente finalizaron satisfactoriamente.

La contribución permanece concentrada en un único archivo y un único commit funcional, facilitando tanto su revisión como un posible rollback.

---

# 29. Referencias

## Proyecto

PolitecnicoOpenWorld  
https://github.com/gabrielhuav/PolitecnicoOpenWorld

## Pull Request

PR #168 — Improve Settings switch interaction and accessibility  
https://github.com/gabrielhuav/PolitecnicoOpenWorld/pull/168

## Issue

Improve Settings switch row interaction and accessibility  
https://github.com/FiniteMusic/PolitecnicoOpenWorld/issues/1

## Fork

https://github.com/FiniteMusic/PolitecnicoOpenWorld

---

# 30. Índice de evidencias

| Evidencia | Enlace |
|---|---|
| Comportamiento antes del cambio | https://github.com/user-attachments/assets/e430d868-acda-477c-aea1-96c926b7e063 |
| TC-01 — Full row interaction | https://github.com/user-attachments/assets/4e4dc0f7-3f47-4424-a6e3-000f1fdbc802 |
| TC-02 — Repeated interactions | https://github.com/user-attachments/assets/4eac665a-193e-492d-93db-5819c1590c43 |
| TC-03 — Direct switch regression | https://github.com/user-attachments/assets/e569f1b7-4a76-4435-a2ac-3dc8a8971e7d |
| TC-04 — Navigation and return | https://github.com/user-attachments/assets/5fa22784-bb36-4474-9d1a-948df9b1fbaf |
| TC-05 — TalkBack accessibility | https://github.com/user-attachments/assets/bec2c711-e1a9-4424-9a41-f6d5ef12ed15 |
| TC-06 — Enlarged system text | https://github.com/user-attachments/assets/6d205c78-7f6f-40e6-8843-de16e275f839 |
| Pull Request | https://github.com/gabrielhuav/PolitecnicoOpenWorld/pull/168 |

---

**Marco Uriel De la Cruz Velázquez**  
**Grupo 7CV4**  
**Instituto Politécnico Nacional — Escuela Superior de Cómputo**  
**Periodo Escolar 2027-1**