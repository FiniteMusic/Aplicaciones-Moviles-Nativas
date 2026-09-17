# DoList

Sistema de gestión de tareas desarrollado para la asignatura de **Aplicaciones Móviles Nativas**.

El proyecto cuenta con un backend REST que permite registrar usuarios, iniciar sesión y administrar tareas mediante operaciones CRUD. La autenticación se realiza mediante JWT y la información se almacena utilizando Prisma ORM con SQLite.

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

La base de datos SQLite se mantiene mediante un volumen:

```yaml
./backend/prisma/dev.db:/app/prisma/dev.db
```

---

## API

### Autenticación

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/auth/register` | Registrar usuario |
| POST | `/api/auth/login` | Iniciar sesión |
| GET | `/api/auth/me` | Obtener usuario autenticado |

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
- Validación de prioridades y estados.
- Manejo de fechas mediante `DateTime`.
- Hora de vencimiento establecida automáticamente a las `23:59`.
- Manejo de códigos HTTP.
- Persistencia mediante SQLite.
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
- CRUD de tareas.
- Validación de datos.
- Fechas y prioridades.
- Recursos inexistentes.
- Códigos HTTP.
- Conexión con SQLite.
- Ejecución completa mediante Docker.

**Todas las pruebas fueron realizadas correctamente.**

---

## Autor

**Marco Uriel De la Cruz Velázquez**

Instituto Politécnico Nacional — ESCOM  
Aplicaciones Móviles Nativas
