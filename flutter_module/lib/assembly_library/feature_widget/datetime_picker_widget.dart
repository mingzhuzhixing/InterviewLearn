import 'package:date_format/date_format.dart';
import 'package:flutter/material.dart';
import 'package:flutter_cupertino_date_picker_fork/flutter_cupertino_date_picker_fork.dart';

/*
 * 日期时间选择
 */
class DateTimePickerBottomSheetPage extends StatefulWidget {
  const DateTimePickerBottomSheetPage({Key? key}) : super(key: key);

  @override
  _DateTimePickerBottomSheetPageState createState() => _DateTimePickerBottomSheetPageState();
}

const String MIN_DATETIME = '2019-05-15 09:23:10';
const String MAX_DATETIME = '2099-06-03 21:11:00';
const String INIT_DATETIME = '2019-05-16 09:00:00';

const String MIN_DATE = '2010-05-12';
const String MAX_DATE = '2099-11-25';
const String INIT_DATE = '2019-05-17';

const String MIN_TIME = '2010-05-12 10:47:00';
const String MAX_TIME = '2021-11-25 22:45:10';
const String INIT_TIME = '2019-05-17 18:13:15';

class _DateTimePickerBottomSheetPageState extends State<DateTimePickerBottomSheetPage> {
  final bool _showTitle = true;

  //日期时间选择器初始值
  final String _format = 'yy年M月d日 EEE,H时:m分:s秒';
  final DateTimePickerLocale _locale = DateTimePickerLocale.zh_cn;
  DateTime _dateTime = DateTime.now();

  //日期选择器初始值
  DateTime _date = DateTime.now();
  final String _format_date = 'yyyy-MMMM-dd';

  //时间选择器初始值
  final String _format_time = 'H时.m分.s秒';
  DateTime _time = DateTime.now();

