const bcrypt = require("bcrypt");
const prisma = require("../config/database");
const jwt = require("jsonwebtoken");
const path = require("path");
const fs = require("fs");

// ============================
// REGISTRO
// ============================
const register = async (req, res) => {
    try {
        const { name, password } = req.body;
        let { email } = req.body;

        email = email?.trim().toLowerCase();

        if (!name || !email || !password) {
            return res.status(400).json({
                error: "Todos los campos son obligatorios"
            });
        }

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (!emailRegex.test(email)) {
            return res.status(400).json({
                error: "El correo electrónico no es válido"
            });
        }

        if (password.length < 8) {
            return res.status(400).json({
                error: "La contraseña debe tener al menos 8 caracteres"
            });
        }

        const existingUser = await prisma.user.findUnique({
            where: {
                email
            }
        });

        if (existingUser) {
            return res.status(409).json({
                error: "El correo ya está registrado"
            });
        }

        const hashedPassword = await bcrypt.hash(password, 10);

        const user = await prisma.user.create({
            data: {
                name,
                email,
                password: hashedPassword,
                avatarUrl: "/uploads/avatars/default.png"
            }
        });

        res.status(201).json({
            message: "Usuario registrado correctamente",
            user: {
                id: user.id,
                name: user.name,
                email: user.email,
                createdAt: user.createdAt,
                avatarUrl: user.avatarUrl
            }
        });

    } catch (error) {
        console.error("Error al registrar usuario:", error);

        res.status(500).json({
            error: "Error interno del servidor"
        });
    }
};


// ============================
// LOGIN
// ============================
const login = async (req, res) => {
    try {
        const { password } = req.body;
        let { email } = req.body;

        email = email?.trim().toLowerCase();

        if (!email || !password) {
            return res.status(400).json({
                error: "Correo y contraseña son obligatorios"
            });
        }

        const user = await prisma.user.findUnique({
            where: {
                email
            }
        });

        if (!user) {
            return res.status(401).json({
                error: "Correo o contraseña incorrectos"
            });
        }

        const passwordCorrect = await bcrypt.compare(
            password,
            user.password
        );

        if (!passwordCorrect) {
            return res.status(401).json({
                error: "Correo o contraseña incorrectos"
            });
        }

        const token = jwt.sign(
            {
                userId: user.id,
                email: user.email
            },
            process.env.JWT_SECRET,
            {
                expiresIn: "2h"
            }
        );

        res.json({
            message: "Inicio de sesión exitoso",
            token
        });

    } catch (error) {
        console.error("Error al iniciar sesión:", error);

        res.status(500).json({
            error: "Error interno del servidor"
        });
    }
};


// ============================
// OBTENER USUARIO AUTENTICADO
// ============================
const getMe = async (req, res) => {
    try {
        const user = await prisma.user.findUnique({
            where: {
                id: req.user.userId
            },
            select: {
                id: true,
                name: true,
                email: true,
                createdAt: true,
                avatarUrl: true
            }
        });

        if (!user) {
            return res.status(404).json({
                error: "Usuario no encontrado"
            });
        }

        res.status(200).json({
            user
        });

    } catch (error) {
        console.error("Error al obtener usuario:", error);

        res.status(500).json({
            error: "Error interno del servidor"
        });
    }
};


// ============================
// CAMBIAR CONTRASEÑA
// ============================
const changePassword = async (req, res) => {
    try {
        const { currentPassword, newPassword } = req.body;

        // Validar que ambos campos existan
        if (!currentPassword || !newPassword) {
            return res.status(400).json({
                error: "La contraseña actual y la nueva contraseña son obligatorias"
            });
        }

        // Validar longitud de la nueva contraseña
        if (newPassword.length < 8) {
            return res.status(400).json({
                error: "La nueva contraseña debe tener al menos 8 caracteres"
            });
        }

        // Obtener usuario autenticado
        const user = await prisma.user.findUnique({
            where: {
                id: req.user.userId
            }
        });

        if (!user) {
            return res.status(404).json({
                error: "Usuario no encontrado"
            });
        }

        // Verificar contraseña actual
        const passwordCorrect = await bcrypt.compare(
            currentPassword,
            user.password
        );

        if (!passwordCorrect) {
            return res.status(401).json({
                error: "La contraseña actual es incorrecta"
            });
        }

        // Evitar que la nueva contraseña sea igual a la actual
        const samePassword = await bcrypt.compare(
            newPassword,
            user.password
        );

        if (samePassword) {
            return res.status(400).json({
                error: "La nueva contraseña debe ser diferente a la actual"
            });
        }

        // Generar nuevo hash
        const hashedPassword = await bcrypt.hash(newPassword, 10);

        // Actualizar contraseña
        await prisma.user.update({
            where: {
                id: user.id
            },
            data: {
                password: hashedPassword
            }
        });

        res.status(200).json({
            message: "Contraseña actualizada correctamente"
        });

    } catch (error) {
        console.error("Error al cambiar contraseña:", error);

        res.status(500).json({
            error: "Error interno del servidor"
        });
    }
};


// ============================
// CAMBIAR Avatar
// ============================

const changeAvatar = async (req, res) => {
    try {
        // Verificar que se haya enviado una imagen
        if (!req.file) {
            return res.status(400).json({
                error: "Debes seleccionar una imagen"
            });
        }

        // Buscar usuario
        const user = await prisma.user.findUnique({
            where: {
                id: req.user.userId
            }
        });

        if (!user) {
            // Eliminar archivo que Multer acaba de guardar
            fs.unlinkSync(req.file.path);

            return res.status(404).json({
                error: "Usuario no encontrado"
            });
        }

        // Ruta del nuevo avatar
        const newAvatarUrl = `/uploads/avatars/${req.file.filename}`;

        // Actualizar usuario
        const updatedUser = await prisma.user.update({
            where: {
                id: user.id
            },
            data: {
                avatarUrl: newAvatarUrl
            },
            select: {
                id: true,
                name: true,
                email: true,
                createdAt: true,
                avatarUrl: true
            }
        });

        // Eliminar avatar anterior si no es el predeterminado
        if (
            user.avatarUrl &&
            user.avatarUrl !== "/uploads/avatars/default.png"
        ) {
            const oldAvatarPath = path.join(
                __dirname,
                "../..",
                user.avatarUrl
            );

            try {
                if (fs.existsSync(oldAvatarPath)) {
                    fs.unlinkSync(oldAvatarPath);
                }
            } catch (error) {
                // El usuario ya tiene el nuevo avatar guardado en BD.
                // Si falla la eliminación del anterior, no debemos afectar la respuesta.
                console.error("No se pudo eliminar el avatar anterior:", error);
            }
        }

        res.status(200).json({
            message: "Foto de perfil actualizada correctamente",
            user: updatedUser
        });

    } catch (error) {
        console.error("Error al cambiar avatar:", error);

        // Si Multer ya creó el archivo y ocurrió otro error,
        // eliminarlo para evitar archivos huérfanos
        if (req.file && req.file.path && fs.existsSync(req.file.path)) {
            fs.unlinkSync(req.file.path);
        }

        res.status(500).json({
            error: "Error interno del servidor"
        });
    }
};

// ============================
// EXPORTACIONES
// ============================
module.exports = {
    register,
    login,
    getMe,
    changePassword,
    changeAvatar
};