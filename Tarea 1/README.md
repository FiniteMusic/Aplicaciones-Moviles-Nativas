# Model Context Protocol (MCP): investigación e implementación de un servidor de sistema de archivos

**Escuela Superior de Cómputo (ESCOM) · Aplicaciones Móviles Nativas**

| Dato | Información |
|---|---|
| Nombre | De la Cruz Velázquez Marco Uriel |
| Boleta | 2024630241 |
| Grupo | 7CV4 |
| Sistema operativo | Windows 11 Home Single Language|

## Descripción

Esta actividad investiga la evolución de los modelos de lenguaje, sus limitaciones de acceso a archivos y el Model Context Protocol (MCP). En la parte práctica se configuró el servidor MCP Filesystem en Visual Studio Code con GitHub Copilot, se realizaron operaciones sobre archivos locales y se probó el límite del directorio autorizado. La investigación y las pruebas se encuentran en documentos Markdown independientes; este README permite localizarlos y reproduce los pasos esenciales de instalación.

**Versión de la especificación consultada:** MCP `2026-07-28`, publicada el 28 de julio de 2026. La versión de la especificación no equivale a la versión del paquete npm ni acredita por sí sola la revisión negociada durante la conexión.

## Estructura del repositorio

```text
Tarea 1/
├── .vscode/
│   └── mcp.json
├── Config/
│   ├── Instalacion.md
│   └── Public/
│       ├── .vscode/
│       │   └── mcp.json
│       ├── ejemplo.txt
│       ├── notas.txt
│       ├── prueba-mcp.txt
│       └── README.md
├── Docs/
│   ├── 1. Evolucion de Modelos.md
│   ├── 2. El problema del aislamiento.md
│   ├── 3. MCP vs API.md
│   ├── 4. Arquitectura.md
│   ├── 5. Servidor de Sistema de Archivos.md
│   ├── 6. Seguridad.md
│   └── 7. Casos de Uso.md
└── README.md
```

`Docs` contiene la investigación; `Config/Instalacion.md` explica el procedimiento reproducible; `Config/Public/README.md` registra las pruebas y sus capturas. Los archivos `.txt` son materiales de prueba.

## Índice de investigación

| N.º | Documento | Contenido |
|---|---|---|
| 1 | [Evolución de modelos](Docs/1.%20Evolucion%20de%20Modelos.md) | LM, LLM y razonamiento |
| 2 | [El problema del aislamiento](Docs/2.%20El%20problema%20del%20aislamiento.md) | Arquitectura y seguridad |
| 3 | [MCP vs API](Docs/3.%20MCP%20vs%20API.md) | Diferencias y complementariedad |
| 4 | [Arquitectura](Docs/4.%20Arquitectura.md) | Host, cliente, servidor y transportes |
| 5 | [Servidor de sistema de archivos](Docs/5.%20Servidor%20de%20Sistema%20de%20Archivos.md) | Herramientas y directorios permitidos |
| 6 | [Seguridad](Docs/6.%20Seguridad.md) | Riesgos y mitigaciones |
| 7 | [Casos de uso](Docs/7.%20Casos%20de%20Uso.md) | Herramientas compatibles con MCP |

## Tabla comparativa: MCP y API

| Criterio | API tradicional | MCP |
|---|---|---|
| Concepto | Contrato de comunicación entre programas | Protocolo abierto para conectar aplicaciones de IA con herramientas y contexto |
| Selección de operaciones | Generalmente determinada por la lógica del programa cliente | El modelo puede seleccionar herramientas publicadas, bajo los controles del host |
| Descubrimiento | Documentación, OpenAPI u otros mecanismos propios | Catálogo de herramientas y esquemas publicado por el servidor |
| Acoplamiento | La integración depende de la interfaz específica | El cliente MCP reutiliza una interfaz común con diferentes servidores |
| Formato | Depende de la API: HTTP/JSON, GraphQL, gRPC, etc. | JSON-RPC 2.0 |
| Autenticación y consentimiento | Definidos por la API y la aplicación | Dependen del transporte, el host y el servidor; requieren controles de autorización y consentimiento |
| Reutilización | Suele requerir integrar cada servicio | Un servidor MCP puede utilizarse desde distintos clientes compatibles |

**MCP no reemplaza las APIs:** un servidor MCP puede envolver una API o un recurso existente para que un modelo descubra y solicite sus capacidades. Desarrollo completo: [MCP vs API](Docs/3.%20MCP%20vs%20API.md).

## Implementación

