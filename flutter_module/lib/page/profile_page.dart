import 'package:flutter/material.dart';
import 'package:flutter_module/page/login_page.dart';

import '../widget/override_pending.dart';

class ProfilePage extends StatefulWidget {
  const ProfilePage({Key? key}) : super(key: key);

  @override
  State<ProfilePage> createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  late List listTitle;
  late List listIcon;

  @override
  void initState() {
    super.initState();
    listTitle = ["我的消息", "阅读记录", "我的博客", "我的问答", "我的活动", "我的团队", "邀请好友"];
    listIcon = [
      Icons.message,
      Icons.menu_book,
      Icons.book_rounded,
      Icons.question_answer_outlined,
      Icons.local_activity,
      Icons.group,
      Icons.share,
    ];
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
      padding: EdgeInsets.all(0.0),
      height: 150,
      color: const Color(0xFF24CF5F),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          GestureDetector(
            child: Container(
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
          const Text(
            "点击头像登录",
            style: TextStyle(color: Colors.white),
          )
        ],
      ),
    );
  }

  ///登录
  void _goToLogin() async{
    Navigator.of(context).push(SlidePageRouteBuilder(const LoginPage()));
  }
}
