import 'package:flutter/material.dart';

import '../data/driver.dart';
import '../data/driver_repository.dart';
import '../theme/garage_theme.dart';

class ListsScreen extends StatefulWidget {
  const ListsScreen({super.key});

  @override
  State<ListsScreen> createState() => _ListsScreenState();
}

class _ListsScreenState extends State<ListsScreen> {
  String? _selectedTeam;

  final List<String> _teams = const [
    'Ferrari',
    'McLaren',
    'Mercedes',
    'Red Bull Racing',
    'Aston Martin',
    'Alpine',
    'Williams',
    'Haas',
    'Racing Bulls',
    'Audi',
    'Cadillac',
  ];

  final List<String> _sessions = const [
    'Gran Premio',
    'Clasificación',
    'Sprint',
    'Prácticas libres',
  ];

  List<Driver> _filterDrivers(List<Driver> drivers) {
    if (_selectedTeam == null) {
      return drivers;
    }

    return drivers.where((driver) => driver.team == _selectedTeam).toList();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: GarageColors.background,
      appBar: AppBar(title: const Text('Listas')),
      body: ValueListenableBuilder<List<Driver>>(
        valueListenable: DriverRepository.instance.drivers,
        builder: (context, drivers, _) {
          final filteredDrivers = _filterDrivers(drivers);

          return ListView(
            padding: const EdgeInsets.all(20),
            children: [
              _buildHeader(),

              const SizedBox(height: 24),

              // =====================================
              // 1. LISTVIEW
              // =====================================
              const _ComponentDocumentation(
                title: '1. Lista simple',
                description: 'ListView organiza elementos verticalmente y proporciona desplazamiento cuando el contenido supera el espacio disponible.',
              ),

              Container(
                decoration: BoxDecoration(
                  color: GarageColors.surface,
                  borderRadius: BorderRadius.circular(16),
                  border: Border.all(color: GarageColors.border),
                ),
                child: Column(
                  children: List.generate(_sessions.length, (index) {
                    return Column(
                      children: [
                        ListTile(
                          leading: CircleAvatar(
                            backgroundColor: GarageColors.williamsAccent,
                            foregroundColor: Colors.white,
                            child: Text('${index + 1}'),
                          ),
                          title: Text(
                            _sessions[index],
                            style: const TextStyle(
                              color: GarageColors.textPrimary,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ),

                        if (index < _sessions.length - 1)
                          const Divider(height: 1),
                      ],
                    );
                  }),
                ),
              ),

              const SizedBox(height: 24),

              // =====================================
              // 2. LISTVIEW HORIZONTAL
              // =====================================
              const _ComponentDocumentation(
                title: '2. Lista horizontal',
                description: 'ListView también puede desplazarse horizontalmente. Aquí las escuderías funcionan como filtros para la lista de pilotos.',
              ),

              SizedBox(
                height: 82,
                child: ListView.separated(
                  scrollDirection: Axis.horizontal,
                  itemCount: _teams.length,
                  separatorBuilder: (_, _) => const SizedBox(width: 10),
                  itemBuilder: (context, index) {
                    final team = _teams[index];

                    final selected = _selectedTeam == team;

                    return InkWell(
                      borderRadius: BorderRadius.circular(14),
                      onTap: () {
                        setState(() {
                          _selectedTeam = selected ? null : team;
                        });
                      },
                      child: Container(
                        width: 140,
                        alignment: Alignment.center,
                        padding: const EdgeInsets.all(12),
                        decoration: BoxDecoration(
                          color: selected
                              ? GarageColors.williams
                              : GarageColors.surface,
                          borderRadius: BorderRadius.circular(14),
                          border: Border.all(
                            color: selected
                                ? GarageColors.williams
                                : GarageColors.border,
                          ),
                        ),
                        child: Text(
                          team,
                          textAlign: TextAlign.center,
                          style: TextStyle(
                            color: selected
                                ? Colors.white
                                : GarageColors.textPrimary,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ),
                    );
                  },
                ),
              ),

              const SizedBox(height: 10),

              Text(
                _selectedTeam == null
                    ? 'Filtro: todas las escuderías'
                    : 'Filtro: $_selectedTeam',
                style: const TextStyle(
                  color: GarageColors.textSecondary,
                  fontSize: 13,
                ),
              ),

              const SizedBox(height: 24),

              // =====================================
              // 3. LISTA DINÁMICA
              // =====================================
              const _ComponentDocumentation(
                title: '3. Lista dinámica',
                description: 'ValueListenableBuilder observa el repositorio compartido. Cuando Ferrari registra o Williams elimina un piloto, la interfaz se reconstruye automáticamente.',
              ),

              Row(
                children: [
                  const Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'Pilotos registrados',
                          style: TextStyle(
                            color: GarageColors.textPrimary,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        SizedBox(height: 3),
                        Text(
                          'Datos compartidos con Ferrari',
                          style: TextStyle(
                            color: GarageColors.textSecondary,
                            fontSize: 12,
                          ),
                        ),
                      ],
                    ),
                  ),

                  Container(
                    padding: const EdgeInsets.symmetric(
                      horizontal: 14,
                      vertical: 7,
                    ),
                    decoration: BoxDecoration(
                      color: GarageColors.williams,
                      borderRadius: BorderRadius.circular(30),
                    ),
                    child: Text(
                      '${filteredDrivers.length}',
                      style: const TextStyle(
                        color: Colors.white,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                ],
              ),

              const SizedBox(height: 14),

              if (filteredDrivers.isEmpty)
                _EmptyDriversCard(filtered: _selectedTeam != null)
              else
                ...filteredDrivers.asMap().entries.map((entry) {
                  return Padding(
                    padding: const EdgeInsets.only(bottom: 10),
                    child: _DriverCard(
                      position: entry.key + 1,
                      driver: entry.value,
                      onDelete: () {
                        _deleteDriver(context, entry.value);
                      },
                    ),
                  );
                }),

              const SizedBox(height: 24),

              // =====================================
              // 4. GRIDVIEW
              // =====================================
              const _ComponentDocumentation(
                title: '4. GridView',
                description: 'GridView organiza elementos en una cuadrícula. Aquí representa las once escuderías del catálogo.',
              ),

              GridView.builder(
                shrinkWrap: true,
                physics: const NeverScrollableScrollPhysics(),
                itemCount: _teams.length,
                gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 2,
                  crossAxisSpacing: 10,
                  mainAxisSpacing: 10,
                  childAspectRatio: 2.2,
                ),
                itemBuilder: (context, index) {
                  return _TeamGridItem(team: _teams[index]);
                },
              ),

              const SizedBox(height: 24),

              // =====================================
              // ESTADO
              // =====================================
              _buildStatusCard(drivers.length),

              const SizedBox(height: 20),
            ],
          );
        },
      ),
    );
  }

  void _deleteDriver(BuildContext context, Driver driver) {
    DriverRepository.instance.removeDriver(driver);

    ScaffoldMessenger.of(context)
        .showSnackBar(SnackBar(content: Text('${driver.name} fue eliminado.')));
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
                      'WILLIAMS · SECCIÓN 04',
                      style: TextStyle(
                        color: GarageColors.williams,
                        fontSize: 12,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),

                  SizedBox(height: 10),

                  Text(
                    'Listas',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 26,
                      fontWeight: FontWeight.bold,
                    ),
                  ),

                  SizedBox(height: 8),

                  Text(
                    'Organiza y presenta colecciones de datos mediante listas, filtros y cuadrículas.',
                    style: TextStyle(color: Color(0xFFD5D8DF), fontSize: 14),
                  ),
                ],
              ),
            ),

