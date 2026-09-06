const prisma = require("../config/database");
const { parseDueDate } = require("../utils/date.utils");
const { isTaskExpired } = require("../utils/task.utils");


const createTask = async (req, res) => {
    try {
        const {
            title,
            description,
            priority,
            dueDate
        } = req.body;

        if (!title) {
            return res.status(400).json({
                error: "El título es obligatorio"
            });
        }

        const validPriorities = ["baja", "media", "alta"];

        if (priority && !validPriorities.includes(priority)) {
            return res.status(400).json({
                error: "La prioridad debe ser baja, media o alta"
            });
        }

        let parsedDueDate = null;

        if (dueDate) {
            parsedDueDate = parseDueDate(dueDate);

            if (!parsedDueDate) {
                return res.status(400).json({
                    error: "La fecha límite debe tener el formato YYYY-MM-DD y ser una fecha válida"
                });
            }
        }

        const task = await prisma.task.create({
            data: {
                title,
                description: description || null,
                priority: priority || "media",
                dueDate: parsedDueDate,
                userId: req.user.userId
            }
        });

        res.status(201).json({
            message: "Tarea creada correctamente",
            task
        });

    } catch (error) {
        console.error("Error al crear tarea:", error);

        res.status(500).json({
            error: "Error interno del servidor"
        });
    }
};

const getTasks = async (req, res) => {
    try {
        const tasks = await prisma.task.findMany({
            where: {
                userId: req.user.userId
            },
            orderBy: {
                createdAt: "desc"
            },
            select: {
                id: true,
                title: true,
                description: true,
                priority: true,
                status: true,
                dueDate: true,
                createdAt: true,
                updatedAt: true
            }
        });

        const tasksWithExpiration = tasks.map(task => ({
            ...task,
            expired: isTaskExpired(task)
        }));

        res.status(200).json({
            tasks: tasksWithExpiration
        });

    } catch (error) {
        console.error("Error al obtener tareas:", error);

        res.status(500).json({
            error: "Error interno del servidor"
        });
    }
};

const getTaskById = async (req, res) => {
    try {
        const taskId = Number(req.params.id);

        if (isNaN(taskId)) {
            return res.status(400).json({
                error: "El ID de la tarea no es válido"
            });
        }

        const task = await prisma.task.findFirst({
            where: {
                id: taskId,
                userId: req.user.userId
            },
            select: {
                id: true,
                title: true,
                description: true,
                priority: true,
                status: true,
                dueDate: true,
                createdAt: true,
                updatedAt: true
            }
        });

        if (!task) {
            return res.status(404).json({
                error: "Tarea no encontrada"
            });
        }

        const taskWithExpiration = {
            ...task,
            expired: isTaskExpired(task)
        };

        res.status(200).json({
            task: taskWithExpiration
        });

    } catch (error) {
        console.error("Error al obtener la tarea:", error);

        res.status(500).json({
            error: "Error interno del servidor"
        });
    }
};

const updateTask = async (req, res) => {
    try {
        const { id } = req.params;
        const {
            title,
            description,
            priority,
            status,
            dueDate
        } = req.body;

        const task = await prisma.task.findFirst({
            where: {
                id: Number(id),
                userId: req.user.userId
            }
        });

        if (!task) {
            return res.status(404).json({
                error: "Tarea no encontrada"
            });
        }

        const validPriorities = ["baja", "media", "alta"];
        const validStatuses = ["pendiente", "en progreso", "completada"];

        if (priority && !validPriorities.includes(priority)) {
            return res.status(400).json({
                error: "La prioridad debe ser baja, media o alta"
            });
        }

        if (status && !validStatuses.includes(status)) {
            return res.status(400).json({
                error: "El estado no es válido"
            });
        }

        let parsedDueDate;

        if (dueDate !== undefined) {
            if (dueDate === null) {
                parsedDueDate = null;
            } else {
                parsedDueDate = parseDueDate(dueDate);

                if (!parsedDueDate) {
                    return res.status(400).json({
                        error: "La fecha límite debe tener el formato YYYY-MM-DD y ser una fecha válida"
                    });
                }
            }
        }

        const updatedTask = await prisma.task.update({
            where: {
                id: Number(id)
            },
            data: {
                ...(title !== undefined && { title }),
                ...(description !== undefined && { description }),
                ...(priority !== undefined && { priority }),
                ...(status !== undefined && { status }),
                ...(dueDate !== undefined && {
                    dueDate: parsedDueDate
                })
            }
        });

        res.status(200).json({
            message: "Tarea actualizada correctamente",
            task: updatedTask
        });

    } catch (error) {
        console.error("Error al actualizar tarea:", error);

        res.status(500).json({
            error: "Error interno del servidor"
        });
    }
};

const deleteTask = async (req, res) => {
    try {
        const taskId = Number(req.params.id);

        if (isNaN(taskId)) {
            return res.status(400).json({
                error: "El ID de la tarea no es válido"
            });
        }

        const existingTask = await prisma.task.findFirst({
            where: {
                id: taskId,
                userId: req.user.userId
            }
        });

        if (!existingTask) {
            return res.status(404).json({
                error: "Tarea no encontrada"
            });
        }

        await prisma.task.delete({
            where: {
                id: taskId
            }
        });

        res.status(200).json({
            message: "Tarea eliminada correctamente"
        });

    } catch (error) {
        console.error("Error al eliminar tarea:", error);

        res.status(500).json({
            error: "Error interno del servidor"
        });
    }
};


module.exports = {
    createTask,
    getTasks,
    getTaskById,
    updateTask,
    deleteTask
};