import 'package:flutter/services.dart';

class MethodChannelUtils {
  static const nativeChannel =
      MethodChannel("com.v.interviewlearn.flutter_app/native");
  static const EventChannel eventChannel =
      EventChannel('com.v.interviewlearn.flutter_app/nativeEvent');
}
