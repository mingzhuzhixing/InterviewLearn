import 'package:flutter/material.dart';

class ConfinePage extends StatefulWidget {
  const ConfinePage({Key? key}) : super(key: key);

  @override
  State<ConfinePage> createState() => _ConfinePageState();
}

class _ConfinePageState extends State<ConfinePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("动态小黑屋"),
      ),
    );
  }
}
