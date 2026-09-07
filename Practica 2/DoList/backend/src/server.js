require("dotenv").config();

const express = require("express");
const prisma = require("./config/database");
const authRoutes = require("./routes/auth.routes");
const taskRoutes = require("./routes/task.routes");
const path = require("path");


const app = express();
const PORT = process.env.PORT || 5000;

app.use(express.json());
app.use("/api/auth", authRoutes);
app.use("/api/tasks", taskRoutes);
app.use("/uploads",express.static(path.join(__dirname, "../uploads")));


app.get("/", (req, res) => {
    res.json({
        message: "DoList API funcionando correctamente"
    });
});

app.get("/api/test-db", async (req, res) => {
    try {
                const users = await prisma.user.findMany({
            select: {
                id: true,
                name: true,
                email: true,
                createdAt: true
            }
        });

        res.json({
            message: "Conexión con la base de datos correcta",
            users
        });
    } catch (error) {
        console.error("Error al consultar la base de datos:", error);

        res.status(500).json({
            error: "No se pudo conectar con la base de datos"
        });
    }
});

app.use((err, req, res, next) => {
    console.error("Error:", err);

    if (err.name === "MulterError") {
        return res.status(400).json({
            error: `Error al subir archivo: ${err.message}`
        });
    }

    if (err.message === "Solo se permiten imágenes JPG, PNG o WEBP") {
        return res.status(400).json({
            error: err.message
        });
    }

    res.status(500).json({
        error: "Error interno del servidor"
    });
});

app.listen(PORT, "0.0.0.0", () => {
    console.log(`Servidor ejecutándose en el puerto ${PORT}`);
});