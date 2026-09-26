import 'package:flutter/material.dart';

import '../theme/garage_theme.dart';

class FeedbackScreen extends StatefulWidget {
  const FeedbackScreen({super.key});

  @override
  State<FeedbackScreen> createState() => _FeedbackScreenState();
}

class _FeedbackScreenState extends State<FeedbackScreen> {
  final _codeController = TextEditingController();

  double _raceProgress = 0;
  bool _progressRunning = false;
  bool _circularLoading = false;

  String? _codeError;

  String _status = 'Sistema de retroalimentación listo.';

  @override
  void dispose() {
    _codeController.dispose();
    super.dispose();
  }

  void _showSnackBar() {
    setState(() {
      _status = 'Configuración eliminada.';
    });

    ScaffoldMessenger.of(context).hideCurrentSnackBar();

    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: const Text('Configuración eliminada'),
        action: SnackBarAction(
          label: 'DESHACER',
          onPressed: () {
            setState(() {
              _status = 'Eliminación cancelada.';
            });
          },
        ),
      ),
    );
  }

  Future<void> _showPitDialog() async {
    final confirmed = await showDialog<bool>(
      context: context,
      builder: (dialogContext) {
        return AlertDialog(
          title: const Text('Confirmar parada'),
          content: const Text(
            '¿Deseas llamar al piloto a boxes en la siguiente vuelta?',
          ),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.pop(dialogContext, false);
              },
              child: const Text('CANCELAR'),
            ),
            TextButton(
              onPressed: () {
                Navigator.pop(dialogContext, true);
              },
              child: const Text(
                'CONFIRMAR',
                style: TextStyle(color: GarageColors.astonMartinAccent),
              ),
            ),
          ],
        );
      },
    );

    if (!mounted || confirmed == null) {
      return;
    }

    setState(() {
      _status = confirmed
          ? 'Parada en pits confirmada.'
          : 'Parada en pits cancelada.';
    });
  }

  Future<void> _startProgress() async {
    if (_progressRunning) {
      return;
    }

    setState(() {
      _progressRunning = true;
      _raceProgress = 0;
      _status = 'Simulación en progreso...';
    });

    for (int step = 1; step <= 100; step++) {
      await Future.delayed(const Duration(milliseconds: 30));

      if (!mounted) {
        return;
      }

      setState(() {
        _raceProgress = step / 100;
      });
    }

    if (!mounted) {
      return;
    }

    setState(() {
      _progressRunning = false;
      _status = 'Simulación completada.';
    });
  }

  Future<void> _synchronizeTelemetry() async {
    if (_circularLoading) {
      return;
    }

    setState(() {
      _circularLoading = true;
      _status = 'Sincronizando telemetría...';
    });

    await Future.delayed(const Duration(milliseconds: 2500));

    if (!mounted) {
      return;
    }

    setState(() {
      _circularLoading = false;
      _status = 'Telemetría sincronizada.';
    });
  }

  void _validateCode() {
    final code = _codeController.text.trim();

    if (code.toUpperCase() == 'BOX') {
      setState(() {
        _codeError = null;
        _status = 'Código BOX validado correctamente.';
      });
    } else {
      setState(() {
        _codeError = 'Código incorrecto. Escribe BOX.';
        _status = 'Error en el código de pits.';
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: GarageColors.background,
      appBar: AppBar(title: const Text('Retroalimentación')),
      body: ListView(
        padding: const EdgeInsets.all(20),
        children: [
          _buildHeader(),

          const SizedBox(height: 24),

          // =====================================
          // 1. SNACKBAR
          // =====================================
          const _ComponentDocumentation(
            title: '1. SnackBar',
            description: 'SnackBar muestra información temporal en la parte inferior de la interfaz y puede incluir una acción adicional.',
          ),

          SizedBox(
            width: double.infinity,
            child: FilledButton(
              onPressed: _showSnackBar,
              style: FilledButton.styleFrom(
                backgroundColor: GarageColors.astonMartinAccent,
                padding: const EdgeInsets.symmetric(vertical: 16),
              ),
              child: const Text('Mostrar SnackBar'),
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 2. ALERT DIALOG
          // =====================================
          const _ComponentDocumentation(
            title: '2. AlertDialog',
            description: 'AlertDialog solicita una decisión antes de realizar una acción importante. Puede incluir opciones de confirmación y cancelación.',
          ),

          SizedBox(
            width: double.infinity,
            child: FilledButton(
              onPressed: _showPitDialog,
              style: FilledButton.styleFrom(
                backgroundColor: GarageColors.astonMartinAccent,
                padding: const EdgeInsets.symmetric(vertical: 16),
              ),
              child: const Text('Solicitar parada en pits'),
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 3. PROGRESO DETERMINADO
          // =====================================
          const _ComponentDocumentation(
            title: '3. LinearProgressIndicator',
            description: 'LinearProgressIndicator representa el avance conocido de una operación mediante un valor comprendido entre 0 y 1.',
          ),

          Container(
            padding: const EdgeInsets.all(16),
            decoration: BoxDecoration(
              color: GarageColors.surface,
              borderRadius: BorderRadius.circular(16),
              border: Border.all(color: GarageColors.border),
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  'Simulación: ${(_raceProgress * 100).round()}%',
                  style: const TextStyle(
                    color: GarageColors.textPrimary,
                    fontWeight: FontWeight.bold,
                  ),
                ),

                const SizedBox(height: 12),

                LinearProgressIndicator(
                  value: _raceProgress,
                  minHeight: 8,
                  color: GarageColors.astonMartin,
                  backgroundColor: GarageColors.border,
                  borderRadius: BorderRadius.circular(10),
                ),

                const SizedBox(height: 14),

                SizedBox(
                  width: double.infinity,
                  child: FilledButton(
                    onPressed: _progressRunning ? null : _startProgress,
                    style: FilledButton.styleFrom(
                      backgroundColor: GarageColors.astonMartinAccent,
                    ),
                    child: Text(
                      _progressRunning ? 'Simulando...' : 'Iniciar simulación',
                    ),
                  ),
                ),
              ],
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 4. PROGRESO INDETERMINADO
          // =====================================
          const _ComponentDocumentation(
            title: '4. CircularProgressIndicator',
            description: 'El progreso indeterminado comunica que una operación está activa cuando todavía no se conoce cuánto falta para completarla.',
          ),

          SizedBox(
            width: double.infinity,
            child: FilledButton(
              onPressed: _circularLoading ? null : _synchronizeTelemetry,
              style: FilledButton.styleFrom(
                backgroundColor: GarageColors.astonMartinAccent,
                padding: const EdgeInsets.symmetric(vertical: 16),
              ),
              child: _circularLoading
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
                        Text('Sincronizando...'),
                      ],
                    )
                  : const Text('Sincronizar telemetría'),
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 5. VALIDACIÓN VISUAL
          // =====================================
          const _ComponentDocumentation(
            title: '5. Error de validación',
            description: 'InputDecoration.errorText permite mostrar visualmente un error asociado a un TextField y explicar qué información debe corregirse.',
          ),

          TextField(
            controller: _codeController,
            textCapitalization: TextCapitalization.characters,
            onChanged: (_) {
              if (_codeError != null) {
                setState(() {
                  _codeError = null;
                });
              }
            },
            decoration: InputDecoration(
              labelText: 'Código de pits',
              hintText: 'Escribe BOX',
              errorText: _codeError,
            ),
          ),

          const SizedBox(height: 12),

          SizedBox(
            width: double.infinity,
            child: FilledButton(
              onPressed: _validateCode,
              style: FilledButton.styleFrom(
                backgroundColor: GarageColors.astonMartinAccent,
                padding: const EdgeInsets.symmetric(vertical: 16),
              ),
              child: const Text('Validar código'),
            ),
          ),

          const SizedBox(height: 24),

          // =====================================
          // 6. ESTADO VISUAL
          // =====================================
          const _ComponentDocumentation(
            title: '6. Estado visual',
            description: 'setState actualiza los datos asociados al widget y provoca la reconstrucción de las partes de la interfaz que dependen de ellos.',
          ),

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
                      'ASTON MARTIN · SECCIÓN 05',
                      style: TextStyle(
                        color: GarageColors.astonMartin,
                        fontSize: 12,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),

                  SizedBox(height: 10),

                  Text(
                    'Retroalimentación',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 26,
                      fontWeight: FontWeight.bold,
                    ),
                  ),

                  SizedBox(height: 8),

                  Text(
                    'Comunica resultados, advertencias, errores y procesos mediante diferentes mecanismos de feedback.',
                    style: TextStyle(color: Color(0xFFD5D8DF), fontSize: 14),
                  ),
                ],
              ),
            ),

            SizedBox(
              height: 5,
              child: ColoredBox(color: GarageColors.astonMartin),
            ),
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
              color: GarageColors.astonMartin,
              fontSize: 12,
              fontWeight: FontWeight.bold,
            ),
          ),

          const SizedBox(height: 10),

          Text(
            _status,
            style: const TextStyle(color: Colors.white, fontSize: 14),
          ),

          const SizedBox(height: 10),

          Text(
            'Progreso: ${(_raceProgress * 100).round()}%',
            style: const TextStyle(color: Color(0xFFD5D8DF), fontSize: 13),
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
