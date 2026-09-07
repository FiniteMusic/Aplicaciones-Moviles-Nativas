const express = require("express");
const { register, login, getMe, changePassword,changeAvatar} = require("../controllers/auth.controller");
const authenticateToken = require("../middleware/auth.middleware");
const upload = require("../config/upload");

const router = express.Router();

router.get("/test", (req, res) => {
    res.json({
        message: "Ruta de autenticación funcionando"
    });
});

router.post("/register", register);
router.post("/login", login);
router.get("/me", authenticateToken, getMe);
router.put("/password", authenticateToken, changePassword);
router.put("/avatar", authenticateToken, upload.single ("avatar"), changeAvatar);

router.get("/protected-test", authenticateToken, (req, res) => {
    res.json({
        message: "Acceso autorizado",
        user: req.user
    });
});


module.exports = router;