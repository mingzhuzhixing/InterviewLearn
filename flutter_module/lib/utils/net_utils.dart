import 'package:http/http.dart' as http;

class NetUtils {
  ///get请求
  static Future<String> get(String url, Map<String, dynamic> params) async {
    if (url.isEmpty) {
      throw "地址不能为空";
    }
    StringBuffer stringBuffer = StringBuffer("?");
    params.forEach((key, value) {
      stringBuffer.write("$key=$value");
    });
    stringBuffer.toString().substring(0, stringBuffer.length - 1);
    url += stringBuffer.toString();
    http.Response response = await http.get(Uri.http(url));
    return response.body;
  }

  ///post请求
  static Future<String> post(String url, Map<String, dynamic> params) async {
    if (url.isEmpty) {
      throw "地址不能为空";
    }
    http.Response response = await http.post(Uri.http(url), body: params);
    return response.body;
  }
}
