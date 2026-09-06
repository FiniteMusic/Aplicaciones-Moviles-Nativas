require("dotenv").config();

const express = require("express");
const prisma = require("./config/database");
const authRoutes = require("./routes/auth.routes");
const taskRoutes = require("./routes/task.routes");

const app = express();
const PORT = 5000;

app.use(express.json());
app.use("/api/auth", authRoutes);
app.use("/api/tasks", taskRoutes);

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

app.listen(PORT, "0.0.0.0", () => {
    console.log(`Servidor ejecutándose en el puerto ${PORT}`);
});