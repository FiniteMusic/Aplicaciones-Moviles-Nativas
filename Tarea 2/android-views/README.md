# Android Views — F1 UI Garage

Implementación de **F1 UI Garage** utilizando el sistema tradicional de vistas de Android con **Kotlin y XML**.

Esta versión utiliza componentes de Material Design, layouts XML, fragments y navegación para implementar el catálogo interactivo.

---

## Información de la implementación

| Característica | Tecnología |
|---|---|
| Lenguaje | Kotlin |
| Interfaz | XML |
| Plataforma | Android |
| Paradigma UI | Imperativo / basado en Views |
| Diseño | Material Components |
| Navegación | Navigation Component |
| SDK mínimo | API 26 |
| SDK objetivo | API 36 |

---

## Arquitectura

La aplicación utiliza una única `MainActivity` que contiene un `NavHostFragment`.

Las diferentes categorías del catálogo se implementan mediante `Fragment`.

```text
MainActivity
    │
    ▼
NavHostFragment
    │
    ├── HomeFragment
    ├── TextInputFragment
    ├── ButtonsFragment
    ├── SelectionFragment
    ├── ListsFragment
    ├── FeedbackFragment
    └── LayoutsFragment
```

La navegación entre las diferentes pantallas se define mediante un Navigation Graph.

---

## Estructura principal

```text
android-views/
└── app/
    └── src/
        └── main/
            ├── java/
            │   └── com.marcudlcv.f1uigarageviews/
            │       ├── MainActivity.kt
            │       ├── data/
            │       │   ├── Driver.kt
            │       │   └── DriverRepository.kt
            │       └── ui/
            │           ├── HomeFragment.kt
            │           ├── TextInputFragment.kt
            │           ├── ButtonsFragment.kt
            │           ├── SelectionFragment.kt
            │           ├── ListsFragment.kt
            │           ├── FeedbackFragment.kt
            │           └── LayoutsFragment.kt
            │
            └── res/
                ├── layout/
                ├── navigation/
                ├── values/
                └── drawable/
```

---

## Secciones implementadas

### Ferrari — Entradas de texto

Demuestra componentes destinados a capturar y validar información.

Incluye:

- `TextInputEditText`
- Entrada numérica
- Contraseña
- Correo electrónico y teléfono
- Texto multilínea
- Selector de escudería
- Búsqueda dinámica
- Validación y registro de pilotos

---

### McLaren — Botones y controles

Demuestra componentes destinados a ejecutar acciones o modificar estados.

Incluye:

- `Button`
- `MaterialButton`
- `ImageButton`
- `FloatingActionButton`
- `ToggleButton`
- `SwitchMaterial`
- Botón con indicador de carga
- Botón deshabilitado

---

### Mercedes — Selecciones

Permite configurar diferentes parámetros mediante controles de selección.

Incluye:

- `CheckBox`
- `RadioButton`
- `Spinner`
- `Switch`
- `SeekBar`
- `RatingBar`
- Chips

---

### Williams — Listas

Demuestra diferentes mecanismos para presentar colecciones.

Incluye:

- `ListView`
- `RecyclerView`
- `GridView`
- Filtros
- Conteo de elementos
- Estado vacío
- Lista dinámica de pilotos

La lista de pilotos obtiene sus datos del mismo `DriverRepository` utilizado por Ferrari.

---

### Aston Martin — Retroalimentación

Demuestra mecanismos para informar al usuario sobre acciones y procesos.

Incluye:

- `Toast`
- `Snackbar`
- `AlertDialog`
- `ProgressBar` horizontal
- `ProgressBar` circular
- Validación mediante `TextInputLayout.error`
- Panel de estado

---

### Alpine — Layouts

Demuestra diferentes mecanismos de distribución de componentes.

Incluye:

- `LinearLayout`
- `ConstraintLayout`
- `FrameLayout`
- `GridLayout`
- `ScrollView`
- `HorizontalScrollView`

También incluye ejemplos interactivos de cambio de orientación y superposición de elementos.

---

## Estado compartido

Ferrari y Williams comparten información mediante:

```text
Driver
  │
  ▼
DriverRepository
  ▲
  │
  ├── TextInputFragment
  └── ListsFragment
```

`TextInputFragment` registra los pilotos y `ListsFragment` consulta los datos almacenados.

El número del piloto funciona como identificador único dentro del ejemplo y no se permiten dorsales duplicados.

Los datos se mantienen únicamente durante la ejecución del proceso de la aplicación.

---

## Navegación

La navegación utiliza Android Navigation Component.

El flujo principal es:

```text
Home
 ├── Ferrari
 ├── McLaren
 ├── Mercedes
 ├── Williams
 ├── Aston Martin
 └── Alpine
```

Todas las secciones permiten regresar al catálogo principal mediante la navegación estándar de Android.

---

## Ejecución

1. Abrir `android-views/` en Android Studio.
2. Esperar la sincronización de Gradle.
3. Seleccionar un dispositivo físico o emulador Android.
4. Ejecutar la configuración `app`.

También puede compilarse desde terminal.

En Windows:

```bash
gradlew.bat assembleDebug
```

En Linux/macOS:

```bash
./gradlew assembleDebug
```

---

## APK

Para generar un APK de depuración:

```bash
gradlew.bat assembleDebug
```

Para generar un APK de release:

```bash
gradlew.bat assembleRelease
```

Los resultados se generan dentro de:

```text
app/build/outputs/apk/
```

---

## Características principales

- Interfaz basada en XML.
- Uso de Fragments.
- Navigation Component.
- Material Components.
- Validación de formularios.
- Estado compartido entre pantallas.
- Listas dinámicas.
- Componentes interactivos.
- Tema claro.
- Diseño inspirado en Fórmula 1.

---

## Relación con las otras implementaciones

Esta versión representa el enfoque tradicional de desarrollo de interfaces Android.

La misma aplicación también se encuentra implementada mediante:

- Jetpack Compose.
- Flutter.

El README principal del repositorio contiene la tabla de equivalencias entre los componentes utilizados en las tres tecnologías.