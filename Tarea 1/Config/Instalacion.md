# Instalación y configuración del servidor MCP Filesystem

## 1. Objetivo

Documentar de forma reproducible la instalación, configuración y
verificación del servidor MCP Filesystem en Windows 11 mediante Visual
Studio Code y GitHub Copilot. El servidor se utiliza para operar sobre
una carpeta de pruebas delimitada, sin conceder acceso a toda la unidad
de almacenamiento ni al directorio completo del usuario.

Las operaciones ejecutadas y sus resultados se documentan por separado
en [`Public/README.md`]

## 2. Elección del cliente

Se eligió **Visual Studio Code (VS Code)** porque permite configurar
servidores MCP mediante un archivo `.vscode/mcp.json`, consultar las
herramientas que publican y utilizarlas desde el chat en modo Agente. En
esta implementación, VS Code actúa como aplicación host, su integración
MCP como cliente del protocolo y
`@modelcontextprotocol/server-filesystem` como servidor. GitHub Copilot
proporciona la interfaz de interacción con el modelo.

El servidor realiza las operaciones de sistema de archivos: el modelo no
accede directamente al disco.

## 3. Entorno y requisitos previos

  Componente              Utilizado / requerido
  ----------------------- -------------------------------------------
  Sistema operativo       Windows 11
  Editor y host MCP       Visual Studio Code
  Interfaz de agente      GitHub Copilot en modo Agente
  Entorno de ejecución    Node.js y npm
  Ejecución del paquete   `npx`
  Servidor                `@modelcontextprotocol/server-filesystem`
  Transporte              `stdio`
  Control de versiones    Git

**Versiones efectivamente utilizadas**:

  -------------------------------------------------------------------------------
  Componente                                  Versión
  ------------------------------------------- -----------------------------------
  Windows 11                                  [Windows 11 Home Single Language]

  Node.js                                     [v24.15.0]

  npm                                         [11.12.1]

  Git                                         [2.54.0.windows.1]


  Fecha de verificación                       [20 de Semptiembre 2026]
  -------------------------------------------------------------------------------

En PowerShell, comprobar:

``` powershell
node --version
npm --version
git --version
```

Para una reproducción más estable se recomienda registrar la versión
concreta del servidor utilizada y, cuando se haya comprobado, fijarla en
la configuración como `@modelcontextprotocol/server-filesystem@VERSION`.

## 4. Preparación del directorio autorizado

La ruta usada en el equipo donde se realizó la práctica fue:

``` text
C:\Users\Marc_\Documents\ESCOM\Aplicaciones Moviles Nativas\Aplicaciones-Moviles-Nativas\Tarea 1\Config\Public
```

En una máquina distinta, sustituir esta ruta por la ruta absoluta de la
carpeta `Config\Public` del repositorio clonado.

Desde PowerShell, ubicado en la raíz de `Tarea 1`, se puede crear la
carpeta y dos archivos iniciales mediante:

``` powershell
New-Item -ItemType Directory -Force -Path ".\Config\Public"
Set-Content -Path ".\Config\Public\ejemplo.txt" -Value "Este es un archivo de prueba para el servidor MCP."
Set-Content -Path ".\Config\Public\notas.txt" -Value "Investigacion sobre el protocolo MCP y sus herramientas."
Get-ChildItem ".\Config\Public"
```

**Precaución:** `Set-Content` reemplaza el contenido si el archivo ya
existe. Ejecutar esos comandos únicamente durante la preparación
inicial, no sobre archivos de prueba que se desee conservar.

## 5. Configuración de MCP en VS Code

### 5.1. Configuración utilizada

Se creó el archivo `.vscode/mcp.json` en la raíz de `Tarea 1` con el
siguiente contenido:

``` json
{
  "servers": {
    "filesystem": {
      "type": "stdio",
      "command": "cmd",
      "args": [
        "/c",
        "npx",
        "-y",
        "@modelcontextprotocol/server-filesystem",
        "C:\\Users\\Marc_\\Documents\\ESCOM\\Aplicaciones Moviles Nativas\\Aplicaciones-Moviles-Nativas\\Tarea 1\\Config\\Public"
      ]
    }
  }
}
```

-   `servers` registra los servidores MCP de VS Code.
-   `filesystem` es el nombre local asignado al servidor.
-   `type: "stdio"` indica comunicación por entrada y salida estándar.
-   `cmd /c` ejecuta `npx` en Windows.
-   `npx -y` permite obtener y ejecutar el paquete; la primera ejecución
    puede requerir Internet.
-   El último argumento indica el directorio permitido **inicialmente**.
    No garantiza por sí solo el alcance efectivo cuando el cliente
    proporciona MCP Roots.

En otra computadora se debe actualizar la ruta absoluta. Las barras
invertidas están duplicadas porque el archivo utiliza sintaxis JSON.

### 5.2. Ajuste necesario por MCP Roots

Durante la primera verificación, VS Code tenía abierta la carpeta
completa `Tarea 1`. Aunque en los argumentos se indicó
`Config\Public`, la herramienta `list_allowed_directories`
devolvió la ruta de `Tarea 1`.

La salida del servidor incluyó:

``` text
Discovered 14 tools
Updated allowed directories from MCP roots: 1 valid directories
```

La documentación y el código fuente del servidor explican este
comportamiento: cuando el cliente admite MCP Roots y proporciona raíces
válidas, el servidor Filesystem reemplaza su lista inicial de
directorios permitidos por las raíces recibidas del cliente (Model
Context Protocol, s. f.-a; Model Context Protocol, s. f.-b).

**Solución aplicada:**

1.  Conservar la ventana principal de VS Code para la documentación de
    `Tarea 1`.
