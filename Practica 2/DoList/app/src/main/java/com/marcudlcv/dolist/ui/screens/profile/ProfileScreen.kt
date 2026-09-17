package com.marcudlcv.dolist.ui.screens.profile

import androidx.compose.foundation.background
import com.marcudlcv.dolist.ui.theme.ThemeMode
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marcudlcv.dolist.ui.components.BottomNavDestination
import com.marcudlcv.dolist.ui.components.DoListBackground
import com.marcudlcv.dolist.ui.components.GlassBottomNav
import com.marcudlcv.dolist.ui.components.GlassSurface
import com.marcudlcv.dolist.ui.screens.tasks.TaskFormScreen
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.ui.draw.scale
import androidx.compose.material.icons.filled.BrightnessAuto
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.QuestionAnswer

@Composable
fun ProfileScreen(
    themeMode: ThemeMode,
    onThemeModeChange: (ThemeMode) -> Unit,
    onNavigate: (BottomNavDestination) -> Unit = {},
    onChangeAvatar: () -> Unit = {},
    onChangePassword: () -> Unit = {},
    onSettings: () -> Unit = {},
    onLogout: () -> Unit = {}
){

    var currentDestination by remember {
        mutableStateOf(
            BottomNavDestination.PROFILE
        )
    }

    var showTaskForm by remember {
        mutableStateOf(false)
    }
    var showChangePassword by remember {
        mutableStateOf(false)
    }

    var notificationsEnabled by remember {
        mutableStateOf(true)
    }

    var expandedProfileOption by remember {
        mutableStateOf<String?>(null)
    }
    var showChangeAvatar by remember {
        mutableStateOf(false)
    }
    // ═════════════════════════════════════
    // FONDO
    // ═════════════════════════════════════

    DoListBackground {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (showTaskForm || showChangePassword || showChangeAvatar) {
                        Modifier.blur(18.dp)
                    } else {
                        Modifier
                    }
                )
        ) {


            // ═════════════════════════════════
            // ORBES DECORATIVOS
            // ═════════════════════════════════

            Box(
                modifier = Modifier
                    .size(170.dp)
                    .offset(
                        x = 190.dp,
                        y = (-75).dp
                    )
                    .alpha(0.04f)
                    .background(
                        color =
                            MaterialTheme
                                .colorScheme
                                .primary,
                        shape = CircleShape
                    )
            )


            Box(
                modifier = Modifier
                    .size(130.dp)
                    .offset(
                        x = (-75).dp,
                        y = 330.dp
                    )
                    .alpha(0.015f)
                    .background(
                        color =
                            MaterialTheme
                                .colorScheme
                                .primary,
                        shape = CircleShape
                    )
            )


            // ═════════════════════════════════
            // CONTENIDO
            // ═════════════════════════════════

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 20.dp
                    )
            ) {


                Spacer(
                    modifier =
                        Modifier.height(55.dp)
                )


                // ═════════════════════════════
                // TÍTULO
                // ═════════════════════════════

                Text(
                    text = "Mi perfil",
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground,
                    fontSize = 22.sp,
                    fontWeight =
                        FontWeight.SemiBold
                )


                Spacer(
                    modifier =
                        Modifier.height(18.dp)
                )


                // ═════════════════════════════
                // INFORMACIÓN DEL USUARIO
                // ═════════════════════════════

                ProfileUserCard(
                    name = "Marco",
                    email = "marco@ejemplo.com",
                    onClick = {
                        showChangeAvatar = true
                    }
                )


                Spacer(
                    modifier =
                        Modifier.height(16.dp)
                )


                // ═════════════════════════════
                // ESTADÍSTICAS
                // ═════════════════════════════

                ProfileStats()


                Spacer(
                    modifier =
                        Modifier.height(18.dp)
                )


                // ═════════════════════════════
                // OPCIONES
                // ═════════════════════════════

                GlassSurface(
                    modifier =
                        Modifier.fillMaxWidth(),
                    shape =
                        RoundedCornerShape(20.dp)
                ) {

                    Column(
                        modifier =
                            Modifier.fillMaxWidth()
                    ) {

                        ProfileOption(
                            icon =
                                Icons.Filled.Notifications,

                            title =
                                "Notificaciones",

                            subtitle =
                                if (notificationsEnabled) {
                                    "Activadas"
                                } else {
                                    "Desactivadas"
                                },

                            expanded = expandedProfileOption == "notifications",

                            expandedContent = {

                                NotificationExpandedContent(
                                    enabled =
                                        notificationsEnabled,

                                    onEnabledChange = {
                                        notificationsEnabled = it
                                    }
                                )
                            },

                            onClick = {
                                expandedProfileOption =
                                    if (expandedProfileOption == "notifications") {
                                        null
                                    } else {
                                        "notifications"
                                    }
                            }
                        )


                        ProfileDivider()

                        ProfileOption(
                            icon = Icons.Filled.Palette,
                            title = "Apariencia",
                            subtitle = when (themeMode) {
                                ThemeMode.SYSTEM -> "Sistema"
                                ThemeMode.LIGHT -> "Claro"
                                ThemeMode.DARK -> "Oscuro"
                            },
                            expanded = expandedProfileOption == "appearance",
                            expandedContent = {
                                AppearanceExpandedContent(
                                    selectedAppearance = when (themeMode) {
                                        ThemeMode.SYSTEM -> "Sistema"
                                        ThemeMode.LIGHT -> "Claro"
                                        ThemeMode.DARK -> "Oscuro"
                                    },
                                    onAppearanceSelected = {
                                        onThemeModeChange(
                                            when (it) {
                                                "Sistema" -> ThemeMode.SYSTEM
                                                "Claro" -> ThemeMode.LIGHT
                                                "Oscuro" -> ThemeMode.DARK
                                                else -> ThemeMode.SYSTEM
                                            }
                                        )
                                    }
                                )
                            },
                            onClick = {
                                expandedProfileOption =
                                    if (expandedProfileOption == "appearance") {
                                        null
                                    } else {
                                        "appearance"
                                    }
                            }
                        )


                        ProfileDivider()


                        ProfileOption(
                            icon = Icons.Filled.Lock,
                            title = "Privacidad",
                            subtitle = "Cambiar contraseña",
                            onClick = {
                                showChangePassword = true
                            }
                        )

                        ProfileDivider()

                        ProfileDivider()


                        ProfileOption(
                            icon =
                                Icons.Filled.Logout,
                            title =
                                "Cerrar sesión",
                            subtitle =
                                null,
                            destructive = true,
                            showArrow = false,
                            onClick = onLogout
                        )
                    }
                }


                Spacer(
                    modifier =
                        Modifier.height(14.dp)
                )


                // ═════════════════════════════
                // VERSIÓN
                // ═════════════════════════════

                Box(
                    modifier =
                        Modifier.fillMaxWidth(),
                    contentAlignment =
                        Alignment.Center
                ) {

                    Text(
                        text =
                            "DoList v1.2.0",
                        color =
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                                .copy(
                                    alpha = 0.55f
                                ),
                        fontSize = 10.sp
                    )
                }


                Spacer(
                    modifier =
                        Modifier.height(100.dp)
                )
            }


            // ═════════════════════════════════
            // BOTTOM NAVIGATION
            // ═════════════════════════════════

            GlassBottomNav(

                current =
                    currentDestination,

                onNavigate = { destination ->

                    currentDestination =
                        destination

                    onNavigate(
                        destination
                    )
                },

                onCreateTask = {
                    showTaskForm = true
                },

                modifier =
                    Modifier
                        .align(
                            Alignment.BottomCenter
                        )
                        .navigationBarsPadding()
                        .padding(
                            horizontal = 12.dp,
                            vertical = 12.dp
                        )
            )
            if (showTaskForm) {
                TaskFormScreen(
                    onDismiss = {
                        showTaskForm = false
                    },
                    onCreateTask = { _, _, _, _ ->
                        showTaskForm = false
                    }
                )
            }
            if (showChangePassword) {
                ChangePasswordScreen(
                    onDismiss = {
                        showChangePassword = false
                    },
                    onChangePassword = {
                        // Pendiente de conectar con el backend
                    }
                )
            }
            if (showChangeAvatar) {
                ChangeAvatarScreen(
                    onDismiss = {
                        showChangeAvatar = false
                    },
                    onSave = { uri ->
                        // Pendiente de conectar con el backend
                        showChangeAvatar = false
                    }
                )
            }
        }
    }
}


