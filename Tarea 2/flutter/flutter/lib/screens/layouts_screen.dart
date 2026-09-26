import 'package:flutter/material.dart';

import '../theme/garage_theme.dart';

class LayoutsScreen extends StatefulWidget {
  const LayoutsScreen({super.key});

  @override
  State<LayoutsScreen> createState() => _LayoutsScreenState();
}

class _LayoutsScreenState extends State<LayoutsScreen> {
  bool _horizontalLayout = true;
  bool _liveBadgeVisible = true;

  final List<String> _circuits = const [
    'Monza',
    'Silverstone',
    'Spa',
    'Suzuka',
    'Interlagos',
    'Mónaco',
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: GarageColors.background,
      appBar: AppBar(title: const Text('Layouts')),
      body: ListView(
        padding: const EdgeInsets.all(20),
        children: [
          _buildHeader(),

          const SizedBox(height: 24),

          // =====================================
          // 1. COLUMN
          // =====================================
          const _ComponentDocumentation(
            title: '1. Column',
            description: 'Column distribuye sus widgets hijos verticalmente. Es equivalente a organizar componentes uno debajo de otro.',
          ),

          Container(
            width: double.infinity,
            padding: const EdgeInsets.all(16),
            decoration: _cardDecoration(),
            child: const Column(
              children: [
                _LayoutBlock(text: 'SECTOR 1'),
                SizedBox(height: 10),
                _LayoutBlock(text: 'SECTOR 2'),
                SizedBox(height: 10),
                _LayoutBlock(text: 'SECTOR 3'),
              ],
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 2. ROW
          // =====================================
          const _ComponentDocumentation(
            title: '2. Row',
            description: 'Row distribuye sus widgets horizontalmente. Expanded permite repartir el espacio disponible entre los elementos.',
          ),

          Container(
            padding: const EdgeInsets.all(16),
            decoration: _cardDecoration(),
            child: const Row(
              children: [
                Expanded(
                  child: _TelemetryBlock(label: 'VEL', value: '312'),
                ),
                SizedBox(width: 8),
                Expanded(
                  child: _TelemetryBlock(label: 'RPM', value: '11.8K'),
                ),
                SizedBox(width: 8),
                Expanded(
                  child: _TelemetryBlock(label: 'MARCHA', value: '8'),
                ),
              ],
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 3. ORIENTACIÓN DINÁMICA
          // =====================================
          const _ComponentDocumentation(
            title: '3. Row y Column dinámicos',
            description: 'Flutter permite cambiar la composición de la interfaz según el estado. El mismo contenido puede organizarse horizontal o verticalmente.',
          ),

          SizedBox(
            width: double.infinity,
            child: OutlinedButton(
              onPressed: () {
                setState(() {
                  _horizontalLayout = !_horizontalLayout;
                });
              },
              style: OutlinedButton.styleFrom(
                foregroundColor: GarageColors.alpineAccent,
                side: const BorderSide(color: GarageColors.alpineAccent),
                padding: const EdgeInsets.symmetric(vertical: 14),
              ),
              child: Text(
                _horizontalLayout ? 'Cambiar a Column' : 'Cambiar a Row',
              ),
            ),
          ),

          const SizedBox(height: 12),

          Container(
            padding: const EdgeInsets.all(16),
            decoration: _cardDecoration(),
            child: _horizontalLayout
                ? const Row(
                    children: [
                      Expanded(child: _DynamicBlock(text: 'A')),
                      SizedBox(width: 8),
                      Expanded(child: _DynamicBlock(text: 'B')),
                      SizedBox(width: 8),
                      Expanded(child: _DynamicBlock(text: 'C')),
                    ],
                  )
                : const Column(
                    children: [
                      _DynamicBlock(text: 'A'),
                      SizedBox(height: 8),
                      _DynamicBlock(text: 'B'),
                      SizedBox(height: 8),
                      _DynamicBlock(text: 'C'),
                    ],
                  ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 4. STACK
          // =====================================
          const _ComponentDocumentation(
            title: '4. Stack',
            description: 'Stack permite superponer widgets. Positioned coloca elementos en posiciones específicas dentro del área disponible.',
          ),

          SizedBox(
            width: double.infinity,
            child: OutlinedButton(
              onPressed: () {
                setState(() {
                  _liveBadgeVisible = !_liveBadgeVisible;
                });
              },
              style: OutlinedButton.styleFrom(
                foregroundColor: GarageColors.alpineAccent,
                side: const BorderSide(color: GarageColors.alpineAccent),
              ),
              child: Text(
                _liveBadgeVisible
                    ? 'Ocultar indicador EN VIVO'
                    : 'Mostrar indicador EN VIVO',
              ),
            ),
          ),

          const SizedBox(height: 12),

          Container(
            height: 190,
            decoration: BoxDecoration(
              color: GarageColors.header,
              borderRadius: BorderRadius.circular(18),
            ),
            child: Stack(
              children: [
                const Center(
                  child: Column(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      Icon(
                        Icons.sports_motorsports,
                        color: Colors.white,
                        size: 52,
                      ),
                      SizedBox(height: 10),
                      Text(
                        'CÁMARA ONBOARD',
                        style: TextStyle(
                          color: Colors.white,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      SizedBox(height: 4),
                      Text(
                        'Alpine Racing',
                        style: TextStyle(color: Color(0xFFD5D8DF)),
                      ),
                    ],
                  ),
                ),

                if (_liveBadgeVisible)
                  Positioned(
                    top: 14,
                    right: 14,
                    child: Container(
                      padding: const EdgeInsets.symmetric(
                        horizontal: 10,
                        vertical: 6,
                      ),
                      decoration: BoxDecoration(
                        color: GarageColors.alpine,
                        borderRadius: BorderRadius.circular(20),
                      ),
                      child: const Text(
                        'EN VIVO',
                        style: TextStyle(
                          color: Colors.white,
                          fontSize: 11,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                  ),

                const Positioned(
                  left: 14,
                  bottom: 14,
                  child: Text(
                    'Vuelta 42 / 57',
                    style: TextStyle(color: Colors.white, fontSize: 12),
                  ),
                ),
              ],
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 5. GRID
          // =====================================
          const _ComponentDocumentation(
            title: '5. GridView',
            description: 'GridView organiza widgets en filas y columnas. shrinkWrap permite integrarlo dentro de otro contenedor desplazable.',
          ),

          GridView.count(
            shrinkWrap: true,
            physics: const NeverScrollableScrollPhysics(),
            crossAxisCount: 2,
            crossAxisSpacing: 10,
            mainAxisSpacing: 10,
            childAspectRatio: 1.8,
            children: const [
              _GridTelemetry(title: 'VELOCIDAD', value: '312 km/h'),
              _GridTelemetry(title: 'RPM', value: '11,842'),
              _GridTelemetry(title: 'NEUMÁTICO', value: 'MEDIO'),
              _GridTelemetry(title: 'COMBUSTIBLE', value: '38%'),
            ],
          ),

          const SizedBox(height: 24),

          // =====================================
          // 6. SCROLL VERTICAL
          // =====================================
          const _ComponentDocumentation(
            title: '6. Scroll vertical',
            description: 'ListView proporciona desplazamiento vertical al contenido de esta pantalla. Permite mostrar una interfaz mayor que el alto disponible.',
          ),

          Container(
            padding: const EdgeInsets.all(16),
            decoration: _cardDecoration(),
            child: const Text(
              'Esta pantalla completa utiliza ListView como contenedor principal. Desplázate hacia arriba y abajo para recorrer todos los ejemplos de layouts.',
              style: TextStyle(color: GarageColors.textSecondary, fontSize: 14),
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 7. LISTVIEW HORIZONTAL
          // =====================================
          const _ComponentDocumentation(
            title: '7. ListView horizontal',
            description: 'ListView con scrollDirection horizontal permite recorrer elementos lateralmente sin modificar el flujo vertical principal.',
          ),

          SizedBox(
            height: 90,
            child: ListView.separated(
              scrollDirection: Axis.horizontal,
              itemCount: _circuits.length,
              separatorBuilder: (_, _) => const SizedBox(width: 10),
              itemBuilder: (context, index) {
                return Container(
                  width: 145,
                  alignment: Alignment.center,
                  padding: const EdgeInsets.all(12),
                  decoration: BoxDecoration(
                    color: GarageColors.surface,
                    borderRadius: BorderRadius.circular(14),
                    border: Border.all(color: GarageColors.border),
                  ),
                  child: Text(
                    _circuits[index],
                    textAlign: TextAlign.center,
                    style: const TextStyle(
                      color: GarageColors.textPrimary,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                );
              },
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 8. SINGLECHILDSCROLLVIEW
          // =====================================
          const _ComponentDocumentation(
            title: '8. SingleChildScrollView horizontal',
            description: 'SingleChildScrollView permite desplazar un único árbol de widgets cuando su contenido excede el espacio disponible.',
          ),

          Container(
            padding: const EdgeInsets.symmetric(vertical: 14),
            decoration: _cardDecoration(),
            child: SingleChildScrollView(
              scrollDirection: Axis.horizontal,
              padding: const EdgeInsets.symmetric(horizontal: 14),
              child: Row(
                children: List.generate(12, (index) {
                  return Padding(
                    padding: const EdgeInsets.only(right: 8),
                    child: Container(
                      width: 74,
                      height: 60,
                      alignment: Alignment.center,
                      decoration: BoxDecoration(
                        color: GarageColors.alpineAccent,
                        borderRadius: BorderRadius.circular(12),
                      ),
                      child: Text(
                        'V${index + 1}',
                        style: const TextStyle(
                          color: Colors.white,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                  );
                }),
              ),
            ),
          ),

          const SizedBox(height: 24),

          _buildStatusCard(),

          const SizedBox(height: 20),
        ],
      ),
    );
  }

  BoxDecoration _cardDecoration() {
    return BoxDecoration(
      color: GarageColors.surface,
      borderRadius: BorderRadius.circular(16),
      border: Border.all(color: GarageColors.border),
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
                      'ALPINE · SECCIÓN 06',
                      style: TextStyle(
                        color: GarageColors.alpine,
                        fontSize: 12,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  SizedBox(height: 10),
                  Text(
                    'Layouts',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 26,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  SizedBox(height: 8),
                  Text(
                    'Organiza, posiciona y desplaza componentes mediante los principales widgets de layout de Flutter.',
                    style: TextStyle(color: Color(0xFFD5D8DF), fontSize: 14),
                  ),
                ],
              ),
            ),
            SizedBox(height: 5, child: ColoredBox(color: GarageColors.alpine)),
          ],
        ),
      ),
    );
  }

  Widget _buildStatusCard() {
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
            'ESTADO DEL LAYOUT',
            style: TextStyle(
              color: GarageColors.alpine,
              fontSize: 12,
              fontWeight: FontWeight.bold,
            ),
          ),
          const SizedBox(height: 10),
          _StatusLine(
            label: 'Orientación',
            value: _horizontalLayout ? 'Row' : 'Column',
          ),
          _StatusLine(
            label: 'Indicador',
            value: _liveBadgeVisible ? 'Visible' : 'Oculto',
          ),
          const _StatusLine(label: 'Scroll principal', value: 'Vertical'),
        ],
      ),
    );
  }
}

class _LayoutBlock extends StatelessWidget {
  final String text;

  const _LayoutBlock({required this.text});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: 54,
      alignment: Alignment.center,
      decoration: BoxDecoration(
        color: GarageColors.alpineAccent,
        borderRadius: BorderRadius.circular(12),
      ),
      child: Text(
        text,
        style: const TextStyle(
          color: Colors.white,
          fontWeight: FontWeight.bold,
        ),
      ),
    );
  }
}

class _TelemetryBlock extends StatelessWidget {
  final String label;
  final String value;

  const _TelemetryBlock({required this.label, required this.value});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 16, horizontal: 6),
      decoration: BoxDecoration(
        color: GarageColors.alpineAccent,
        borderRadius: BorderRadius.circular(12),
      ),
      child: Column(
        children: [
          Text(
            value,
            style: const TextStyle(
              color: Colors.white,
              fontSize: 18,
              fontWeight: FontWeight.bold,
            ),
          ),
          const SizedBox(height: 3),
          Text(
            label,
            style: const TextStyle(color: Color(0xFFD5D8DF), fontSize: 10),
          ),
        ],
      ),
    );
  }
}

class _DynamicBlock extends StatelessWidget {
  final String text;

  const _DynamicBlock({required this.text});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      height: 60,
      alignment: Alignment.center,
      decoration: BoxDecoration(
        color: GarageColors.alpineAccent,
        borderRadius: BorderRadius.circular(12),
      ),
      child: Text(
        text,
        style: const TextStyle(
          color: Colors.white,
          fontSize: 18,
          fontWeight: FontWeight.bold,
        ),
      ),
    );
  }
}

class _GridTelemetry extends StatelessWidget {
  final String title;
  final String value;

  const _GridTelemetry({required this.title, required this.value});

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(14),
      decoration: BoxDecoration(
        color: GarageColors.surface,
        borderRadius: BorderRadius.circular(14),
        border: Border.all(color: GarageColors.border),
      ),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text(
            value,
            textAlign: TextAlign.center,
            style: const TextStyle(
              color: GarageColors.alpineAccent,
              fontSize: 17,
              fontWeight: FontWeight.bold,
            ),
          ),
          const SizedBox(height: 5),
          Text(
            title,
            textAlign: TextAlign.center,
            style: const TextStyle(
              color: GarageColors.textSecondary,
              fontSize: 10,
            ),
          ),
        ],
      ),
    );
  }
}

class _StatusLine extends StatelessWidget {
  final String label;
  final String value;

  const _StatusLine({required this.label, required this.value});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 3),
      child: Row(
        children: [
          Expanded(
            child: Text(
              '$label:',
              style: const TextStyle(color: Color(0xFFD5D8DF), fontSize: 13),
            ),
          ),
          Text(
            value,
            style: const TextStyle(
              color: Colors.white,
              fontSize: 13,
              fontWeight: FontWeight.bold,
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
