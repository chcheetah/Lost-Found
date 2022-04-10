
import 'package:flutter/material.dart';
import 'dart:async';
import 'package:flutter/services.dart';
const platform = MethodChannel('samples.flutter.dev/battery');
List<String> _stringvalues = [];
class Foundlist extends StatefulWidget {
  @override
  foundlist createState() => foundlist();
}
Future <void> _getj() async{
  var o = [];
  try{
    var result = await platform.invokeMethod('getf');
    o = result.split("^#");
    print(o);
    for(int i = 0; i<o.length;i++){
      _stringvalues.add(o[i]);
    }
  }on PlatformException catch(e){
  }

}
class foundlist extends State<Foundlist> {
  @override
  Widget build(BuildContext co) {
    rebuildAllChildren(co);
    _getj();
    return new Material(
      child: SingleChildScrollView(
        child: Column(
            children:[
              ListView.builder(
                  primary: false,
                  shrinkWrap: true,
                  itemCount:_stringvalues.length,
                  itemBuilder: (context, index) {
                   try{ var Tex = TextStyle(color: Colors.white,fontSize: 20);
                    var p = _stringvalues[index];
                    var lp = [];
                    lp = p.split('\t');
                    return Card(
                        color: Colors.blueGrey,
                        child: Column(
                          children: [
                            Text("Item Name : "+lp[0]+"\n", style:Tex ),
                            Text("Found at : "+lp[2]+"\n", style:Tex ),
                            Text("Found by : "+lp[3]+"\n", style:Tex ,),
                            Text("Found on : "+lp[1]+"\n", style:Tex ,),
                            Text("Contact Details : "+lp[4]+"\n", style:Tex ,)
                          ],
                        )
                    );}
                    catch(e){
                     return Text("");
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