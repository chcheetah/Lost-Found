
import 'package:flutter/material.dart';
import 'dart:async';
import 'package:flutter/services.dart';
const platform = MethodChannel('samples.flutter.dev/battery');
List<String> _stringvalues = [];
class Lest extends StatefulWidget {
  @override
  lest createState() => lest();
}
Future <void> _get() async{
  var o = [];
  try{
    var result = await platform.invokeMethod('get');
    o = result.split("^#");
    print(o);
    for(int i = 0; i<o.length;i++){
      _stringvalues.add(o[i]);
    }
  }on PlatformException catch(e){
  }
  print("Successful 5");

}
class lest extends State<Lest> {
  @override
  Widget build(BuildContext co) {
    rebuildAllChildren(co);
    _get();
    return new Material(
      child: SingleChildScrollView(
          child: Column(
            children:[
              ListView.builder(
                primary: false,
                  shrinkWrap: true,
                  itemCount:_stringvalues.length,
                  itemBuilder: (context, index) {
                   try {
                     var Tex = TextStyle(color: Colors.white, fontSize: 20);
                     var p = _stringvalues[index];
                     var lp = [];
                     lp = p.split('\t');
                     return Card(
                         color: Colors.blueGrey,
                         child: Column(
                           children: [
                             Text("Item : " + lp[1] + "\n", style: Tex),
                             Text("Quantity : " + lp[2] + "\n", style: Tex),
                             Text("Owner : " + lp[6] + "\n", style: Tex,),
                             Text(
                               "Last Seen At : " + lp[3] + "\n", style: Tex,),
                             Text("Date : " + lp[4] + "\n", style: Tex,),
                             Text(
                               "Contact Details : " + lp[7] + "\n", style: Tex,)
                           ],
                         )
                     );
                   }
                     catch(e){
                      return  Text("");
                     };
                  }
              ),
            ]
        ),
      ),
    );
  }
}
void rebuildAllChildren(BuildContext context) {
  void rebuild(Element el) {
    el.markNeedsBuild();
    el.visitChildren(rebuild);
  }
  (context as Element).visitChildren(rebuild);
}