            SizedBox(
              height: 5,
              child: ColoredBox(color: GarageColors.williams),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildStatusCard(int totalDrivers) {
    return Container(
      width: double.infinity,
      padding: const EdgeInsets.all(20),
      decoration: BoxDecoration(
        color: GarageColors.header,
        borderRadius: BorderRadius.circular(18),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Text(
            'PADDOCK',
            style: TextStyle(
              color: GarageColors.williams,
              fontSize: 12,
              fontWeight: FontWeight.bold,
            ),
          ),

          const SizedBox(height: 8),

          Text(
            totalDrivers == 0
                ? 'Aún no hay pilotos registrados. Registra uno desde Ferrari.'
                : '$totalDrivers piloto(s) registrado(s) actualmente.',
            style: const TextStyle(color: Colors.white, fontSize: 14),
          ),
        ],
      ),
    );
  }
}

class _DriverCard extends StatelessWidget {
  final int position;
  final Driver driver;
  final VoidCallback onDelete;

  const _DriverCard({
    required this.position,
    required this.driver,
    required this.onDelete,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: GarageColors.surface,
        borderRadius: BorderRadius.circular(16),
        border: Border.all(color: GarageColors.border),
      ),
      child: Row(
        children: [
          Container(
            width: 52,
            height: 52,
            alignment: Alignment.center,
            decoration: BoxDecoration(
              color: GarageColors.williams,
              borderRadius: BorderRadius.circular(12),
            ),
            child: Text(
              '#${driver.number}',
              style: const TextStyle(
                color: Colors.white,
                fontWeight: FontWeight.bold,
              ),
            ),
          ),

          const SizedBox(width: 14),

          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  driver.name,
                  style: const TextStyle(
                    color: GarageColors.textPrimary,
                    fontSize: 16,
                    fontWeight: FontWeight.bold,
                  ),
                ),

                const SizedBox(height: 3),

                Text(
                  driver.team,
                  style: const TextStyle(
                    color: GarageColors.textSecondary,
                    fontSize: 13,
                  ),
                ),

                Text(
                  'Posición en lista: $position',
                  style: const TextStyle(
                    color: GarageColors.textSecondary,
                    fontSize: 12,
                  ),
                ),
              ],
            ),
          ),

          TextButton(
            onPressed: onDelete,
            child: const Text(
              'Eliminar',
              style: TextStyle(color: GarageColors.williamsAccent),
            ),
          ),
        ],
      ),
    );
  }
}

