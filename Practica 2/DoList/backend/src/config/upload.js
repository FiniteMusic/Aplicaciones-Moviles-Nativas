const multer = require("multer");
const path = require("path");
const fs = require("fs");

// Ruta donde se almacenarán los avatares
const uploadPath = path.join(__dirname, "../../uploads/avatars");

// Crear carpeta si no existe
if (!fs.existsSync(uploadPath)) {
    fs.mkdirSync(uploadPath, { recursive: true });
}

// Configuración del almacenamiento
const storage = multer.diskStorage({
    destination: (req, file, cb) => {
        cb(null, uploadPath);
    },

    filename: (req, file, cb) => {
        const extension = path.extname(file.originalname).toLowerCase();

        const fileName = `avatar-${req.user.userId}-${Date.now()}${extension}`;

        cb(null, fileName);
    }
});

// Tipos de imagen permitidos
const fileFilter = (req, file, cb) => {
    const allowedTypes = [
        "image/jpeg",
        "image/png",
        "image/webp"
    ];

    if (allowedTypes.includes(file.mimetype)) {
        cb(null, true);
    } else {
        cb(new Error("Solo se permiten imágenes JPG, PNG o WEBP"));
    }
};

// Configuración de Multer
const upload = multer({
    storage,
    fileFilter,
    limits: {
        fileSize: 5 * 1024 * 1024
    }
});

module.exports = upload;