2.  Abrir **otra ventana de VS Code** y seleccionar exclusivamente
    `Tarea 1\Config\Public` mediante **Archivo \> Abrir
    carpeta**.
3.  Crear también `Public\.vscode\mcp.json` con la misma configuración
    anterior, ajustando la ruta absoluta si se reproduce en otro equipo.
4.  Iniciar el servidor desde la **ventana cuyo espacio de trabajo es
    `Public`**.
5.  Ejecutar `list_allowed_directories` y comprobar que devuelve
    solamente la ruta de `Public`.

La copia `Public\.vscode\mcp.json` se usa porque esa segunda ventana
abre `Public` como raíz independiente. Mantener sincronizadas ambas
configuraciones.

**Importante:** no se utilizó `MCP_FS_DISABLE_ROOTS` como solución: no
es una opción documentada del servidor oficial. La solución verificada
consistió en hacer coincidir la raíz de VS Code con la carpeta
autorizada.

## 6. Inicio y verificación

En la ventana que tiene abierta `Public`:

1.  Abrir `.vscode/mcp.json` y guardar el archivo.

2.  Iniciar `filesystem` desde el control que aparece en el editor o
    mediante **Ctrl + Shift + P \> MCP: List Servers**.

3.  Revisar la salida del servidor hasta observar que está **En
    ejecución** y que VS Code ha descubierto las herramientas.

4.  Abrir el chat de GitHub Copilot y seleccionar el modo **Agente**.

5.  Enviar:

    > Utiliza exclusivamente la herramienta `list_allowed_directories`
    > del servidor MCP filesystem e indica todas las rutas autorizadas.

**Resultado de la verificación comunicada:** el servidor quedó en
ejecución, VS Code descubrió **14 herramientas** y la consulta de
directorios autorizados mostró la carpeta `Config\Public`
deseada.

Para comprobar el funcionamiento sin recurrir a herramientas nativas del
editor, las solicitudes de prueba deben indicar expresamente que se
utilice el servidor MCP `filesystem`.

## 7. Pruebas funcionales y de seguridad

Se realizaron las siguientes operaciones; los resultados observados y
las capturas se conservan en
[`Public/README.md`](Public/README.md):

  ----------------------------------------------------------------------------
  Prueba                  Herramienta MCP              Finalidad
  ----------------------- ---------------------------- -----------------------
  Verificación de alcance `list_allowed_directories`   Identificar los
                                                       directorios autorizados

  Listar directorio       `list_directory`             Enumerar archivos y
                                                       carpetas

  Leer archivo            `read_text_file`             Consultar `ejemplo.txt`

  Crear archivo           `write_file`                 Crear `prueba-mcp.txt`

  Modificar archivo       `edit_file`                  Cambiar parte del
                                                       contenido

  Buscar archivo          `search_files`               Localizar un archivo
                                                       por nombre

  Límite de seguridad     `read_text_file`             Intentar leer un
                                                       archivo fuera de
                                                       `Public`
  ----------------------------------------------------------------------------

**Registrar en el documento de pruebas el mensaje real de la herramienta
durante la prueba de seguridad.** La negativa del agente a ejecutar una
solicitud, sin una invocación del servidor, no prueba por sí sola que el
servidor haya bloqueado el acceso.

El alcance de Filesystem no restringe las herramientas nativas de VS
Code ni otras aplicaciones: delimita las operaciones realizadas **a
través de ese servidor**. Asimismo, el acceso a subdirectorios de
`Public`, incluido `.vscode`, está dentro del alcance autorizado.

## 8. Reproducción en una máquina limpia

1.  Instalar Windows 11, VS Code, Node.js con npm y Git; iniciar sesión
    en GitHub Copilot y habilitar el modo Agente.
2.  Clonar o descargar el repositorio y abrir su carpeta principal.
3.  Preparar `Config\Public` y sus archivos iniciales, si aún no
    existen.
4.  Reemplazar la ruta absoluta del último argumento en **ambos**
    archivos `mcp.json` por la ubicación local de
    `Config\Public`.
5.  Abrir una segunda ventana de VS Code directamente en
    `Config\Public`.
6.  Iniciar el servidor `filesystem` desde esa ventana; permitir la
    descarga inicial del paquete si es necesario.
7.  Comprobar el estado **En ejecución**, el catálogo de herramientas y
    la salida de `list_allowed_directories`.
8.  Ejecutar las operaciones y la prueba de seguridad según
    `Registro de Pruebas.md`.

Si la ruta mostrada por `list_allowed_directories` es la del proyecto
completo, revisar **qué carpeta está abierta en la ventana desde la que
se inició el servidor**. No ejecutar operaciones de escritura hasta
confirmar el alcance correcto.

## 9. Conclusión

La instalación muestra que MCP permite a un agente solicitar operaciones
de archivos mediante herramientas publicadas por un servidor separado.
La verificación de `list_allowed_directories` es indispensable porque el
alcance efectivo del servidor puede actualizarse mediante las raíces
comunicadas por el cliente. La reproducción correcta requiere documentar
tanto el archivo de configuración como la carpeta abierta en la ventana
de VS Code que ejecuta el servidor.

## Referencias

Microsoft. (s. f.). *MCP configuration reference*. Visual Studio Code.
https://code.visualstudio.com/docs/agents/reference/mcp-configuration

Model Context Protocol. (s. f.-a). *Filesystem MCP server*
\[Documentación de software\]. GitHub.
https://github.com/modelcontextprotocol/servers/blob/main/src/filesystem/README.md

Model Context Protocol. (s. f.-b). *Filesystem server source code*
\[Código fuente\]. GitHub.
https://github.com/modelcontextprotocol/servers/blob/main/src/filesystem/index.ts
