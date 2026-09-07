# DoList

Sistema de gestión de tareas desarrollado para la asignatura de **Aplicaciones Móviles Nativas**.

El proyecto cuenta con un backend REST que permite registrar usuarios, iniciar sesión, administrar el perfil y gestionar tareas mediante operaciones CRUD. La autenticación se realiza mediante JWT y la información se almacena utilizando Prisma ORM con SQLite.

La versión 1.2 incorpora cambio de contraseña, avatar predeterminado y actualización de foto de perfil, además de manejo de errores y ejecución mediante Docker.

---

## Tecnologías

- Node.js
- Express.js
- Prisma ORM
- SQLite
- JWT
- bcrypt
- Docker / Docker Compose

---

## Estructura

```text
DoList/
├── backend/
│   ├── src/
│   │   ├── config/
│   │   ├── controllers/
│   │   ├── middleware/
│   │   ├── routes/
│   │   ├── utils/
│   │   └── server.js
│   ├── prisma/
│   │   ├── migrations/
│   │   ├── dev.db
│   │   └── schema.prisma
│   ├── uploads/
│   │   └── avatars/
│   │       └── default.png
│   ├── .env
│   ├── .dockerignore
│   ├── Dockerfile
│   ├── package.json
│   └── package-lock.json
├── docker-compose.yml
└── README.md
```

---

## Configuración

Crear el archivo `backend/.env`:

```env
DATABASE_URL="file:./dev.db"
JWT_SECRET="tu_clave_secreta"
PORT=5000
```

El archivo `.env` no debe subirse al repositorio.

---

## Ejecución local

Entrar al directorio del backend:

```bash
cd backend
```

Instalar dependencias:

```bash
npm install
```

Generar Prisma Client:

```bash
npx prisma generate
```

Aplicar las migraciones:

```bash
npx prisma migrate deploy
```

Iniciar el servidor:

```bash
npm start
```

El backend estará disponible en:

```text
http://localhost:5000
```

Para desarrollo:

```bash
npm run dev
```

---

## Ejecución con Docker

Desde la raíz del proyecto:

```bash
docker compose up --build
```

O en segundo plano:

```bash
docker compose up -d --build
```

El backend estará disponible en:

```text
http://localhost:5000
```

Para detener el contenedor:

```bash
docker compose down
```

La base de datos SQLite y los avatares se mantienen mediante volúmenes:

```yaml
./backend/prisma/dev.db:/app/prisma/dev.db
./backend/uploads/avatars:/app/uploads/avatars
```

El archivo `default.png` se incluye como avatar predeterminado. Las imágenes de perfil personalizadas no se incluyen en el repositorio.

---

## API

### Autenticación

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/auth/register` | Registrar usuario |
| POST | `/api/auth/login` | Iniciar sesión |
| GET | `/api/auth/me` | Obtener usuario autenticado |
| PUT | `/api/auth/password` | Cambiar contraseña |
| PUT | `/api/auth/avatar` | Actualizar avatar |

### Tareas

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/tasks` | Crear tarea |
| GET | `/api/tasks` | Obtener tareas |
| PUT | `/api/tasks/:id` | Actualizar tarea |
| DELETE | `/api/tasks/:id` | Eliminar tarea |

Los endpoints de tareas requieren autenticación mediante:

```http
Authorization: Bearer <token>
```

---

## Características

- Autenticación mediante JWT.
- Contraseñas almacenadas mediante hash con bcrypt.
- Normalización de correos electrónicos.
- CRUD de tareas.
- Cambio de contraseña.
- Avatar predeterminado y actualización de foto de perfil.
- Validación de prioridades y estados.
- Manejo de fechas mediante `DateTime`.
- Hora de vencimiento establecida automáticamente a las `23:59`.
- Manejo de códigos HTTP.
- Persistencia mediante SQLite.
- Manejo global de errores.
- Dockerización del backend.

---

## Códigos HTTP

| Código | Uso |
|---|---|
| `200` | Operación exitosa |
| `201` | Recurso creado |
| `400` | Datos inválidos |
| `401` | No autenticado |
| `404` | Recurso no encontrado |
| `409` | Conflicto |
| `500` | Error interno |

---

## Pruebas

Se realizaron pruebas de:

- Registro e inicio de sesión.
- Autenticación mediante JWT.
- Cambio de contraseña y gestión de avatar.
- CRUD de tareas.
- Validación de datos.
- Fechas y prioridades.
- Recursos inexistentes.
- Códigos HTTP.
- Conexión con SQLite.
- Cambio de contraseña.
- Cambio y persistencia de avatar.
- Ejecución completa mediante Docker.

**Todas las pruebas fueron realizadas correctamente, incluyendo las pruebas de ejecución y persistencia mediante Docker.**

---

## Autor

**Marco Uriel De la Cruz Velázquez**

Instituto Politécnico Nacional — ESCOM  
Aplicaciones Móviles Nativas