  //弹框选择日期时间
  DateTime _nowDate1 = DateTime.now();
  DateTime _nowDate2 = DateTime.now();
  TimeOfDay _timeOfDay = const TimeOfDay(hour: 12, minute: 20);

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("日期时间选择"),
      ),
      body: Column(
        children: <Widget>[
          const SizedBox(height: 20),
          InkWell(
            child: Row(
              children: [
                Text(formatDate(_nowDate1, [yyyy, '-', mm, '-', dd, ''])),
                const Icon(Icons.arrow_drop_down)
              ],
            ),
            onTap: () {
              _showDatePickerDialog();
            },
          ),
          const SizedBox(height: 20),
          Row(
            children: <Widget>[
              InkWell(
                child: Row(
                  children: <Widget>[
                    Text(formatDate(_nowDate2, [yyyy, '年', mm, '月', dd, '日'])),
                    const Icon(Icons.arrow_drop_down)
                  ],
                ),
                onTap: () {
                  _showDatePicker2Dialog();
                },
              ),
              InkWell(
                child: Row(
                  children: <Widget>[
                    Text(_timeOfDay.format(context)),
                    const Icon(Icons.arrow_drop_down)
                  ],
                ),
                onTap: () {
                  _showTimePickerDialog();
                },
              )
            ],
          ),
          const SizedBox(height: 20),
          InkWell(
            child: Row(
              children: <Widget>[
                const Text("时间选择器:"),
                Text(
                    "${_dateTime.year}-${_dateTime.month.toString().padLeft(2, '0')}-${_dateTime.day.toString().padLeft(2, '0')} ${_dateTime.hour.toString().padLeft(2, '0')}:${_dateTime.minute.toString().padLeft(2, '0')}:${_dateTime.second.toString().padLeft(2, '0')}"),
                const Icon(Icons.arrow_drop_down)
              ],
            ),
            onTap: () {
              _showDateTimePicker();
            },
          ),
          const SizedBox(height: 20),
          InkWell(
            child: Row(
              children: <Widget>[
                const Text("日期选择器:"),
                Text(formatDate(_date, ['yyyy', '年', 'mm', '月', 'dd', '日'])),
                const Icon(Icons.arrow_drop_down)
              ],
            ),
            onTap: () {
              _showDatePicker();
            },
          ),
          const SizedBox(height: 20),
          InkWell(
            child: Row(
              children: <Widget>[
                const Text("时间选择器:"),
                Text(
                    "${_time.hour.toString().padLeft(2, '0')}:${_time.minute.toString().padLeft(2, '0')}:${_time.second.toString().padLeft(2, '0')}"),
                const Icon(Icons.arrow_drop_down)
              ],
            ),
            onTap: () {
              _showTimePicker();
            },
          )
        ],
      ),
    );
  }

  /// 第一种获取时间值
  void _showDatePickerDialog() {
    showDatePicker(
            context: context,
            initialDate: _nowDate1,
            firstDate: DateTime(1980),
            lastDate: DateTime(2120))
        .then((result) {
      print("第一种获取日期:$result");
      setState(() {
        _nowDate1 = result!;
      });
    });
  }

  /// 第二种获取日期值
  void _showDatePicker2Dialog() async {
    var result = await showDatePicker(
        context: context,
        initialDate: _nowDate2,
        firstDate: DateTime(1980),
        lastDate: DateTime(2120));
    print("第二种获取日期:$result");
    setState(() {
      _nowDate2 = result!;
    });
  }

  /// 获取时间值
  void _showTimePickerDialog() async {
    var result = await showTimePicker(context: context, initialTime: _timeOfDay);
    print("获取时间:$result");
    setState(() {
      _timeOfDay = result!;
    });
  }

  /// Display datetime picker.(日期时间选择器)
  void _showDateTimePicker() {
    DatePicker.showDatePicker(
      context,
      minDateTime: DateTime.parse(MIN_DATETIME),
      maxDateTime: DateTime.parse(MAX_DATETIME),
      initialDateTime: _dateTime /*DateTime.parse(INIT_DATETIME)*/,
      dateFormat: _format,
      locale: _locale,
      pickerTheme: DateTimePickerTheme(
          showTitle: _showTitle,
          confirm: const Text('确定', style: TextStyle(color: Colors.red)),
          cancel: const Text('取消', style: TextStyle(color: Colors.cyan))),
      pickerMode: DateTimePickerMode.datetime,
      // show TimePicker
      onCancel: () {
        debugPrint('onCancel');
      },
      //   onChange: (dateTime, List<int> index) {
      //     setState(() {
      //       _dateTime = dateTime;
      //     });
      //   },
      onConfirm: (dateTime, List<int> index) {
        setState(() {
          _dateTime = dateTime;
        });
      },
    );
  }

  /// Display date picker.（日期选择器）
  void _showDatePicker() {
    DatePicker.showDatePicker(
      context,
      onMonthChangeStartWithFirstDate: true,
      pickerTheme: DateTimePickerTheme(
        showTitle: _showTitle,
        confirm: const Text('确定', style: TextStyle(color: Colors.red)),
        cancel: const Text('取消', style: TextStyle(color: Colors.cyan)),
      ),
      minDateTime: DateTime.parse(MIN_DATE),
      maxDateTime: DateTime.parse(MAX_DATE),
      initialDateTime: _date,
      dateFormat: _format_date,
      locale: _locale,
      onClose: () => print("----- onClose -----"),
      onCancel: () => print('onCancel'),
      //   onChange: (dateTime, List<int> index) {
      //     setState(() {
      //       _dateTime = dateTime;
      //     });
      //   },
      onConfirm: (dateTime, List<int> index) {
        setState(() {
          _date = dateTime;
        });
      },
    );
  }

  /// Display time picker.(时间选择器)
  void _showTimePicker() {
    DatePicker.showDatePicker(
      context,
      minDateTime: DateTime.parse(MIN_TIME),
      maxDateTime: DateTime.parse(MAX_TIME),
      initialDateTime: DateTime.parse(INIT_TIME),
      dateFormat: _format_time,
      locale: _locale,
      pickerMode: DateTimePickerMode.time,
      // show TimePicker
      pickerTheme: const DateTimePickerTheme(
          // title: Container(
          //   decoration: BoxDecoration(color: Color(0xFFEFEFEF)),
          //   width: double.infinity,
          //   height: 56.0,
          //   alignment: Alignment.center,
          //   child: Text(
          //     '选择时间',
          //     style: TextStyle(color: Colors.green, fontSize: 24.0),
          //   ),
          // ),
          titleHeight: 56.0,
          showTitle: true,
          confirm: Text('确定', style: TextStyle(color: Colors.red)),
          cancel: Text('取消', style: TextStyle(color: Colors.cyan))),
      onCancel: () {
        debugPrint('onCancel');
      },
      //   onChange: (dateTime, List<int> index) {
      //     setState(() {
      //       _dateTime = dateTime;
      //     });
      //   },
      onConfirm: (dateTime, List<int> index) {
        setState(() {
          _time = dateTime;
        });
      },
    );
  }
}
