import 'package:flutter/material.dart';

class CustomScrollViewPage extends StatefulWidget {
  const CustomScrollViewPage({Key? key}) : super(key: key);

  @override
  State<CustomScrollViewPage> createState() => _CustomScrollViewPageState();
}

class _CustomScrollViewPageState extends State<CustomScrollViewPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: CustomScrollView(
        slivers: [
          // AppBar，包含一个导航栏
          SliverAppBar(
            pinned: true,
            expandedHeight: 200.0,
            flexibleSpace: FlexibleSpaceBar(
              title: Text('CustomScrollView Demo'),
              background: Image(
                image: NetworkImage(
                    'https://tva1.sinaimg.cn/large/006y8mN6gy1g72j6nk1d4j30u00k0n0j.jpg'),
                fit: BoxFit.cover,
              ),
            ),
          ),
          SliverGrid(
            delegate: SliverChildBuilderDelegate(
              (BuildContext context, int index) {
                return Container(
                  alignment: Alignment.center,
                  color: Colors.teal[100 * (index % 9)],
                  child: Text('grid item $index'),
                );
              },
              childCount: 10,
            ),
            gridDelegate: SliverGridDelegateWithMaxCrossAxisExtent(
              maxCrossAxisExtent: 200.0,
              mainAxisSpacing: 10.0,
              crossAxisSpacing: 10.0,
              childAspectRatio: 4.0,
            ),
          ),
          SliverFixedExtentList(
            delegate: SliverChildBuilderDelegate(
              (BuildContext context, int index) {
                return Container(
                  alignment: Alignment.center,
                  color: Colors.lightBlue[100 * (index % 9)],
                  child: Text('list item $index'),
                );
              },
              childCount: 20,
            ),
            itemExtent: 50.0,
          ),
        ],
      ),
    );
  }
}