// ═════════════════════════════════════
// TARJETA DE USUARIO
// ═════════════════════════════════════

@Composable
private fun ProfileUserCard(
    name: String,
    email: String,
    onClick: () -> Unit
) {

    val primary =
        MaterialTheme.colorScheme.primary


    GlassSurface(

        modifier =
            Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(20.dp)
                )
                .clickable(
                    onClick = onClick
                ),

        shape =
            RoundedCornerShape(20.dp),

        backgroundColor =
            primary.copy(
                alpha = 0.07f
            ),

        borderColor =
            primary.copy(
                alpha = 0.16f
            )

    ) {

        Row(

            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 13.dp
                    ),

            verticalAlignment =
                Alignment.CenterVertically

        ) {


            // ─────────────────────────
            // Avatar
            // ─────────────────────────

            Box(

                modifier =
                    Modifier
                        .size(52.dp)
                        .background(
                            color =
                                primary.copy(
                                    alpha = 0.13f
                                ),
                            shape =
                                RoundedCornerShape(
                                    15.dp
                                )
                        ),

                contentAlignment =
                    Alignment.Center

            ) {

                Icon(

                    imageVector =
                        Icons.Filled.Person,

                    contentDescription =
                        "Foto de perfil",

                    tint =
                        primary,

                    modifier =
                        Modifier.size(25.dp)
                )
            }


            Spacer(
                modifier =
                    Modifier.width(13.dp)
            )


            // ─────────────────────────
            // Nombre y correo
            // ─────────────────────────

            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text = name,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurface,
                    fontSize = 14.sp,
                    fontWeight =
                        FontWeight.SemiBold
                )


                Spacer(
                    modifier =
                        Modifier.height(3.dp)
                )


                Text(
                    text = email,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant,
                    fontSize = 11.sp
                )
            }


            Icon(

                imageVector =
                    Icons.Filled.ChevronRight,

                contentDescription =
                    null,

                tint =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
                        .copy(
                            alpha = 0.6f
                        ),

                modifier =
                    Modifier.size(18.dp)
            )
        }
    }
}


