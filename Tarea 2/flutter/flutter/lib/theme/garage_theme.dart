import 'package:flutter/material.dart';

class GarageColors {
  static const background = Color(0xFFF6F7F9);
  static const surface = Color(0xFFFFFFFF);
  static const header = Color(0xFF20232B);

  static const textPrimary = Color(0xFF20232B);
  static const textSecondary = Color(0xFF626875);
  static const border = Color(0xFFE4E7EC);

  static const ferrari = Color(0xFFE21B2E);
  static const mclaren = Color(0xFFFF8000);
  static const mercedes = Color(0xFF00C8B3);
  static const williams = Color(0xFF2563EB);
  static const astonMartin = Color(0xFF00665E);
  static const alpine = Color(0xFF2454F4);

  static const ferrariAccent = Color(0xFFC41629);
  static const mclarenAccent = Color(0xFFB85C00);
  static const mercedesAccent = Color(0xFF007D72);
  static const williamsAccent = Color(0xFF1D4ED8);
  static const astonMartinAccent = Color(0xFF00665E);
  static const alpineAccent = Color(0xFF2146C7);
}

class GarageTheme {
  static ThemeData get lightTheme {
    return ThemeData(
      useMaterial3: true,
      brightness: Brightness.light,
      scaffoldBackgroundColor: GarageColors.background,
      colorScheme: ColorScheme.fromSeed(
        seedColor: GarageColors.ferrari,
        brightness: Brightness.light,
        surface: GarageColors.surface,
      ),
      appBarTheme: const AppBarTheme(
        backgroundColor: GarageColors.header,
        foregroundColor: Colors.white,
        elevation: 0,
      ),
      inputDecorationTheme: InputDecorationTheme(
        filled: true,
        fillColor: GarageColors.surface,
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(14),
        ),
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(14),
          borderSide: const BorderSide(
            color: GarageColors.border,
          ),
        ),
      ),
    );
  }
}