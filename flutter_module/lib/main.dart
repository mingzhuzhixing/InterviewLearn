// 必须在dart文件的第一行,可以加在任何dart文件中
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';
import 'package:flutter_module/oschinabody.dart';
import 'package:flutter_module/page/splash_page.dart';
import 'package:flutter_module/utils/method_channel_utils.dart';
import 'package:flutter_module/utils/sp_utils.dart';
import 'package:flutter_module/utils/ys_global.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:power_image/power_image.dart';

bool runType = false;

///创建一个自定义的Binding，继承和with的关系如下，里面什么都不用写
class CustomFlutterBinding extends WidgetsFlutterBinding with BoostFlutterBinding {
  @override
  ImageCache createImageCache() {
    return ImageCacheExt();
  }
}

void main() {
  // WidgetsFlutterBinding.ensureInitialized();
  print("zm1234 进入flutter main(): $runType");
  ///这里的CustomFlutterBinding调用务必不可缺少，用于控制Boost状态的resume和pause
  if (runType) {
    CustomFlutterBinding();
    PowerImageLoader.instance.setup(PowerImageSetupOptions(renderingTypeTexture,
        errorCallbackSamplingRate: 1.0,
        errorCallback: (PowerImageLoadException exception) {}));
    //先将 onError 保存起来
    var onError = FlutterError.onError;
    FlutterError.onError = (FlutterErrorDetails details) {
      //调用默认的onError
      onError?.call(details);
      print(details.stack.toString());
    };
    runApp(FlutterBoostMyApp());
  } else {
    runApp(SeparateMyApp());
  }
  SpUtils.preInit();
}

///单独运行module
class SeparateMyApp extends StatelessWidget {
  SeparateMyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return ScreenUtilInit(
      //designSize: Size(750, 1334),
      designSize: Size(375, 667), //设计稿中设备的尺寸(单位随意,建议dp,但在使用过程中必须保持一致)
      minTextAdapt: true, //是否根据宽度/高度中的最小值适配文字
      splitScreenMode: true, //支持分屏尺寸
      builder: (BuildContext? context, Widget? child) {
        return MaterialApp(
          debugShowCheckedModeBanner: false,
          theme: ThemeData(
            primarySwatch: Colors.blue,
          ),
          home: SplashPage(),
          // 初始化路由
          routes: <String, WidgetBuilder>{
            '/index': (BuildContext context) => OsChinaBody()
          },
        );
      },
    );
  }
}

///FlutterBoost 混合开发
class FlutterBoostMyApp extends StatefulWidget {

  @override
  State<FlutterBoostMyApp> createState() => _FlutterBoostMyAppState();
}

class _FlutterBoostMyAppState extends State<FlutterBoostMyApp> {
  static Map<String, FlutterBoostRouteFactory> routerMap = {
    '/': (settings, uniqueId) {
      return PageRouteBuilder(
        settings: settings,
        pageBuilder: (_, __, ___) {
          return Material(
            child: Center(
              child: Text("默认主页面"),
            ),
          );
        },
      );
    },
    'splash': (settings, uniqueId) {
      return PageRouteBuilder(
        settings: settings,
        pageBuilder: (_, __, ___) {
          return SplashPage();
        },
      );
    },
    'index': (settings, uniqueId) {
      return PageRouteBuilder(
        settings: settings,
        pageBuilder: (_, __, ___) {
          return OsChinaBody();
        },
      );
    },
  };

  Route<dynamic>? routeFactory(RouteSettings settings, String? uniqueId) {
    FlutterBoostRouteFactory? func = routerMap[settings.name];
    if (func == null) {
      return null;
    }
    return func(settings, uniqueId);
  }

  /// android调用flutter方法,来自android的参数
  /// 注意：flutter 不能给返回值
  void _initNativeFlutterChannel() {
    //定义一个channel
    MethodChannelUtils.nativeChannel.setMethodCallHandler((call) {
      switch (call.method) {
      //方法名
        case "android.invoke/flutter":
        //接收android调用flutter方法的参数
          String msg = call.arguments['msg'];
          print("zm1234 main 原生调用Flutter成功，参数是：$msg");
          break;
        case "onBackPressed":
          BuildContext? c = YsGlobal.navigatorKey.currentState?.context;
          var canPop = Navigator.of(c!).canPop();
          print("zm1234 main 原生调用onBackPressed成功 ${canPop}");
          if (canPop) {
            Navigator.of(c).maybePop();
            return Future<dynamic>.value(true);
          } else {
            return Future<dynamic>.value(false);
          }
      }
      return Future<dynamic>.value();
    });
  }

  late BuildContext mContext;

  @override
  void initState() {
    super.initState();
    _initNativeFlutterChannel();
  }

  @override
  Widget build(BuildContext context) {
    return ScreenUtilInit(
      designSize: Size(375, 667), //设计稿中设备的尺寸(单位随意,建议dp,但在使用过程中必须保持一致)
      minTextAdapt: true, //是否根据宽度/高度中的最小值适配文字
      splitScreenMode: true, //支持分屏尺寸
      builder: (BuildContext? context, Widget? child) {
        print("zm1234 main build:${child}");
        return MaterialApp(
          navigatorKey: YsGlobal.navigatorKey, //加上此配置
          debugShowCheckedModeBanner: false,
          theme: ThemeData(primarySwatch: Colors.blue),
          //区分的原因：iOS showModalBottomSheet 不能全屏 滚动列表底部会出让安全区域
          home: Platform.isIOS ? child! : SafeArea(child: child!),
        );
      },
      child: FlutterBoostApp(routeFactory),
    );
  }
}