// ═════════════════════════════════════
// ESTADÍSTICAS
// ═════════════════════════════════════

@Composable
private fun ProfileStats() {

    Row(
        modifier =
            Modifier.fillMaxWidth(),

        horizontalArrangement =
            Arrangement.spacedBy(10.dp)
    ) {

        ProfileStatCard(
            modifier =
                Modifier.weight(1f),
            value = "8",
            label = "Total",
            color =
                MaterialTheme
                    .colorScheme
                    .primary
        )


        ProfileStatCard(
            modifier =
                Modifier.weight(1f),
            value = "2",
            label = "Completadas",
            color =
                MaterialTheme
                    .colorScheme
                    .tertiary
        )
    }


    Spacer(
        modifier =
            Modifier.height(10.dp)
    )


    Row(
        modifier =
            Modifier.fillMaxWidth(),

        horizontalArrangement =
            Arrangement.spacedBy(10.dp)
    ) {

        ProfileStatCard(
            modifier =
                Modifier.weight(1f),
            value = "6",
            label = "Pendientes",
            color =
                MaterialTheme
                    .colorScheme
                    .primary
        )


        ProfileStatCard(
            modifier =
                Modifier.weight(1f),
            value = "2",
            label = "Alta prioridad",
            color =
                MaterialTheme
                    .colorScheme
                    .error
        )
    }
}


// ═════════════════════════════════════
// TARJETA DE ESTADÍSTICA
// ═════════════════════════════════════

