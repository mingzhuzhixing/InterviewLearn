import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/utils/shared_preference_utils.dart';
import 'package:flutter_module/widget/common_app_bar.dart';
import 'package:flutter_module/widget/loading_dialog.dart';
import 'package:fluttertoast/fluttertoast.dart';

/// 修改个人签名
class ChangeIntroPage extends StatefulWidget {
  const ChangeIntroPage({Key? key}) : super(key: key);

  @override
  State<ChangeIntroPage> createState() => _ChangeIntroPageState();
}

class _ChangeIntroPageState extends State<ChangeIntroPage> {
  bool _hasText = false;
  final int counter = 60;
  final TextEditingController _controller = TextEditingController();

  @override
  void initState() {
    super.initState();
    SharePreferenceUtils.getUserInfo().then((value){
      String name = value.name ??= "";
      _controller.text = name;
      setState(() {
        _hasText = name.isNotEmpty;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CommonAppBar(
        context,
        "个人签名",
        backgroundColor: Colors.white,
        elevation: 0.3,
        actions: [
          Padding(
              padding: const EdgeInsets.only(right: 10),
              child: Center(
                child: GestureDetector(
                  onTap: () {
                    saveIntroData();
                  },
                  child: Text(
                    "保存",
                    textScaleFactor: 1.0,
                    style: TextStyle(
                        color: _hasText ? const Color(0xffc82507) : const Color(0xffb2b2b2),
                        fontSize: 15),
                  ),
                ),
              ))
        ],
      ),
      backgroundColor: const Color(0xfff7f7f7),
      body: Column(
        children: [
          const SizedBox(
            height: 10,
          ),
          Stack(
            children: [
              Container(
                height: 150,
                color: Colors.white,
                padding: const EdgeInsets.only(left: 14, right: 14, bottom: 20),
                child: TextField(
                  onChanged: (value) => {
                    setState(() {
                      _hasText = value.isNotEmpty;
                    })
                  },
                  controller: _controller,
                  decoration: const InputDecoration(
                      hintText: "", counterText: "", border: InputBorder.none),
                  maxLength: counter,
                  maxLines: null,
                  textAlign: TextAlign.left,
                  style: const TextStyle(fontSize: 16.0),
                ),
              ),
              Align(
                alignment: Alignment.bottomRight,
                child: Container(
                  margin: const EdgeInsets.only(top: 125, right: 10),
                  child: Text(
                    "${counter - _controller.text.length}",
                    style: TextStyle(fontSize: 13, color: const Color(0xff989898)),
                  ),
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }

  ///保存个人签名数据
  void saveIntroData() {
    if (!_hasText) {
      return;
    }
    if (_controller.text.isEmpty) {
      Fluttertoast.showToast(msg: "内容不能为空", gravity: ToastGravity.CENTER);
      return;
    }
    if (_controller.text.length > 60) {
      Fluttertoast.showToast(msg: "内容限制60字", gravity: ToastGravity.CENTER);
      return;
    }
    LoadingDialog.showLoading(context);
    FocusScope.of(context).unfocus();
    Fluttertoast.showToast(msg: "开始请求接口", gravity: ToastGravity.CENTER);
    // RequestUtil.post("userinfo/save", {
    //   'intro': _controller.text,
    // }, (result) {
    //   Fluttertoast.showToast(msg: "修改成功", gravity: ToastGravity.CENTER);
    //   UserInfoUtil.updateUserInfoData("user_info", result);
    //   LoadingDialog.hideLoading(context);
    //   NavigatorUtil.pop();
    // }, onFailCallBack: (result) {
    //   LoadingDialog.hideLoading(context);
    // });
  }
}
