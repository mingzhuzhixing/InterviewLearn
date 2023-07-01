class CardButton {
  String? type;

  String? label;

  String? icon;

  String? style;

  String? scheme;

  String? content;

  String? sub_label;

  String? sub_icon;

  Map<String, dynamic>? params;

  ActionLog? action_log;

  CardButton();

  CardButton.fromJson(dynamic json) {
    type = json['type'];
    label = json['label'];
    icon = json['icon'];
    style = json['style'];
    scheme = json['scheme'];
    content = json['content'];
    sub_label = json['sub_label'];
    sub_icon = json['sub_icon'];
    params = json['params'];
    if (json['action_log'] != null) {
      action_log = ActionLog.fromJson(json['action_log']);
    }
  }
}

class ActionLog {
  String? et; //埋点key
  String? ec; //
  String? ed; //埋点额外参数。json格式
  String? cp; //曝光日志的ContentPiece
  String? edu; //umeng埋点额外参数
  String? sdet; //事件名称
  String? sded; //事件属性

  @override
  String toString() {
    return "ActionLog{"
        "et='$et"
        '\''
        ", ec='$ec"
        '\''
        ", ed='$ed"
        '\''
        ", cp='$cp"
        '\''
        ", edu='$edu"
        '\''
        ", sdet='$sdet"
        '\''
        ", sded='$sded"
        '\''
        '}';
  }

  ActionLog();

  ActionLog.fromJson(dynamic json) {
    et = json['et'];
    ec = json['ec'];
    ed = json['ed'];
    cp = json['cp'];
    edu = json['edu'];
    sdet = json['sdet'];
    sded = json['sded'];
  }
}

