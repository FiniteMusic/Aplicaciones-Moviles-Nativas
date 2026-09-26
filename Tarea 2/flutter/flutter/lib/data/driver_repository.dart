import 'package:flutter/foundation.dart';

import 'driver.dart';

class DriverRepository {
  DriverRepository._();

  static final DriverRepository instance = DriverRepository._();

  final ValueNotifier<List<Driver>> drivers = ValueNotifier<List<Driver>>([]);

  bool addDriver(Driver driver) {
    final duplicatedNumber = drivers.value.any(
      (currentDriver) => currentDriver.number == driver.number,
    );

    if (duplicatedNumber) {
      return false;
    }

    drivers.value = [...drivers.value, driver];

    return true;
  }

  void removeDriver(Driver driver) {
    drivers.value = drivers.value
        .where((currentDriver) => currentDriver.number != driver.number)
        .toList();
  }

  void clear() {
    drivers.value = [];
  }
}
