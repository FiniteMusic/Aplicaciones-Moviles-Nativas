package com.marcudlcv.dolist.ui.theme

import androidx.compose.ui.graphics.Color

// ============================================================
// DoList — Design System
// Identidad visual: Azul profundo + Azul + Cian
// ============================================================


// ------------------------------------------------------------
// DARK THEME
// ------------------------------------------------------------

val DarkAppBackground = Color(0xFF050A0F)
val DarkAppBackgroundMid = Color(0xFF0A141C)

// Glass azulado sutil.
// Ya no usamos blanco como base del cristal.
val DarkSurface = Color(0x14243A4A)
val DarkSurfaceStrong = Color(0x1F2C4656)
val DarkSurfaceHover = Color(0x26365060)

val DarkBorder = Color(0x266B8797)
val DarkBorderStrong = Color(0x3D8AA7B8)

val DarkTextPrimary = Color(0xFFF1F7FA)
val DarkTextSecondary = Color(0x99D5E2E8)
val DarkTextTertiary = Color(0x52A9BBC5)


// ------------------------------------------------------------
// LIGHT THEME
// ------------------------------------------------------------

val LightAppBackground = Color(0xFFF2F7FA)
val LightAppBackgroundMid = Color(0xFFE7F1F5)

val LightSurface = Color(0x85FFFFFF)
val LightSurfaceStrong = Color(0xB8FFFFFF)
val LightSurfaceHover = Color(0xD6FFFFFF)

// Eliminamos el matiz morado de los bordes.
val LightBorder = Color(0x1F4C7182)
val LightBorderStrong = Color(0x384C7182)

val LightTextPrimary = Color(0xFF071C28)
val LightTextSecondary = Color(0x8C263F4C)
val LightTextTertiary = Color(0x522F4B58)


// ------------------------------------------------------------
// ACCENT
// ------------------------------------------------------------

// Azul principal de DoList.
val Accent = Color(0xFF0081B3)

// Azul/cian luminoso para elementos destacados.
val AccentLight = Color(0xFF18B8E8)

// Variante para fondos claros.
val AccentLightTheme = Color(0xFF006F99)

val AccentDim = Color(0x260081B3)
val AccentBorder = Color(0x520081B3)


// ------------------------------------------------------------
// BLUE
// ------------------------------------------------------------

// Azul secundario utilizado en gradientes y elementos visuales.
val Blue = Color(0xFF35BCE8)
val BlueLightTheme = Color(0xFF168FBA)

val BlueDim = Color(0x2635BCE8)


// ------------------------------------------------------------
// PRIORIDAD — ALTA
// ------------------------------------------------------------

val Red = Color(0xFFFF6B8A)
val RedLightTheme = Color(0xFFE04060)

val RedDim = Color(0x26FF6B8A)
val RedBorder = Color(0x47FF6B8A)


// ------------------------------------------------------------
// PRIORIDAD — MEDIA
// ------------------------------------------------------------

val Orange = Color(0xFFFFB347)
val OrangeLightTheme = Color(0xFFD4831A)

val OrangeDim = Color(0x26FFB347)
val OrangeBorder = Color(0x47FFB347)


// ------------------------------------------------------------
// PRIORIDAD — BAJA / ÉXITO
// ------------------------------------------------------------

val Green = Color(0xFF5CF5A8)
val GreenLightTheme = Color(0xFF1A9E62)

val GreenDim = Color(0x265CF5A8)
val GreenBorder = Color(0x475CF5A8)


// ------------------------------------------------------------
// NAVEGACIÓN
// ------------------------------------------------------------

val DarkNavBackground = Color(0xE60A151D)
val DarkNavBorder = Color(0x1F6B8797)

val LightNavBackground = Color(0xE6EDF5F8)
val LightNavBorder = Color(0x1F4C7182)


// ------------------------------------------------------------
// MODALES / OVERLAYS
// ------------------------------------------------------------

val DarkModalOverlay = Color(0xC7050A0F)
val LightModalOverlay = Color(0x66111E26)


// ------------------------------------------------------------
// INPUTS
// ------------------------------------------------------------

// Inputs oscuros ligeramente azulados.
val DarkInputBackground = Color(0x18243A4A)
val DarkInputBorder = Color(0x386B8797)

val LightInputBackground = Color(0x99FFFFFF)
val LightInputBorder = Color(0x2E4C7182)


// ------------------------------------------------------------
// EFECTOS / FONDO
// ------------------------------------------------------------

// Orbes basados en la identidad azul.
// Nada de morado.
val DarkOrb1 = Color(0x290081B3)
val DarkOrb2 = Color(0x2635BCE8)

val LightOrb1 = Color(0x1A0081B3)
val LightOrb2 = Color(0x1435BCE8)


// ------------------------------------------------------------
// UTILIDADES
// ------------------------------------------------------------

val ErrorColor = Red
val DividerColor = Color(0x26788792)


// ------------------------------------------------------------
// PRIORITY COLORS
// ------------------------------------------------------------

val PriorityHigh = Color(0xFFFF6B8A)
val PriorityMedium = Color(0xFFFFB347)
val PriorityLow = Color(0xFF5CF5A8)