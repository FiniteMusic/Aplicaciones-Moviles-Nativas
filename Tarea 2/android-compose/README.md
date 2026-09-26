# Jetpack Compose — F1 UI Garage

Implementación de **F1 UI Garage** utilizando **Kotlin y Jetpack Compose**.

Esta versión reconstruye el mismo catálogo de componentes de Android Views mediante un paradigma declarativo basado en funciones `@Composable` y estado reactivo.

---

## Información de la implementación

| Característica | Tecnología |
|---|---|
| Lenguaje | Kotlin |
| Interfaz | Jetpack Compose |
| Plataforma | Android |
| Paradigma UI | Declarativo |
| Diseño | Material 3 |
| Navegación | Navigation Compose |
| SDK mínimo | API 26 |
| SDK objetivo | API 36 |

---

## Arquitectura

La aplicación utiliza una única `MainActivity`.

A partir de ella se carga la navegación principal de Compose y las diferentes pantallas se representan mediante funciones `@Composable`.

```text
MainActivity
    │
    ▼
GarageNavigation
    │
    ├── HomeScreen
    ├── TextInputScreen
    ├── ButtonsScreen
    ├── SelectionScreen
    ├── ListsScreen
    ├── FeedbackScreen
    └── LayoutsScreen
```

---

## Estructura principal

```text
android-compose/
└── app/
    └── src/
        └── main/
            └── java/
                └── com.marcudlcv.f1uigaragecompose/
                    ├── MainActivity.kt
                    │
                    ├── data/
                    │   ├── Driver.kt
                    │   └── DriverRepository.kt
                    │
                    ├── navigation/
                    │   ├── GarageNavigation.kt
                    │   └── GarageRoutes.kt
                    │
                    └── ui/
                        ├── components/
                        │   └── TeamCategoryCard.kt
                        ├── screens/
                        │   ├── HomeScreen.kt
                        │   ├── TextInputScreen.kt
                        │   ├── ButtonsScreen.kt
                        │   ├── SelectionScreen.kt
                        │   ├── ListsScreen.kt
                        │   ├── FeedbackScreen.kt
                        │   └── LayoutsScreen.kt
                        └── theme/
                            ├── Color.kt
                            ├── Theme.kt
                            └── Type.kt
```

---

## Secciones implementadas

### Ferrari — Entradas de texto

Incluye ejemplos de:

- `OutlinedTextField`
- Entrada numérica
- Contraseña
- Teclado para correo y teléfono
- Texto multilínea
- `ExposedDropdownMenuBox`
- Búsqueda dinámica
- Validación y registro

Los pilotos registrados se almacenan mediante `DriverRepository`.

---

### McLaren — Botones y controles

Incluye:

- `Button`
- `FilledTonalButton`
- `IconButton`
- `FloatingActionButton`
- Control de estado para DRS
- `Switch`
- `CircularProgressIndicator`
- Botón deshabilitado

Los controles modifican estado mediante Compose y provocan automáticamente la recomposición de la interfaz.

---

### Mercedes — Selecciones

Incluye:

- `Checkbox`
- `RadioButton`
- `ExposedDropdownMenuBox`
- `Switch`
- `Slider`
- Selector personalizado de nivel
- `FilterChip`

Las selecciones se reflejan inmediatamente en un resumen de configuración.

---

### Williams — Listas

Incluye:

- `LazyColumn`
- `LazyRow`
- Listas dinámicas
- Filtrado por escudería
- Cuadrícula declarativa
- Conteo de pilotos
- Estado vacío

Los datos provienen del repositorio compartido con Ferrari.

---

### Aston Martin — Retroalimentación

Incluye:

- Mensajes temporales
- `SnackbarHost`
- `AlertDialog`
- `LinearProgressIndicator`
- `CircularProgressIndicator`
- Validación visual
- Panel de estado

---

### Alpine — Layouts

Incluye:

- `Column`
- `Row`
- `Box`
- Composición en cuadrícula
- `LazyColumn`
- `LazyRow`
- `horizontalScroll`

También demuestra cambios de layout provocados por el estado y superposición de elementos.

---

## Estado y recomposición

Jetpack Compose utiliza un modelo declarativo.

En lugar de modificar directamente un componente visual, la interfaz se describe en función del estado.

Conceptualmente:

```text
Estado
  │
  ▼
@Composable
  │
  ▼
Interfaz
```

Cuando cambia un valor observado por una función `@Composable`, Compose puede volver a ejecutar las partes necesarias de la composición.

Esto se utiliza en ejemplos como:

- Activación del DRS.
- Telemetría.
- Selección de estrategia.
- Filtros.
- Indicadores de progreso.
- Cambio de layouts.
- Registro de pilotos.

---

## Repositorio compartido

El registro de pilotos utiliza:

```text
DriverRepository
      │
      ├── TextInputScreen
      │       │
      │       └── Registra
      │
      └── ListsScreen
              │
              └── Consulta / elimina
```

`DriverRepository` utiliza una colección observable para que los cambios puedan reflejarse en la interfaz.

No se utiliza persistencia permanente.

---

## Navegación

La navegación se implementa mediante Navigation Compose.

Las rutas principales son:

```text
HOME
TEXT_INPUT
BUTTONS
SELECTION
LISTS
FEEDBACK
LAYOUTS
```

Cada tarjeta del Home navega a su correspondiente pantalla.

---

## Tema

La aplicación utiliza Material 3 y un esquema exclusivamente claro.

La paleta mantiene la identidad visual compartida con las otras implementaciones:

- Fondo gris claro.
- Superficies blancas.
- Encabezados grafito.
- Ferrari rojo.
- McLaren naranja.
- Mercedes turquesa.
- Williams azul.
- Aston Martin verde.
- Alpine azul.

---

## Ejecución

1. Abrir `android-compose/` en Android Studio.
2. Esperar la sincronización de Gradle.
3. Seleccionar un emulador o dispositivo Android.
4. Ejecutar `app`.

Desde terminal en Windows:

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

Los APK se generan dentro de:

```text
app/build/outputs/apk/
```

---

## Características principales

- Interfaz 100 % declarativa.
- Kotlin.
- Jetpack Compose.
- Material 3.
- Navigation Compose.
- Estado reactivo.
- Recomposición automática.
- Estado compartido entre pantallas.
- Componentes interactivos.
- Tema claro.
- Diseño inspirado en Fórmula 1.

---

## Relación con las otras implementaciones

Esta implementación permite comparar el desarrollo declarativo de Jetpack Compose con:

- Android Views y XML.
- Flutter y Dart.

El README principal contiene una tabla con las equivalencias funcionales entre los componentes utilizados.