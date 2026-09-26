import 'package:flutter/material.dart';

import '../theme/garage_theme.dart';
import '../widgets/team_category_card.dart';
import 'text_input_screen.dart';
import 'buttons_screen.dart';
import 'selection_screen.dart';
import 'lists_screen.dart';
import 'feedback_screen.dart';
import 'layouts_screen.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: GarageColors.background,
      body: SafeArea(
        child: ListView(
          padding: const EdgeInsets.all(20),
          children: [
            _buildHeader(),

            const SizedBox(height: 24),

            const Text(
              'SELECCIONA UNA SECCIÓN',
              style: TextStyle(
                color: GarageColors.textSecondary,
                fontSize: 12,
                fontWeight: FontWeight.bold,
              ),
            ),

            const SizedBox(height: 14),

            TeamCategoryCard(
              team: 'Ferrari',
              title: 'Entradas de texto',
              description: 'Campos de texto, validación, búsqueda y registro de pilotos.',
              accentColor: GarageColors.ferrari,
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => const TextInputScreen()),
                );
              },
            ),

            const SizedBox(height: 14),

            TeamCategoryCard(
              team: 'McLaren',
              title: 'Botones y controles',
              description:
                  'Acciones, botones, interruptores y controles interactivos.',
              accentColor: GarageColors.mclaren,
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => const ButtonsScreen()),
                );
              },
            ),

            const SizedBox(height: 14),

            TeamCategoryCard(
              team: 'Mercedes',
              title: 'Selecciones',
              description: 'Casillas, opciones, listas desplegables y controles de selección.',
              accentColor: GarageColors.mercedes,
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => const SelectionScreen()),
                );
              },
            ),

            const SizedBox(height: 14),

            TeamCategoryCard(
              team: 'Williams',
              title: 'Listas',
              description: 'Visualización de pilotos y escuderías mediante diferentes listas.',
              accentColor: GarageColors.williams,
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => const ListsScreen()),
                );
              },
            ),

            const SizedBox(height: 14),

            TeamCategoryCard(
              team: 'Aston Martin',
              title: 'Retroalimentación',
              description:
                  'Mensajes, diálogos, errores e indicadores de progreso.',
              accentColor: GarageColors.astonMartin,
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => const FeedbackScreen()),
                );
              },
            ),

            const SizedBox(height: 14),

            TeamCategoryCard(
              team: 'Alpine',
              title: 'Layouts',
              description:
                  'Distribución y organización de elementos de la interfaz.',
              accentColor: GarageColors.alpine,
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (_) => const LayoutsScreen()),
                );
              },
            ),

            const SizedBox(height: 20),
          ],
        ),
      ),
    );
  }

  Widget _buildHeader() {
    return ClipRRect(
      borderRadius: BorderRadius.circular(20),
      child: Container(
        color: GarageColors.header,
        child: Column(
          children: [
            const Padding(
              padding: EdgeInsets.all(24),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Align(
                    alignment: Alignment.centerLeft,
                    child: Text(
                      'F1 UI GARAGE',
                      style: TextStyle(
                        color: GarageColors.ferrari,
                        fontSize: 13,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),

                  SizedBox(height: 8),

                  Align(
                    alignment: Alignment.centerLeft,
                    child: Text(
                      'Catálogo de componentes',
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 26,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),

                  SizedBox(height: 8),

                  Align(
                    alignment: Alignment.centerLeft,
                    child: Text(
                      'Flutter · Dart',
                      style: TextStyle(color: Color(0xFFD5D8DF), fontSize: 14),
                    ),
                  ),
                ],
              ),
            ),

            Row(
              children: const [
                Expanded(child: _HeaderColor(color: GarageColors.ferrari)),
                Expanded(child: _HeaderColor(color: GarageColors.mclaren)),
                Expanded(child: _HeaderColor(color: GarageColors.mercedes)),
                Expanded(child: _HeaderColor(color: GarageColors.williams)),
                Expanded(child: _HeaderColor(color: GarageColors.astonMartin)),
                Expanded(child: _HeaderColor(color: GarageColors.alpine)),
              ],
            ),
          ],
        ),
      ),
    );
  }
}

class _HeaderColor extends StatelessWidget {
  final Color color;

  const _HeaderColor({required this.color});

  @override
  Widget build(BuildContext context) {
    return Container(height: 6, color: color);
  }
}
