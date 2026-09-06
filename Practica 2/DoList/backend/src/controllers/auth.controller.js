const bcrypt = require("bcrypt");
const prisma = require("../config/database");
const jwt = require("jsonwebtoken");


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
                password: hashedPassword
            }
        });

        res.status(201).json({
            message: "Usuario registrado correctamente",
            user: {
                id: user.id,
                name: user.name,
                email: user.email,
                createdAt: user.createdAt
            }
        });

    } catch (error) {
        console.error("Error al registrar usuario:", error);

        res.status(500).json({
            error: "Error interno del servidor"
        });
    }
};



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
                createdAt: true
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


module.exports = {
    register,
    login,
    getMe
};