const isTaskExpired = (task) => {
    // Una tarea sin fecha límite nunca expira
    if (!task.dueDate) {
        return false;
    }

    // Una tarea completada no se considera expirada
    if (task.status === "completada") {
        return false;
    }

    // Comparamos el instante de vencimiento con el momento actual
    return new Date(task.dueDate) < new Date();
};

module.exports = {
    isTaskExpired
};