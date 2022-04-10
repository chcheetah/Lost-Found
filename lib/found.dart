import 'package:flutter/material.dart';
import 'dart:async';
import 'package:flutter/services.dart';

class Found extends StatefulWidget {
  @override
  found createState() => found();
}

class found extends State<Found> {
  static const platform = MethodChannel('samples.flutter.dev/battery');
  TextEditingController item_name = new TextEditingController();
  TextEditingController found_at = new TextEditingController();

  TextEditingController found_on = new TextEditingController();
  TextEditingController found_nm= new TextEditingController();

  TextEditingController found_contact= new TextEditingController();

  Future <void> _delete(a,b,c,d,e) async{
    var o = [];
    try{
      var result = await platform.invokeMethod('found'+a+'\t'+b+'\t'+c+'\t'+d+'\t'+e);
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
              Text("Have you found a missing item? As we all know, finders are not always keepers :). Please enter the following details:", style: TextStyle(fontSize: 20),),
              Container(child: Text("\n"),),
              ListView(
                primary: false,
                scrollDirection: Axis.vertical,
                shrinkWrap: true,
                children: [
                  TextField(controller: item_name,maxLength: 50,decoration: InputDecoration(border: OutlineInputBorder(),hintText: 'Item Name'),keyboardType: TextInputType.name),
                  TextField(controller: found_on,maxLength: 10,decoration: InputDecoration(border: OutlineInputBorder(),hintText: 'Found on (YYYY-MM-DD)'),keyboardType:TextInputType.name,),
                  TextField(controller: found_at,maxLength: 50,decoration: InputDecoration(border: OutlineInputBorder(),hintText: 'Found At'),keyboardType: TextInputType.name),
                  TextField(controller: found_nm ,maxLength: 50,decoration: InputDecoration(border: OutlineInputBorder(),hintText: 'Who found it?'),keyboardType:TextInputType.name,),
                  TextField(controller: found_contact ,maxLength: 100,decoration: InputDecoration(border: OutlineInputBorder(),hintText: 'Finder(s) contact details'),keyboardType:TextInputType.name,),
                ],
              ),
              ElevatedButton(
                child: const Text('Submit Found Item Details'),
                onPressed: () => _delete(item_name.text, found_on.text,found_at.text,found_nm.text,found_contact.text)
              ),
            ]

        ),
      ),
    );
  }
}