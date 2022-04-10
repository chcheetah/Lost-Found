import 'package:flutter/material.dart';
import 'dart:async';
import 'package:flutter/services.dart';
class Insertion extends StatefulWidget {
  const Insertion({Key? key}) : super(key: key);

  @override
  insertion createState() => insertion();
}

class insertion extends State<Insertion> {
  TextEditingController ide = TextEditingController();
  TextEditingController name = TextEditingController();
  TextEditingController count = TextEditingController();
  TextEditingController found = TextEditingController();
  TextEditingController date = TextEditingController();
  TextEditingController category = TextEditingController();
  TextEditingController ownernm = TextEditingController();
  TextEditingController contact = TextEditingController();
  static const platform = MethodChannel('samples.flutter.dev/battery');

  Future<void> _insert(a,b,c,d,e,f,g,h) async {
    try {
      var result = await platform.invokeMethod('insert'+a+'\t'+b+'\t'+c+'\t'+d+'\t'+e+'\t'+f+'\t'+g+'\t'+h);
      //if(result == 1){
    //    Navigator.pushNamed(context,'success');
     // }
    } on PlatformException catch (e) {
    }
  }
  @override
  Widget build(BuildContext context) {
    return Material(
      child: Center(
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              ListView(
                primary: false,
                scrollDirection: Axis.vertical,
                shrinkWrap: true,
                children: [
                  Container(child: Text("\n"),),

                  Text("Have you lost something? \n  Fear not, fill out this form.",style: TextStyle(
                    fontSize: 20,
                  ), textAlign: TextAlign.center,
                  ),
                  Container(child: Text("\n"),),
                  TextField(controller: ide,maxLength: 4,decoration: InputDecoration(border: OutlineInputBorder(),hintText: 'Item ID < Starting from 0010 onwards >'),keyboardType: TextInputType.number,),
                  TextField(controller: name, maxLength: 50,decoration: InputDecoration(border: OutlineInputBorder(),hintText: 'Item Name?')),
                  TextField(controller: count, maxLength: 3,decoration: InputDecoration(border: OutlineInputBorder(),hintText: 'Number of Item(s) lost?'),keyboardType: TextInputType.number,),
                  TextField(controller: found, maxLength: 50,decoration: InputDecoration(border: OutlineInputBorder(),hintText: 'Last Location of Item?')),
                  TextField(controller: date, maxLength: 10,decoration: InputDecoration(border: OutlineInputBorder(),hintText: 'Last Date seen? (YYYY-MM-DD)')),
                  TextField(controller: category, maxLength: 50,decoration: InputDecoration(border: OutlineInputBorder(),hintText: 'Category?')),
                  TextField(controller: ownernm, maxLength: 100,decoration: InputDecoration(border: OutlineInputBorder(),hintText: 'Name of Owner?'),keyboardType: TextInputType.name,),
                  TextField(controller: contact, maxLength: 100,decoration: InputDecoration(border: OutlineInputBorder(),hintText: 'Owner Contact Details?'))

                ],),
              ElevatedButton(
                child: const Text('Add Lost Item'),
                onPressed: () => _insert(ide.text,name.text,count.text,found.text,date.text,category.text,ownernm.text,contact.text),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