@Composable
private fun ProfileStatCard(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    color: androidx.compose.ui.graphics.Color
) {

    GlassSurface(

        modifier =
            modifier
                .height(70.dp),

        shape =
            RoundedCornerShape(18.dp),

        backgroundColor =
            MaterialTheme
                .colorScheme
                .surface
                .copy(
                    alpha = 0.38f
                ),

        borderColor =
            MaterialTheme
                .colorScheme
                .outline
                .copy(
                    alpha = 0.07f
                )

    ) {

        Column(

            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 14.dp,
                        vertical = 10.dp
                    ),

            verticalArrangement =
                Arrangement.Center

        ) {

            Text(
                text = value,
                color = color,
                fontSize = 19.sp,
                fontWeight =
                    FontWeight.SemiBold
            )


            Spacer(
                modifier =
                    Modifier.height(1.dp)
            )


            Text(
                text = label,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant,
                fontSize = 9.sp
            )
        }
    }
}


// ═════════════════════════════════════
// OPCIÓN
// ═════════════════════════════════════

@Composable
private fun ProfileOption(
    icon:
    androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String?,
    destructive: Boolean = false,
    showArrow: Boolean = true,
    expanded: Boolean = false,
    expandedContent: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {

    val iconColor =
        if (destructive) {

            MaterialTheme
                .colorScheme
                .error

        } else {

            MaterialTheme
                .colorScheme
                .primary
        }


    Column(
        modifier =
            Modifier.fillMaxWidth()
    ) {

        Row(

            modifier =
                Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
                    .clickable(
                        onClick = onClick
                    )
                    .padding(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    ),

            verticalAlignment =
                Alignment.CenterVertically

        ) {

            Box(

                modifier =
                    Modifier
                        .size(34.dp)
                        .background(
                            color =
                                iconColor.copy(
                                    alpha = 0.09f
                                ),
                            shape = CircleShape
                        ),

                contentAlignment =
                    Alignment.Center

            ) {

                Icon(

                    imageVector = icon,

                    contentDescription = null,

                    tint = iconColor,

                    modifier =
                        Modifier.size(17.dp)
                )
            }


            Spacer(
                modifier =
                    Modifier.width(12.dp)
            )


            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    color =
                        if (destructive) {

                            MaterialTheme
                                .colorScheme
                                .error

                        } else {

                            MaterialTheme
                                .colorScheme
                                .onSurface
                        },
                    fontSize = 12.sp,
                    fontWeight =
                        FontWeight.Medium
                )


                if (subtitle != null) {

                    Spacer(
                        modifier =
                            Modifier.height(2.dp)
                    )


                    Text(
                        text = subtitle,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant,
                        fontSize = 9.sp
                    )
                }
            }


            if (showArrow) {

                Icon(
                    imageVector =
                        Icons.Filled.ChevronRight,
                    contentDescription = null,
                    tint =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                            .copy(
                                alpha = 0.55f
                            ),
                    modifier =
                        Modifier.size(17.dp)
                )
            }
        }


        if (expanded && expandedContent != null) {

            expandedContent()
        }
    }
}


// ═════════════════════════════════════
// DIVISOR
// ═════════════════════════════════════

