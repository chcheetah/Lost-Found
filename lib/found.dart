import 'package:flutter/material.dart';
import 'dart:async';
import 'package:flutter/services.dart';

class Found extends StatefulWidget {
  @override
  found createState() => found();
}

class found extends State<Found> {
  static const platform = MethodChannel('samples.flutter.dev/battery');
  TextEditingController item_id = new TextEditingController();
  TextEditingController Owner_nm = new TextEditingController();
  Future <void> _delete(a,b) async{
    var o = [];
    try{
      var result = await platform.invokeMethod('delete'+a+'\t'+b);
      if(result == 0){
        print('yay');
      }
      else{
        print("nope");
        throw Exception(["error"]);
      }
    }on Exception catch(e){
    }

  }
  @override

  Widget build(BuildContext context) {
    return Material(
      child: SingleChildScrollView(
        child: Column(
            children:[
              Container(child: Text("\n"),),
              Text("Have you found a missing item? If you have, could you please type in  the following details about the item?", style: TextStyle(fontSize: 20),),
              Container(child: Text("\n"),),
              ListView(
                primary: false,
                scrollDirection: Axis.vertical,
                shrinkWrap: true,
                children: [
                  TextField(controller: item_id,maxLength: 4,decoration: InputDecoration(border: OutlineInputBorder(),hintText: 'Item ID'),keyboardType: TextInputType.number,),
                  TextField(controller: Owner_nm,maxLength: 100,decoration: InputDecoration(border: OutlineInputBorder(),hintText: 'Owner Name'),keyboardType:TextInputType.name,),
                ],
              ),
              ElevatedButton(
                child: const Text('Submit Found Item Details'),
                onPressed: () => _delete(item_id.text, Owner_nm.text)
              ),
            ]

        ),
      ),
    );
  }
}