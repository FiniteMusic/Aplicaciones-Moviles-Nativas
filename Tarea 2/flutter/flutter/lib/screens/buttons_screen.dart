import 'package:flutter/material.dart';

import '../theme/garage_theme.dart';

class ButtonsScreen extends StatefulWidget {
  const ButtonsScreen({super.key});

  @override
  State<ButtonsScreen> createState() => _ButtonsScreenState();
}

class _ButtonsScreenState extends State<ButtonsScreen> {
  bool _drsEnabled = false;
  bool _telemetryEnabled = false;
  bool _isLoading = false;

  String _status = 'Sistema de controles listo.';

  Future<void> _startLoading() async {
    if (_isLoading) return;

    setState(() {
      _isLoading = true;
      _status = 'Procesando estrategia...';
    });

    await Future.delayed(const Duration(milliseconds: 2500));

    if (!mounted) return;

    setState(() {
      _isLoading = false;
      _status = 'Estrategia procesada correctamente.';
    });
  }

  void _updateStatus(String message) {
    setState(() {
      _status = message;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: GarageColors.background,

      appBar: AppBar(title: const Text('Botones y controles')),

      floatingActionButton: FloatingActionButton(
        backgroundColor: GarageColors.mclaren,
        foregroundColor: Colors.black,
        onPressed: () {
          _updateStatus('Nueva acción rápida creada.');

          ScaffoldMessenger.of(context).showSnackBar(
            const SnackBar(content: Text('FloatingActionButton presionado')),
          );
        },
        child: const Text(
          '+',
          style: TextStyle(fontSize: 28, fontWeight: FontWeight.bold),
        ),
      ),

      body: ListView(
        padding: const EdgeInsets.all(20),
        children: [
          _buildHeader(),

          const SizedBox(height: 24),

          // =====================================
          // 1. FILLED BUTTON
          // =====================================
          const _ComponentDocumentation(
            title: '1. FilledButton',
            description: 'FilledButton representa una acción principal de alta importancia. En este ejemplo inicia una simulación de carrera.',
          ),

          SizedBox(
            width: double.infinity,
            child: FilledButton(
              onPressed: () {
                _updateStatus('Carrera iniciada.');
              },
              style: FilledButton.styleFrom(
                backgroundColor: GarageColors.mclarenAccent,
                padding: const EdgeInsets.symmetric(vertical: 16),
              ),
              child: const Text('Iniciar carrera'),
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 2. OUTLINED BUTTON
          // =====================================
          const _ComponentDocumentation(
            title: '2. OutlinedButton',
            description: 'OutlinedButton representa una acción secundaria. Conserva menor énfasis visual que el botón principal.',
          ),

          SizedBox(
            width: double.infinity,
            child: OutlinedButton(
              onPressed: () {
                _updateStatus('Clasificación iniciada.');
              },
              style: OutlinedButton.styleFrom(
                foregroundColor: GarageColors.mclarenAccent,
                side: const BorderSide(color: GarageColors.mclarenAccent),
                padding: const EdgeInsets.symmetric(vertical: 16),
              ),
              child: const Text('Iniciar clasificación'),
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 3. ICON BUTTON
          // =====================================
          const _ComponentDocumentation(
            title: '3. IconButton',
            description: 'IconButton ejecuta una acción mediante un icono compacto. Es adecuado para operaciones fácilmente reconocibles.',
          ),

          Align(
            alignment: Alignment.centerLeft,
            child: IconButton.filledTonal(
              onPressed: () {
                _updateStatus('Comunicación por radio activada.');
              },
              icon: const Icon(Icons.radio),
              tooltip: 'Radio',
              color: GarageColors.mclarenAccent,
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 4. FLOATING ACTION BUTTON
          // =====================================
          const _ComponentDocumentation(
            title: '4. FloatingActionButton',
            description: 'FloatingActionButton destaca una acción importante y permanece flotando sobre el contenido de la pantalla.',
          ),

          Container(
            padding: const EdgeInsets.all(16),
            decoration: BoxDecoration(
              color: GarageColors.surface,
              borderRadius: BorderRadius.circular(16),
              border: Border.all(color: GarageColors.border),
            ),
            child: const Text(
              'Utiliza el botón flotante + ubicado en la esquina inferior derecha.',
              style: TextStyle(color: GarageColors.textSecondary, fontSize: 14),
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 5. TOGGLE
          // =====================================
          const _ComponentDocumentation(
            title: '5. Botón de estado',
            description: 'Un botón también puede representar un estado binario. Aquí controlamos manualmente la activación del DRS.',
          ),

          SizedBox(
            width: double.infinity,
            child: FilledButton(
              onPressed: () {
                setState(() {
                  _drsEnabled = !_drsEnabled;

                  _status = _drsEnabled ? 'DRS activado.' : 'DRS desactivado.';
                });
              },
              style: FilledButton.styleFrom(
                backgroundColor: _drsEnabled
                    ? GarageColors.mclarenAccent
                    : GarageColors.header,
                padding: const EdgeInsets.symmetric(vertical: 16),
              ),
              child: Text(_drsEnabled ? 'DRS ACTIVADO' : 'DRS DESACTIVADO'),
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 6. SWITCH
          // =====================================
          const _ComponentDocumentation(
            title: '6. Switch',
            description: 'Switch permite activar o desactivar una opción de forma inmediata. Su estado se representa mediante un valor booleano.',
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
                        'Telemetría',
                        style: TextStyle(
                          color: GarageColors.textPrimary,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      SizedBox(height: 3),
                      Text(
                        'Transmisión de datos en tiempo real',
                        style: TextStyle(
                          color: GarageColors.textSecondary,
                          fontSize: 12,
                        ),
                      ),
                    ],
                  ),
                ),

                Switch(
                  value: _telemetryEnabled,
                  activeThumbColor: GarageColors.mclarenAccent,
                  onChanged: (value) {
                    setState(() {
                      _telemetryEnabled = value;

                      _status = value
                          ? 'Telemetría activada.'
                          : 'Telemetría desactivada.';
                    });
                  },
                ),
              ],
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 7. LOADING BUTTON
          // =====================================
          const _ComponentDocumentation(
            title: '7. Botón con carga',
            description: 'El estado del botón puede cambiar mientras se ejecuta una operación asíncrona. CircularProgressIndicator comunica que el proceso continúa activo.',
          ),

          SizedBox(
            width: double.infinity,
            child: FilledButton(
              onPressed: _isLoading ? null : _startLoading,
              style: FilledButton.styleFrom(
                backgroundColor: GarageColors.mclarenAccent,
                disabledBackgroundColor: GarageColors.mclarenAccent.withValues(
                  alpha: 0.55,
                ),
                padding: const EdgeInsets.symmetric(vertical: 16),
              ),
              child: _isLoading
                  ? const Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        SizedBox(
                          width: 20,
                          height: 20,
                          child: CircularProgressIndicator(
                            strokeWidth: 2,
                            color: Colors.white,
                          ),
                        ),
                        SizedBox(width: 10),
                        Text('Procesando...'),
                      ],
                    )
                  : const Text('Procesar estrategia'),
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 8. DISABLED BUTTON
          // =====================================
          const _ComponentDocumentation(
            title: '8. Botón deshabilitado',
            description: 'Un botón con onPressed igual a null queda deshabilitado. Flutter modifica automáticamente su apariencia y evita la interacción.',
          ),

          const SizedBox(
            width: double.infinity,
            child: FilledButton(
              onPressed: null,
              child: Text('Acción no disponible'),
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // ESTADO
          // =====================================
          _buildStatusCard(),

          const SizedBox(height: 90),
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
                      'MCLAREN · SECCIÓN 02',
                      style: TextStyle(
                        color: GarageColors.mclaren,
                        fontSize: 12,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),

                  SizedBox(height: 10),

                  Text(
                    'Botones y controles',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 26,
                      fontWeight: FontWeight.bold,
                    ),
                  ),

                  SizedBox(height: 8),

                  Text(
                    'Ejecuta acciones y modifica estados mediante diferentes controles interactivos.',
                    style: TextStyle(color: Color(0xFFD5D8DF), fontSize: 14),
                  ),
                ],
              ),
            ),

            SizedBox(height: 5, child: ColoredBox(color: GarageColors.mclaren)),
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
            'ESTADO DEL SISTEMA',
            style: TextStyle(
              color: GarageColors.mclaren,
              fontSize: 12,
              fontWeight: FontWeight.bold,
            ),
          ),

          const SizedBox(height: 10),

          Text(
            _status,
            style: const TextStyle(color: Colors.white, fontSize: 14),
          ),

          const SizedBox(height: 12),

          _StatusLine(
            label: 'DRS',
            value: _drsEnabled ? 'Activado' : 'Desactivado',
          ),

          _StatusLine(
            label: 'Telemetría',
            value: _telemetryEnabled ? 'Activada' : 'Desactivada',
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
