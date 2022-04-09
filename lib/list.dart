import 'package:flutter/material.dart';
import 'dart:async';
import 'package:flutter/services.dart';

class Lest extends StatefulWidget {
  @override
  lest createState() => lest();
}

class lest extends State<Lest> {
  static const platform = MethodChannel('samples.flutter.dev/battery');
  var _stringvalues = [];
  Future <void> _get() async{
    var o = [];
    try{
      var result = await platform.invokeMethod('get');
      o = result.split("^#");
      _stringvalues = o;
    }on PlatformException catch(e){
    }

  }
  @override

  Widget build(BuildContext context) {
    _get();
    return Material(
      child: SingleChildScrollView(
          child: Container(
            child:
              ListView.builder(
                primary: false,
                  shrinkWrap: true,
                  itemCount: _stringvalues.length,
                  itemBuilder: (context, index){
                    var p = _stringvalues[index];
                    return Card(
                      color: Colors.black12,
                      child: Text(
                        p,style: TextStyle(
                        color: Colors.white
                      ),
                      ),
                    );
                  }

              )


        ),
      ),
    );
  }
}