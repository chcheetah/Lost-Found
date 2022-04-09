import 'package:flutter/material.dart';
import 'main.dart';
import 'output.dart';
class about extends StatefulWidget {
  @override
  About createState() => About();
}
class About extends State<about>{
  @override
  Widget build(BuildContext op){
    return Scaffold(
      backgroundColor: Colors.black,
        appBar: AppBar(
          leading: IconButton(
            icon: Icon(Icons.keyboard_backspace_sharp,color: Colors.white,size: 50,), onPressed: () { Navigator.pushNamed(context, 'oput'); },
          ),
          title: const Text(
              "Lost & Found"
          ),
          centerTitle: true,

          backgroundColor: Colors.black26,
          titleTextStyle: const TextStyle(
              color: Colors.white, fontSize: 40) ,
        ),
        body: Text("Made by \nHarshiv Chandra ( @chcheetah )\n Rishikesh K R ( @Rish-ProProgrammer )\n \n This app has been created to solve \n problem statement no. 5 of the \n app-a-thon contest organised by \n BITS Pilani. \n\n\n Code Base in Flutter + Java + SQL",style: TextStyle(fontSize: 20, color: Colors.white),)
    );
  }
}