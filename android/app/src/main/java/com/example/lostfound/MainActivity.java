package com.example.lostfound;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;

import androidx.annotation.NonNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;
class iteem {
    public String a,b,c,d,e,f,g,h;
    public iteem(String i, String j, String k, String l, String m, String n, String o, String p){
        a = i; b= j; c =k ; d = l; e = m; f = n; g = o; h = p;
    }
    public String a(){
        return a;
    }
    public String b(){
        return b;
    }
    public String c(){
        return c;
    }
    public String d(){
        return d;
    }
    public String e(){
        return e;
    }
    public String f(){
        return f;
    }
    public String g(){
        return g;
    }
    public String h(){
        return h;
    }
}
class remove extends AsyncTask<String,Void, Integer>{
    @Override
    protected Integer doInBackground(String ... strings) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            stmt.execute("delete from lost where item_id='"+strings+"'");
            return 0;
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
}
class insert extends AsyncTask<String, Void, Void> {
    @Override
    public Void doInBackground(String... strings) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(String.valueOf(strings));
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
class gete extends AsyncTask<Void,Void,String[]>{
    ArrayList<String> temp = new ArrayList<>();
    public String[] getu() {
        return temp.toArray(new String[0]);
    }
    @Override
    protected String[] doInBackground(Void... voids) {
        try{
            System.out.println("successful 1");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("successful 2");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app";
            System.out.println("successful 2.5");
            Connection con= DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            System.out.println("successful 3");
            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery("select * from lost");
            System.out.println("successful 4");
            while(r.next())
                temp.add(r.getString("item_id")+"\t"+r.getString("item_nm")+"\t"+r.getString("item_count")+"\t"+r.getString("last_found")+"\t"+r.getString("lost_at")+"\t"+r.getString("category")+"\t"+r.getString("owner_nm")+"\t"+r.getString("contact"));
        }
        catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            temp.add("error, unable to connect to server");
            System.out.println("error");
        }
        finally{
            String[] o = temp.toArray(new String[0]);
            return o;
        }
    }

}
public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "samples.flutter.dev/battery";
    private int getBatteryLevel(){
        int batteryLevel = -1;
        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
            batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        } else {
            Intent intent = new ContextWrapper(getApplicationContext()).
                    registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            batteryLevel = (intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) * 100) /
                    intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        }
        return batteryLevel;
    }
    public void search(String iname)
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("successful 1");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery("select * from lost where item_nm='"+iname+"'");
            while(r.next())
                System.out.println(r.getString("item_id")+" "+r.getString("item_nm")+" "+r.getString("item_count")+" "+r.getString("last_found")+" "+r.getString("lost_at")+" "+r.getString("category")+" "+r.getString("owner_nm")+" "+r.getString("contact"));
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public void update()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            stmt.execute("update lost set item_id='0004' where item_nm='aasasa'");
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    public void insert(String id,String nm,String c,String lf,String ld,String cat,String onm,String ph) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into lost values('"+id+"','"+nm+"',"+Integer.parseInt(c)+",'"+lf+"','"+ld+"','"+cat+"','"+onm+"','"+ph+"')");
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    public String get(String a) {
        String s="";
        try{
            System.out.println("successful 1");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("successful 2");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app?useSSL=true";
            System.out.println("successful 3");
            Connection con=DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            System.out.println("successful 3.5");
            Statement stmt = con.createStatement();

            ResultSet r = stmt.executeQuery("select * from lost");
            System.out.println("successful 4");
            while(r.next())
                s=r.getString(a);
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return s;
    }
    public String[] get() {
        ArrayList<String> temp = new ArrayList<>();
        try{
            System.out.println("successful 1");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("successful 2");
            String url="jdbc:mysql://appathonproject.mysql.database.azure.com:3306/app";
            System.out.println("successful 2.5");
            Connection con= DriverManager.getConnection(url, "appathon_admin", "bdzP8TDf4httvD!");
            System.out.println("successful 3");
            Statement stmt = con.createStatement();
            ResultSet r = stmt.executeQuery("select * from lost");
            System.out.println("successful 4");
            while(r.next())
                temp.add(r.getString("item_id")+"\t"+r.getString("item_nm")+"\t"+r.getString("item_count")+"\t"+r.getString("last_found")+"\t"+r.getString("lost_at")+"\t"+r.getString("category")+"\t"+r.getString("owner_nm")+"\t"+r.getString("contact"));
        }
        catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            temp.add("error, unable to connect to server");
            System.out.println("error");
        }
        finally{
        String[] o = temp.toArray(new String[0]);
        return o;
        }
    }

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler(
                        (call, result) -> {
                            // Note: this method is invoked on the main thread.

                            if(call.method.contains("insert")){
                                System.out.println(call.method.replace("insert",""));
                                StringTokenizer d = new StringTokenizer(call.method.replace("insert",""),"\t");
                                String[] x = new String[8];
                                int i = 0;
                                while (d.hasMoreTokens()){
                                    x[i] = d.nextToken();
                                    i++;
                                }
                                String rewq =  "insert into lost values('"+x[0]+"','"+x[1]+"',"+Integer.parseInt(x[2])+",'"+x[3]+"','"+x[4]+"','"+x[5]+"','"+x[6]+"','"+x[7]+"')";
                                new insert().execute(rewq);
                                result.success(1);
                            }
                            if(call.method.equals("get")){
                                String[] q = new gete().execute().getu();
                                String th = "";
                                for(String u: q){
                                    th+=u;
                                    th+="^#";
                                }


                                result.success(th);
                            }
                            if(call.method.contains("delete")){
                                remove eee = new remove();
                                System.out.println(call.method.replace("delete",""));
                                String f = call.method.replace("delete","");
                                 new remove().execute(f);
                                result.success(0);
                                }

                        }

                );

    }
}
