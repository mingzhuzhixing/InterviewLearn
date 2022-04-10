abstract class AppData {}

abstract class NetData {
  ///OAuth2客户ID
  static const CLIENT_ID = 'iwekcHoIBZG2EbM8qqQz';

  ///OAuth2密钥
  static const CLIENT_SECRET = 'cnptJiTvoSs7qR8t5sJUspEbJwdxBvuR';

  ///	回调地址
  static const REDIRECT_URI = 'https://www.dongnaoedu.com/';

  static const HOST = 'https://www.oschina.net';
  static const OAUTH2_TOKEN = HOST + '/action/openapi/token';

  //申请授权
  static const OAUTH2_AUTHORIZE = HOST + '/action/oauth2/authorize';
}
