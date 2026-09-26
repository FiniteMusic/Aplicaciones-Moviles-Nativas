import 'package:flutter/material.dart';

import '../data/driver.dart';
import '../data/driver_repository.dart';
import '../theme/garage_theme.dart';

class TextInputScreen extends StatefulWidget {
  const TextInputScreen({super.key});

  @override
  State<TextInputScreen> createState() => _TextInputScreenState();
}

class _TextInputScreenState extends State<TextInputScreen> {
  final _nameController = TextEditingController();
  final _numberController = TextEditingController();
  final _passwordController = TextEditingController();
  final _emailController = TextEditingController();
  final _phoneController = TextEditingController();
  final _observationsController = TextEditingController();
  final _searchController = TextEditingController();

  String? _selectedTeam;

  String? _nameError;
  String? _numberError;
  String? _teamError;

  bool _passwordVisible = false;

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

  final List<String> _sampleDrivers = const [
    'Charles Leclerc',
    'Lewis Hamilton',
    'Lando Norris',
    'Oscar Piastri',
    'George Russell',
    'Max Verstappen',
    'Fernando Alonso',
    'Pierre Gasly',
    'Carlos Sainz',
    'Alexander Albon',
  ];

  @override
  void dispose() {
    _nameController.dispose();
    _numberController.dispose();
    _passwordController.dispose();
    _emailController.dispose();
    _phoneController.dispose();
    _observationsController.dispose();
    _searchController.dispose();

    super.dispose();
  }

  List<String> get _searchResults {
    final query = _searchController.text.trim().toLowerCase();

    if (query.isEmpty) {
      return [];
    }

    return _sampleDrivers.where((driver) {
      return driver.toLowerCase().contains(query);
    }).toList();
  }

  void _registerDriver() {
    final name = _nameController.text.trim();
    final number = int.tryParse(_numberController.text.trim());

    setState(() {
      _nameError = null;
      _numberError = null;
      _teamError = null;

      if (name.isEmpty) {
        _nameError = 'Ingresa el nombre del piloto.';
      }

      if (number == null || number < 1 || number > 99) {
        _numberError = 'El número debe encontrarse entre 1 y 99.';
      }

      if (_selectedTeam == null) {
        _teamError = 'Selecciona una escudería.';
      }
    });

    if (_nameError != null ||
        _numberError != null ||
        _teamError != null ||
        number == null ||
        _selectedTeam == null) {
      return;
    }

    final driver = Driver(name: name, number: number, team: _selectedTeam!);

    final registered = DriverRepository.instance.addDriver(driver);

    if (!registered) {
      setState(() {
        _numberError = 'El número $number ya está registrado.';
      });

      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('Número de piloto duplicado.')),
      );

