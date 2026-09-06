const express = require("express");
const { register, login, getMe} = require("../controllers/auth.controller");
const authenticateToken = require("../middleware/auth.middleware");

const router = express.Router();

router.get("/test", (req, res) => {
    res.json({
        message: "Ruta de autenticación funcionando"
    });
});

router.post("/register", register);
router.post("/login", login);
router.get("/me", authenticateToken, getMe);

router.get("/protected-test", authenticateToken, (req, res) => {
    res.json({
        message: "Acceso autorizado",
        user: req.user
    });
});


module.exports = router;