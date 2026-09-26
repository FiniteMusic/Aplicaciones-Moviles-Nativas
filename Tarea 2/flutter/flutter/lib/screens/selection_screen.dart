import 'package:flutter/material.dart';

import '../theme/garage_theme.dart';

class SelectionScreen extends StatefulWidget {
  const SelectionScreen({super.key});

  @override
  State<SelectionScreen> createState() => _SelectionScreenState();
}

class _SelectionScreenState extends State<SelectionScreen> {
  bool _softTyre = false;
  bool _mediumTyre = true;
  bool _hardTyre = false;

  String _selectedStrategy = 'Una parada';
  String _selectedCircuit = 'Monza';
  bool _automaticMode = false;

  double _brakeBalance = 55;
  int _confidence = 3;

  String _selectedWeather = 'Seco';

  final List<String> _strategies = const [
    'Una parada',
    'Dos paradas',
    'Tres paradas',
  ];

  final List<String> _circuits = const [
    'Monza',
    'Silverstone',
    'Spa-Francorchamps',
    'Suzuka',
    'Interlagos',
    'Mónaco',
  ];

  final List<String> _weatherOptions = const ['Seco', 'Lluvia', 'Mixto'];

  String get _enabledTyres {
    final tyres = <String>[];

    if (_softTyre) {
      tyres.add('Blando');
    }

    if (_mediumTyre) {
      tyres.add('Medio');
    }

    if (_hardTyre) {
      tyres.add('Duro');
    }

    if (tyres.isEmpty) {
      return 'Ninguno';
    }

    return tyres.join(', ');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: GarageColors.background,
      appBar: AppBar(title: const Text('Selecciones')),
      body: ListView(
        padding: const EdgeInsets.all(20),
        children: [
          _buildHeader(),

          const SizedBox(height: 24),

          // =====================================
          // 1. CHECKBOX
          // =====================================
          const _ComponentDocumentation(
            title: '1. Checkbox',
            description: 'Checkbox permite seleccionar varias opciones independientes. En este ejemplo se habilitan los compuestos disponibles.',
          ),

          _TyreCheckbox(
            title: 'Blando',
            subtitle: 'Máximo agarre y menor duración',
            value: _softTyre,
            onChanged: (value) {
              setState(() {
                _softTyre = value ?? false;
              });
            },
          ),

          _TyreCheckbox(
            title: 'Medio',
            subtitle: 'Equilibrio entre agarre y duración',
            value: _mediumTyre,
            onChanged: (value) {
              setState(() {
                _mediumTyre = value ?? false;
              });
            },
          ),

          _TyreCheckbox(
            title: 'Duro',
            subtitle: 'Mayor duración y menor agarre',
            value: _hardTyre,
            onChanged: (value) {
              setState(() {
                _hardTyre = value ?? false;
              });
            },
          ),

          const SizedBox(height: 24),

          // =====================================
          // 2. RADIO
          // =====================================
          const _ComponentDocumentation(
            title: '2. Radio',
            description: 'Radio permite elegir una única opción dentro de un grupo. Aquí seleccionamos la estrategia de paradas.',
          ),

          RadioGroup<String>(
            groupValue: _selectedStrategy,
            onChanged: (value) {
              if (value == null) {
                return;
              }

              setState(() {
                _selectedStrategy = value;
              });
            },
            child: Column(
              children: _strategies.map((strategy) {
                return RadioListTile<String>(
                  title: Text(strategy),
                  value: strategy,
                  activeColor: GarageColors.mercedesAccent,
                  contentPadding: EdgeInsets.zero,
                );
              }).toList(),
            ),
          ),

          const SizedBox(height: 16),

          // =====================================
          // 3. DROPDOWN
          // =====================================
          const _ComponentDocumentation(
            title: '3. DropdownButtonFormField',
            description: 'DropdownButtonFormField permite seleccionar un elemento de una colección sin ocupar espacio permanente con todas las opciones.',
          ),

          DropdownButtonFormField<String>(
            initialValue: _selectedCircuit,
            isExpanded: true,
            decoration: const InputDecoration(labelText: 'Circuito'),
            items: _circuits.map((circuit) {
              return DropdownMenuItem(value: circuit, child: Text(circuit));
            }).toList(),
            onChanged: (value) {
              if (value == null) return;

              setState(() {
                _selectedCircuit = value;
              });
            },
          ),

          const SizedBox(height: 24),

          // =====================================
          // 4. SWITCH
          // =====================================
          const _ComponentDocumentation(
            title: '4. Switch',
            description: 'Switch representa una preferencia binaria. El modo automático activa o desactiva la gestión automática de estrategia.',
          ),

          Container(
            padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
            decoration: BoxDecoration(
              color: GarageColors.surface,
              borderRadius: BorderRadius.circular(16),
              border: Border.all(color: GarageColors.border),
            ),
            child: Row(
              children: [
                const Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Estrategia automática',
                        style: TextStyle(
                          color: GarageColors.textPrimary,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      SizedBox(height: 3),
                      Text(
                        'Permitir ajustes durante la carrera',
                        style: TextStyle(
                          color: GarageColors.textSecondary,
                          fontSize: 12,
                        ),
                      ),
                    ],
                  ),
                ),
                Switch(
                  value: _automaticMode,
                  activeThumbColor: GarageColors.mercedesAccent,
                  onChanged: (value) {
                    setState(() {
                      _automaticMode = value;
                    });
                  },
                ),
              ],
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 5. SLIDER
          // =====================================
          const _ComponentDocumentation(
            title: '5. Slider',
            description: 'Slider permite seleccionar un valor dentro de un intervalo continuo. Aquí ajustamos el balance de frenado entre 45% y 65%.',
          ),

          Container(
            padding: const EdgeInsets.all(16),
            decoration: BoxDecoration(
              color: GarageColors.surface,
              borderRadius: BorderRadius.circular(16),
              border: Border.all(color: GarageColors.border),
            ),
            child: Column(
              children: [
                Row(
                  children: [
                    const Expanded(
                      child: Text(
                        'Balance de frenado',
                        style: TextStyle(
                          color: GarageColors.textPrimary,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                    Text(
                      '${_brakeBalance.round()}%',
                      style: const TextStyle(
                        color: GarageColors.mercedesAccent,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ],
                ),
                Slider(
                  value: _brakeBalance,
                  min: 45,
                  max: 65,
                  divisions: 20,
                  label: '${_brakeBalance.round()}%',
                  activeColor: GarageColors.mercedesAccent,
                  onChanged: (value) {
                    setState(() {
                      _brakeBalance = value;
                    });
                  },
                ),
                const Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      '45%',
                      style: TextStyle(
                        color: GarageColors.textSecondary,
                        fontSize: 12,
                      ),
                    ),
                    Text(
                      '65%',
                      style: TextStyle(
                        color: GarageColors.textSecondary,
                        fontSize: 12,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 6. NIVEL DE CONFIANZA
          // =====================================
          const _ComponentDocumentation(
            title: '6. Selector de nivel',
            description: 'Flutter permite construir controles personalizados combinando widgets básicos. Aquí seleccionamos un nivel de confianza entre 1 y 5.',
          ),

          Row(
            children: List.generate(5, (index) {
              final level = index + 1;
              final selected = level <= _confidence;

              return Expanded(
                child: Padding(
                  padding: EdgeInsets.only(right: index < 4 ? 8 : 0),
                  child: InkWell(
                    borderRadius: BorderRadius.circular(12),
                    onTap: () {
                      setState(() {
                        _confidence = level;
                      });
                    },
                    child: Container(
                      height: 52,
                      alignment: Alignment.center,
                      decoration: BoxDecoration(
                        color: selected
                            ? GarageColors.mercedesAccent
                            : GarageColors.surface,
                        borderRadius: BorderRadius.circular(12),
                        border: Border.all(
                          color: selected
                              ? GarageColors.mercedesAccent
                              : GarageColors.border,
                        ),
                      ),
                      child: Text(
                        '$level',
                        style: TextStyle(
                          color: selected
                              ? Colors.white
                              : GarageColors.textPrimary,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                  ),
                ),
              );
            }),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 7. FILTER CHIP
          // =====================================
          const _ComponentDocumentation(
            title: '7. FilterChip',
            description: 'FilterChip representa opciones compactas que pueden utilizarse para filtrar o seleccionar características específicas.',
          ),

          Wrap(
            spacing: 10,
            runSpacing: 10,
            children: _weatherOptions.map((weather) {
              final selected = _selectedWeather == weather;

              return FilterChip(
                label: Text(weather),
                selected: selected,
                selectedColor: GarageColors.mercedes.withValues(alpha: 0.25),
                checkmarkColor: GarageColors.mercedesAccent,
                onSelected: (_) {
                  setState(() {
                    _selectedWeather = weather;
                  });
                },
              );
            }).toList(),
          ),

          const SizedBox(height: 24),

          // =====================================
          // RESUMEN
          // =====================================
          _buildSummary(),

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
                      'MERCEDES · SECCIÓN 03',
                      style: TextStyle(
                        color: GarageColors.mercedes,
                        fontSize: 12,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  SizedBox(height: 10),
                  Text(
                    'Selecciones',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 26,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  SizedBox(height: 8),
                  Text(
                    'Selecciona configuraciones y preferencias mediante diferentes controles de Material.',
                    style: TextStyle(color: Color(0xFFD5D8DF), fontSize: 14),
                  ),
                ],
              ),
            ),
            SizedBox(
              height: 5,
              child: ColoredBox(color: GarageColors.mercedes),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildSummary() {
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
            'CONFIGURACIÓN ACTUAL',
            style: TextStyle(
              color: GarageColors.mercedes,
              fontSize: 12,
              fontWeight: FontWeight.bold,
            ),
          ),

          const SizedBox(height: 12),

          _SummaryLine(label: 'Circuito', value: _selectedCircuit),

          _SummaryLine(label: 'Estrategia', value: _selectedStrategy),

          _SummaryLine(label: 'Clima', value: _selectedWeather),

          _SummaryLine(label: 'Balance', value: '${_brakeBalance.round()}%'),

          _SummaryLine(label: 'Confianza', value: '$_confidence / 5'),

          _SummaryLine(
            label: 'Automático',
            value: _automaticMode ? 'Sí' : 'No',
          ),

          const SizedBox(height: 8),

          const Text(
            'COMPUESTOS',
            style: TextStyle(
              color: Color(0xFFD5D8DF),
              fontSize: 11,
              fontWeight: FontWeight.bold,
            ),
          ),

          const SizedBox(height: 4),

          Text(
            _enabledTyres,
            style: const TextStyle(
              color: Colors.white,
              fontSize: 14,
              fontWeight: FontWeight.bold,
            ),
          ),
        ],
      ),
    );
  }
}

class _TyreCheckbox extends StatelessWidget {
  final String title;
  final String subtitle;
  final bool value;
  final ValueChanged<bool?> onChanged;

  const _TyreCheckbox({
    required this.title,
    required this.subtitle,
    required this.value,
    required this.onChanged,
  });

  @override
  Widget build(BuildContext context) {
    return CheckboxListTile(
      value: value,
      onChanged: onChanged,
      activeColor: GarageColors.mercedesAccent,
      contentPadding: EdgeInsets.zero,
      controlAffinity: ListTileControlAffinity.leading,
      title: Text(
        title,
        style: const TextStyle(
          color: GarageColors.textPrimary,
          fontWeight: FontWeight.bold,
        ),
      ),
      subtitle: Text(
        subtitle,
        style: const TextStyle(color: GarageColors.textSecondary, fontSize: 12),
      ),
    );
  }
}

class _SummaryLine extends StatelessWidget {
  final String label;
  final String value;

  const _SummaryLine({required this.label, required this.value});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 3),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Expanded(
            child: Text(
              '$label:',
              style: const TextStyle(color: Color(0xFFD5D8DF), fontSize: 13),
            ),
          ),
          Flexible(
            child: Text(
              value,
              textAlign: TextAlign.end,
              style: const TextStyle(
                color: Colors.white,
                fontSize: 13,
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
        ],
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
