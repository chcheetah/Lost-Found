import 'package:flutter/material.dart';
import 'package:lostfound/insert.dart';

import 'found.dart';
import 'list.dart';
import 'lost_found_tab_switcher.dart';

int _currentindex = 0;
class Output extends StatefulWidget {
  @override
  output createState() => output();
}
class output extends State<Output>{
  List ewchild = [
    new L_f(),
    new Insertion(),
    new Found(),

  ];
  @override
  // TODO: implement widget
  Widget build(BuildContext op){
    return Scaffold(
        appBar: AppBar(
          leading: IconButton(
            icon: Icon(Icons.info_outline_rounded,size: 30,color: Colors.white),
            onPressed: (){
              Navigator.pushNamed(op,'about');
            },
          ),
          title: Text(
              "Lost & Found"
          ),
          centerTitle: true,
          backgroundColor: Colors.black,
          foregroundColor: Colors.white,
          titleTextStyle:  TextStyle(
              color: Colors.white,
            fontSize: 40
          ) ,
        ),
        body: ewchild[_currentindex],
        bottomNavigationBar: BottomNavigationBar(
          backgroundColor: Colors.white,
            onTap: butpress,
            currentIndex: _currentindex,
            items:  [
              BottomNavigationBarItem(
                  icon: Icon(Icons.search_sharp),
                  label:  "List Items",

              ),
              BottomNavigationBarItem(
                  icon: Icon(Icons.add_circle_outline_sharp),
                  label: "Lost an Item?"

              ),
              BottomNavigationBarItem(
                  icon: Icon(Icons.check_circle_outline_sharp),
                  label: "Found an Item?"
              )
            ])
    );
  }
  void butpress(int index){
    setState((){
      _currentindex = index;
    });
  }
}