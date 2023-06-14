import 'package:event_bus/event_bus.dart';

class EventBusUtils {
  final EventBus _eventBus = EventBus();

  EventBusUtils._privateConstructor();

  static final EventBusUtils _busManager = EventBusUtils._privateConstructor();

  factory EventBusUtils() {
    return _busManager;
  }

  static EventBusUtils get instance {
    return _busManager;
  }

  static EventBus get eventBus {
    return _busManager._eventBus;
  }

  static void onDestroy() {
    eventBus.destroy();
  }

  // EventBusUtils._internal();
  // factory EventBusUtils() => _instance;
  // static late final EventBusUtils _instance = EventBusUtils._internal();
}