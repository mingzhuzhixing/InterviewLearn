import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_module/page/change_intro_page.dart';
import 'package:flutter_module/page/login_page/login_page.dart';
import 'package:flutter_module/utils/event_bus_utils.dart';

import '../../common/resources.dart';
import '../../utils/net_utils.dart';
import '../../utils/shared_preference_utils.dart';
import '../../widget/override_pending.dart';

class ProfilePage extends StatefulWidget {
  const ProfilePage({Key? key}) : super(key: key);

  @override
  State<ProfilePage> createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  late List listTitle;
  late List listIcon;
  var userName;
  var userAvatar;

  @override
  void initState() {
    super.initState();
    _showUerInfo();
    listTitle = ["我的消息", "阅读记录", "我的博客", "我的问答", "我的活动", "我的团队", "邀请好友", "个人简介"];
    listIcon = [
      Icons.message,
      Icons.menu_book,
      Icons.book_rounded,
      Icons.question_answer_outlined,
      Icons.local_activity,
      Icons.group,
      Icons.share,
      Icons.format_paint_rounded,
    ];

    eventBus.on<LoginEvent>().listen((event) {
      _getUserInfo();
    });

    eventBus.on<LogoutEvent>().listen((event) {
      _showUerInfo();
    });
  }

  @override
  void dispose() {
    super.dispose();
    eventBus.destroy();
  }

  @override
  Widget build(BuildContext context) {
    return ListView.separated(
      itemBuilder: (context, index) {
        if (index == 0) {
          return _buildHeader();
        }
        index -= 1;
        return ListTile(
          title: Text(listTitle[index]),
          leading: Icon(listIcon[index]),
          trailing: const Icon(Icons.arrow_forward_ios),
          onTap: (){
            if (index == 7) {
              //个人简介
              Navigator.push(context, MaterialPageRoute(builder: (context) {
                return const ChangeIntroPage();
              }));
            }
          },
        );
      },
      separatorBuilder: (context, index) {
        // if (index == 0 || index ==1) {
        //   return const Divider(thickness: 0.0);
        // }
        return const Divider(thickness: 0.0);
      },
      itemCount: listTitle.length + 1,
    );
  }

  ///头部信息
  Widget _buildHeader() {
    return Container(
      padding: const EdgeInsets.all(0.0),
      height: 150,
      color: const Color(0xFF24CF5F),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          GestureDetector(
            child: userAvatar != null
                ? Container(
                    width: 60,
                    height: 60,
                    decoration: BoxDecoration(
                      shape: BoxShape.rectangle,
                      borderRadius: const BorderRadius.all(Radius.circular(50.0)),
                      image: DecorationImage(
                        fit: BoxFit.cover,
                        image: NetworkImage(userAvatar),
                      ),
                    ),
                  )
                : SizedBox(
                    width: 60,
                    height: 60,
                    child: Image.asset("assets/images/ic_avatar_default.png"),
                  ),
            onTap: () {
              _goToLogin();
            },
          ),
          const SizedBox(
            height: 10.0,
          ),
          Text(
            (userName == null) ? "点击头像登录" : userName,
            style: const TextStyle(color: Colors.white),
          )
        ],
      ),
    );
  }

  ///登录
  void _goToLogin() async {
    var result = Navigator.of(context).push(SlidePageRouteBuilder(const LoginPage()));
    if (result != null && result == "refresh") {
      eventBus.fire(LoginEvent());
    }
  }

  void _getUserInfo() async {
    SharePreferenceUtils.getToken().then((value) {
      Map<String, dynamic> params = Map();
      params['access_token'] = value;
      params['dataType'] = "json";
      NetUtils.get(NetData.OPENAPI_USER, params).then((value) {
        print('value=$value');
        Map<String, dynamic> map = json.decode(value);
        if (mounted) {
          setState(() {
            userAvatar = map['avatar'];
            userName = map['name'];
          });
        }
        SharePreferenceUtils.saveUserInfo(map);
      });
    });
  }

  _showUerInfo() {
    SharePreferenceUtils.getUserInfo().then((user) {
      if (mounted) {
        setState(() {
          if (user != null) {
            userAvatar = user.avatar;
            userName = user.name;
          } else {
            userAvatar = null;
            userName = null;
          }
        });
      }
    });
  }
}