      return;
    }

    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text('Piloto registrado: ${driver.name} #${driver.number}'),
      ),
    );

    setState(() {
      _nameController.clear();
      _numberController.clear();
      _selectedTeam = null;

      _nameError = null;
      _numberError = null;
      _teamError = null;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: GarageColors.background,
      appBar: AppBar(title: const Text('Entradas de texto')),
      body: ListView(
        padding: const EdgeInsets.all(20),
        children: [
          _buildHeader(),

          const SizedBox(height: 24),

          // =====================================
          // 1. TEXTFIELD
          // =====================================
          const _ComponentDocumentation(
            title: '1. TextField',
            description: 'TextField permite capturar texto introducido por el usuario. En este ejemplo almacenamos el nombre del piloto.',
          ),

          TextField(
            controller: _nameController,
            textCapitalization: TextCapitalization.words,
            decoration: InputDecoration(
              labelText: 'Nombre del piloto',
              hintText: 'Ej. Fernando Alonso',
              errorText: _nameError,
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 2. CAMPO NUMÉRICO
          // =====================================
          const _ComponentDocumentation(
            title: '2. Entrada numérica',
            description: 'TextField puede solicitar un teclado numérico mediante keyboardType. El dorsal se valida entre 1 y 99.',
          ),

          TextField(
            controller: _numberController,
            keyboardType: TextInputType.number,
            decoration: InputDecoration(
              labelText: 'Número del piloto',
              hintText: '1 - 99',
              errorText: _numberError,
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 3. PASSWORD
          // =====================================
          const _ComponentDocumentation(
            title: '3. Contraseña',
            description: 'obscureText permite ocultar el contenido de un TextField. El botón lateral permite alternar su visibilidad.',
          ),

          TextField(
            controller: _passwordController,
            obscureText: !_passwordVisible,
            decoration: InputDecoration(
              labelText: 'Contraseña del ingeniero',
              suffixIcon: IconButton(
                onPressed: () {
                  setState(() {
                    _passwordVisible = !_passwordVisible;
                  });
                },
                icon: Icon(
                  _passwordVisible ? Icons.visibility_off : Icons.visibility,
                ),
              ),
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 4. EMAIL / TELÉFONO
          // =====================================
          const _ComponentDocumentation(
            title: '4. Tipos de teclado',
            description: 'keyboardType adapta el teclado virtual al tipo de información solicitada, por ejemplo correo electrónico o teléfono.',
          ),

          TextField(
            controller: _emailController,
            keyboardType: TextInputType.emailAddress,
            decoration: const InputDecoration(
              labelText: 'Correo electrónico',
              hintText: 'ingeniero@equipo.com',
            ),
          ),

          const SizedBox(height: 12),

          TextField(
            controller: _phoneController,
            keyboardType: TextInputType.phone,
            decoration: const InputDecoration(
              labelText: 'Teléfono',
              hintText: '+52 55 0000 0000',
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 5. MULTILÍNEA
          // =====================================
          const _ComponentDocumentation(
            title: '5. Texto multilínea',
            description: 'maxLines permite convertir el campo en un área de texto adecuada para información extensa u observaciones.',
          ),

          TextField(
            controller: _observationsController,
            minLines: 3,
            maxLines: 5,
            decoration: const InputDecoration(
              labelText: 'Observaciones',
              hintText: 'Escribe observaciones sobre el piloto...',
              alignLabelWithHint: true,
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 6. DROPDOWN
          // =====================================
          const _ComponentDocumentation(
            title: '6. DropdownButtonFormField',
            description: 'DropdownButtonFormField presenta una colección de opciones y permite seleccionar una única escudería.',
          ),

          DropdownButtonFormField<String>(
            initialValue: _selectedTeam,
            isExpanded: true,
            decoration: InputDecoration(
              labelText: 'Escudería',
              errorText: _teamError,
            ),
            items: _teams.map((team) {
              return DropdownMenuItem(value: team, child: Text(team));
            }).toList(),
            onChanged: (value) {
              setState(() {
                _selectedTeam = value;
                _teamError = null;
              });
            },
          ),

          const SizedBox(height: 24),

          // =====================================
          // 7. BÚSQUEDA
          // =====================================
          const _ComponentDocumentation(
            title: '7. Búsqueda interactiva',
            description: 'onChanged permite reaccionar mientras el usuario escribe. La lista se filtra dinámicamente utilizando el contenido del campo.',
          ),

          TextField(
            controller: _searchController,
            onChanged: (_) {
              setState(() {});
            },
            decoration: const InputDecoration(
              labelText: 'Buscar piloto',
              hintText: 'Ej. Alonso',
              prefixIcon: Icon(Icons.search),
            ),
          ),

          if (_searchController.text.isNotEmpty) ...[
            const SizedBox(height: 10),

            if (_searchResults.isEmpty)
              const _SearchMessage(text: 'No se encontraron pilotos.')
            else
              ..._searchResults.map((driver) => _SearchResult(driver: driver)),
          ],

          const SizedBox(height: 24),

          // =====================================
          // 8. REGISTRO
          // =====================================
          const _ComponentDocumentation(
            title: '8. Validación y registro',
            description: 'El botón valida los datos obligatorios antes de crear el piloto. Los dorsales duplicados son rechazados.',
          ),

          SizedBox(
            width: double.infinity,
            child: FilledButton(
              onPressed: _registerDriver,
              style: FilledButton.styleFrom(
                backgroundColor: GarageColors.ferrariAccent,
                padding: const EdgeInsets.symmetric(vertical: 16),
              ),
              child: const Text('Registrar piloto'),
            ),
          ),

          const SizedBox(height: 24),

          _buildStatusCard(),

          const SizedBox(height: 20),
        ],
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
                      'FERRARI · SECCIÓN 01',
                      style: TextStyle(
                        color: GarageColors.ferrari,
                        fontSize: 12,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  SizedBox(height: 10),
                  Text(
                    'Entradas de texto',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 26,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  SizedBox(height: 8),
                  Text(
                    'Captura, valida y procesa diferentes tipos de información mediante campos de entrada.',
                    style: TextStyle(color: Color(0xFFD5D8DF), fontSize: 14),
                  ),
                ],
              ),
            ),
            Container(height: 5, color: GarageColors.ferrari),
          ],
        ),
      ),
    );
  }

  Widget _buildStatusCard() {
    return ValueListenableBuilder<List<Driver>>(
      valueListenable: DriverRepository.instance.drivers,
      builder: (context, drivers, _) {
        return Container(
          padding: const EdgeInsets.all(20),
          decoration: BoxDecoration(
            color: GarageColors.header,
            borderRadius: BorderRadius.circular(18),
          ),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const Text(
                'REGISTRO DE PILOTOS',
                style: TextStyle(
                  color: GarageColors.ferrari,
                  fontSize: 12,
                  fontWeight: FontWeight.bold,
                ),
              ),
              const SizedBox(height: 8),
              Text(
                drivers.isEmpty
                    ? 'Aún no hay pilotos registrados.'
                    : '${drivers.length} piloto(s) registrado(s).',
                style: const TextStyle(color: Colors.white, fontSize: 14),
              ),
            ],
          ),
        );
      },
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

class _SearchResult extends StatelessWidget {
  final String driver;

  const _SearchResult({required this.driver});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      margin: const EdgeInsets.only(bottom: 6),
      padding: const EdgeInsets.symmetric(horizontal: 14, vertical: 10),
      decoration: BoxDecoration(
        color: GarageColors.surface,
        borderRadius: BorderRadius.circular(10),
        border: Border.all(color: GarageColors.border),
      ),
      child: Text(
        driver,
        style: const TextStyle(color: GarageColors.textPrimary),
      ),
    );
  }
}

class _SearchMessage extends StatelessWidget {
  final String text;

  const _SearchMessage({required this.text});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      padding: const EdgeInsets.all(14),
      decoration: BoxDecoration(
        color: GarageColors.surface,
        borderRadius: BorderRadius.circular(10),
      ),
      child: Text(
        text,
        style: const TextStyle(color: GarageColors.textSecondary),
      ),
    );
  }
}
