void main() {
  var person = Person();
  print("1234"+person.getName);
}

///1、类的定义
///Dart 中使用 class 关键字来定义类：
///
class Person {
  late String name;

  set setName(String name) {
    this.name = name;
  }

  String get getName {
    return name;
  }
}
