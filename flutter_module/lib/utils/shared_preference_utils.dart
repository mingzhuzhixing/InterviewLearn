import 'package:shared_preferences/shared_preferences.dart';

class SharePreferenceUtils {
  //{"access_token":"d98fea26-7308-4a1d-adb1-ff7e5b044625",
  // "refresh_token":"a581c1e0-7729-44c1-84b6-a652fdc37e7c",
  // "uid":4544532,"token_type":"bearer","expires_in":512280}
  static const ACCESS_TOKEN = 'access_token';
  static const REFRESH_TOKEN = 'refresh_token';
  static const UID = 'uid';
  static const TOKEN_TYPE = 'token_type';
  static const EXPIRES_IN = 'expires_in';
  static const IS_LOGIN = 'isLogin';

  //存储返回数据
  static Future<void> saveData(Map<String, dynamic> values) async {
    if (values == null) {
      throw '数据为空';
    }
    SharedPreferences sp = await SharedPreferences.getInstance();
    sp
      ..setString(ACCESS_TOKEN, values[ACCESS_TOKEN])
      ..setString(REFRESH_TOKEN, values[REFRESH_TOKEN])
      ..setInt(UID, values[UID])
      ..setString(TOKEN_TYPE, values[TOKEN_TYPE])
      ..setInt(EXPIRES_IN, values[EXPIRES_IN])
      ..setBool(IS_LOGIN, true);
  }

  //判断是否登录
  static Future<bool> isLogin() async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    bool? result = sp.getBool(IS_LOGIN);
    return result ?? false;
  }
}