class _EmptyDriversCard extends StatelessWidget {
  final bool filtered;

  const _EmptyDriversCard({required this.filtered});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      padding: const EdgeInsets.all(24),
      decoration: BoxDecoration(
        color: GarageColors.surface,
        borderRadius: BorderRadius.circular(16),
        border: Border.all(color: GarageColors.border),
      ),
      child: Column(
        children: [
          Icon(
            Icons.person_off_outlined,
            color: GarageColors.williamsAccent,
            size: 34,
          ),

          const SizedBox(height: 10),

          Text(
            filtered
                ? 'Sin pilotos en esta escudería'
                : 'Sin pilotos registrados',
            textAlign: TextAlign.center,
            style: const TextStyle(
              color: GarageColors.textPrimary,
              fontWeight: FontWeight.bold,
            ),
          ),

          const SizedBox(height: 6),

          Text(
            filtered
                ? 'Selecciona nuevamente la escudería para quitar el filtro.'
                : 'Utiliza Ferrari → Entradas de texto para registrar el primer piloto.',
            textAlign: TextAlign.center,
            style: const TextStyle(
              color: GarageColors.textSecondary,
              fontSize: 13,
            ),
          ),
        ],
      ),
    );
  }
}

class _TeamGridItem extends StatelessWidget {
  final String team;

  const _TeamGridItem({required this.team});

  @override
  Widget build(BuildContext context) {
    return Container(
      alignment: Alignment.center,
      padding: const EdgeInsets.all(10),
      decoration: BoxDecoration(
        color: GarageColors.surface,
        borderRadius: BorderRadius.circular(14),
        border: Border.all(color: GarageColors.border),
      ),
      child: Text(
        team,
        textAlign: TextAlign.center,
        style: const TextStyle(
          color: GarageColors.textPrimary,
          fontSize: 13,
          fontWeight: FontWeight.bold,
        ),
      ),
    );
  }
}

class _ComponentDocumentation extends StatelessWidget {
  final String title;
  final String description;

  const _ComponentDocumentation({
    required this.title,
    required this.description,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 12),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            title,
            style: const TextStyle(
              color: GarageColors.textPrimary,
              fontSize: 18,
              fontWeight: FontWeight.bold,
            ),
          ),

          const SizedBox(height: 4),

          Text(
            description,
            style: const TextStyle(
              color: GarageColors.textSecondary,
              fontSize: 14,
            ),
          ),
        ],
      ),
    );
  }
}
