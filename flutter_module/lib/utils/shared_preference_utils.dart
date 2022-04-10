import 'package:shared_preferences/shared_preferences.dart';

import '../models/user.dart';

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

  //用户信息字段
  static const String SP_USER_GENDER = 'gender';
  static const String SP_USER_NAME = 'name';
  static const String SP_USER_LOCATION = 'location';
  static const String SP_USER_ID = 'id';
  static const String SP_USER_AVATAR = 'avatar';
  static const String SP_USER_EMAIL = 'email';
  static const String SP_USER_URL = 'url';

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

  static Future<void> clearLoginInfo() async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    sp
      ..setString(ACCESS_TOKEN, '')
      ..setString(REFRESH_TOKEN, '')
      ..setInt(UID, -1)
      ..setString(TOKEN_TYPE, '')
      ..setInt(EXPIRES_IN, -1)
      ..setBool(IS_LOGIN, false);
  }

  //判断是否登录
  static Future<bool> isLogin() async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    bool? result = sp.getBool(IS_LOGIN);
    return result ?? false;
  }

  //判断是否登录
  static Future<String> getToken() async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    return sp.getString(ACCESS_TOKEN) ?? "";
  }

  //{"gender":"male","name":"Damon2019","location":"湖南 长沙","id":2006874,"avatar":"https://oscimg.oschina.net/oscnet/up-21zvuaor7bbvi8h2a4g93iv9vve2wrnz.jpg!/both/50x50?t=1554975223000","email":"3262663349@qq.com","url":"https://my.oschina.net/damon007"}
  static saveUserInfo(Map<String, dynamic> map) async {
    if (map != null && map.isNotEmpty) {
      SharedPreferences sp = await SharedPreferences.getInstance();
      String gender = map[SP_USER_GENDER];
      String name = map[SP_USER_NAME];
      String location = map[SP_USER_LOCATION];
      int id = map[SP_USER_ID];
      String avatar = map[SP_USER_AVATAR];
      String email = map[SP_USER_EMAIL];
      String url = map[SP_USER_URL];

      sp
        ..setString(SP_USER_GENDER, gender)
        ..setString(SP_USER_NAME, name)
        ..setString(SP_USER_LOCATION, location)
        ..setInt(SP_USER_ID, id)
        ..setString(SP_USER_AVATAR, avatar)
        ..setString(SP_USER_EMAIL, email)
        ..setString(SP_USER_URL, url);

      User user = User(
          id: id,
          name: name,
          gender: gender,
          avatar: avatar,
          email: email,
          location: location,
          url: url);
      return user;
    }
    return null;
  }

  //获取用户信息
  static Future<User> getUserInfo() async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    bool? isLogin = sp.getBool(IS_LOGIN);
    User user = User();
    if (isLogin == null || !isLogin) {
      return user;
    }
    user.gender = sp.getString(SP_USER_GENDER);
    user.name = sp.getString(SP_USER_NAME);
    user.location = sp.getString(SP_USER_LOCATION);
    user.id = sp.getInt(SP_USER_ID);
    user.avatar = sp.getString(SP_USER_AVATAR);
    user.email = sp.getString(SP_USER_EMAIL);
    user.url = sp.getString(SP_USER_URL);
    return user;
  }
}