@Composable
private fun ProfileDivider() {

    Box(

        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    start = 62.dp,
                    end = 16.dp
                )
                .height(1.dp)
                .background(
                    MaterialTheme
                        .colorScheme
                        .outline
                        .copy(
                            alpha = 0.065f
                        )
                )
    )
}
@Composable
private fun NotificationExpandedContent(
    enabled: Boolean,
    onEnabledChange: (Boolean) -> Unit
) {

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(
                    start = 62.dp,
                    end = 16.dp,
                    bottom = 14.dp
                )
    ) {

        Row(

            modifier =
                Modifier.fillMaxWidth(),

            verticalAlignment =
                Alignment.CenterVertically

        ) {

            Column(
                modifier =
                    Modifier.weight(1f)
            ) {

                Text(
                    text =
                        "Notificaciones",

                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurface,

                    fontSize = 11.sp,

                    fontWeight =
                        FontWeight.Medium
                )


                Spacer(
                    modifier =
                        Modifier.height(2.dp)
                )


                Text(
                    text =
                        "Recibe avisos sobre tus tareas.",

                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant,

                    fontSize = 9.sp
                )
            }


            Switch(

                checked =
                    enabled,

                onCheckedChange =
                    onEnabledChange,

                modifier =
                    Modifier.scale(0.75f),

                colors =
                    SwitchDefaults.colors(

                        checkedThumbColor =
                            MaterialTheme
                                .colorScheme
                                .onPrimary,

                        checkedTrackColor =
                            MaterialTheme
                                .colorScheme
                                .primary,

                        uncheckedThumbColor =
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant,

                        uncheckedTrackColor =
                            MaterialTheme
                                .colorScheme
                                .surfaceVariant
                    )
            )
        }
    }
}
@Composable
private fun AppearanceExpandedContent(
    selectedAppearance: String,
    onAppearanceSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 62.dp,
                end = 16.dp,
                bottom = 14.dp
            )
    ) {
        AppearanceOption(
            icon = Icons.Filled.Person,
            title = "Sistema",
            selected = selectedAppearance == "Sistema",
            onClick = {
                onAppearanceSelected("Sistema")
            }
        )

        AppearanceOption(
            icon = Icons.Filled.Palette,
            title = "Claro",
            selected = selectedAppearance == "Claro",
            onClick = {
                onAppearanceSelected("Claro")
            }
        )

        AppearanceOption(
            icon = Icons.Filled.Lock,
            title = "Oscuro",
            selected = selectedAppearance == "Oscuro",
            onClick = {
                onAppearanceSelected("Oscuro")
            }
        )
    }
}
@Composable
private fun AppearanceOption(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val primary = MaterialTheme.colorScheme.primary

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .background(
                color = if (selected) {
                    primary.copy(alpha = 0.08f)
                } else {
                    androidx.compose.ui.graphics.Color.Transparent
                }
            )
            .padding(
                horizontal = 10.dp,
                vertical = 9.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (selected) {
                primary
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
            modifier = Modifier.size(17.dp)
        )

        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Text(
            text = title,
            color = if (selected) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            },
            fontSize = 10.sp,
            fontWeight = if (selected) {
                FontWeight.Medium
            } else {
                FontWeight.Normal
            },
            modifier = Modifier.weight(1f)
        )

        if (selected) {
            Box(
                modifier = Modifier
                    .size(7.dp)
                    .background(
                        color = primary,
                        shape = CircleShape
                    )
            )
        }
    }
}
@Composable
private fun PrivacyExpandedContent(
    onAccountDataClick: () -> Unit,
    onSecurityClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 62.dp,
                end = 16.dp,
                bottom = 14.dp
            )
    ) {
        PrivacyOption(
            icon = Icons.Filled.AccountCircle,
            title = "Datos de tu cuenta",
            subtitle = "Información almacenada en DoList",
            onClick = onAccountDataClick
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        PrivacyOption(
            icon = Icons.Filled.Security,
            title = "Sesión y seguridad",
            subtitle = "Contraseña y acceso a tu cuenta",
            onClick = onSecurityClick
        )
    }
}
@Composable
private fun PrivacyOption(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .padding(
                horizontal = 10.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(17.dp)
        )

        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            Text(
                text = subtitle,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 9.sp
            )
        }

        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                alpha = 0.45f
            ),
            modifier = Modifier.size(15.dp)
        )
    }
}
@Composable
private fun HelpExpandedContent(
    onFaqClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 62.dp,
                end = 16.dp,
                bottom = 14.dp
            )
    ) {
        HelpOption(
            icon = Icons.Filled.QuestionAnswer,
            title = "Preguntas frecuentes",
            subtitle = "Respuestas sobre el uso de DoList",
            onClick = onFaqClick
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        HelpOption(
            icon = Icons.Filled.Info,
            title = "Acerca de DoList",
            subtitle = "Información y versión de la aplicación",
            onClick = onAboutClick
        )
    }
}
@Composable
private fun HelpOption(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .padding(
                horizontal = 10.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(17.dp)
        )

        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            Text(
                text = subtitle,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 9.sp
            )
        }

        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                alpha = 0.45f
            ),
            modifier = Modifier.size(15.dp)
        )
    }
}