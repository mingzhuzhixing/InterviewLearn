import 'package:flutter/material.dart';
import 'package:flutter_module/widget/common_app_bar.dart';

/**
 * flutter_webview
 *
 * flutter_inappwebview需要依赖androidx和swift。
 *
 * https://blog.csdn.net/u013038616/article/details/119034807
 */
class WebviewWidgetPage extends StatefulWidget {
  final String url = "http:wwww.baidu.com";
  final String title = "百度";

  WebviewWidgetPage({Key? key}) : super(key: key);

  @override
  State<WebviewWidgetPage> createState() => _WebviewWidgetPageState();
}

class _WebviewWidgetPageState extends State<WebviewWidgetPage> {
  final String TAG = "webview";
  final GlobalKey webViewKey = GlobalKey();
  // InAppWebViewGroupOptions options = InAppWebViewGroupOptions(
  //   //夸平台配置
  //   crossPlatform: InAppWebViewOptions(
  //     //用于配置Android和iOS通用的接口功能。
  //     useShouldOverrideUrlLoading: true, //加载url拦截功能
  //     useShouldInterceptAjaxRequest: true, //ajax请求拦截
  //     useOnLoadResource: true, //资源加载回调
  //     allowFileAccessFromFileURLs: true, //资源加载
  //     mediaPlaybackRequiresUserGesture: false, //多媒体控制
  //   ),
  //
  //   //Android平台配置
  //   android: AndroidInAppWebViewOptions(
  //     //仅用于配置Android特有的接口功能。
  //     useHybridComposition: true,
  //   ),
  //
  //   //iOS平台配置
  //   ios: IOSInAppWebViewOptions(
  //     //仅用于配置iOS特有的接口功能。
  //     allowsInlineMediaPlayback: true,
  //   ),
  // );

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(context, "flutter_webview"),
      // body: InAppWebView(
      //   key: webViewKey,
      //   initialUrlRequest: URLRequest(url: Uri.parse(widget.url)),
      //   initialOptions: options, // 开关配置项
      //   onWebViewCreated: (InAppWebViewController controller) {
      //     print("$TAG onWebViewCreated");
      //   },
      //   onLoadStart: (InAppWebViewController controller, Uri? url) {
      //     print("$TAG onLoadStart url:$url");
      //   },
      //   onLoadStop: (InAppWebViewController controller, Uri? url) {
      //     print("$TAG onLoadStop url:$url");
      //   },
      //   onLoadError: (InAppWebViewController controller, Uri? url, int code,
      //       String message) {
      //     print("$TAG onLoadError url:$url code:$code message:$message");
      //   },
      //   onLoadHttpError: (InAppWebViewController controller, Uri? url,
      //       int statusCode, String description) {
      //     print(
      //         "$TAG onLoadHttpError url:$url statusCode:$statusCode description:$description");
      //   },
      //   onConsoleMessage:
      //       (InAppWebViewController controller, ConsoleMessage consoleMessage) {
      //     print("$TAG onConsoleMessage consoleMessage:$consoleMessage");
      //   },
      //   onProgressChanged: (InAppWebViewController controller, int progress) {
      //     print("$TAG onProgressChanged progress:$progress");
      //   },
      //   shouldOverrideUrlLoading: (InAppWebViewController controller,
      //       NavigationAction navigationAction) async {
      //     print(
      //         "$TAG shouldOverrideUrlLoading navigationAction:$navigationAction");
      //     return null;
      //   },
      //   // 资源加载监听器
      //   onLoadResource:
      //       (InAppWebViewController controller, LoadedResource resource) {
      //     print("$TAG onLoadResource resource:$resource");
      //   },
      //   // 滚动监听器
      //   onScrollChanged: (InAppWebViewController controller, int x, int y) {
      //     print("$TAG onScrollChanged x:$x  y:$y");
      //   },
      //   onLoadResourceCustomScheme:
      //       (InAppWebViewController controller, Uri url) async {
      //     print("$TAG onLoadResourceCustomScheme url:$url");
      //     return null;
      //   },
      //   onCreateWindow: (InAppWebViewController controller,
      //       CreateWindowAction createWindowAction) async {
      //     print("$TAG onCreateWindow");
      //     return true;
      //   },
      //   onCloseWindow: (InAppWebViewController controller) {
      //     print("$TAG onCloseWindow");
      //   },
      //   // 过量滚动监听器
      //   onOverScrolled: (InAppWebViewController controller, int x, int y,
      //       bool clampedX, bool clampedY) async {
      //     print(
      //         "$TAG onOverScrolled x:$x  y:$y clampedX:$clampedX clampedY:$clampedY");
      //   },
      //
      //   //Android特有功能，请求加载链接，可以拦截资源加载，并替换为本地Web离线包内的资源
      //   androidShouldInterceptRequest: (InAppWebViewController controller,
      //       WebResourceRequest request) async {
      //     print("$TAG androidShouldInterceptRequest request:$request");
      //     return null;
      //   },
      //
      //   //iOS特有功能
      //   iosOnNavigationResponse: (InAppWebViewController controller,
      //       IOSWKNavigationResponse navigationResponse) async {
      //     return null;
      //   },
      // ),
    );
  }
}
