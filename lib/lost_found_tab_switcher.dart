import 'package:flutter/material.dart';
import 'package:lostfound/foundlist.dart';
import 'package:lostfound/list.dart';

class L_f extends StatefulWidget{
  @override
  l_f createState() => l_f();
}

class l_f extends State<L_f>{
  List<String> tabs = ['Lost Items','Found Items'];
  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: tabs.length,
      child: NestedScrollView(
        headerSliverBuilder: (BuildContext context, bool innerBoxIsScrolled){
          return <Widget> [
            SliverOverlapAbsorber(handle: NestedScrollView.sliverOverlapAbsorberHandleFor(context),
            sliver:
            SliverAppBar(
              backgroundColor: Colors.black,
              foregroundColor: Colors.white,
              pinned: true,
              forceElevated: innerBoxIsScrolled,
              title: TabBar(tabs: tabs.map((tab) => Tab(text: tab)).toList(),),
            )
          ),
          ];
        },
        body: TabBarView(
          children: [
            _wrapTabWidget(tabWidget2( new Lest())),
            _wrapTabWidget(tabWidget2(new Foundlist()))
        ],
      ),
      ),
    );
  }
    Widget tabWidget2(Widget widget){
      return SliverToBoxAdapter(
        child: widget,
      );
    }
    _wrapTabWidget(tabWidget){
      return Builder(
        builder: (BuildContext context){
          return CustomScrollView(
            slivers: <Widget>[
              SliverOverlapInjector(handle: NestedScrollView.sliverOverlapAbsorberHandleFor(context)),
              tabWidget
            ],
          );
        },
      );
    }
}