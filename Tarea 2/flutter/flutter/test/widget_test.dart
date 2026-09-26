import 'package:flutter_test/flutter_test.dart';
import 'package:f1_ui_garage_flutter/main.dart';

void main() {
  testWidgets('F1 UI Garage starts correctly', (WidgetTester tester) async {
    await tester.pumpWidget(const F1UIGarageApp());

    expect(find.text('F1 UI GARAGE'), findsOneWidget);

    expect(find.text('Catálogo de componentes'), findsOneWidget);
  });
}