Se eligió **Visual Studio Code** como aplicación host por su soporte de configuración MCP en `.vscode/mcp.json` y su integración con GitHub Copilot en modo Agente. Se utilizó el servidor `@modelcontextprotocol/server-filesystem` mediante transporte local `stdio`. El modelo no accede directamente al disco: solicita operaciones que ejecuta el servidor.

### Versiones utilizadas

| Componente | Versión |
|---|---|
| Windows 11 | [Windows 11 Home Single Language] |
| Visual Studio Code | [1.138.0] |
| Node.js | [v24.15.0] |
| npm | [11.12.1] |
| Git | [2.54.0.windows.1] |

```powershell
node --version
npm --version
git --version
code --version
```

### Instalación resumida y reproducible

1. Instalar VS Code, Node.js con npm y Git en Windows 11. Habilitar GitHub Copilot y el modo Agente.
2. Clonar el repositorio y localizar `Tarea 1/Config/Public`.
3. Ajustar la ruta absoluta de `Public` en **ambos** archivos `.vscode/mcp.json` para que corresponda a la máquina donde se reproduce la práctica.
4. Abrir una **segunda ventana de VS Code** directamente en `Config/Public`.
5. Iniciar `filesystem` desde `Config/Public/.vscode/mcp.json` o desde **MCP: List Servers**.
6. Verificar que el servidor esté **En ejecución**, que aparezcan sus herramientas y que `list_allowed_directories` muestre solamente `Config/Public`.
7. Ejecutar las operaciones documentadas en el [registro de pruebas](Config/Public/README.md).

La segunda ventana es importante: durante la práctica, MCP Roots actualizó los directorios autorizados a la carpeta abierta en VS Code. Abrir `Public` como espacio de trabajo independiente permitió comprobar el alcance deseado. **Procedimiento y explicación completos: [Config/Instalacion.md](Config/Instalacion.md).**

### Archivo de configuración `mcp.json`

Configuración con la ruta correspondiente a la **estructura final** del proyecto. En otra máquina, reemplazar la ruta por la ubicación absoluta de `Config/Public`:

```json
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

**Comprobación pendiente:** si alguno de los archivos `mcp.json` conservó la ruta anterior `Implementacion\Public`, actualizarlo a `Config\Public` y volver a ejecutar `list_allowed_directories`. El argumento de la ruta es inicial; MCP Roots puede sustituir los directorios permitidos efectivos.

## Evidencias y prueba del límite de seguridad

Las capturas incorporadas y los resultados observados se encuentran en **[Config/Public/README.md — Registro de pruebas](Config/Public/README.md)**.

| Operación | Herramienta |
|---|---|
| Consultar directorios autorizados | `list_allowed_directories` |
| Listar archivos | `list_directory` |
| Leer un archivo existente | `read_text_file` |
| Crear y escribir un archivo | `write_file` |
| Modificar un archivo | `edit_file` |
| Buscar un archivo | `search_files` |
| Intentar leer fuera de `Public` | `read_text_file` |


## Conclusiones 

La actividad me permitió distinguir entre generar instrucciones y ejecutar operaciones sobre archivos. Un modelo puede proponer código, pero necesita una aplicación y herramientas autorizadas para interactuar con el sistema operativo. También comprendí que MCP no sustituye las APIs, sino que proporciona una forma común de descubrir y utilizar capacidades externas.

Durante la configuración observé que el directorio indicado inicialmente en `mcp.json` no coincidía con el directorio autorizado que devolvía `list_allowed_directories`: el servidor había recibido la raíz del espacio de trabajo mediante MCP Roots. Abrir `Public` como carpeta independiente y verificar nuevamente la ruta me permitió entender mejor la relación entre host, cliente, servidor y permisos. Registrar las operaciones y la prueba de seguridad reforzó la importancia de comprobar el comportamiento real, en lugar de asumir que una configuración es correcta solo porque el servidor inicia.


## Referencias

Microsoft. (s. f.). *Add and manage MCP servers in VS Code*. Visual Studio Code. https://code.visualstudio.com/docs/agent-customization/mcp-servers

Microsoft. (s. f.). *MCP configuration reference*. Visual Studio Code. https://code.visualstudio.com/docs/agents/reference/mcp-configuration

Model Context Protocol. (2026, 28 de julio). *Specification (2026-07-28)*. https://modelcontextprotocol.io/specification/2026-07-28

Model Context Protocol. (s. f.). *Filesystem MCP server* [Documentación de software]. GitHub. https://github.com/modelcontextprotocol/servers/blob/main/src/filesystem/README.md

Soria Parra, D., & Delimarsky, D. (2026, 28 de julio). *The 2026-07-28 specification*. Model Context Protocol Blog. https://blog.modelcontextprotocol.io/posts/2026-07-28/

