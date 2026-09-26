import 'package:flutter/material.dart';

import 'screens/home_screen.dart';
import 'theme/garage_theme.dart';

void main() {
  runApp(const F1UIGarageApp());
}

class F1UIGarageApp extends StatelessWidget {
  const F1UIGarageApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'F1 UI Garage',
      debugShowCheckedModeBanner: false,
      theme: GarageTheme.lightTheme,
      home: const HomeScreen(),
    );
  }
}